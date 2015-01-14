package pe.jcbv.wilson.cliente.domain;

public class Articulo
{
	private String art_id;
	private String art_nombre;
	private String art_descrip;
	private int art_stock;
	private double art_pcosto;		// Precio de Costo
	private double art_pctgan;		// Porcentaje de Ganancia
	private double art_pventa;		// Precio de Venta
	private double art_poferta;		// Precio de Oferta
	private String cat_id;			// Categoría de artículo
	
	public Articulo()
	{
		
	}

	// ---METODOS SET --- //
	public void setArt_id( String art_id )
	{
		this.art_id = art_id;
	}

	public void setArt_nombre( String art_nombre )
	{
		this.art_nombre = art_nombre;
	}

	public void setArt_descrip( String art_descrip )
	{
		this.art_descrip = art_descrip;
	}

	public void setArt_stock( int art_stock )
	{
		this.art_stock = art_stock;
	}

	public void setArt_pcosto( double art_pcosto )
	{
		this.art_pcosto = art_pcosto;
	}

	public void setArt_pctgan( double art_pctgan )
	{
		this.art_pctgan = art_pctgan;
	}

	public void setArt_pventa( double art_pventa )
	{
		this.art_pventa = art_pventa;
	}
	
	public void setArt_pventa()
	{
		this.art_pventa = getArt_pcosto() + getArt_pctgan() * getArt_pcosto();
	}

	public void setArt_poferta( double art_poferta )
	{
		this.art_poferta = art_poferta;
	}
	
	public void setArt_poferta()
	{
		this.art_poferta = getArt_pcosto() + 0.5 * getArt_pctgan() * getArt_pcosto();
	}

	public void setCat_id( String cat_id )
	{
		this.cat_id = cat_id;
	}

	// --- METODOS GET --- //
	public String getArt_id()
	{
		return art_id;
	}

	public String getArt_nombre()
	{
		return art_nombre;
	}

	public String getArt_descrip()
	{
		return art_descrip;
	}

	public int getArt_stock()
	{
		return art_stock;
	}

	public double getArt_pcosto()
	{
		return art_pcosto;
	}

	public double getArt_pctgan()
	{
		return art_pctgan;
	}

	public double getArt_pventa()
	{
		return art_pventa;
	}

	public double getArt_poferta()
	{
		return art_poferta;
	}

	public String getCat_id()
	{
		return cat_id;
	}
}
