package pe.jcbv.wilson.cliente.layer.controller;

import java.util.Date;
import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;
import pe.jcbv.wilson.cliente.layer.service.ConsultaService;

public class ConsultaController
{
	private ConsultaService consultaService;
	
	public ConsultaController()
	{
		consultaService = new ConsultaService();
	}
	
	public List< ConsultaPorArticulo > consultarPorArticulo( Date fechaI, Date fechaF)
	{
		return consultaService.consultarPorArticulo( fechaI, fechaF );
	}
	
	public List< ConsultaPorCategoria > consultarPorCategoria( Date fechaI, Date fechaF )
	{
		return consultaService.consultarPorCategoria( fechaI, fechaF );
	}

}
