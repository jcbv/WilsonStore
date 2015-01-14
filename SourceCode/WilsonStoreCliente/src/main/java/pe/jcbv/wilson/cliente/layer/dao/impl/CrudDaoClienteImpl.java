package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoCliente;

public class CrudDaoClienteImpl implements CrudDaoCliente
{

	@Override
	public Cliente consultarPorCodigo( String codigo )
	{
		Cliente cliente = null;
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT cli_id, cli_paterno, cli_materno, " +
						 "cli_nombre, cli_ciudad, cli_direccion " +
						 "FROM cliente " +
						 "WHERE cli_id = ? " +
						 "FOR UPDATE";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, codigo );
			ResultSet rs = pstm.executeQuery();
			
			if( rs.next() )
				cliente = currentRowToBean( rs );
			
			rs.close();
			pstm.close();
		}
		catch( SQLException e )
		{
			throw new RuntimeException( e.getMessage() );
		}
		finally
		{
			try
			{
				conexion.close();
			}
			catch( Exception e2 )
			{
				
			}
		}
		
		return cliente;
	}

	@Override
	public List< Cliente > consultarPorNombre( String nombre )
	{
		List< Cliente > lista = new ArrayList< Cliente >();
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT cli_id, cli_paterno, cli_materno, " +
						 "cli_nombre, cli_ciudad, cli_direccion " +
						 "FROM cliente " +
						 "WHERE UPPER(cli_nombre) LIKE ? " +
						 "FOR UPDATE";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			nombre = nombre.toUpperCase() + "%";
			pstm.setString( 1, nombre );
			ResultSet rs = pstm.executeQuery();
			
			while( rs.next() )
			{
				Cliente cliente = currentRowToBean( rs );
				lista.add( cliente );
			}
			rs.close();
			pstm.close();
		}
		catch( SQLException e )
		{
			throw new RuntimeException( e.getMessage() );
		}
		finally
		{
			try
			{
				conexion.close();
			}
			catch( Exception e2 )
			{
				
			}
		}
		
		return lista;
	}

	@Override
	public void insertar( Cliente cliente )
	{
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			conexion.setAutoCommit( false );
			
			String sql = "SELECT con_item, con_longitud " +
						 "FROM contador " +
						 "WHERE con_tabla = UPPER('Cliente') " +
						 "FOR UPDATE";
			
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			
			if( !rs.next() )
				throw new SQLException( "No existe el contador." );
			
			int cont = rs.getInt( "con_item" ) + 1;
			int ancho = rs.getInt( "con_longitud" );
			rs.close();
			String codigo = AccesoDB.generarCodigo( cont, ancho );
			
			sql = "INSERT INTO cliente ( cli_id, cli_paterno, " +
				  "cli_materno, cli_nombre, cli_ciudad, " +
				  "cli_direccion ) " +
				  "VALUES ( ?,?,?,?,?,? )";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, codigo );
			pstm.setString( 2, cliente.getCli_paterno() );
			pstm.setString( 3, cliente.getCli_materno() );
			pstm.setString( 4, cliente.getCli_nombre() );
			pstm.setString( 5, cliente.getCli_ciudad() );
			pstm.setString( 6, cliente.getCli_direccion() );
			pstm.executeUpdate();
			
			sql = "UPDATE contador SET con_item = con_item + 1 " +
				  "WHERE con_tabla = UPPER('Cliente')";
			stm.executeUpdate( sql );
			conexion.commit();
			cliente.setCli_id( codigo );
			stm.close();
			pstm.close();
		}
		catch( SQLException e )
		{
			try
			{
				conexion.rollback();
			}
			catch( Exception e2 )
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
			catch( Exception e2 )
			{
				
			}
			throw new RuntimeException( "Error en el proceso de inserción." );
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

	@Override
	public void actualizar( Cliente cliente )
	{
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			conexion.setAutoCommit( false );
			
			String sql = "UPDATE cliente " +
						 "SET cli_paterno = ?, " +
						 "cli_materno = ?, " +
						 "cli_nombre = ?, " +
						 "cli_ciudad = ?, " +
						 "cli_direccion = ? " +
						 "WHERE cli_id = ?";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, cliente.getCli_paterno() );
			pstm.setString( 2, cliente.getCli_materno() );
			pstm.setString( 3, cliente.getCli_nombre() );
			pstm.setString( 4, cliente.getCli_ciudad() );
			pstm.setString( 5, cliente.getCli_direccion() );
			pstm.setString( 6, cliente.getCli_id() );
			pstm.executeUpdate();
			
			conexion.commit();
			pstm.close();
		}
		catch( SQLException e )
		{
			try
			{
				conexion.rollback();
			}
			catch( Exception e2 )
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
			catch( Exception e2 )
			{
				
			}
			throw new RuntimeException( "Error en el proceso de inserción." );
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

	@Override
	public void eliminar( String codigo )
	{
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			conexion.setAutoCommit( false );
			
			String sql = "DELETE FROM cliente " +
						 "WHERE cli_id = ?";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, codigo );
			pstm.executeUpdate();
			
			conexion.commit();
			pstm.close();
		}
		catch( SQLException e )
		{
			try
			{
				conexion.rollback();
			}
			catch( Exception e2 )
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
			catch( Exception e2 )
			{
				
			}
			throw new RuntimeException( "Error en el proceso de inserción." );
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
	
	
	private Cliente currentRowToBean( ResultSet rs ) throws SQLException
	{
		Cliente cliente = new Cliente();
		
		cliente.setCli_id( rs.getString( "cli_id" ) );
		cliente.setCli_paterno( rs.getString( "cli_paterno" ) );
		cliente.setCli_materno( rs.getString( "cli_materno" ) );
		cliente.setCli_nombre( rs.getString( "cli_nombre" ) );
		cliente.setCli_ciudad( rs.getString( "cli_ciudad" ) );
		cliente.setCli_direccion( rs.getString( "cli_direccion" ) );
		
		return cliente;
	}
}
