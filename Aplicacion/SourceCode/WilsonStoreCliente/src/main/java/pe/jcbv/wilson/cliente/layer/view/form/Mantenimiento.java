package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pe.jcbv.wilson.cliente.domain.Articulo;
import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.layer.controller.ArticuloController;
import pe.jcbv.wilson.cliente.layer.controller.ClienteController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.SwingConstants;

public class Mantenimiento extends JDialog
{
	protected static JComboBox cboCategoria;
	private JTextField txtNomArt;
	private JButton btnBuscarArt;
	private JButton btnRegistrarArt;
	private JButton btnEditarArt;
	private JButton btnEliminarArt;
	private JButton btnSalirArt;
	protected static JTable tableArt;
	private JLabel lblNewLabel;
	protected static String categoria[] = { "Línea Blanca",
		   									"Electrodomésticos",
		   									"TV y Video",
		   									"Audio y MP3",
		   									"Computación" };
	
	protected static String categoriaId[] = { "0001", "0002", "0003", "0004", "0005" };
	private JTextField txtNomCli;
	private JButton btnBuscarCli;
	private JButton btnRegistrarCli;
	private JButton btnEditarCli;
	private JButton btnEliminarCli;
	private JButton btnSalirCli;
	private JScrollPane scrollPaneCli;
	private JScrollPane scrollPane;
	protected static JTable tableCli;
	protected static String accion = null;

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
					Mantenimiento dialog = new Mantenimiento();
					dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
					dialog.setVisible(true);
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
	public Mantenimiento()
	{
		setTitle( "MANTENIMIENTO" );
		setBounds( 100, 100, 950, 504 );
		getContentPane().setLayout( new BorderLayout( 0, 0 ) );
		
		JTabbedPane tabbedPane = new JTabbedPane( JTabbedPane.TOP );
		getContentPane().add( tabbedPane, BorderLayout.CENTER );
		
		JPanel panelArticulo = new JPanel();
		tabbedPane.addTab( "Art\u00EDculos", null, panelArticulo, null );
		panelArticulo.setLayout( null );
		
		cboCategoria = new JComboBox( categoria );
		cboCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboCategoria.setBounds( 212, 33, 200, 25 );
		panelArticulo.add( cboCategoria );
		
		txtNomArt = new JTextField();
		txtNomArt.setBounds( 212, 72, 200, 23 );
		panelArticulo.add( txtNomArt );
		txtNomArt.setColumns( 10 );
		
		JLabel lblCategora = new JLabel( "CATEGORIA" );
		lblCategora.setHorizontalAlignment( SwingConstants.RIGHT );
		lblCategora.setForeground( Color.WHITE );
		lblCategora.setFont( new Font("Tahoma", Font.BOLD, 14) );
		lblCategora.setBounds( 88, 33, 106, 25 );
		panelArticulo.add( lblCategora );
		
		JLabel lblNombreDelArtculo = new JLabel( "NOMBRE ARTICULO" );
		lblNombreDelArtculo.setForeground( Color.WHITE );
		lblNombreDelArtculo.setFont( new Font("Tahoma", Font.BOLD, 14) );
		lblNombreDelArtculo.setBounds( 52, 71, 142, 24 );
		panelArticulo.add( lblNombreDelArtculo );
		
		btnBuscarArt = new JButton( "" );
		btnBuscarArt.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accionBuscar();
			}
		});
		btnBuscarArt.setToolTipText( "Buscar" );
		btnBuscarArt.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnBuscarArt.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/buscar.png")) );
		btnBuscarArt.setBounds( 482, 36, 56, 56 );
		panelArticulo.add( btnBuscarArt );
		
		btnRegistrarArt = new JButton( "" );
		btnRegistrarArt.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				CUDArticulo.main( null );
				accion = "REGISTRAR";
			}
		});
		btnRegistrarArt.setToolTipText( "Registrar" );
		btnRegistrarArt.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnRegistrarArt.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/agregar.png")) );
		btnRegistrarArt.setBounds( 559, 36, 56, 56 );
		panelArticulo.add( btnRegistrarArt );
		
		btnEditarArt = new JButton( "" );
		btnEditarArt.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accion = "EDITAR";
				
				if( tableArt.getRowCount() == 0 )
				{
					MessageWin.showError( "La tabla no tiene filas." );
					return;
				}
				
				int fila = tableArt.getSelectedRow();
				
				if( fila == -1 )
				{
					MessageWin.showError( "No hay ninguna fila seleccionada." );
					return;
				}
				
				String codigo = tableArt.getValueAt( fila , 0 ).toString();
				
				CUDArticulo view = new CUDArticulo();
				view.setModal( true );
				view.setCodigo( codigo );
				view.setVisible( true );
			}
		});
		btnEditarArt.setToolTipText( "Editar" );
		btnEditarArt.setIcon( new ImageIcon( Mantenimiento.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/edit.png" ) ) );
		btnEditarArt.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnEditarArt.setBounds( 636, 36, 56, 56 );
		panelArticulo.add( btnEditarArt );
		
		btnEliminarArt = new JButton( "" );
		btnEliminarArt.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accion = "ELIMINAR";
				
				if( tableArt.getRowCount() == 0 )
				{
					MessageWin.showError( "La tabla no tiene filas." );
					return;
				}
				
				int fila = tableArt.getSelectedRow();
				
				if( fila == -1 )
				{
					MessageWin.showError( "No hay ninguna fila seleccionada." );
					return;
				}
				
				String codigo = tableArt.getValueAt( fila, 0 ).toString();
				
				CUDArticulo view = new CUDArticulo();
				view.setModal( true );
				view.setCodigo( codigo );
				view.setVisible( true );
			}
		});
		btnEliminarArt.setToolTipText( "Eliminar" );
		btnEliminarArt.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/quitar.png")) );
		btnEliminarArt.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnEliminarArt.setBounds( 713, 36, 56, 56 );
		panelArticulo.add( btnEliminarArt );
		
		btnSalirArt = new JButton( "" );
		btnSalirArt.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				dispose();
			}
		});
		btnSalirArt.setToolTipText( "Salir" );
		btnSalirArt.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cerrar.png")) );
		btnSalirArt.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnSalirArt.setBounds( 790, 36, 56, 56 );
		panelArticulo.add( btnSalirArt );
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds( 10, 131, 909, 296 );
		panelArticulo.add( scrollPane );
		
		tableArt = new JTable();
		tableArt.setModel( new DefaultTableModel(
			new Object[][] {
				{ null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Descripci\u00F3n", "Stock", "P. Costo", "Pct. Ganancia", "P. Venta", "P. Oferta", "Categor\u00EDa"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Double.class, Double.class, Double.class, Double.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable( int row, int column ) {
				return columnEditables[ column ];
			}
		});
		tableArt.getColumnModel().getColumn( 0 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 0 ).setPreferredWidth( 30 );
		tableArt.getColumnModel().getColumn( 3 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 3 ).setPreferredWidth( 30 );
		tableArt.getColumnModel().getColumn( 4 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 4 ).setPreferredWidth( 55 );
		tableArt.getColumnModel().getColumn( 5 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 5 ).setPreferredWidth( 60 );
		tableArt.getColumnModel().getColumn( 6 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 6 ).setPreferredWidth( 55 );
		tableArt.getColumnModel().getColumn( 7 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 7 ).setPreferredWidth( 55 );
		tableArt.getColumnModel().getColumn( 8 ).setResizable( false );
		tableArt.getColumnModel().getColumn( 8 ).setPreferredWidth( 45 );
		scrollPane.setViewportView( tableArt );
		
		lblNewLabel = new JLabel( "" );
		lblNewLabel.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")) );
		lblNewLabel.setBounds( 0, 0, 929, 438 );
		panelArticulo.add( lblNewLabel );
		
		JPanel panelCliente = new JPanel();
		tabbedPane.addTab( "Cliente", null, panelCliente, null );
		panelCliente.setLayout(null);
		
		btnBuscarCli = new JButton( "" );
		btnBuscarCli.setToolTipText("Buscar");
		btnBuscarCli.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accionBuscarCliente();
			}
		});
		btnBuscarCli.setIcon(new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/buscar.png")));
		btnBuscarCli.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBuscarCli.setBounds(482, 36, 56, 56);
		panelCliente.add(btnBuscarCli);
		
		btnRegistrarCli = new JButton( "" );
		btnRegistrarCli.setToolTipText("Registrar");
		btnRegistrarCli.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				CUDCliente.main( null );
				accion = "REGISTRAR";
			}
		});
		btnRegistrarCli.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/agregar.png")) );
		btnRegistrarCli.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnRegistrarCli.setBounds( 559, 36, 56, 56 );
		panelCliente.add( btnRegistrarCli );
		
		btnEditarCli = new JButton( "" );
		btnEditarCli.setToolTipText("Editar");
		btnEditarCli.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accion = "EDITAR";
				
				if( tableCli.getRowCount() == 0 )
				{
					MessageWin.showError( "La tabla no tiene filas." );
					return;
				}
				
				int fila = tableCli.getSelectedRow();
				
				if( fila == -1 )
				{
					MessageWin.showError( "No hay ninguna fila seleccionada." );
					return;
				}
				
				String codigo = tableCli.getValueAt( fila, 0 ).toString();
				
				CUDCliente view = new CUDCliente();
				view.setModal( true );
				view.setCodigo( codigo );
				view.setVisible( true );
			}
		});
		btnEditarCli.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnEditarCli.setIcon( new ImageIcon( Mantenimiento.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/edit.png" ) ) );
		btnEditarCli.setBounds( 636, 36, 56, 56 );
		panelCliente.add( btnEditarCli );
		
		btnEliminarCli = new JButton( "" );
		btnEliminarCli.setToolTipText("Eliminar");
		btnEliminarCli.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accion = "ELIMINAR";
				
				if( tableCli.getRowCount() == 0 )
				{
					MessageWin.showError( "La tabla no tiene filas." );
					return;
				}
				
				int fila = tableCli.getSelectedRow();
				
				if( fila == -1 )
				{
					MessageWin.showError( "No hay ninguna fila seleccionada." );
					return;
				}
				
				String codigo = tableCli.getValueAt( fila, 0 ).toString();
				
				CUDCliente view = new CUDCliente();
				view.setModal( true );
				view.setCodigo( codigo );
				view.setVisible( true );
			}
		});
		btnEliminarCli.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/quitar.png")) );
		btnEliminarCli.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnEliminarCli.setBounds( 713, 36, 56, 56 );
		panelCliente.add( btnEliminarCli );
		
		btnSalirCli = new JButton( "" );
		btnSalirCli.setToolTipText("Salir");
		btnSalirCli.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				dispose();
			}
		});
		btnSalirCli.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnSalirCli.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cerrar.png")) );
		btnSalirCli.setBounds( 790, 36, 56, 56 );
		panelCliente.add( btnSalirCli );
		
		txtNomCli = new JTextField();
		txtNomCli.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNomCli.setBounds( 212, 53, 200, 23 );
		panelCliente.add( txtNomCli );
		txtNomCli.setColumns( 10 );
		
		JLabel lblNombreDelCliente = new JLabel( "NOMBRE CLIENTE" );
		lblNombreDelCliente.setForeground( Color.WHITE );
		lblNombreDelCliente.setFont( new Font("Tahoma", Font.BOLD, 14) );
		lblNombreDelCliente.setHorizontalAlignment( SwingConstants.RIGHT );
		lblNombreDelCliente.setBounds( 64, 52, 130, 24 );
		panelCliente.add( lblNombreDelCliente );
		
		scrollPaneCli = new JScrollPane();
		scrollPaneCli.setBounds( 10, 131, 909, 296 );
		panelCliente.add( scrollPaneCli );
		
		tableCli = new JTable();
		tableCli.setModel( new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Apellido Paterno", "Apellido Materno", "Ciudad", "Direcci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCli.getColumnModel().getColumn( 0 ).setResizable( false );
		tableCli.getColumnModel().getColumn( 0 ).setPreferredWidth( 15 );
		tableCli.getColumnModel().getColumn( 5 ).setPreferredWidth( 155 );
		scrollPaneCli.setViewportView( tableCli );
		
		JLabel lblNewLabel_1 = new JLabel( "" );
		lblNewLabel_1.setIcon( new ImageIcon(Mantenimiento.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")) );
		lblNewLabel_1.setBounds( 0, 0, 929, 438 );
		panelCliente.add( lblNewLabel_1 );
	}
	
	private void accionBuscar()
	{
		try
		{		
			String nombre = txtNomArt.getText();
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
		
		for( int i = 0 ; i < categoriaId.length ; i++ )
		{
			if( cboCategoria.getSelectedIndex() == i )
				catId = categoriaId[ i ];
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
	
	// --- CLIENTE --- //
	public void accionBuscarCliente()
	{
		try
		{		
			String nombre = txtNomCli.getText();
			ClienteController controller = new ClienteController();
			List< Cliente > lista = controller.consultarPorNombre( nombre );
			limpiarTablaCliente();
			mostrarListaCliente( lista );
		}
		catch( Exception e )
		{
			MessageWin.showError( e.getMessage() );
		}
	}
	
	private void limpiarTablaCliente()
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tableCli.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarListaCliente( List< Cliente > lista )
	{	
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tableCli.getModel();
		
		for( Cliente a : lista )
		{
			Object[] rowData = { a.getCli_id(), a.getCli_nombre(), a.getCli_paterno(),
					 			 a.getCli_materno(), a.getCli_ciudad(), a.getCli_direccion() };

			tabla.addRow( rowData );
		}
		
		if( lista.size() > 0 )
			tableCli.setRowSelectionInterval( 0, 0 );
	}	
}
