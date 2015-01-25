package pe.jcbv.wilson.cliente.layer.export;

import java.util.List;
import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;

public class ExportXLS
{
	public ExportXLS()
	{
		
	}
	
	public void generateXlsFileA( List< ConsultaPorArticulo > lista, String fileName )
	{
		try
		{
			WritableWorkbook workbook =
					Workbook.createWorkbook( new File ( fileName ) );
			
			WritableSheet sheet = 
					workbook.createSheet("ARTICULO", 0 );
			
			sheet.addCell( new jxl.write.Label( 0, 0, "CODIGO" ) );
			sheet.addCell( new jxl.write.Label( 1, 0, "NOMBRE" ) );
			sheet.addCell( new jxl.write.Label( 2, 0, "CANT." ) );
			sheet.addCell( new jxl.write.Label( 3, 0, "P. UNIT." ) );
			sheet.addCell( new jxl.write.Label( 4, 0, "IMPORTE" ) );
			
			
			for( int i = 0 ; i < lista.size() ; i++ )
			{
				sheet.addCell( new jxl.write.Label( 0, i + 1, lista.get( i ).getCodigoArt() ) );
				sheet.addCell( new jxl.write.Label( 1, i + 1, lista.get( i ).getNombreArt() ) );
				sheet.addCell( new jxl.write.Number( 2, i + 1, lista.get( i ).getCantArt() ) );
				sheet.addCell( new jxl.write.Number( 3, i + 1, lista.get( i ).getPrecioArt() ) );
				sheet.addCell( new jxl.write.Number( 4, i + 1, lista.get( i ).getImporteArt() ) );
			}
			
			workbook.write();
			workbook.close();
		}
		catch( IOException e )
		{
			System.out.println( "Error al crear el archivo." );
		}
		catch( WriteException e2 )
		{
			System.out.println( "Error al escribir el archivo." );
		}
	}
	
	public void generateXlsFileC( List< ConsultaPorCategoria > lista, String fileName )
	{
		try
		{
			WritableWorkbook workbook =
					Workbook.createWorkbook( new File ( fileName ) );
			
			WritableSheet sheet = 
					workbook.createSheet("CATEGORIA", 0 );
			
			sheet.addCell( new jxl.write.Label( 0, 0, "NOMBRE" ) );
			sheet.addCell( new jxl.write.Label( 1, 0, "CANT." ) );
			sheet.addCell( new jxl.write.Label( 2, 0, "IMPORTE" ) );
			
			for( int i = 0 ; i < lista.size() ; i++ )
			{
				sheet.addCell( new jxl.write.Label( 0, i + 1, lista.get( i ).getNombreCat() ) );
				sheet.addCell( new jxl.write.Number( 1, i + 1, lista.get( i ).getCantCat() ) );
				sheet.addCell( new jxl.write.Number( 2, i + 1, lista.get( i ).getImporteCat() ) );
			}
			
			workbook.write();
			workbook.close();
		}
		catch( IOException e )
		{
			System.out.println( "Error al crear el archivo." );
		}
		catch( WriteException e2 )
		{
			System.out.println( "Error al escribir el archivo." );
		}
	}
}
