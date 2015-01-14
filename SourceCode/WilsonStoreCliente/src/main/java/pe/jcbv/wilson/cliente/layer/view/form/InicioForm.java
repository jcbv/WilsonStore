package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import pe.jcbv.wilson.cliente.layer.controller.InicioController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class InicioForm extends JDialog
{
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					InicioForm dialog = new InicioForm();
					dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
					//dialog.setUndecorated( true );
					dialog.setLocationRelativeTo( null );
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
	public InicioForm()
	{
		setTitle( "WILSON STORE" );
		setBounds( 100, 100, 300, 243 );
		getContentPane().setLayout( null );
		
		JLabel lblUsuario = new JLabel( "Usuario" );
		lblUsuario.setForeground( Color.WHITE );
		lblUsuario.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
		lblUsuario.setBounds( 32, 24, 68, 17 );
		getContentPane().add( lblUsuario );
		
		JLabel lblContrasea = new JLabel( "Contrase\u00F1a" );
		lblContrasea.setForeground( Color.WHITE );
		lblContrasea.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
		lblContrasea.setBounds( 32, 83, 89, 17 );
		getContentPane().add( lblContrasea );
		
		JLabel IconUsuario = new JLabel( "" );
		IconUsuario.setIcon( new ImageIcon( InicioForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/usuario.png" ) ) );
		IconUsuario.setBounds( 234, 44, 28, 28 );
		getContentPane().add( IconUsuario );
		
		JLabel IconClave = new JLabel( "" );
		IconClave.setIcon( new ImageIcon( InicioForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/clave.png" ) ) );
		IconClave.setBounds( 234, 102, 28, 28 );
		getContentPane().add( IconClave );
		
		txtUsuario = new JTextField();
		txtUsuario.setFont( new Font( "Tahoma", Font.PLAIN, 15 ) );
		txtUsuario.setBorder( null );
		txtUsuario.setBounds( 33, 44, 195, 28 );
		getContentPane().add( txtUsuario );
		txtUsuario.setColumns( 10 );
		
		JButton btnIngresar = new JButton( "Ingresar" );
		btnIngresar.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnIngresar.setFont( new Font( "Tahoma", Font.BOLD, 12 ) );
		btnIngresar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				try
				{
					String usuario = txtUsuario.getText();
					String clave = String.valueOf( txtClave.getPassword() );
					
					InicioController controller = new InicioController();
					controller.validar( usuario, clave );
					
					dispose();
					PrincipalForm.main( null );
				}
				catch( Exception e2 )
				{
					MessageWin.showError( e2.getMessage() );
				}
			}
		});
		btnIngresar.setBounds( 35, 153, 89, 30 );
		getContentPane().add( btnIngresar );
		
		JButton btnCerrar = new JButton( "Salir" );
		btnCerrar.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		btnCerrar.setFont( new Font( "Tahoma", Font.BOLD, 12 ) );
		btnCerrar.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				System.exit( 0 );
			}
		});
		btnCerrar.setBounds( 159, 153, 89, 30 );
		getContentPane().add( btnCerrar );
		
		txtClave = new JPasswordField();
		txtClave.setBorder( null );
		txtClave.setBounds( 32, 102, 195, 28 );
		getContentPane().add( txtClave );
		
		JPanel panel = new JPanel();
		panel.setBackground( Color.BLUE );
		panel.setBounds( 0, 0, 284, 205 );
		getContentPane().add( panel );
		panel.setLayout( new BorderLayout( 0, 0 ) );
		
		JLabel lblNewLabel = new JLabel( "" );
		lblNewLabel.setIcon( new ImageIcon( InicioForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/background.jpg" ) ) );
		panel.add( lblNewLabel, BorderLayout.CENTER );

	}
}
