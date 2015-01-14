package pe.jcbv.wilson.cliente.layer.controller;

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
	
	public List< ConsultaPorArticulo > consultarPorArticulo( String fechaInicio, String fechaFinal )
	{
		return consultaService.consultarPorArticulo( fechaInicio, fechaFinal );
	}
	
	public List< ConsultaPorCategoria > consultarPorCategoria( String fechaInicio, String fechaFinal )
	{
		return consultaService.consultarPorCategoria( fechaInicio, fechaFinal );
	}
}
