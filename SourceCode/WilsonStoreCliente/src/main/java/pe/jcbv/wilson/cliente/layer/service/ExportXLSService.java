package pe.jcbv.wilson.cliente.layer.service;

import java.util.List;
import pe.jcbv.wilson.cliente.layer.export.ExportXLS;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;

public class ExportXLSService
{
	private ExportXLS exportXLS;
	
	public ExportXLSService()
	{
		exportXLS = new ExportXLS();
	}
	
	public void exportarXLSArt( List< ConsultaPorArticulo > lista, String fileName )
	{
		exportXLS.generateXlsFileA( lista, fileName );
	}
	
	public void exportarXLSCat( List< ConsultaPorCategoria > lista, String fileName )
	{
		exportXLS.generateXlsFileC( lista, fileName );
	}
}
