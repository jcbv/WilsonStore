package pe.jcbv.wilson.cliente.domain;

public class ConsultaPorCategoria
{
	private String nombreCat;
	private int cantCat;
	private double importeCat;
	
	public ConsultaPorCategoria()
	{
		
	}

	// --- METODOS SET --- //
	public void setNombreCat( String nombreCat )
	{
		this.nombreCat = nombreCat;
	}

	public void setCantCat( int cantCat )
	{
		this.cantCat = cantCat;
	}

	public void setImporteCat( double importeCat )
	{
		this.importeCat = importeCat;
	}

	// --- METODOS GET --- //
	public String getNombreCat()
	{
		return nombreCat;
	}

	public int getCantCat()
	{
		return cantCat;
	}

	public double getImporteCat()
	{
		return importeCat;
	}
	
	public String toString()
	{
		/*String cabecera = String.format( "%20s%10s%12s\n",
										 "CATEGORIA", "CANT.", "IMPORTE" );*/
		
		return String.format( "%20s%10d%12.2f\n",
							  getNombreCat(),
							  getCantCat(),
							  getImporteCat() );
	}
}
