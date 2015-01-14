package pe.jcbv.wilson.cliente.layer.service;

import pe.jcbv.wilson.cliente.domain.Venta;
import pe.jcbv.wilson.cliente.layer.dao.espec.DaoProcesos;
import pe.jcbv.wilson.cliente.layer.dao.impl.DaoProcesosImpl;

public class ProcesosService
{
	private DaoProcesos daoProcesos;
	
	public ProcesosService()
	{
		daoProcesos = new DaoProcesosImpl();
	}
	
	public void grabarVenta( Venta venta )
	{
		daoProcesos.grabarVenta( venta );
	}
}
