package prop.gomoku.tests.taulervisual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.controladors.ControladorPartida;
import prop.gomoku.domini.controladors.ControladorPreparacioPartida;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;

public class TaulerGUIEntrenament extends JPanel
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 599174304255843905L;

	// private static int nombre_clics = 0;
	private ImageIcon casella_buida, fitxa_negra, fitxa_blanca, tauler_fons;
	private boolean tipusTauler;
	private CasellaGUIEntrenament[][] caselles;

	public TaulerGUIEntrenament()
	{
		initComponents();
	}

	public TaulerGUIEntrenament( int mida, boolean tipus )
	{
		initComponents();
		int x, y;
		setLayout( new GridLayout( mida, mida ) );
		this.tipusTauler = tipus;
		this.carregaImatges();
		this.caselles = new CasellaGUIEntrenament[mida][mida];
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				this.caselles[i][j] = new CasellaGUIEntrenament( this );
				this.caselles[i][j].setOpaque( false );
				this.caselles[i][j].setFons( this.casella_buida );
				x = ( i * 35 ) + 1;
				y = ( j * 35 ) + 1;
				this.caselles[i][j].setBounds( x, y, 34, 34 );
				this.add( this.caselles[i][j] );
			}
		}
		this.setFons( this.tauler_fons );
	}

	public boolean getTipusTauler()
	{
		return this.esTipusTauler();
	}

	public void setFons( ImageIcon fons )
	{
		this.tauler_fons = fons;
	}

	public ImageIcon getFons()
	{
		return this.tauler_fons;
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage( tauler_fons.getImage(), 0, 0, this.getWidth(), this.getHeight(), this );
	}

	public void pinta( int fila, int columna, EstatCasella color )
	{
		if ( color == EstatCasella.JUGADOR_A )
		{
			this.caselles[fila][columna].setFons( this.fitxa_negra );
		}

		else if ( color == EstatCasella.JUGADOR_B )
		{
			this.caselles[fila][columna].setFons( this.fitxa_blanca );
		}
		this.repaint();
	}

	private void carregaImatges()
	{
		this.casella_buida = TaulerGUIEntrenament.carregaFons( "casella_buida.gif" );
		this.fitxa_negra = TaulerGUIEntrenament.carregaFons( "fitxa_negra.png" );
		this.fitxa_blanca = TaulerGUIEntrenament.carregaFons( "fitxa_blanca.png" );
		this.tauler_fons = TaulerGUIEntrenament.carregaFons( "tauler_fons.JPG" );
	}

	protected static ImageIcon carregaFons( String ruta )
	{
		URL localizacio = TaulerGUIEntrenament.class.getResource( ruta );
		if ( localizacio != null )
		{
			return new ImageIcon( localizacio );
		}
		else
		{
			System.err.println( "No s'ha trobat l'arxiu: " + ruta );
			return null;
		}
	}

	public int[] getCoordenades( CasellaGUIEntrenament casella )
	{
		int[] coordenades = new int[2];
		for ( int i = 0; i < this.caselles.length; i++ )
		{
			for ( int j = 0; j < this.caselles.length; j++ )
			{
				if ( this.caselles[i][j] == casella )
				{
					coordenades[0] = i;
					coordenades[1] = j;
				}
			}
		}
		return coordenades;
	}

	public CasellaGUIEntrenament[][] getCasillas()
	{
		return this.caselles;
	}

	public void setCasillas( CasellaGUIEntrenament[][] caselles )
	{
		this.caselles = caselles;
	}

	public boolean esTipusTauler()
	{
		return this.tipusTauler;
	}

	public void setTipoTablero( boolean tipusTauler )
	{
		this.tipusTauler = tipusTauler;
	}

	private void initComponents()
	{
		this.setLayout( null );
		this.setBackground( new Color( 255, 255, 255 ) );
		this.setBorder( BorderFactory.createLineBorder( new Color( 0, 0, 0 ) ) );

		// Mida en pixels de la imatge insertada a la casella * nombre de caselles del tauler + 1
		// 35 (pixels/casella) * 15 (caselles/tauler) + 1 = 526
		this.setPreferredSize( new Dimension( 526, 526 ) );

		this.setFons( this.tauler_fons );
	}

	private UsuariGomoku jugador = new UsuariGomoku( "nacho", "nacho" );
	private UsuariGomoku oponent = new UsuariGomoku( "CPU Facil", "cpufacil", TipusUsuari.FACIL );
	private ControladorPartida ctrl_partida = new ControladorPartida();
	private PartidaGomoku partida = ctrl_partida.creaNovaPartida( jugador, jugador, oponent, "demo" );
	private ControladorPreparacioPartida ctrl_prep = new ControladorPreparacioPartida( partida );
	private EstatPartida estat = EstatPartida.NO_FINALITZADA;
	private int[] ultim_moviment = { 0, 0 };

	public boolean intentaFerMoviment( int[] coord )
	{
		if ( estat == EstatPartida.NO_FINALITZADA )
		{
			estat = partida.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
			if ( estat != EstatPartida.NO_FINALITZADA )
			{
				System.out.println( estat );
				return false;
			}
		}
		else
		{
			System.out.println( estat );
			return false;
		}

		int fila = coord[0];
		int columna = coord[1];
		System.out.println( "Clicat: " + fila + " " + columna );

//		ctrl_partida.
		
		if ( ctrl_prep.mouFitxa( fila, columna ) )
		{

			this.pinta( fila, columna, EstatCasella.JUGADOR_A );
			ultim_moviment[0] = fila;
			ultim_moviment[1] = columna;
		}
		
		return true;
	}

}
