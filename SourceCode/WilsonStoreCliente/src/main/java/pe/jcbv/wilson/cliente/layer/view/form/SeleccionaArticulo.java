package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pe.jcbv.wilson.cliente.domain.Articulo;
import pe.jcbv.wilson.cliente.layer.controller.ArticuloController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class SeleccionaArticulo extends JDialog
{
	private JComboBox cboCategoria;
	private JTextField txtNombreArt;
	private JTextField txtCantidadArt;
	private JTable tableArt;
	private JButton btnAceptar;

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
					SeleccionaArticulo dialog = new SeleccionaArticulo();
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
	public SeleccionaArticulo()
	{
		setTitle( "SELECCIONA ARTICULO" );
		setBounds( 100, 100, 817, 398 );
		getContentPane().setLayout( null );
		
		JLabel lblCategora = new JLabel( "CATEGORIA" );
		lblCategora.setForeground(Color.WHITE);
		lblCategora.setFont( new Font("Tahoma", Font.BOLD, 14) );
		lblCategora.setHorizontalAlignment( SwingConstants.RIGHT );
		lblCategora.setBounds( 79, 26, 157, 14 );
		getContentPane().add( lblCategora );
		
		JLabel lblNombreArtculo = new JLabel( "NOMBRE ARTICULO" );
		lblNombreArtculo.setForeground(Color.WHITE);
		lblNombreArtculo.setHorizontalAlignment( SwingConstants.RIGHT );
		lblNombreArtculo.setFont( new Font("Tahoma", Font.BOLD, 14) );
		lblNombreArtculo.setBounds( 79, 64, 157, 14 );
		getContentPane().add( lblNombreArtculo );
		
		JLabel lblCantidad = new JLabel( "CANTIDAD" );
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setHorizontalAlignment( SwingConstants.RIGHT );
		lblCantidad.setFont( new Font("Tahoma", Font.BOLD, 14) );
		lblCantidad.setBounds( 79, 104, 157, 14 );
		getContentPane().add( lblCantidad );
		
		cboCategoria = new JComboBox( Mantenimiento.categoria );
		cboCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboCategoria.setBounds( 246, 23, 157, 20 );
		getContentPane().add(cboCategoria);
		
		txtNombreArt = new JTextField();
		txtNombreArt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombreArt.setBounds( 246, 60, 157, 23 );
		getContentPane().add( txtNombreArt );
		txtNombreArt.setColumns( 10 );
		
		txtCantidadArt = new JTextField();
		txtCantidadArt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCantidadArt.setColumns( 10 );
		txtCantidadArt.setBounds( 246, 100, 86, 23 );
		getContentPane().add( txtCantidadArt );
		
		JButton btnBuscar = new JButton( "" );
		btnBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBuscar.setIcon(new ImageIcon(SeleccionaArticulo.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/buscar.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accionBuscar();
			}
		});
		btnBuscar.setBounds( 450, 41, 60, 60 );
		getContentPane().add( btnBuscar );
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds( 10, 142, 781, 207 );
		getContentPane().add( scrollPane );
		
		tableArt = new JTable();
		tableArt.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Descripci\u00F3n", "Stock", "P. Costo", "% Ganancia", "P. Venta", "P.Oferta"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Double.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableArt.getColumnModel().getColumn(0).setResizable(false);
		tableArt.getColumnModel().getColumn(0).setPreferredWidth(45);
		tableArt.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableArt.getColumnModel().getColumn(4).setResizable(false);
		tableArt.getColumnModel().getColumn(5).setResizable(false);
		tableArt.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableArt.getColumnModel().getColumn(6).setResizable(false);
		tableArt.getColumnModel().getColumn(7).setResizable(false);
		scrollPane.setViewportView( tableArt );
		
		btnAceptar = new JButton( "" );
		btnAceptar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAceptar.setIcon(new ImageIcon(SeleccionaArticulo.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/aceptar.png")));
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				accionAceptar();
			}
		});
		btnAceptar.setBounds( 540, 43, 56, 56 );
		getContentPane().add( btnAceptar );
		
		JButton btnCancelar = new JButton( "" );
		btnCancelar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.setIcon(new ImageIcon(SeleccionaArticulo.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cancelar.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				dispose();
			}
		});
		btnCancelar.setBounds( 630, 43, 56, 56 );
		getContentPane().add( btnCancelar );
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SeleccionaArticulo.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")));
		lblNewLabel.setBounds(0, 0, 801, 360);
		getContentPane().add(lblNewLabel);

	}
	
	public void accionBuscar()
	{
		try
		{		
			String nombre = txtNombreArt.getText();
			ArticuloController controller = new ArticuloController();
			List< Articulo > lista = controller.consultarPorNombre( nombre );
			limpiarTabla();
			mostrarLista( lista );
		}
		catch( Exception e )
		{
			MessageWin.showError( e.getMessage() );
		}
	}
	
	private void limpiarTabla()
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tableArt.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarLista( List< Articulo > lista )
	{
		String catId = null;
		
		for( int i = 0 ; i < Mantenimiento.categoriaId.length ; i++ )
		{
			if( cboCategoria.getSelectedIndex() == i )
				catId = Mantenimiento.categoriaId[ i ];
		}
		
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tableArt.getModel();
		
		for( Articulo a : lista )
		{
			if( a.getCat_id().equals( catId ) )
			{
				Object[] rowData = { a.getArt_id(), a.getArt_nombre(), a.getArt_descrip(),
						 			 a.getArt_stock(), a.getArt_pcosto(), a.getArt_pctgan(),
						 			 a.getArt_pventa(), a.getArt_poferta(), a.getCat_id() };
	
				tabla.addRow( rowData );
			}			
		}
		
		if( lista.size() > 0 )
			tableArt.setRowSelectionInterval( 0, 0 );
	}
	
	public void accionAceptar()
	{
		if( tableArt.getRowCount() == 0 )
		{
			MessageWin.showError( "LA TABLA NO TIENE FILAS." );
			return;
		}
		
		int fila = tableArt.getSelectedRow();
		
		if( fila == -1 )
		{
			MessageWin.showError( "NINGUNA FILA SELECCIONADA." );
			return;
		}
		
		String codigoArt = tableArt.getValueAt( fila, 0 ).toString();
		String nombreArt = tableArt.getValueAt( fila, 1 ).toString();
		String descriArt = tableArt.getValueAt( fila, 2 ).toString();
		int cantidadArt = Integer.parseInt( txtCantidadArt.getText() );
		double pVentaArt = ( double ) tableArt.getValueAt( fila, 6 );
		double importeArt = cantidadArt * pVentaArt;
		
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) Procesos.tablaVentaArt.getModel();
		
		Object[] rowData = { codigoArt,
							 nombreArt,
							 descriArt,
							 cantidadArt,
							 pVentaArt,
							 importeArt };
		
		tabla.addRow( rowData );
		Procesos.calculaSubTotal();
		Procesos.calculaImpuesto();
		Procesos.calculaTotal();
			
		dispose();
		/*Procesos.codigo = tableArt.getValueAt( fila , 0 ).toString();
		Procesos.cantidad = Integer.parseInt( txtCantidadArt.getText() );*/
	}
}
