package pe.jcbv.wilson.cliente.layer.dao.espec;

import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;

public interface DaoConsulta
{
	List< ConsultaPorArticulo > consultarPorArticulo( String fechaInicio, String fechaFinal );
	List< ConsultaPorCategoria > consultarPorCategoria( String fechaInicio, String fechaFinal );
}
