package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.jcbv.wilson.cliente.domain.Articulo;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoArticulo;

public class CrudDaoArticuloImpl implements CrudDaoArticulo
{
	@Override
	public Articulo consultarPorCodigo( String codigo )
	{
		Articulo articulo = null;
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT art_id, art_nombre, " +
						 "art_descrip, art_stock, " +
						 "art_pcosto, art_pctgan, " +
						 "art_pventa, art_poferta, " +
						 "cat_id " +
						 "FROM articulo " +
						 "WHERE art_id = ? " +
						 "FOR UPDATE";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1,  codigo );
			ResultSet rs = pstm.executeQuery();
			
			if( rs.next() )
			{
				articulo = currentRowToBean( rs );
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
		
		return articulo;
	}

	@Override
	public List< Articulo > consultarPorNombre( String nombre )
	{
		List< Articulo > lista = new ArrayList< Articulo >();
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT art_id, art_nombre, " +
					 	 "art_descrip, art_stock, " +
					 	 "art_pcosto, art_pctgan, " +
					 	 "art_pventa, art_poferta, " +
					 	 "cat_id " +
					 	 "FROM articulo " +
					 	 "WHERE UPPER(art_nombre) LIKE ? " +
					 	 "FOR UPDATE";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			nombre = nombre.toUpperCase() + "%";
			pstm.setString( 1, nombre );
			ResultSet rs = pstm.executeQuery();
			
			while( rs.next() )
			{
				Articulo articulo = currentRowToBean( rs );
				lista.add( articulo );
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
	public void insertar( Articulo articulo )
	{
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			conexion.setAutoCommit( false );
			
			String sql = "SELECT con_item, con_longitud " +
						 "FROM contador " +
						 "WHERE con_tabla = UPPER('Articulo') " +
						 "FOR UPDATE";
			
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			
			if( !rs.next() )
				throw new SQLException( "No existe el contador." );
			
			int cont = rs.getInt( "con_item" ) + 1;
			int ancho = rs.getInt( "con_longitud" );
			rs.close();
			String codigo = AccesoDB.generarCodigo( cont, ancho );
			
			sql = "INSERT INTO articulo ( art_id, art_nombre, " +
				  "art_descrip, art_stock, art_pcosto, art_pctgan," +
				  "art_pventa, art_poferta, cat_id ) " +
				  "VALUES ( ?,?,?,?,?,?,?,?,? )";
			
			PreparedStatement psmt = conexion.prepareStatement( sql );
			psmt.setString( 1,  codigo );
			psmt.setString( 2, articulo.getArt_nombre() );
			psmt.setString( 3, articulo.getArt_descrip() );
			psmt.setInt( 4, articulo.getArt_stock() );
			psmt.setDouble( 5, articulo.getArt_pcosto() );
			psmt.setDouble( 6, articulo.getArt_pctgan() );
			psmt.setDouble( 7, articulo.getArt_pventa() );
			psmt.setDouble( 8, articulo.getArt_poferta() );
			psmt.setString( 9, articulo.getCat_id() );
			psmt.executeUpdate();
			
			sql = "UPDATE contador SET con_item = con_item + 1 " +
				  "WHERE con_tabla = UPPER('Articulo')";
			stm.executeUpdate( sql );
			conexion.commit();
			articulo.setArt_id( codigo );
			stm.close();
			psmt.close();
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
	public void actualizar( Articulo articulo )
	{
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			conexion.setAutoCommit( false );
			
			String sql = "UPDATE articulo " +
						 "SET art_nombre = ?, " +
						 "art_descrip = ?, " +
						 "art_stock = ?, " +
						 "art_pcosto = ?, " +
						 "art_pctgan = ?, " +
						 "art_pventa = ?, " +
						 "art_poferta = ?, " +
						 "cat_id = ? " +
						 "WHERE art_id = ?";
			
			PreparedStatement psmt = conexion.prepareStatement( sql );
			psmt.setString( 1, articulo.getArt_nombre() );
			psmt.setString( 2, articulo.getArt_descrip() );
			psmt.setInt( 3, articulo.getArt_stock() );
			psmt.setDouble( 4, articulo.getArt_pcosto() );
			psmt.setDouble( 5, articulo.getArt_pctgan() );
			psmt.setDouble( 6, articulo.getArt_pventa() );
			psmt.setDouble( 7, articulo.getArt_poferta() );
			psmt.setString( 8, articulo.getCat_id() );
			psmt.setString( 9, articulo.getArt_id() );
			psmt.executeUpdate();
			
			conexion.commit();
			psmt.close();
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
			
			String sql = "DELETE FROM articulo " +
						 "WHERE art_id = ?";
			
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
			throw new RuntimeException( "Error en el proceso de inserción" );
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
	
	private Articulo currentRowToBean( ResultSet rs ) throws SQLException
	{	
		Articulo articulo = new Articulo();
		
		articulo.setArt_id( rs.getString( "art_id" ) );
        articulo.setArt_nombre( rs.getString( "art_nombre" ) );
        articulo.setArt_descrip( rs.getString( "art_descrip" ) ) ;
        articulo.setArt_stock( rs.getInt( "art_stock" ) );
        articulo.setArt_pcosto( rs.getDouble( "art_pcosto" ) );
        articulo.setArt_pctgan( rs.getDouble( "art_pctgan" ) );
        articulo.setArt_pventa( rs.getDouble( "art_pventa" ) );
        articulo.setArt_poferta( rs.getDouble( "art_poferta" ) );
        articulo.setCat_id( rs.getString( "cat_id" ) );
		
		return articulo;
	}
}
