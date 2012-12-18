package prop.gomoku.presentacio;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;

/**
 * 
 * @author Genís Riera Pérez
 *
 */
public class FrameTaulerGUIEntrenament extends JFrame
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 5094949518314170984L;
	private TaulerGUI tauler;
	private ControladorPresentacio controlador_presentacio;

	public FrameTaulerGUIEntrenament()
	{
		initComponents();
	}

	private void initComponents()
	{
		this.setTitle( "Gomoku72 - Preparació Partida" );

		JButton boto_comenca = new JButton( "Començar Partida" );
		boto_comenca.setBounds( 600, 325, 155, 40 );
		boto_comenca.addMouseListener( new MouseAdapter()
		{
			public void mouseClicked( MouseEvent event )
			{
				comencaPartidaMouseMouseClicked( event );
			}
		} );
		this.getContentPane().add( boto_comenca );

		JButton boto_sortir = new JButton( "Sortir" );
		boto_sortir.setBounds( 600, 405, 155, 40 );
		boto_sortir.addMouseListener( new MouseAdapter()
		{
			public void mouseClicked( MouseEvent event )
			{
				boto_sortirMouseMouseClicked( event );
			}
		} );
		this.getContentPane().add( boto_sortir );
		
		this.tauler = getTauler();
		GroupLayout taulerLayout = new GroupLayout( tauler );
		this.tauler.setLayout( taulerLayout );
		taulerLayout.setHorizontalGroup( taulerLayout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0,
				349, Short.MAX_VALUE ) );
		taulerLayout.setVerticalGroup( taulerLayout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0,
				349, Short.MAX_VALUE ) );

		GroupLayout layout = new GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent( tauler, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 276, Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addComponent( tauler, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 53, Short.MAX_VALUE ) ) );
	}

	private void comencaPartidaMouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincroEntrenamentPartida( this );
	}

	private void boto_sortirMouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincronitzacioEntrenamentMenu( this );
	}

	private TaulerGUI getTauler()
	{
		TaulerGUI taulernou = null;
		if ( this.tauler == null )
		{
			taulernou = new TaulerGUI( 15, true );
		}
		return taulernou;
	}

	public void main()
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				FrameTaulerGUI tauler = new FrameTaulerGUI();
				tauler.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
				tauler.setVisible( false );
			}
		} );
	}

	public void setPartida( PartidaGomoku partida )
	{
		this.tauler.setPartida( partida );
	}

	public void activa()
	{
		this.setSize( 600, 600 );
		this.setLocationRelativeTo( null );
		this.setResizable( false );
		this.pack();
		this.setVisible( true );
	}

	public TaulerGUI getTaulerActual()
	{
		return tauler;
	}

	public void pinta( int i, int j, EstatCasella color )
	{
		tauler.pinta( i, j, color );
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;
	}

	public void setControladorPresentacioTauler( ControladorPresentacio controlador_presentacio )
	{
		this.tauler.setControladorPresentacio( controlador_presentacio );
	}

	public void pinta()
	{
		int mida = this.controlador_presentacio.getPartidaactual().getTauler().getMida();
		for ( int i = 0; i < mida; ++i )
		{
			for ( int j = 0; j < mida; ++j )
			{
				tauler.pinta( i, j, controlador_presentacio.getPartidaactual().getTauler().getEstatCasella( i, j ) );
			}
		}

	}
}
