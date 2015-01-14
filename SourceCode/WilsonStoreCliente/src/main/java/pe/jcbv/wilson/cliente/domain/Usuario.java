package pe.jcbv.wilson.cliente.domain;

public class Usuario
{
	private String emp_id;		// Id de empleado
	private String usu_nombre;
	private String usu_clave;
	
	public Usuario()
	{
		
	}

	// --- METODOS SET --- //
	public void setEmp_id( String emp_id )
	{
		this.emp_id = emp_id;
	}

	public void setUsu_nombre( String usu_nombre )
	{
		this.usu_nombre = usu_nombre;
	}

	public void setUsu_clave( String usu_clave )
	{
		this.usu_clave = usu_clave;
	}
	
	// --- METODOS GET --- //
	public String getEmp_id()
	{
		return emp_id;
	}

	public String getUsu_nombre()
	{
		return usu_nombre;
	}

	public String getUsu_clave()
	{
		return usu_clave;
	}
}
