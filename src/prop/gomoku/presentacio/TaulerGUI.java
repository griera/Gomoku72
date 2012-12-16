package prop.gomoku.presentacio;

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
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;
import prop.gomoku.domini.models.PartidaGomoku;

public class TaulerGUI extends JPanel
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 599174304255843905L;

	// private static int nombre_clics = 0;
	private ImageIcon casella_buida, fitxa_negra, fitxa_blanca, tauler_fons;
	private boolean tipusTauler;
	private CasellaGUI[][] caselles;
	private ControladorPresentacio controlador_presentacio;
	private PartidaGomoku partida;
	private ControladorPartidaEnJoc ctrl_en_joc;
	private EstatPartida estat = EstatPartida.NO_FINALITZADA;

	public TaulerGUI()
	{
		initComponents();
	}

	public TaulerGUI( int mida, boolean tipus )
	{
		initComponents();
		int x, y;
		setLayout( new GridLayout( mida, mida ) );
		this.tipusTauler = tipus;
		this.carregaImatges();
		this.caselles = new CasellaGUI[mida][mida];
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				this.caselles[i][j] = new CasellaGUI( this );
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
		this.casella_buida = TaulerGUI.carregaFons( "/prop/gomoku/recursos/casella_buida.gif" );
		this.fitxa_negra = TaulerGUI.carregaFons( "/prop/gomoku/recursos/fitxa_negra.png" );
		this.fitxa_blanca = TaulerGUI.carregaFons( "/prop/gomoku/recursos/fitxa_blanca.png" );
		this.tauler_fons = TaulerGUI.carregaFons( "/prop/gomoku/recursos/tauler_fons.JPG" );
	}

	protected static ImageIcon carregaFons( String ruta )
	{
		URL localizacio = TaulerGUI.class.getResource( ruta );
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

	public int[] getCoordenades( CasellaGUI casella )
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

	public CasellaGUI[][] getCasillas()
	{
		return this.caselles;
	}

	public void setCasillas( CasellaGUI[][] caselles )
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

	public boolean intentaFerMoviment( int[] coord )
	{
		boolean moviment = controlador_presentacio.ferMoviment( coord );
		return moviment;
	}

	public void setPartida( PartidaGomoku partida )
	{
		this.partida = partida;
		ctrl_en_joc = new ControladorPartidaEnJoc( this.partida );
	}

	public PartidaGomoku getPartida()
	{
		return partida;
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;
	}

	public void bloquejacasella( int i, int j )
	{
		this.caselles[i][j].setEnabled( false );
		this.caselles[i][j].setFocusable( false );
	}

}
