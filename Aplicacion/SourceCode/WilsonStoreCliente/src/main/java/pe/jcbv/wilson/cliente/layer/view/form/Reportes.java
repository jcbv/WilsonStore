package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pe.jcbv.wilson.cliente.domain.ConsultaPorArticulo;
import pe.jcbv.wilson.cliente.domain.ConsultaPorCategoria;
import pe.jcbv.wilson.cliente.layer.controller.ConsultaController;
import pe.jcbv.wilson.cliente.layer.controller.ExportXLSController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;
import pe.jcbv.wilson.cliente.layer.controller.ExportCSVController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class ConsultaVentas extends JDialog
{
	private ObservingTextField txtFechaI;
	private ObservingTextField txtFechaF;
	private JButton btnBuscar;
	private JTable tablaArt;
	private JButton btnXLS;
	private JButton btnCSV;
	private JButton btnSalir;
	private JScrollPane scrollPane_1;
	private JTable tablaCat;
	private JButton btnNewButton;
	private JPanel panelArt;
	private JPanel panelCat;
	private JButton btnFechaI;
	private JButton btnFechaF;
	private JLabel lblNewLabel;

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
					ConsultaVentas dialog = new ConsultaVentas();
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
	public ConsultaVentas()
	{
		setTitle("CONSULTA DE VENTAS");
		setBounds( 100, 100, 732, 477 );
		getContentPane().setLayout(null);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDesde.setBounds(83, 26, 46, 14);
		getContentPane().add(lblDesde);
		
		txtFechaI = new ObservingTextField();
		txtFechaI.setHorizontalAlignment(SwingConstants.CENTER);
		txtFechaI.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFechaI.setBounds(83, 43, 86, 23);
		getContentPane().add(txtFechaI);
		txtFechaI.setColumns(10);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHasta.setBounds(257, 26, 46, 14);
		getContentPane().add(lblHasta);
		
		txtFechaF = new ObservingTextField();
		txtFechaF.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFechaF.setColumns(10);
		txtFechaF.setBounds(257, 43, 86, 23);
		getContentPane().add(txtFechaF);
		
		btnBuscar = new JButton( "" );
		btnBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBuscar.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/buscar.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				if( panelArt.isShowing() )
					accionBuscarArt();
				
				if( panelCat.isShowing() )
					accionBuscarCat();
			}
		});
		btnBuscar.setBounds(380, 26, 56, 56);
		getContentPane().add(btnBuscar);
		
		btnXLS = new JButton( "" );
		btnXLS.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnXLS.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/excel.png")));
		btnXLS.setToolTipText("Excel");
		btnXLS.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				if( panelArt.isShowing() )
					accionExportarXLSArt();
				
				if( panelCat.isShowing() )
					accionExportarXLSCat();
			}
		});
		btnXLS.setBounds(460, 26, 56, 56);
		getContentPane().add(btnXLS);
		
		btnCSV = new JButton( "" );
		btnCSV.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCSV.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/csv.png")));
		btnCSV.setToolTipText("CSV");
		btnCSV.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				if( panelArt.isShowing() )
					accionExportarCSVArt();
				
				if( panelCat.isShowing() )
					accionExportarCSVCat();
			}
		});
		btnCSV.setBounds( 540, 26, 56, 56 );
		getContentPane().add( btnCSV );
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 114, 696, 314);
		getContentPane().add(tabbedPane);
		
		panelArt = new JPanel();
		tabbedPane.addTab("ARTICULO", null, panelArt, null);
		panelArt.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelArt.add(scrollPane, BorderLayout.CENTER);
		
		tablaArt = new JTable();
		tablaArt.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Cant.", "P. Unit.", "Importe"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaArt.getColumnModel().getColumn(0).setResizable(false);
		tablaArt.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablaArt.getColumnModel().getColumn(1).setResizable(false);
		tablaArt.getColumnModel().getColumn(1).setPreferredWidth(180);
		tablaArt.getColumnModel().getColumn(2).setResizable(false);
		tablaArt.getColumnModel().getColumn(2).setPreferredWidth(20);
		tablaArt.getColumnModel().getColumn(3).setResizable(false);
		tablaArt.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablaArt.getColumnModel().getColumn(4).setResizable(false);
		tablaArt.getColumnModel().getColumn(4).setPreferredWidth(50);
		scrollPane.setViewportView(tablaArt);
		
		panelCat = new JPanel();
		tabbedPane.addTab("CATEGORIA", null, panelCat, null);
		panelCat.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 691, 226);
		panelCat.add(scrollPane_1);
		
		tablaCat = new JTable();
		tablaCat.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"NOMBRE", "CANT.", "IMPORTE"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaCat.getColumnModel().getColumn(0).setResizable(false);
		tablaCat.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaCat.getColumnModel().getColumn(1).setResizable(false);
		tablaCat.getColumnModel().getColumn(1).setPreferredWidth(30);
		tablaCat.getColumnModel().getColumn(2).setResizable(false);
		scrollPane_1.setViewportView(tablaCat);
		
		btnNewButton = new JButton("GRAFICO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				
				crearGrafico( obtenerListaCategoria() );
			}
		});
		btnNewButton.setBounds(541, 237, 89, 23);
		panelCat.add(btnNewButton);
		
		btnSalir = new JButton( "" );
		btnSalir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSalir.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cerrar.png")));
		btnSalir.setToolTipText("Salir");
		btnSalir.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				dispose();
			}
		});
		btnSalir.setBounds(620, 26, 56, 56);
		getContentPane().add(btnSalir);
		
		btnFechaI = new JButton("");
		btnFechaI.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFechaI.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/calend.png")));
		btnFechaI.setToolTipText("Fecha inicial");
		btnFechaI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String lang = null;
				
				final Locale locale = getLocale( lang );
				DatePicker dp = new DatePicker( txtFechaI, locale );
				Date selectedDate = dp.parseDate( txtFechaI.getText() );
				dp.setSelectedDate(selectedDate);
				dp.start( txtFechaI );
			}
		});
		btnFechaI.setBounds(38, 42, 35, 25);
		getContentPane().add(btnFechaI);
		
		btnFechaF = new JButton("");
		btnFechaF.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFechaF.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/calend.png")));
		btnFechaF.setToolTipText("Fecha final");
		btnFechaF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String lang = null;
				
				final Locale locale = getLocale( lang );
				DatePicker dp = new DatePicker( txtFechaF, locale );
				Date selectedDate = dp.parseDate( txtFechaF.getText() );
				dp.setSelectedDate(selectedDate);
				dp.start( txtFechaF );
			}
		});
		btnFechaF.setBounds(212, 42, 35, 25);
		getContentPane().add(btnFechaF);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ConsultaVentas.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")));
		lblNewLabel.setBounds(0, 0, 716, 439);
		getContentPane().add(lblNewLabel);

	}
	
	// ------------------------------ //
	// ----> TABLA DE ARTICULOS <---- //
	// ------------------------------ //
	public void accionBuscarArt()
	{
		try
		{		
			String fechaI = txtFechaI.getText();
			String fechaF = txtFechaF.getText();
			ConsultaController controller = new ConsultaController();
			List< ConsultaPorArticulo > lista = controller.consultarPorArticulo( fechaI, fechaF );
			limpiarTablaArt();
			mostrarListaArt( lista );
		}
		catch( Exception e )
		{
			MessageWin.showError( e.getMessage() );
		}
	}
	
	private void limpiarTablaArt()
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tablaArt.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarListaArt( List< ConsultaPorArticulo > lista )
	{	
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tablaArt.getModel();
		
		for( ConsultaPorArticulo a : lista )
		{
			Object[] rowData = { a.getCodigoArt(), a.getNombreArt(), a.getCantArt(),
								 a.getPrecioArt(), a.getImporteArt() };

			tabla.addRow( rowData );
		}
		
		if( lista.size() > 0 )
			tablaArt.setRowSelectionInterval( 0, 0 );
	}
	
	// ------------------------------ //
	// ----> TABLA DE CATEGORIA <---- //
	// ------------------------------ //
	public void accionBuscarCat()
	{
		try
		{		
			String fechaI = txtFechaI.getText();
			String fechaF = txtFechaF.getText();
			ConsultaController controller = new ConsultaController();
			List< ConsultaPorCategoria > lista = controller.consultarPorCategoria( fechaI, fechaF );
			limpiarTablaCat();
			mostrarListaCat( lista );
		}
		catch( Exception e )
		{
			MessageWin.showError( e.getMessage() );
		}
	}
	
	private void limpiarTablaCat()
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tablaCat.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarListaCat( List< ConsultaPorCategoria > lista )
	{	
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tablaCat.getModel();
		
		for( ConsultaPorCategoria c : lista )
		{
			Object[] rowData = { c.getNombreCat(), c.getCantCat(), c.getImporteCat() };

			tabla.addRow( rowData );
		}
		
		if( lista.size() > 0 )
			tablaCat.setRowSelectionInterval( 0, 0 );
	}
	
	// -------------------------------- //
	// ---> EXPORTAR A FORMATO CSV <--- //
	// -------------------------------- //
	public void accionExportarCSVArt()
	{
		ExportCSVController controller = new ExportCSVController();
		List< ConsultaPorArticulo > lista = new ArrayList< ConsultaPorArticulo >();
		
		for( int i = 0 ; i < tablaArt.getRowCount() ; i++ )
		{
			ConsultaPorArticulo a = new ConsultaPorArticulo();
			
			a.setCodigoArt( tablaArt.getValueAt( i, 0 ).toString() );
			a.setNombreArt( tablaArt.getValueAt( i, 1 ).toString() );
			a.setCantArt( ( int ) tablaArt.getValueAt( i, 2) );
			a.setPrecioArt( ( double ) tablaArt.getValueAt( i, 3 ) );
			a.setImporteArt( ( double ) tablaArt.getValueAt( i, 4 ) );
			
			lista.add( a );
		}
		
		controller.exportarCSVArt( lista, "D:/articulo.csv" );
		MessageWin.showInfo( "ARCHIVO CSV CREADO" );
	}
	
	public void accionExportarCSVCat()
	{
		ExportCSVController controller = new ExportCSVController();
		List< ConsultaPorCategoria > lista = new ArrayList< ConsultaPorCategoria >();
		
		for( int i = 0 ; i < tablaCat.getRowCount() ; i++ )
		{
			ConsultaPorCategoria c = new ConsultaPorCategoria();
			
			c.setNombreCat( tablaCat.getValueAt( i, 0 ).toString() );
			c.setCantCat( ( int ) tablaCat.getValueAt( i, 1 ) );
			c.setImporteCat( ( double ) tablaCat.getValueAt( i, 2 ) );
			
			lista.add( c );
		}
		
		controller.exportarCSVCat( lista, "D:/categoria.csv" );
		MessageWin.showInfo( "ARCHIVO CSV CREADO" );
	}
	
	// -------------------------------- //
	// ---> EXPORTAR A FORMATO XLS <--- //
	// -------------------------------- //
	public void accionExportarXLSArt()
	{
		ExportXLSController controller = new ExportXLSController();
		List< ConsultaPorArticulo > lista = new ArrayList< ConsultaPorArticulo >();
		
		for( int i = 0 ; i < tablaArt.getRowCount() ; i++ )
		{
			ConsultaPorArticulo a = new ConsultaPorArticulo();
			
			a.setCodigoArt( tablaArt.getValueAt( i, 0 ).toString() );
			a.setNombreArt( tablaArt.getValueAt( i, 1 ).toString() );
			a.setCantArt( ( int ) tablaArt.getValueAt( i, 2) );
			a.setPrecioArt( ( double ) tablaArt.getValueAt( i, 3 ) );
			a.setImporteArt( ( double ) tablaArt.getValueAt( i, 4 ) );
			
			lista.add( a );
		}
		
		controller.exportarXLSArt( lista, "D:/articulo.xls" );
		MessageWin.showInfo( "ARCHIVO XLS CREADO" );
	}
	
	public void accionExportarXLSCat()
	{
		ExportXLSController controller = new ExportXLSController();
		List< ConsultaPorCategoria > lista = new ArrayList< ConsultaPorCategoria >();
		
		for( int i = 0 ; i < tablaCat.getRowCount() ; i++ )
		{
			ConsultaPorCategoria c = new ConsultaPorCategoria();
			
			c.setNombreCat( tablaCat.getValueAt( i, 0 ).toString() );
			c.setCantCat( ( int ) tablaCat.getValueAt( i, 1 ) );
			c.setImporteCat( ( double ) tablaCat.getValueAt( i, 2 ) );
			
			lista.add( c );
		}
		
		controller.exportarXLSCat( lista, "D:/categoria.xls" );
		MessageWin.showInfo( "ARCHIVO XLS CREADO" );
	}
	
	
	private Locale getLocale( String loc )
	{
		if( loc != null && loc.length() > 0 )
			return new Locale( loc );
		else
			return Locale.UK;
	}
	
	// ----------------------------------- //
	// ---> CREA EL GRAFICO DE BARRAS <--- //
	// ----------------------------------- //
	public List< ConsultaPorCategoria > obtenerListaCategoria()
	{
		List< ConsultaPorCategoria > lista = new ArrayList< ConsultaPorCategoria >();
		
		for( int i = 0 ; i < tablaCat.getRowCount() ; i++ )
		{
			ConsultaPorCategoria c = new ConsultaPorCategoria();
			
			c.setNombreCat( tablaCat.getValueAt( i, 0 ).toString() );
			c.setCantCat( ( int ) tablaCat.getValueAt( i, 1 ) );
			c.setImporteCat( ( double ) tablaCat.getValueAt( i, 2 ) );
			
			lista.add( c );
		}
		
		return lista;
	}
	
	
	private void crearGrafico( List< ConsultaPorCategoria > list )
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue( list.get( 0 ).getCantCat(), "", "Línea Blanca" );
		dataset.setValue( list.get( 1 ).getCantCat(), "", "Audio y MP3" );
		dataset.setValue( list.get( 2 ).getCantCat(), "", "Electrodomésticos" );
		dataset.setValue( list.get( 3 ).getCantCat(), "", "TV y Video" );
		dataset.setValue( list.get( 4 ).getCantCat(), "", "Computación" );
		
		JFreeChart chart = ChartFactory.createBarChart3D( "VENTAS POR CATEGORIA",
		"CATEGORIAS", "VENTAS REALIZADAS", dataset, PlotOrientation.VERTICAL, true, true, false );
		
		CategoryPlot plot = chart.getCategoryPlot();
		
		BarRenderer render = ( BarRenderer )plot.getRenderer();
		render.setSeriesPaint( 0, Color.BLUE );
		render.setSeriesPaint( 1, Color.YELLOW );
		chart.setBackgroundPaint(Color.WHITE );

		/*try
		{
			ChartUtilities.saveChartAsPNG(new File("D:/barras.png"), chart, 500, 400);
		}
		catch( IOException e )
		{
			System.out.println("Error al crear el archivo");
		}*/
		
		ChartFrame frame = new ChartFrame( "Gráfico de Barras", chart );
		frame.pack();
		frame.setVisible( true );
	}
	
}
