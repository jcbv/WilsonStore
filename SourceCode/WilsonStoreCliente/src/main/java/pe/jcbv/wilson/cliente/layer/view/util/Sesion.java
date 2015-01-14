package pe.jcbv.wilson.cliente.layer.view.util;

import java.util.HashMap;
import java.util.Map;

public class Sesion
{
	private static Map<String, Object> datos;
	
	static
	{
		datos = new HashMap<String, Object>();
	}
	
	private Sesion()
	{
		
	}
	
	public static void put( String key, Object value )
	{
		datos.put( key , value );
	}
	
	public static Object get( String key )
	{
		return datos.get( key );
	}
}
