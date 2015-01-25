package pe.jcbv.wilson.cliente.layer.service;

import java.util.List;

import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoCliente;
import pe.jcbv.wilson.cliente.layer.dao.impl.CrudDaoClienteImpl;

public class ClienteService
{
	private CrudDaoCliente crudDaoCliente;
	
	public ClienteService()
	{
		crudDaoCliente = new CrudDaoClienteImpl();
	}
	
	public Cliente consultarPorCodigo( String codigo )
	{
		return crudDaoCliente.consultarPorCodigo( codigo );
	}
	
	public List< Cliente > consultarPorNombre( String nombre )
	{
		return crudDaoCliente.consultarPorNombre( nombre );
	}
	
	public void insertar( Cliente cliente )
	{
		crudDaoCliente.insertar( cliente );
	}
	
	public void actualizar( Cliente cliente )
	{
		crudDaoCliente.actualizar(cliente );
	}
	
	public void eliminar( String codigo )
	{
		crudDaoCliente.eliminar( codigo );
	}
}
