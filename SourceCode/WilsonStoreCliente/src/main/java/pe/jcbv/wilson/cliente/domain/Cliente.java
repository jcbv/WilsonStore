package pe.jcbv.wilson.cliente.domain;

public class Cliente
{
    private String cli_id;
    private String cli_paterno;
    private String cli_materno;
    private String cli_nombre;
    private String cli_ciudad;
    private String cli_direccion;

    public Cliente()
    {

    }

    // --- METODOS SET --- //
    public void setCli_id( String cli_id )
    {
        this.cli_id = cli_id;
    }

    public void setCli_paterno( String cli_paterno )
    {
        this.cli_paterno = cli_paterno;
    }

    public void setCli_materno( String cli_materno )
    {
        this.cli_materno = cli_materno;
    }

    public void setCli_nombre( String cli_nombre )
    {
        this.cli_nombre = cli_nombre;
    }

    public void setCli_ciudad( String cli_ciudad )
    {
        this.cli_ciudad = cli_ciudad;
    }

    public void setCli_direccion( String cli_direccion )
    {
        this.cli_direccion = cli_direccion;
    }

    // --- METODOS GET --- //
    public String getCli_id()
    {
        return cli_id;
    }

    public String getCli_paterno()
    {
        return cli_paterno;
    }

    public String getCli_materno()
    {
        return cli_materno;
    }

    public String getCli_nombre()
    {
        return cli_nombre;
    }

    public String getCli_ciudad()
    {
        return cli_ciudad;
    }

    public String getCli_direccion()
    {
        return cli_direccion;
    }
}
