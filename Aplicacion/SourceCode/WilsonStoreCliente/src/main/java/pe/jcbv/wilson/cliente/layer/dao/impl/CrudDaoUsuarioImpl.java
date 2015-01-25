package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pe.jcbv.wilson.cliente.domain.Usuario;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoUsuario;

public class CrudDaoUsuarioImpl implements CrudDaoUsuario
{
	@Override
	public Usuario consultarPorCodigo( String codigo )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> consultarPorNombre( String nombre )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar( Usuario usuario )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar( Usuario usuario )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar( String codigo )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario consultarPorUsuario( String usuario )
	{
		Usuario user = null;
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT emp_id, usu_nombre, usu_clave " +
						 "FROM usuario " +
						 "WHERE usu_nombre = ?";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, usuario );
			ResultSet rs = pstm.executeQuery();
			
			if( rs.next() )
				user = currentRowToBean( rs );
			
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
		
		return user;
	}
	
	private Usuario currentRowToBean( ResultSet rs ) throws SQLException
	{
		Usuario user = new Usuario();
		
		user.setEmp_id( rs.getString( "emp_id" ) );
        user.setUsu_nombre( rs.getString( "usu_nombre" ) );
        user.setUsu_clave( rs.getString( "usu_clave" ) );
	
		return user;
	}
}
