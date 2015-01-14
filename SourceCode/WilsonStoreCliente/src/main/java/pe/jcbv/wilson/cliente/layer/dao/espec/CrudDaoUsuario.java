package pe.jcbv.wilson.cliente.layer.dao.espec;

import pe.jcbv.wilson.cliente.domain.Usuario;

public interface CrudDaoUsuario extends CrudDao<Usuario>
{
	Usuario consultarPorUsuario( String usuario );
}
