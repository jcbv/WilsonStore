package pe.jcbv.wilson.cliente.layer.controller;

import pe.jcbv.wilson.cliente.domain.Usuario;
import pe.jcbv.wilson.cliente.layer.service.InicioService;
import pe.jcbv.wilson.cliente.layer.view.util.Sesion;

public class InicioController
{
	public void validar( String usuario, String clave )
	{
		InicioService service = new InicioService();
		Usuario user = service.validar( usuario, clave );
		Sesion.put( "usuario", user );
	}	
}
