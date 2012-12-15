package prop.gomoku.tests.taulervisual;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProvaTaulerFrame
{
	public static void main( String[] args )
	{
		FramePartidaEnCurs frame = new FramePartidaEnCurs();
		TaulerGUI tauler = new TaulerGUI(15, true);
//		JPanel tauler = new JPanel();
		tauler.setPreferredSize( new Dimension( 500, 500 ) );
		frame.add( tauler );
		frame.setVisible( true );
	}
}
