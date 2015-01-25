package pe.jcbv.wilson.cliente.layer.service;

import pe.jcbv.wilson.cliente.domain.Usuario;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoUsuario;
import pe.jcbv.wilson.cliente.layer.dao.impl.CrudDaoUsuarioImpl;

public class InicioService
{
	public Usuario validar( String usuario, String clave )
	{
		Usuario user;
		CrudDaoUsuario daoUsuario = new CrudDaoUsuarioImpl();
		user = daoUsuario.consultarPorUsuario( usuario );
		
		if( user == null )
		{
			throw new RuntimeException( "Usuario no existe." );
		}
		
		if( !user.getUsu_clave().equals( clave ) )
		{
			throw new RuntimeException( "Clave incorrecta." );
		}
		
		return user;
	}
}
