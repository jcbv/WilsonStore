package pe.jcbv.wilson.cliente.layer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;
import pe.jcbv.wilson.cliente.layer.dao.espec.DaoConsulta;

public class DaoConsultaImpl implements DaoConsulta
{

	@Override
	public List< ConsultaPorArticulo > consultarPorArticulo(
			Date fechaInicio,	Date fechaFinal )
	{
		List< ConsultaPorArticulo > lista = new ArrayList< ConsultaPorArticulo >();
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT a.art_id, a.art_nombre, SUM(d.det_cant), a.art_pventa, SUM(d.det_precio) " +
						 "FROM detalle d " +
						 "JOIN venta v ON v.ven_id = d.ven_id " +
						 "JOIN articulo a ON a.art_id = d.art_id " +
						 "WHERE v.ven_fecha > cast(? as date) AND v.ven_fecha < cast(? as date) " +
						 "GROUP BY a.art_id, a.art_nombre, a.art_pventa " +
						 "ORDER BY 1";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setDate(1, (new java.sql.Date(fechaInicio.getTime())) );
			pstm.setDate(2, (new java.sql.Date(fechaFinal.getTime())) );
			ResultSet rs = pstm.executeQuery();
			
			while( rs.next() )
			{
				ConsultaPorArticulo articulo = currentRowToBean1( rs );
				lista.add( articulo );
			}
			rs.close();
			pstm.close();
		}
		catch( SQLException e )
		{
			throw new RuntimeException( e.getMessage() );
		}
		finally
		{
			try
			{
				conexion.close();
			}
			catch( Exception e2 )
			{
				
			}
		}
		
		return lista;
	}

	@Override
	public List< ConsultaPorCategoria > consultarPorCategoria(
			Date fechaInicio, Date fechaFinal )
	{
		List< ConsultaPorCategoria > lista = new ArrayList< ConsultaPorCategoria >();
		Connection conexion = null;
		
		try
		{
			conexion = AccesoDB.getConnection();
			
			String sql = "SELECT c.cat_nombre, SUM(d.det_cant), SUM(d.det_precio) " +
						 "FROM articulo a " +
						 "JOIN categoria c ON c.cat_id = a.cat_id " +
						 "JOIN detalle d ON d.art_id = a.art_id " +
						 "JOIN venta v ON v.ven_id = d.ven_id " +
						 "WHERE v.ven_fecha > cast(? as date) AND v.ven_fecha < cast(? as date) " +
						 "GROUP BY c.cat_nombre";
			
			PreparedStatement pstm = conexion.prepareStatement( sql );
			pstm.setDate(1, (new java.sql.Date(fechaInicio.getTime())) );
			pstm.setDate(2, (new java.sql.Date(fechaFinal.getTime())) );
			ResultSet rs = pstm.executeQuery();
			
			while( rs.next() )
			{
				ConsultaPorCategoria categoria = currentRowToBean2( rs );
				lista.add( categoria );
			}
			rs.close();
			pstm.close();
		}
		catch( SQLException e )
		{
			throw new RuntimeException( e.getMessage() );
		}
		finally
		{
			try
			{
				conexion.close();
			}
			catch( Exception e2 )
			{
				
			}
		}
		
		return lista;
	}
	
	private ConsultaPorArticulo currentRowToBean1( ResultSet rs ) throws SQLException
	{
		ConsultaPorArticulo articulo = new ConsultaPorArticulo();
		
		articulo.setCodigoArt( rs.getString( 1) );
		articulo.setNombreArt( rs.getString( 2 ) );
		articulo.setCantArt( rs.getInt( 3 ) );
		articulo.setPrecioArt( rs.getDouble( 4 ) );
		articulo.setImporteArt( rs.getDouble( 5 ) );
		
		return articulo;
	}
	
	private ConsultaPorCategoria currentRowToBean2( ResultSet rs ) throws SQLException
	{
		ConsultaPorCategoria categoria = new ConsultaPorCategoria();
		
		categoria.setNombreCat( rs.getString( 1 ) );
		categoria.setCantCat( rs.getInt( 2 ) );
		categoria.setImporteCat( rs.getDouble( 3 ) );
		
		return categoria;
	}
	
	
	public static void main( String[] args )
	{
		DaoConsultaImpl c = new DaoConsultaImpl();
		
		String fi = "13/11/2011";
		String ff = "10/02/2012";
		
		List< ConsultaPorArticulo > art = new ArrayList< ConsultaPorArticulo >();
		List< ConsultaPorCategoria > cat = new ArrayList< ConsultaPorCategoria >();
		
//		art = c.consultarPorArticulo( fi, ff );
//		cat = c.consultarPorCategoria( fi, ff );
		
		System.out.printf( "%10s%60s%10s%12s%12s\n",
				  "CODIGO", "NOMBRE", "CANT.", "P.UNIT", "IMPORTE" );
		
		for( ConsultaPorArticulo a : art )
			System.out.print( a );
		
		System.out.println();
		System.out.println();
		
		System.out.printf( "%20s%10s%12s\n",
				 "CATEGORIA", "CANT.", "IMPORTE" );
		
		for( ConsultaPorCategoria v : cat )
			System.out.print( v );
	}
}
