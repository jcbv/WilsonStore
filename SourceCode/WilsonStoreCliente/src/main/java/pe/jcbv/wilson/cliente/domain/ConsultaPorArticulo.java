package pe.jcbv.wilson.cliente.domain;

public class ConsultaPorArticulo
{
	private String codigoArt;
	private String nombreArt;
	private int cantArt;
	private double precioArt;
	private double importeArt;
	
	public ConsultaPorArticulo()
	{
		
	}

	// --- METODOS SET --- //
	public void setCodigoArt( String codigoArt )
	{
		this.codigoArt = codigoArt;
	}

	public void setNombreArt( String nombreArt )
	{
		this.nombreArt = nombreArt;
	}

	public void setCantArt( int cantArt )
	{
		this.cantArt = cantArt;
	}

	public void setPrecioArt( double precioArt )
	{
		this.precioArt = precioArt;
	}

	public void setImporteArt( double importeArt )
	{
		this.importeArt = importeArt;
	}

	// --- METODOS GET --- //
	public String getCodigoArt()
	{
		return codigoArt;
	}

	public String getNombreArt()
	{
		return nombreArt;
	}

	public int getCantArt()
	{
		return cantArt;
	}

	public double getPrecioArt()
	{
		return precioArt;
	}

	public double getImporteArt()
	{
		return importeArt;
	}
	
	public String toString()
	{	
		/*String cabecera = String.format( "%10s%60s%10s%12s%12s\n",
								  "CODIGO", "NOMBRE", "CANT.", "P.UNIT", "IMPORTE" );*/
		
		return String.format( "%10s%60s%10d%12.2f%12.2f\n",
				  getCodigoArt(), getNombreArt(), getCantArt(),
				  getPrecioArt(), getImporteArt() );
	}
}
