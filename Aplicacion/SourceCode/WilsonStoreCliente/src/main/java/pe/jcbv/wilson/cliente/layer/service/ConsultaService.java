package pe.jcbv.wilson.cliente.layer.service;

import java.util.Date;
import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;
import pe.jcbv.wilson.cliente.layer.dao.espec.DaoConsulta;
import pe.jcbv.wilson.cliente.layer.dao.impl.DaoConsultaImpl;

public class ConsultaService
{
	private DaoConsulta daoConsulta;
	
	public ConsultaService()
	{
		daoConsulta = new DaoConsultaImpl();
	}
	
	public List< ConsultaPorArticulo > consultarPorArticulo( Date fechaInicio, Date fechaFinal )
	{
		return daoConsulta.consultarPorArticulo(fechaInicio, fechaFinal);
	}
	
	public List< ConsultaPorCategoria > consultarPorCategoria( Date fechaInicio, Date fechaFinal )
	{
		return daoConsulta.consultarPorCategoria( fechaInicio, fechaFinal );
	}
}
