package pe.jcbv.wilson.cliente.domain;

public class Empleado
{
	private String emp_id;
	private String emp_paterno;
	private String emp_materno;
	private String emp_nombre;
	private String emp_ciudad;
	private String emp_direccion;
	
	public Empleado()
	{
		
	}

	// --- METODOS SET --- //
	public void setEmp_id( String emp_id )
	{
		this.emp_id = emp_id;
	}

	public void setEmp_paterno( String emp_paterno )
	{
		this.emp_paterno = emp_paterno;
	}

	public void setEmp_materno( String emp_materno )
	{
		this.emp_materno = emp_materno;
	}

	public void setEmp_nombre( String emp_nombre )
	{
		this.emp_nombre = emp_nombre;
	}

	public void setEmp_ciudad( String emp_ciudad )
	{
		this.emp_ciudad = emp_ciudad;
	}

	public void setEmp_direccion( String emp_direccion )
	{
		this.emp_direccion = emp_direccion;
	}

	// --- METODOS GET --- //
	public String getEmp_id()
	{
		return emp_id;
	}

	public String getEmp_paterno()
	{
		return emp_paterno;
	}

	public String getEmp_materno()
	{
		return emp_materno;
	}

	public String getEmp_nombre()
	{
		return emp_nombre;
	}

	public String getEmp_ciudad()
	{
		return emp_ciudad;
	}

	public String getEmp_direccion()
	{
		return emp_direccion;
	}	
}
