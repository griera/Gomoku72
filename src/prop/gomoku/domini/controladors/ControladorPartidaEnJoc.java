package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;

/**
 * Controlador de domini encarregat del control de partides en joc. Abstrau les interaccions més comuns que els
 * controladors de presentació realitzen amb els models de dades necessaris per a duur a terme una partida completa
 */
public class ControladorPartidaEnJoc
{
	/**
	 * Partida que controlem
	 */
	private PartidaGomoku partida;
	/**
	 * Fila de l'últim moviment realitzat a la partida
	 */
	int fila_ult_moviment;
	/**
	 * Col·luma de l'últim moviment realitzat a la partida
	 */
	int columna_ult_moviment;

	/**
	 * Variable que, en cas de ser necessària, s'utilitzarà per al còmput de moviments de la IA jugant amb fitxes negres
	 */
	InteligenciaCPU ia_negres;

	/**
	 * Variable que, en cas de ser necessària, s'utilitzarà per al còmput de moviments de la IA jugant amb fitxes
	 * blanques
	 */
	InteligenciaCPU ia_blanques;

	/**
	 * Constructora on s'indica la partida que volem controlar
	 * 
	 * @param partida Partida a controlar
	 */
	public ControladorPartidaEnJoc( PartidaGomoku partida )
	{
		this.partida = partida;
		this.fila_ult_moviment = 0;
		this.columna_ult_moviment = 0;

		// Anàlisi i inicialització de les IAs en cas de ser necessari
		TipusUsuari tipus_negres = partida.getJugadorA().getTipus();
		switch ( tipus_negres )
		{
			case CONVIDAT:
			case HUMA:
				break;
			default:
				ia_negres = new InteligenciaCPU( tipus_negres, EstatCasella.JUGADOR_A );
				break;
		}
		TipusUsuari tipus_blanques = partida.getJugadorA().getTipus();
		switch ( tipus_blanques )
		{
			case CONVIDAT:
			case HUMA:
				break;
			default:
				ia_negres = new InteligenciaCPU( tipus_blanques, EstatCasella.JUGADOR_B );
				break;
		}

	}

	/**
	 * Mètode consultor de la partida
	 * 
	 * @return La partida controlada
	 */
	public PartidaGomoku getPartida()
	{
		return this.partida;
	}

	/**
	 * Mètode consultor del jugador actual (al que li toca moure fitxa)
	 * 
	 * @return Jugador al qual li toca moure
	 */
	public UsuariGomoku getJugadorActual()
	{
		int torn_actual = this.getTornActual();
		if ( torn_actual % 2 == 1 )
		{
			return (UsuariGomoku) this.getPartida().getJugadorA();
		}
		else
		{
			return (UsuariGomoku) this.getPartida().getJugadorB();
		}
	}

	/**
	 * Mètode consultor del torn actual
	 * 
	 * @return Número que representa el torn actual
	 */
	public int getTornActual()
	{
		return this.partida.getTornsJugats() + 1;
	}

	/**
	 * Mètode consultor del moviment òptim per al jugador màquina (si n'hi ha) a l'actual situació de la partida
	 * 
	 * @return Moviment òptim segons l'algorisme que implementa IAGomoku amb <em>rofunditat_maxima</em>
	 * @throws IllegalArgumentException en cas de demanar un moviment quan la partida està al torn d'un jugador que és
	 *         humà
	 */
	public int[] getMovimentMaquina() throws IllegalArgumentException
	{
		UsuariGomoku jugador_actual = this.getJugadorActual();
		TipusUsuari tipus = jugador_actual.getTipus();
		if ( tipus == TipusUsuari.CONVIDAT || tipus == TipusUsuari.HUMA )
		{
			throw new IllegalArgumentException( "No es una màquina" );
		}

		InteligenciaCPU ia = new InteligenciaCPU( tipus, this.getColorActual() );
		return ia.getMoviment( this.partida, this.fila_ult_moviment, this.columna_ult_moviment );
	}

	/**
	 * Mètode consultor del color del jugador al que li toca moure
	 * 
	 * @return Representació del "color"/jugador
	 */
	public EstatCasella getColorActual()
	{
		if ( this.getTornActual() % 2 == 1 )
		{
			return EstatCasella.JUGADOR_A;
		}
		else
		{
			return EstatCasella.JUGADOR_B;
		}
	}

