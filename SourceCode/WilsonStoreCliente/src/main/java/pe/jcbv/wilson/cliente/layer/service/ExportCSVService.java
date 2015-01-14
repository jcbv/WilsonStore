package pe.jcbv.wilson.cliente.layer.service;

import pe.jcbv.wilson.cliente.layer.export.ExportCSV;
import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;

public class ExportCSVService
{
	private ExportCSV exportCSV;
	
	public ExportCSVService()
	{
		exportCSV = new ExportCSV();
	}
	
	public void exportarCSVArt( List< ConsultaPorArticulo > lista, String fileName )
	{
		exportCSV.generateCsvFileA( lista, fileName );
	}
	
	public void exportarCSVCat( List< ConsultaPorCategoria > lista, String fileName )
	{
		exportCSV.generateCsvFileC(lista, fileName );
	}
}
