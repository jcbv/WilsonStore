package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import pe.jcbv.wilson.cliente.domain.Articulo;
import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.layer.controller.ArticuloController;
import pe.jcbv.wilson.cliente.layer.controller.ClienteController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;

import java.awt.Color;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CUDArticulo extends JDialog
{
	private JTextField txtCodigoArt;
	private JTextField txtNombreArt;
	private JTextField txtDescripcionArt;
	private JTextField txtStockArt;
	private JTextField txtPCostoArt;
	private JTextField txtPctganArt;
	private JTextField txtPVentaArt;
	private JTextField txtPOfertaArt;
	private JButton btnCUD;
	private JComboBox cboCategoria;
	private String codigo = null;

	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable() {
			public void run()
			{
				try
				{
					CUDArticulo dialog = new CUDArticulo();
					dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
					dialog.setVisible( true );
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public CUDArticulo()
	{
		setTitle( Mantenimiento.accion + " ARTICULO" );
		setBounds( 100, 100, 475, 417 );
		getContentPane().setLayout( null );
		
		JLabel lblCdigo = new JLabel( "CODIGO :" );
		lblCdigo.setForeground( Color.WHITE );
		lblCdigo.setHorizontalAlignment( SwingConstants.RIGHT );
		lblCdigo.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblCdigo.setBounds( 29, 30, 93, 15 );
		getContentPane().add( lblCdigo );
		
		JLabel lblNombre = new JLabel( "NOMBRE :" );
		lblNombre.setForeground( Color.WHITE );
		lblNombre.setHorizontalAlignment( SwingConstants.RIGHT );
		lblNombre.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblNombre.setBounds( 29, 56, 93, 15 );
		getContentPane().add( lblNombre );
		
		JLabel lblDescripcin = new JLabel("DESCRIPCION :");
		lblDescripcin.setForeground(Color.WHITE);
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescripcin.setBounds(29, 82, 93, 15);
		getContentPane().add(lblDescripcin);
		
		JLabel lblStock = new JLabel("STOCK :");
		lblStock.setForeground(Color.WHITE);
		lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStock.setBounds(29, 108, 93, 15);
		getContentPane().add(lblStock);
		
		JLabel lblPrecioCosto = new JLabel("PRECIO COSTO :");
		lblPrecioCosto.setForeground(Color.WHITE);
		lblPrecioCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioCosto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioCosto.setBounds(10, 134, 112, 15);
		getContentPane().add(lblPrecioCosto);
		
		JLabel lblGanancia = new JLabel("% GANANCIA :");
		lblGanancia.setForeground(Color.WHITE);
		lblGanancia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGanancia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGanancia.setBounds(20, 160, 102, 15);
		getContentPane().add(lblGanancia);
		
		JLabel lblPrecioVenta = new JLabel("PRECIO VENTA :");
		lblPrecioVenta.setForeground(Color.WHITE);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioVenta.setBounds(10, 186, 112, 15);
		getContentPane().add(lblPrecioVenta);
		
		JLabel lblPrecioOferta = new JLabel("PRECIO PFERTA :");
		lblPrecioOferta.setForeground(Color.WHITE);
		lblPrecioOferta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioOferta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioOferta.setBounds(10, 212, 112, 15);
		getContentPane().add(lblPrecioOferta);
		
		JLabel lblCategora = new JLabel("CATEGORIA :");
		lblCategora.setForeground(Color.WHITE);
		lblCategora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCategora.setBounds(29, 238, 93, 15);
		getContentPane().add(lblCategora);
		
		txtCodigoArt = new JTextField();
		txtCodigoArt.setEditable(false);
		txtCodigoArt.setBounds(132, 27, 147, 20);
		getContentPane().add(txtCodigoArt);
		txtCodigoArt.setColumns(10);
		
		txtNombreArt = new JTextField();
		txtNombreArt.setColumns(10);
		txtNombreArt.setBounds(132, 53, 284, 20);
		getContentPane().add(txtNombreArt);
		
		txtDescripcionArt = new JTextField();
		txtDescripcionArt.setColumns(10);
		txtDescripcionArt.setBounds(132, 79, 284, 20);
		getContentPane().add(txtDescripcionArt);
		
		txtStockArt = new JTextField();
		txtStockArt.setColumns(10);
		txtStockArt.setBounds(132, 105, 147, 20);
		getContentPane().add(txtStockArt);
		
		txtPCostoArt = new JTextField();
		txtPCostoArt.setColumns(10);
		txtPCostoArt.setBounds(132, 131, 147, 20);
		getContentPane().add(txtPCostoArt);
		
		txtPctganArt = new JTextField();
		txtPctganArt.setColumns(10);
		txtPctganArt.setBounds(132, 157, 147, 20);
		getContentPane().add(txtPctganArt);
		
		txtPVentaArt = new JTextField();
		txtPVentaArt.setEditable( false );
		txtPVentaArt.setColumns( 10 );
		txtPVentaArt.setBounds( 132, 183, 147, 20 );
		getContentPane().add( txtPVentaArt );
		
		txtPOfertaArt = new JTextField();
		txtPOfertaArt.setEditable( false );
		txtPOfertaArt.setColumns( 10 );
		txtPOfertaArt.setBounds( 132, 209, 147, 20 );
		getContentPane().add( txtPOfertaArt );
		
		cboCategoria = new JComboBox( Mantenimiento.categoria );
		cboCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboCategoria.setBounds( 132, 235, 147, 20 );
		getContentPane().add( cboCategoria );
		
		btnCUD = new JButton( Mantenimiento.accion );
		btnCUD.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				String accion = Mantenimiento.accion;
				
				if( accion.equals( "REGISTRAR" ) )
					accionRegistrar();
				else if( accion.equals( "EDITAR" ) )
					accionEditar();
				else if( accion.equals( "ELIMINAR" ) )
					accionEliminar();
			}
		});
		btnCUD.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		btnCUD.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnCUD.setBounds( 84, 306, 103, 39 );
		getContentPane().add( btnCUD );
		
		JButton btnCancelar = new JButton( "CANCELAR" );
		btnCancelar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				dispose();
			}
		});
		btnCancelar.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		btnCancelar.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnCancelar.setBounds( 271, 306, 103, 39 );
		getContentPane().add( btnCancelar );
		
		JLabel lblNewLabel = new JLabel( "" );
		lblNewLabel.setIcon( new ImageIcon(CUDArticulo.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")) );
		lblNewLabel.setBounds( 0, 0, 459, 379 );
		getContentPane().add( lblNewLabel );
	}
	
	public void accionRegistrar()
	{
		try
		{
			ArticuloController controller = new ArticuloController();
			Articulo articulo = new Articulo();
			
			double pCosto = Double.parseDouble( txtPCostoArt.getText() );
			double pctGanancia = Double.parseDouble( txtPctganArt.getText() ); 
			
			articulo.setArt_nombre( txtNombreArt.getText() );
			articulo.setArt_descrip( txtDescripcionArt.getText() );
			articulo.setArt_stock( Integer.parseInt( txtStockArt.getText() ) );
			articulo.setArt_pcosto( pCosto );
			articulo.setArt_pctgan(  pctGanancia );
			articulo.setArt_pventa();
			articulo.setArt_poferta();
			
			for( int i = 0 ; i < Mantenimiento.categoria.length ; i++ )
			{
				if( cboCategoria.getSelectedIndex() == i )
					articulo.setCat_id( Mantenimiento.categoriaId[ i ] );
			}
				
			controller.insertar( articulo );
			actualizarLista();
			MessageWin.showInfo( "ARTICULO REGISTRADO" );
			dispose();
		}
		catch( Exception e2 )
		{
			MessageWin.showError( e2.getMessage() );
		}
	}
	
	private void accionEditar()
	{
		try
		{
			Articulo articulo = new Articulo();
			
			double pCosto = Double.parseDouble( txtPCostoArt.getText() );
			double pctGanancia = Double.parseDouble( txtPctganArt.getText() ); 
			
			articulo.setArt_id( codigo );
			articulo.setArt_nombre( txtNombreArt.getText() );
			articulo.setArt_descrip( txtDescripcionArt.getText() );
			articulo.setArt_stock( Integer.parseInt( txtStockArt.getText() ) );
			articulo.setArt_pcosto( pCosto );
			articulo.setArt_pctgan( pctGanancia ); 
			articulo.setArt_pventa();
			articulo.setArt_poferta();
			
			for( int i = 0 ; i < Mantenimiento.categoria.length ; i++ )
			{
				if( cboCategoria.getSelectedIndex() == i )
					articulo.setCat_id( Mantenimiento.categoriaId[ i ] );
			}
		
			ArticuloController controller = new ArticuloController();
			if( codigo == null )
			{
				controller.insertar( articulo );
			}
			else
			{
				controller.actualizar( articulo );
				actualizarLista();
			}
			
			MessageWin.showInfo( "ARTICULO EDITADO" );
			dispose();
		}
		catch( Exception e2 )
		{
			MessageWin.showError( e2.getMessage() );
		}
	}
	
	public void accionEliminar()
	{
		String codigo = txtCodigoArt.getText();
		ArticuloController controller = new ArticuloController();
		controller.eliminar( codigo );
		actualizarLista();
		MessageWin.showInfo( "ARTICULO ELIMINADO" );
		dispose();
	}
	
	public void actualizarLista()
	{
		ArticuloController controller = new ArticuloController();
		List< Articulo > lista = controller.consultarPorNombre( "" );
		limpiarTabla();
		mostrarLista( lista );
	}
	
	private void limpiarTabla()
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) Mantenimiento.tableArt.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarLista( List<Articulo> lista )
	{
		String catId = null;
		
		for( int i = 0 ; i < Mantenimiento.categoriaId.length ; i++ )
		{
			if( cboCategoria.getSelectedIndex() == i )
				catId = Mantenimiento.categoriaId[ i ];
		}
		
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) Mantenimiento.tableArt.getModel();
		
		for( Articulo a : lista )
		{
			if( a.getCat_id().equals( catId ) )
			{
				Object[] rowData = { a.getArt_id(), a.getArt_nombre(), a.getArt_descrip(), a.getArt_stock(),
									 a.getArt_pcosto(), a.getArt_pctgan(), a.getArt_pventa(),
									 a.getArt_poferta(), a.getCat_id() };
	
				tabla.addRow( rowData );
			}
		}
		
		if( lista.size() > 0 )
			Mantenimiento.tableArt.setRowSelectionInterval( 0 , 0 );
	}
	
	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
		
		if( codigo != null )
			cargarArticulo();
	}
	
	private void cargarArticulo()
	{
		try
		{
			ArticuloController controller = new ArticuloController();
			Articulo articulo = controller.consultarPorCodigo( codigo );
			
			if( articulo == null )
			{
				this.codigo = null;
			}
			else
			{
				txtCodigoArt.setText( articulo.getArt_id() );
				txtNombreArt.setText( articulo.getArt_nombre() );
				txtDescripcionArt.setText( articulo.getArt_descrip() );
				txtStockArt.setText( String.valueOf( articulo.getArt_stock() ) );
				txtPCostoArt.setText( String.valueOf( articulo.getArt_pcosto() ) );
				txtPctganArt.setText( String.valueOf( articulo.getArt_pctgan() ) );
				txtPVentaArt.setText( String.valueOf( articulo.getArt_pventa() ) );
				txtPOfertaArt.setText( String.valueOf( articulo.getArt_poferta() ) );
				
				for( int i = 0 ; i < Mantenimiento.categoriaId.length ; i++ )
				{
					if( articulo.getCat_id().equals( Mantenimiento.categoriaId[ i ] ) )
						cboCategoria.setSelectedIndex( i );	
				}
				
			}
		}
		catch( Exception e )
		{
			this.codigo = null;
		}
	}
}
