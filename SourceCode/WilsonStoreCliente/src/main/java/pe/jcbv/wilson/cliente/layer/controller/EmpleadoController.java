package pe.jcbv.wilson.cliente.layer.controller;

import pe.jcbv.wilson.cliente.domain.Empleado;
import pe.jcbv.wilson.cliente.layer.service.EmpleadoService;

public class EmpleadoController
{
	private EmpleadoService empleadoService;
	
	public EmpleadoController()
	{
		empleadoService = new EmpleadoService();
	}
	
	public Empleado consultarPorCodigo( String codigo )
	{
		return empleadoService.consultarPorCodigo( codigo );
	}
}
