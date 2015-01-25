package pe.jcbv.wilson.cliente.layer.controller;

import pe.jcbv.wilson.cliente.domain.Venta;
import pe.jcbv.wilson.cliente.layer.service.ProcesosService;

public class ProcesosController
{
	private ProcesosService procesosService;
	
	public ProcesosController()
	{
		procesosService = new ProcesosService();
	}
	
	public void grabarVenta( Venta venta )
	{
		procesosService.grabarVenta( venta );
	}
}
