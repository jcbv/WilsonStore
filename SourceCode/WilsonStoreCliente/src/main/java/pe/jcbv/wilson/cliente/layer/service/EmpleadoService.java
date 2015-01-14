package pe.jcbv.wilson.cliente.layer.service;

import pe.jcbv.wilson.cliente.domain.Empleado;
import pe.jcbv.wilson.cliente.layer.dao.espec.CrudDaoEmpleado;
import pe.jcbv.wilson.cliente.layer.dao.impl.CrudDaoEmpleadoImpl;

public class EmpleadoService
{
	private CrudDaoEmpleado crudDaoEmpleado;
	
	public EmpleadoService()
	{
		crudDaoEmpleado = new CrudDaoEmpleadoImpl();
	}
	
	public Empleado consultarPorCodigo( String codigo )
	{
		return crudDaoEmpleado.consultarPorCodigo( codigo );
	}
}
