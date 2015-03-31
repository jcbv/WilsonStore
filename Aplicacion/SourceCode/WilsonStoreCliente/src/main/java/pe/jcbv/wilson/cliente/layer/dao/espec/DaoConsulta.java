package pe.jcbv.wilson.cliente.layer.dao.espec;

import java.util.Date;
import java.util.List;
import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;

public interface DaoConsulta
{
	List< ConsultaPorArticulo > consultarPorArticulo( Date fechaInicio, Date fechaFinal );
	List< ConsultaPorCategoria > consultarPorCategoria( Date fechaInicio, Date fechaFinal );
}
