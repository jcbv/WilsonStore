package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JFrame;

import pe.jcbv.wilson.cliente.domain.Usuario;
import pe.jcbv.wilson.cliente.layer.view.util.Sesion;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class PrincipalForm
{

	private JFrame frmWilsonStore;

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
					PrincipalForm window = new PrincipalForm();
					window.frmWilsonStore.setVisible( true );
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrincipalForm()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmWilsonStore = new JFrame();
		frmWilsonStore.setTitle( titulo() );
		frmWilsonStore.setBounds( 100, 100, 394, 277 );
		frmWilsonStore.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmWilsonStore.getContentPane().setLayout( null );
		
		JPanel panel = new JPanel();
		panel.setBounds( 0, 0, 116, 239 );
		frmWilsonStore.getContentPane().add( panel );
		panel.setLayout( new GridLayout( 0, 1, 0, 0 ) );
		
		JButton btnProceso = new JButton( "Proceso" );
		btnProceso.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent  e)
			{
				Procesos.main( null );
			}
		});
		btnProceso.setIcon( new ImageIcon( PrincipalForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/proceso.png" ) ) );
		btnProceso.setFont( new Font( "Tahoma", Font.PLAIN, 13 ) );
		btnProceso.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		panel.add( btnProceso );
		
		JButton btnTablas = new JButton( "Tablas" );
		btnTablas.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				Mantenimiento.main( null );
			}
		});
		btnTablas.setIcon( new ImageIcon( PrincipalForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/tablas.png" ) ) );
		btnTablas.setFont( new Font( "Tahoma", Font.PLAIN, 13 ) );
		btnTablas.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		panel.add( btnTablas );
		
		JButton btnConsulta = new JButton( "Consulta" );
		btnConsulta.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ConsultaVentas.main( null );
			}
		});
		btnConsulta.setIcon( new ImageIcon( PrincipalForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/consulta.png" ) ) );
		btnConsulta.setFont( new Font( "Tahoma", Font.PLAIN, 13 ) );
		btnConsulta.setBorder(new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		panel.add( btnConsulta );
		
		JButton btnReporte = new JButton( "Reporte" );
		btnReporte.setIcon( new ImageIcon( PrincipalForm.class.getResource( "/pe/jcbv/wilson/cliente/layer/view/img/reporte.png" ) ) );
		btnReporte.setFont( new Font("Tahoma", Font.PLAIN, 13 ) );
		btnReporte.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		panel.add( btnReporte );
		
		JButton btnSalir = new JButton( "Salir" );
		btnSalir.setIcon( new ImageIcon(PrincipalForm.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/salir.png")) );
		btnSalir.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 )
			{
				System.exit( 0 );
			}
		});
		btnSalir.setFont( new Font( "Tahoma", Font.PLAIN, 13 ) );
		btnSalir.setBorder( new SoftBevelBorder( BevelBorder.RAISED, null, null, null, null ) );
		panel.add( btnSalir );
	}
	
	public String titulo()
	{
		Usuario user = ( Usuario )Sesion.get( "usuario" );
		String titulo = "WILSON STORE [ Usuario : " + user.getUsu_nombre() + " ]";
		
		return titulo;
	}

}
