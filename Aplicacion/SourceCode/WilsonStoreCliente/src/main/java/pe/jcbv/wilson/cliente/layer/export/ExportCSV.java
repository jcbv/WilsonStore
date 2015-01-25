package pe.jcbv.wilson.cliente.layer.export;

import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportCSV
{
	public ExportCSV()
	{
		
	}

	public void generateCsvFileA( List< ConsultaPorArticulo > lista, String fileName )
	{
		try
		{
			File file = new File( fileName );
			file.createNewFile();
			
			FileWriter writer = new FileWriter( fileName );
			
			writer.append( "CODIGO" );
			writer.append( ',' );
			writer.append( "NOMBRE" );
			writer.append( ',' );
			writer.append( "CANT." );
			writer.append( ',' );
			writer.append( "P. UNIT." );
			writer.append( ',' );
			writer.append( "IMPORTE" );
			writer.append( '\n' );
			
			for( ConsultaPorArticulo a : lista )
			{
				writer.append( a.getCodigoArt() );
				writer.append( ',' );
				writer.append( a.getNombreArt() );
				writer.append( ',' );
				writer.append( String.valueOf( a.getCantArt() ) );
				writer.append( ',' );
				writer.append( String.valueOf( a.getPrecioArt() ) );
				writer.append( ',' );
				writer.append( String.valueOf( a.getImporteArt() ) );
				writer.append( '\n' );
			}
			
			writer.flush();
			writer.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void generateCsvFileC( List< ConsultaPorCategoria > lista, String fileName )
	{
		try
		{
			File file = new File( fileName );
			file.createNewFile();
			
			FileWriter writer = new FileWriter( fileName );
			
			writer.append( "CATEGORIA" );
			writer.append( ',' );
			writer.append( "CANT." );
			writer.append( ',' );
			writer.append( "IMPORTE" );
			writer.append( '\n' );
			
			for( ConsultaPorCategoria c : lista )
			{
				writer.append( c.getNombreCat() );
				writer.append( ',' );
				writer.append( String.valueOf( c.getCantCat() ) );
				writer.append( ',' );
				writer.append( String.valueOf( c.getImporteCat() ) );
				writer.append( '\n' );
			}
			
			writer.flush();
			writer.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}
