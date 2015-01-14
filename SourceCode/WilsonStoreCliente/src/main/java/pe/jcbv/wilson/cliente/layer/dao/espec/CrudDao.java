package pe.jcbv.wilson.cliente.layer.dao.espec;

import java.util.List;

public interface CrudDao<T>
{
	T consultarPorCodigo( String codigo );
	List<T> consultarPorNombre( String nombre );
	void insertar( T objeto );
	void actualizar( T objeto );
	void eliminar( String codigo );
}
