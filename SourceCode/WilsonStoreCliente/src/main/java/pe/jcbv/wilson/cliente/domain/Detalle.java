package pe.jcbv.wilson.cliente.domain;

import pe.jcbv.wilson.cliente.domain.Articulo;

public class Detalle
{
	private String art_id;
	private int det_cant;		// Cantidad de artículos
	private double det_precio;	// Importe o precio (det_cant x art_pventa)
	private Articulo articulo;
	
	public Detalle()
	{
		
	}

	// --- METODOS SET --- //
	public void setArt_id( String art_id )
	{
		this.art_id = art_id;
	}
	
	public void setArt_id()
	{
		this.art_id = getArticulo().getArt_id();
	}

	public void setDet_cant( int det_cant )
	{
		this.det_cant = det_cant;
	}

	public void setDet_precio( double det_precio )
	{
		this.det_precio = det_precio;
	}
	
	public void setDet_precio()
	{
		this.det_precio = getDet_cant() * getArticulo().getArt_pventa();
	}
	
	public void setArticulo( Articulo articulo )
	{
		this.articulo = articulo;
	}

	// --- METODOS GET --- //
	public String getArt_id()
	{
		return art_id;
	}

	public int getDet_cant()
	{
		return det_cant;
	}

	public double getDet_precio()
	{
		return det_precio;
	}
	
	public Articulo getArticulo()
	{
		return articulo;
	}
}
