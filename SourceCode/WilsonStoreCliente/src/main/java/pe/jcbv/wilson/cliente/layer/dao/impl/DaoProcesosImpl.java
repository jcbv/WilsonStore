package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pe.jcbv.wilson.cliente.domain.Detalle;
import pe.jcbv.wilson.cliente.domain.Venta;
import pe.jcbv.wilson.cliente.layer.dao.espec.DaoProcesos;

public class DaoProcesosImpl implements DaoProcesos
{
	@Override
	public void grabarVenta( Venta venta )
	{
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			conexion.setAutoCommit( false );
			
			String sql = "SELECT con_item, con_longitud " +
						 "FROM contador " +
						 "WHERE con_tabla = UPPER('Venta') " +
						 "FOR UPDATE";
			
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			
			if( !rs.next() )
				throw new SQLException( "No existe el contador." );
			
			int cont = rs.getInt( "con_item" ) + 1;
			int ancho = rs.getInt( "con_longitud" );
			rs.close();
			
			String codigo = AccesoDB.generarCodigo( cont, ancho );
			venta.setVen_id( codigo );
			
			// ------------------------ //
			// ---> GRABA LA VENTA <--- //
			// ------------------------ //
			/*sql = "INSERT INTO venta ( ven_id, ven_fecha, ven_subtotal, " +
				  "ven_impuesto, ven_total, emp_id, cli_id ) " +
				  "VALUES ( ?,TO_CHAR(SYSDATE,'DD-MON-YYYY'),?,?,?,?,? )";*/
			
			sql = "INSERT INTO venta ( ven_id, ven_fecha, ven_subtotal, " +
				  "ven_impuesto, ven_total, emp_id, cli_id ) " +
				  "VALUES ( ?,SYSDATE,?,?,?,?,? )";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, codigo );
			pstm.setDouble( 2, venta.getVen_subtotal() );
			pstm.setDouble( 3, venta.getVen_impuesto() );
			pstm.setDouble( 4, venta.getVen_total() );
			pstm.setString( 5, venta.getEmp_id() );
			pstm.setString( 6, venta.getCli_id() );
			pstm.executeUpdate();
			
			// ---------------------------- //
			// ---> GRABA LOS DETALLES <--- //
			// ---------------------------- //
			sql = "INSERT INTO detalle ( ven_id, art_id, " +
				  "det_cant, det_precio ) " +
				  "VALUES ( ?,?,?,? )";
			
			String sql1 = "SELECT art_stock " +
						  "FROM articulo " +
						  "WHERE art_id = ? " +
						  "FOR UPDATE";
			
			String sql2 = "UPDATE articulo " +
						  "SET art_stock = ? " +
						  "WHERE art_id = ?";
			
			PreparedStatement pstmLeerStock = conexion.prepareStatement( sql1 );
			PreparedStatement pstmActuStock = conexion.prepareStatement( sql2 );
			int stock;
			
			pstm = conexion.prepareStatement( sql );
			
			for( Detalle d : venta.getDetalle() )
			{
				pstmLeerStock.setString( 1, d.getArt_id() );
				ResultSet rset = pstmLeerStock.executeQuery();
				rset.next();
				stock = rset.getInt( "art_stock" );
				rset.close();
				
				if( stock < d.getDet_cant() )
					throw new SQLException( "Stock insuficiente." );
				
				pstmActuStock.setInt( 1, stock - d.getDet_cant() );
				pstmActuStock.setString( 2, d.getArt_id() );
				pstmActuStock.executeUpdate();
				
				pstm.setString( 1, venta.getVen_id() );
				pstm.setString( 2, d.getArt_id() );
				pstm.setInt( 3, d.getDet_cant() );
				pstm.setDouble( 4, d.getDet_precio() );
				pstm.executeUpdate();
			}
					
			// ---------------------------------- //		
			// ---> ACTUALIZA TABLA CONTADOR <--- //
			// ---------------------------------- //
			sql = "UPDATE contador SET con_item = con_item + 1 " +
				  "WHERE con_tabla = UPPER('Venta')";
			stm.executeUpdate( sql );
			conexion.commit();
			stm.close();
			pstm.close();
		}
		catch( SQLException e )
		{
			try
			{
				conexion.rollback();
			}
			catch( Exception e1 )
			{
				
			}
			throw new RuntimeException( e.getMessage() );
		}
		catch( Exception e )
		{
			try
			{
				conexion.rollback();
			}
			catch( Exception e1 )
			{
				
			}
			throw new RuntimeException( "Error en el proceso de grabar venta." );
		}
		finally
		{
			try
			{
				conexion.close();
			}
			catch( Exception e )
			{
				
			}
		}
	}
}
