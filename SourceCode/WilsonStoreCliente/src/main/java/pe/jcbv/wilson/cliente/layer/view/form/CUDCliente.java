package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.layer.controller.ClienteController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CUDCliente extends JDialog
{
	private JTextField txtCodCli;
	private JTextField txtNombreCli;
	private JTextField txtPaternoCli;
	private JTextField txtMaternoCli;
	private JTextField txtCiudadCli;
	private JTextField txtDireccionCli;
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
					CUDCliente dialog = new CUDCliente();
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
	public CUDCliente()
	{
		setTitle( Mantenimiento.accion + " CLIENTE" );
		setBounds( 100, 100, 359, 325 );
		getContentPane().setLayout( null );
		
		JLabel lblCdigo = new JLabel( "CODIGO" );
		lblCdigo.setForeground(Color.WHITE);
		lblCdigo.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblCdigo.setHorizontalAlignment( SwingConstants.RIGHT );
		lblCdigo.setBounds( 29, 30, 93, 15 );
		getContentPane().add( lblCdigo );
		
		JLabel lblNombre = new JLabel( "NOMBRE" );
		lblNombre.setForeground( Color.WHITE );
		lblNombre.setHorizontalAlignment( SwingConstants.RIGHT );
		lblNombre.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblNombre.setBounds( 29, 56, 93, 15 );
		getContentPane().add( lblNombre );
		
		JLabel lblApellidoPaterno = new JLabel( "AP. PATERNO" );
		lblApellidoPaterno.setForeground( Color.WHITE );
		lblApellidoPaterno.setHorizontalAlignment( SwingConstants.RIGHT );
		lblApellidoPaterno.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblApellidoPaterno.setBounds( 29, 82, 93, 15 );
		getContentPane().add( lblApellidoPaterno );
		
		JLabel lblApMaterno = new JLabel( "AP. MATERNO" );
		lblApMaterno.setForeground( Color.WHITE );
		lblApMaterno.setHorizontalAlignment( SwingConstants.RIGHT );
		lblApMaterno.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblApMaterno.setBounds( 29, 108, 93, 15 );
		getContentPane().add( lblApMaterno );
		
		JLabel lblCiudad = new JLabel( "CIUDAD" );
		lblCiudad.setForeground( Color.WHITE );
		lblCiudad.setHorizontalAlignment( SwingConstants.RIGHT );
		lblCiudad.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblCiudad.setBounds( 29, 134, 93, 15 );
		getContentPane().add( lblCiudad );
		
		JLabel lblDireccin = new JLabel( "DIRECCION" );
		lblDireccin.setForeground( Color.WHITE );
		lblDireccin.setHorizontalAlignment( SwingConstants.RIGHT );
		lblDireccin.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		lblDireccin.setBounds( 29, 160, 93, 15 );
		getContentPane().add( lblDireccin );
		
		txtCodCli = new JTextField();
		txtCodCli.setEditable( false );
		txtCodCli.setBounds( 132, 27, 93, 20 );
		getContentPane().add( txtCodCli );
		txtCodCli.setColumns( 10 );
		
		txtNombreCli = new JTextField();
		txtNombreCli.setColumns( 10 );
		txtNombreCli.setBounds( 132, 53, 160, 20 );
		getContentPane().add( txtNombreCli );
		
		txtPaternoCli = new JTextField();
		txtPaternoCli.setColumns( 10 );
		txtPaternoCli.setBounds( 132, 79, 160, 20 );
		getContentPane().add( txtPaternoCli );
		
		txtMaternoCli = new JTextField();
		txtMaternoCli.setColumns( 10);
		txtMaternoCli.setBounds( 132, 105, 160, 20 );
		getContentPane().add( txtMaternoCli );
		
		txtCiudadCli = new JTextField();
		txtCiudadCli.setColumns( 10 );
		txtCiudadCli.setBounds( 132, 131, 160, 20 );
		getContentPane().add( txtCiudadCli );
		
		txtDireccionCli = new JTextField();
		txtDireccionCli.setColumns( 10 );
		txtDireccionCli.setBounds( 132, 157, 160, 20 );
		getContentPane().add( txtDireccionCli );
		
		JButton btnCUD = new JButton( Mantenimiento.accion );
		btnCUD.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
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
		btnCUD.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnCUD.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		btnCUD.setBounds( 46, 216, 110, 39 );
		getContentPane().add( btnCUD );
		
		JButton btnCancelarCli = new JButton( "CANCELAR" );
		btnCancelarCli.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				dispose();
			}
		});
		btnCancelarCli.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
		btnCancelarCli.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnCancelarCli.setBounds( 186, 216, 110, 39 );
		getContentPane().add( btnCancelarCli );
		
		JLabel lblFondo = new JLabel( "" );
		lblFondo.setIcon( new ImageIcon(CUDCliente.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")) );
		lblFondo.setBounds( 0, 0, 343, 287 );
		getContentPane().add( lblFondo );

	}
	
	public void accionRegistrar()
	{
		try
		{
			ClienteController controller = new ClienteController();
			Cliente cliente = new Cliente();
			
			cliente.setCli_nombre( txtNombreCli.getText() );
			cliente.setCli_paterno( txtPaternoCli.getText() );
			cliente.setCli_materno( txtMaternoCli.getText() );
			cliente.setCli_ciudad( txtCiudadCli.getText() );
			cliente.setCli_direccion( txtDireccionCli.getText() );
			
			controller.insertar( cliente );
			actualizarLista();
			MessageWin.showInfo( "CLIENTE REGISTRADO" );
			dispose();
		}
		catch( Exception e2 )
		{
			MessageWin.showError( e2.getMessage() );
		}
	}
	
	public void accionEditar()
	{
		try
		{
			ClienteController controller = new ClienteController();
			Cliente cliente = new Cliente();
			
			cliente.setCli_id( codigo );
			cliente.setCli_nombre( txtNombreCli.getText() );
			cliente.setCli_paterno( txtPaternoCli.getText() );
			cliente.setCli_materno( txtMaternoCli.getText() );
			cliente.setCli_ciudad( txtCiudadCli.getText() );
			cliente.setCli_direccion( txtDireccionCli.getText() );
			
			if( codigo == null )
				controller.insertar( cliente );
			else
			{
				controller.actualizar( cliente );
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
		String codigo = txtCodCli.getText();
		ClienteController controller = new ClienteController();
		controller.eliminar( codigo );
		actualizarLista();
		MessageWin.showInfo( "ARTICULO ELIMINADO" );
		dispose();
	}
	
	public void actualizarLista()
	{
		ClienteController controller = new ClienteController();
		List< Cliente > lista = controller.consultarPorNombre( "" );
		limpiarTabla();
		mostrarLista( lista );
	}
	
	private void limpiarTabla()
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) Mantenimiento.tableCli.getModel();
		tabla.setRowCount( 0 );
	}
	
	public void mostrarLista( List< Cliente > lista )
	{
		DefaultTableModel tabla;
		tabla = ( DefaultTableModel ) Mantenimiento.tableCli.getModel();
		
		for( Cliente c : lista )
		{
			Object[] rowData = { c.getCli_id(), c.getCli_nombre(),
								 c.getCli_paterno(), c.getCli_materno(),
								 c.getCli_ciudad(), c.getCli_direccion() };
			
			tabla.addRow( rowData );
		}
		
		if( lista.size() > 0)
			Mantenimiento.tableCli.setRowSelectionInterval( 0, 0 );
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
			ClienteController controller = new ClienteController();
			Cliente cliente = controller.consultarPorCodigo( codigo );
			
			if( cliente == null )
				this.codigo = null;
			else
			{
				txtCodCli.setText( cliente.getCli_id() );
				txtNombreCli.setText( cliente.getCli_nombre() );
				txtPaternoCli.setText( cliente.getCli_paterno() );
				txtMaternoCli.setText( cliente.getCli_materno() );
				txtCiudadCli.setText( cliente.getCli_ciudad() );
				txtDireccionCli.setText( cliente.getCli_direccion() );
			}
		}
		catch( Exception e )
		{
			this.codigo = null;
		}
	}
}
