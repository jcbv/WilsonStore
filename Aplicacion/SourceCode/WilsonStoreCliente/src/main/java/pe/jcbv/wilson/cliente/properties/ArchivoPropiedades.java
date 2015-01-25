package pe.jcbv.wilson.cliente.properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ArchivoPropiedades
{
	public Properties getProperties()
	{	
		try( FileReader reader = new FileReader( "config.properties" ) )
		{
			Properties propiedades = new Properties();
			propiedades.load( reader );
			
			return propiedades;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main( String[] args ) throws IOException
	{
		Properties propiedades = new Properties();
		propiedades.setProperty( "ip", "localhost" );
		propiedades.setProperty("database", "wilsonstore" );
		propiedades.setProperty( "password", "admin" );
		
		FileWriter writer = new FileWriter( "config.properties" );
		propiedades.store( writer, "Autor: CJBS" );
		writer.close();
	}
}

