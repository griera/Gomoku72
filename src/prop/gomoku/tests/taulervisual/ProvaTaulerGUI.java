package prop.gomoku.tests.taulervisual;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ProvaTaulerGUI extends JFrame
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 5094949518314170984L;

	public ProvaTaulerGUI()
	{
		initComponents();
	}

	private void initComponents()
	{
		TaulerGUI1 = new TaulerGUI( 15, true );

		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		GroupLayout TaulerGUI1Layout = new GroupLayout( TaulerGUI1 );
		TaulerGUI1.setLayout( TaulerGUI1Layout );
		TaulerGUI1Layout.setHorizontalGroup( TaulerGUI1Layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGap( 0, 349, Short.MAX_VALUE ) );
		TaulerGUI1Layout.setVerticalGroup( TaulerGUI1Layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGap( 0, 349, Short.MAX_VALUE ) );

		GroupLayout layout = new GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent( TaulerGUI1, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 176, Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addComponent( TaulerGUI1, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 13, Short.MAX_VALUE ) ) );
		pack();
	}

	public static void main( String args[] )
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				new ProvaTaulerGUI().setVisible( true );
			}
		} );
	}

	private TaulerGUI TaulerGUI1;

}
