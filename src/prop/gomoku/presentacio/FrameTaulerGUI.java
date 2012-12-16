package prop.gomoku.presentacio;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;


public class FrameTaulerGUI extends JFrame
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 5094949518314170984L;
	private TaulerGUI tauler;
	private ControladorPresentacio controlador_presentacio;
	public FrameTaulerGUI()
	{
		initComponents();
	}

	private void initComponents()
	{
		this.setTitle( "Gomoku - Partida en joc" );

		JLabel informacio = new JLabel( "INFORMACIO DE LA PARTIDA" );
		informacio.setBounds( 600, 20, 170, 20 );
		this.getContentPane().add( informacio );

		//TODO Capturar un String amb el nom del jugador amb fitxes negres
		JLabel jugador_negre = new JLabel( "Negres: " );
		jugador_negre.setBounds( 600, 70, 170, 20 );
		this.getContentPane().add( jugador_negre );

		//TODO Capturar un String amb el nom del jugador amb fitxes blanques
		JLabel jugador_blanc = new JLabel( "Blanques: " );
		jugador_blanc.setBounds( 600, 90, 170, 20 );
		this.getContentPane().add( jugador_blanc );

		//TODO Capturar un String amb el nombre del torn actual
		JLabel torn_actual = new JLabel( "Torn Actual: " );
		torn_actual.setBounds( 600, 110, 170, 20 );
		this.getContentPane().add( torn_actual );

		//TODO Capturar un String amb el nom del jugador que ha de jugar l'actual torn
		JLabel avis_torn = new JLabel( ", és el seu torn" );
		avis_torn.setBounds( 600, 150, 170, 20 );
		this.getContentPane().add( avis_torn );

		JButton boto_guardar = new JButton( "Guardar Partida" );
		boto_guardar.setBounds( 600, 325, 135, 40 );
		boto_guardar.addMouseListener( new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				botoguardarMouseMouseClicked( event );
			}
		});
		this.getContentPane().add( boto_guardar );

		JButton boto_sortir = new JButton( "Sortir" );
		boto_sortir.setBounds( 600, 405, 135, 40 );
		boto_sortir.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				boto_sortirMouseMouseClicked(event);
			}
		});
		this.getContentPane().add( boto_sortir );

		JButton avancar_torn = new JButton( "Avançar Torn" );
		avancar_torn.setBounds( 600, 485, 135, 40 );
		avancar_torn.setEnabled( true);
		avancar_torn.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				avancar_tornMouseMouseClicked(event);
			}
		});
		this.getContentPane().add( avancar_torn );
		
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
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 276, Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup().addComponent( tauler, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addContainerGap( 53, Short.MAX_VALUE ) ) );
	}
				private void avancar_tornMouseMouseClicked( MouseEvent event )
				{
					controlador_presentacio.juga_maquines();	
				}
				private void botoguardarMouseMouseClicked( MouseEvent event )
				{
					controlador_presentacio.guardaPartida(tauler.getPartida());
				}
				private void boto_sortirMouseMouseClicked(MouseEvent event){
					controlador_presentacio.sincronitzacioPartidaMenu( this );
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
				FrameTaulerGUI tauler = new FrameTaulerGUI();
				tauler.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
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
		this.setResizable( false );
		this.pack();
		this.setVisible(true);
	}
	public TaulerGUI getTaulerActual(){
		return tauler;
	}

	public void pinta( int i, int j, EstatCasella color )
	{
		tauler.pinta( i, j, color );
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio=controlador_presentacio;		
	}
	public void setControladorPresentacioTauler(ControladorPresentacio controlador_presentacio){
		this.tauler.setControladorPresentacio( controlador_presentacio );
	}

	public void pinta()
	{
		for(int i=0; i<controlador_presentacio.getPartidaactual().getTauler().getMida(); ++i){
			for(int j=0; j<controlador_presentacio.getPartidaactual().getTauler().getMida(); ++j){
				tauler.pinta(i,j,controlador_presentacio.getPartidaactual().getTauler().getEstatCasella( i, j ));
			}
		}
		
	}

	public void bloquejaTauler()
	{
		for(int i=0; i<controlador_presentacio.getPartidaactual().getTauler().getMida(); ++i){
			for(int j=0; j<controlador_presentacio.getPartidaactual().getTauler().getMida(); ++j){
					tauler.bloquejacasella(i,j);
			}
		}
	}
	public void setTauler(TaulerGUI tauler){
		 this.tauler=tauler;
	}
}

