package pe.jcbv.wilson.cliente.domain;

import java.util.List;

public class Venta
{
	final private double IGV = 0.18;
	private String ven_id;
	private double ven_subtotal;
	private double ven_impuesto;
	private double ven_total;
	private String emp_id;
	private String cli_id;
	private List< Detalle > detalle;
	
	public Venta()
	{
		
	}

	// --- METODOS SET --- //
	public void setVen_id( String ven_id )
	{
		this.ven_id = ven_id;
	}

	public void setVen_subtotal( double ven_subtotal )
	{
		this.ven_subtotal = ven_subtotal;
	}
	
	public void setVen_subtotal()
	{
		double sum = 0.0;
		
		for( Detalle d : detalle )
			sum += d.getDet_precio();
		
		this.ven_subtotal = sum;
	}

	public void setVen_impuesto( double ven_impuesto )
	{
		this.ven_impuesto = ven_impuesto;
	}
	
	public void setVen_impuesto()
	{
		this.ven_impuesto = IGV * getVen_subtotal();
	}

	public void setVen_total( double ven_total )
	{
		this.ven_total = ven_total;
	}
	
	public void setVen_total()
	{
		this.ven_total = getVen_impuesto() + getVen_subtotal();		
	}

	public void setEmp_id( String emp_id )
	{
		this.emp_id = emp_id;
	}

	public void setCli_id( String cli_id )
	{
		this.cli_id = cli_id;
	}

	public void setDetalle( List<Detalle> detalle )
	{
		this.detalle = detalle;
	}

	// --- METODOS GET --- //
	public String getVen_id()
	{
		return ven_id;
	}

	public double getVen_subtotal()
	{
		return ven_subtotal;
	}

	public double getVen_impuesto()
	{
		return ven_impuesto;
	}

	public double getVen_total()
	{
		return ven_total;
	}

	public String getEmp_id()
	{
		return emp_id;
	}

	public String getCli_id()
	{
		return cli_id;
	}

	public List<Detalle> getDetalle()
	{
		return detalle;
	}
}
