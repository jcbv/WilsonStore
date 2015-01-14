package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pe.jcbv.wilson.cliente.domain.Empleado;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoEmpleado;

public class CrudDaoEmpleadoImpl implements CrudDaoEmpleado
{

	@Override
	public Empleado consultarPorCodigo( String codigo )
	{
		Empleado empleado = null;
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT emp_id, emp_paterno, emp_materno, " +
						 "emp_nombre, emp_ciudad, emp_direccion " +
						 "FROM empleado " +
						 "WHERE emp_id = ? " +
						 "FOR UPDATE";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setString( 1, codigo );
			ResultSet rs = pstm.executeQuery();
			
			if( rs.next() )
				empleado = currentRowToBean( rs );
			
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
		
		return empleado;
	}

	@Override
	public List<Empleado> consultarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(Empleado objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Empleado objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(String codigo) {
		// TODO Auto-generated method stub
		
	}
	
	private Empleado currentRowToBean( ResultSet rs ) throws SQLException
	{
		Empleado empleado = new Empleado();
		
		empleado.setEmp_id( rs.getString( "emp_id" ) );
		empleado.setEmp_paterno( rs.getString( "emp_paterno" ) );
		empleado.setEmp_materno( rs.getString( "emp_materno" ) );
		empleado.setEmp_nombre( rs.getString( "emp_nombre" ) );
		empleado.setEmp_ciudad( rs.getString( "emp_ciudad" ) );
		empleado.setEmp_direccion( rs.getString( "emp_direccion" ) );
		
		return empleado;
	}
}
