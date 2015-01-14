package pe.jcbv.wilson.cliente.layer.view.util;

import javax.swing.JOptionPane;

public class MessageWin
{
	public static void showError( String message )
	{
		JOptionPane.showMessageDialog( null, message, "ERROR", JOptionPane.ERROR_MESSAGE );
	}
	
	public static void showInfo( String message )
	{
		JOptionPane.showMessageDialog( null, message, "MENSAJE", JOptionPane.INFORMATION_MESSAGE );
	}
	
}
