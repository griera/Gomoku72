package prop.gomoku.tests.taulervisual;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.presentacio.ControladorPresentacio;


public class ProvaTaulerGUI extends JFrame
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 5094949518314170984L;
	private TaulerGUI tauler;
	private ControladorPresentacio controlador_presentacio;
	public ProvaTaulerGUI()
	{
		initComponents();
	}

	private void initComponents()
	{
		tauler=getTauler();
		GroupLayout taulerLayout = new GroupLayout( tauler );
		tauler.setLayout( taulerLayout );
		taulerLayout.setHorizontalGroup( taulerLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGap( 0, 349, Short.MAX_VALUE ) );
		taulerLayout.setVerticalGroup( taulerLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGap( 0, 349, Short.MAX_VALUE ) );

		GroupLayout layout = new GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent( tauler, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 176, Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addComponent( tauler, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 13, Short.MAX_VALUE ) ) );
		
	}
	
	private TaulerGUI getTauler(){
		TaulerGUI taulernou = null;
		if(this.tauler==null){
			 taulernou = new TaulerGUI(15,true);
		}
		return taulernou;
	}

	public void main( )
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				ProvaTaulerGUI tauler = new ProvaTaulerGUI();
				tauler.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
				tauler.pack();
				tauler.setVisible( false );
			}
		} );
	}
	public void setPartida(PartidaGomoku partida){
		this.tauler.setPartida( partida );
	}	
	public void activa(){
		
		this.setSize( 600,600 );
		this.setLocationRelativeTo( null );
		this.setVisible(true);
	}
	public TaulerGUI getTaulerActual(){
		return tauler;
	}
}
