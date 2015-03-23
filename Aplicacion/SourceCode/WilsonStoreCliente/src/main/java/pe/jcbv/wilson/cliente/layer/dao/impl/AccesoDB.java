package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;

import pe.jcbv.wilson.cliente.properties.ArchivoPropiedades;

public class AccesoDB
{

	private AccesoDB()
	{
		
	}

	public static Connection getConnection() throws SQLException
	{
		Connection conexion = null;
		
		try
		{
			//Properties p = new ArchivoPropiedades().getProperties();
			
			String driver = "org.postgresql.Driver";
			String url = "jdbc:postgresql://localhost:5433/wilsonstore";
			String user = "postgres";
			String pass = "postgres";
//			String driver = "oracle.jdbc.OracleDriver";
//			String url = "jdbc:oracle:thin:@localhost:1521:XE";
//			String user = "wilsonstore";
//			String pass = "admin";
			//String url = "jdbc:oracle:thin:@" + p.getProperty("ip") +":1521:XE";
			//String user = p.getProperty("database");
			//String pass = p.getProperty("password");
			Class.forName( driver ).newInstance();
			conexion = DriverManager.getConnection( url, user, pass );
		}
		catch( SQLException e )
		{
			throw e;
		}
		catch( InstantiationException e )
		{
			e.printStackTrace();
			throw new SQLException( "No se puede instanciar el driver." );
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
			throw new SQLException( "Driver no es compatible." );
		}
		catch( ClassNotFoundException e )
		{
			e.printStackTrace();
			throw new SQLException( "No se encontró el driver." );
		} 
		
		return conexion;
	}

	public static String generarCodigo( int cont, int ancho )
	{
		String formato = "";
		
		for( int i = 1 ; i <= ancho ; i++ )
			formato += "0";
			
		DecimalFormat df = new DecimalFormat( formato );
		String codigo = df.format( cont );
		
		return codigo;
	}
}
