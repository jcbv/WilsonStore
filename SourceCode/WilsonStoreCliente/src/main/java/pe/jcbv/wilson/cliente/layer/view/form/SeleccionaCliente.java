package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.layer.controller.ClienteController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Color;

public class SeleccionaCliente extends JDialog
{
	private JTextField txtNombreCli;
	private JTable tablaCli;
	private JButton btnBuscar;

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
					SeleccionaCliente dialog = new SeleccionaCliente();
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
	public SeleccionaCliente()
	{
		setTitle("SELECCIONA CLIENTE");
		setBounds( 100, 100, 803, 360 );
		getContentPane().setLayout(null);
		
		JLabel lblNombreDelCliente = new JLabel("NOMBRE CLIENTE");
		lblNombreDelCliente.setForeground(Color.WHITE);
		lblNombreDelCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreDelCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelCliente.setBounds(50, 51, 145, 14);
		getContentPane().add(lblNombreDelCliente);
		
		txtNombreCli = new JTextField();
		txtNombreCli.setBounds(215, 47, 200, 23);
		getContentPane().add(txtNombreCli);
		txtNombreCli.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 767, 198);
		getContentPane().add(scrollPane);
		
		tablaCli = new JTable();
		tablaCli.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Ap. Paterno", "Ap. Materno", "Ciudad", "Direcci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaCli.getColumnModel().getColumn(0).setResizable(false);
		tablaCli.getColumnModel().getColumn(0).setPreferredWidth(45);
		scrollPane.setViewportView( tablaCli );
		
		btnBuscar = new JButton( "" );
		btnBuscar.setIcon(new ImageIcon(SeleccionaCliente.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/buscar.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBuscar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accionBuscarCliente();
			}
		});
		btnBuscar.setBounds( 448, 30, 56, 56 );
		getContentPane().add( btnBuscar );
		
		JButton btnAceptar = new JButton( "" );
		btnAceptar.setIcon(new ImageIcon(SeleccionaCliente.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/aceptar.png")));
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAceptar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				accionAceptar();
			}
		});
		btnAceptar.setBounds(538, 30, 56, 56);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton( "" );
		btnCancelar.setIcon(new ImageIcon(SeleccionaCliente.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cancelar.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				dispose();
			}
		});
		btnCancelar.setBounds(628, 30, 56, 56);
		getContentPane().add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SeleccionaCliente.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")));
		lblNewLabel.setBounds(0, 0, 787, 322);
		getContentPane().add(lblNewLabel);

	}
	
	public void accionBuscarCliente()
	{
		try
		{		
			String nombre = txtNombreCli.getText();
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
		tabla = ( DefaultTableModel ) tablaCli.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarListaCliente( List< Cliente > lista )
	{	
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) tablaCli.getModel();
		
		for( Cliente a : lista )
		{
			Object[] rowData = { a.getCli_id(), a.getCli_nombre(), a.getCli_paterno(),
					 			 a.getCli_materno(), a.getCli_ciudad(), a.getCli_direccion() };

			tabla.addRow( rowData );
		}
		
		if( lista.size() > 0 )
			tablaCli.setRowSelectionInterval( 0, 0 );
	}
	
	public void accionAceptar()
	{
		if( tablaCli.getRowCount() == 0 )
		{
			MessageWin.showError( "LA TABLA NO TIENE FILAS." );
			return;
		}
		
		int fila = tablaCli.getSelectedRow();
		
		if( fila == -1 )
		{
			MessageWin.showError( "NINGUNA FILA SELECCIONADA." );
			return;
		}
		
		Procesos.codigoCli = tablaCli.getValueAt( fila, 0 ).toString();
		String nombre = tablaCli.getValueAt( fila, 1 ).toString();
		String paterno = tablaCli.getValueAt( fila, 2 ).toString();
		String materno = tablaCli.getValueAt( fila, 3 ).toString();
		
		Procesos.txtNombreCli.setText( nombre + " " + paterno + " " + materno );
		
		dispose();
	}
}