	// TODO completar informació del que fa aquesta classe
	/**
	 * Mètode per a moure una fitxa a la partida. Avança el nombre de torns jugats en conseqüència i comprova l'estat de
	 * la partida com a resultat del moviment
	 * 
	 * @param fitxa El color/jugador de la fitxa que volem col·locar a la casella
	 * @param fila Índex que indica la fila on volem posicionar la fitxa
	 * @param columna Índex que indica la col·lumna on volem posicionar la fitxa
	 * @return Estat en el que queda la partida un cop moguda la fitxa
	 */
	public EstatPartida mouFitxa( EstatCasella fitxa, int fila, int columna )
	{
		this.getPartida().getTauler().mouFitxa( fitxa, fila, columna );
		this.fila_ult_moviment = fila;
		this.columna_ult_moviment = columna;

		EstatPartida estat_partida = this.partida.comprovaEstatPartida( fila, columna );
		if ( estat_partida == EstatPartida.NO_FINALITZADA )
		{
			this.partida.incrementaTornsJugats( 1 );
		}
		else
		{
			// Es realitzen les gestions de finalització de partida
			this.partida.setFinalitzada( true );

			UsuariGomoku jugador_a = this.partida.getJugadorA();
			UsuariGomoku jugador_b = this.partida.getJugadorB();

			if ( estat_partida == EstatPartida.EMPAT )
			{
				jugador_a.incrementaEmpats( jugador_b.getTipus() );
				jugador_b.incrementaEmpats( jugador_a.getTipus() );
			}
			else if ( estat_partida == EstatPartida.GUANYA_JUGADOR_A )
			{
				jugador_a.incrementaVictories( jugador_b.getTipus() );
				jugador_b.incrementaDerrotes( jugador_a.getTipus() );
			}
			else if ( estat_partida == EstatPartida.GUANYA_JUGADOR_B )
			{
				jugador_a.incrementaDerrotes( jugador_b.getTipus() );
				jugador_b.incrementaVictories( jugador_a.getTipus() );
			}

			actualitzaUsuarisNoConvidats();

		}
		return estat_partida;
	}

	/**
	 * Mètode que actualitza les dades a disc dels usuaris implicats a la partida, sempre que aquests no siguin
	 * convidats
	 * 
	 * @return <em>true</em> si l'operació ha tingut èxit (independenment del nombre de convidats participant a la
	 *         partida); <em>false</em> en cas contrari
	 */
	private boolean actualitzaUsuarisNoConvidats()
	{
		// Si els usuaris no són convidats, es guarden les seves dades a disc
		UsuariGomoku jugador_a = partida.getJugadorA();
		UsuariGomoku jugador_b = partida.getJugadorB();

		ControladorUsuari ctrl_usuari = new ControladorUsuari();
		boolean estat_a = true;
		if ( jugador_a.getTipus() != TipusUsuari.CONVIDAT )
		{
			estat_a = ctrl_usuari.actualitzaUsuari( jugador_a );
		}
		boolean estat_b = true;
		if ( jugador_b.getTipus() != TipusUsuari.CONVIDAT )
		{
			estat_b = ctrl_usuari.actualitzaUsuari( jugador_b );
		}

		return estat_a && estat_b;
	}

	/**
	 * Mètode que permet guardar a disc la partida actual en l'estat en que es troba al moment de la crida
	 * 
	 * @return <em>true</em> si la partida s'ha guardat amb èxit; <em>false</em> en cas contrari
	 */
	public boolean guardaPartida()
	{
		ControladorPartidesGuardades ctrl_pg = new ControladorPartidesGuardades();
		return ctrl_pg.guardaPartida( this.partida );
	}

	/**
	 * Mètode per consultar les coordenades de l'últim moviment realitzat a l'actual partida des de que el controlador
	 * implícit s'encarrega del seu control
	 * 
	 * @return Coordenades de l'últim moviment realitzat des de que s'ha iniciat el control de la partida
	 */
	public int[] getUltimMoviment()
	{
		int[] coord = new int[2];
		coord[0] = this.fila_ult_moviment;
		coord[1] = this.columna_ult_moviment;
		return coord;
	}
}
