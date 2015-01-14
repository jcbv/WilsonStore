package pe.jcbv.wilson.cliente.layer.controller;

import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;
import pe.jcbv.wilson.cliente.layer.service.ExportXLSService;

public class ExportXLSController
{
	private ExportXLSService exportXLS;
	
	public ExportXLSController()
	{
		exportXLS = new ExportXLSService();
	}
	
	public void exportarXLSArt( List< ConsultaPorArticulo > lista, String fileName )
	{
		exportXLS.exportarXLSArt( lista, fileName );
	}
	
	public void exportarXLSCat( List< ConsultaPorCategoria > lista, String fileName )
	{
		exportXLS.exportarXLSCat( lista, fileName );
	}
}
