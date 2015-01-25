package pe.jcbv.wilson.cliente.layer.controller;

import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;
import pe.jcbv.wilson.cliente.layer.service.ExportCSVService;

public class ExportCSVController
{
	private ExportCSVService exportCSV;
	
	public ExportCSVController()
	{
		exportCSV = new ExportCSVService();
	}
	
	public void exportarCSVArt( List< ConsultaPorArticulo > lista, String fileName )
	{
		exportCSV.exportarCSVArt( lista, fileName );
	}
	
	public void exportarCSVCat( List< ConsultaPorCategoria > lista, String fileName )
	{
		exportCSV.exportarCSVCat( lista, fileName );
	}
}
