package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

/**
 * Controlador de domini encarregat del control de partides en joc. Abstrau les interaccions més comuns que els
 * controladors de presentació realitzen amb els models de dades necessaris per a duur a terme una partida completa
 */
public class ControladorPartidaEnJoc
{
	/**
	 * Indica la profunditat màxima amb la que es cridarà a IAGomoku
	 */
	private int profunditat_maxima = 3;
	/**
	 * Partida que controlem
	 */
	private PartidaGomoku partida;
	/**
	 * Serà la classe amb la qual obtindrem els moviments del jugador CPU, si s'escau
	 */
	private IAGomoku ia;
	/**
	 * Fila de l'últim moviment realitzat a la partida
	 */
	int fila_ult_moviment;
	/**
	 * Col·luma de l'últim moviment realitzat a la partida
	 */
	int columna_ult_moviment;

	/**
	 * Constructora on s'indica la partida que volem controlar
	 * 
	 * @param partida Partida a controlar
	 */
	public ControladorPartidaEnJoc( PartidaGomoku partida )
	{
		this.partida = partida;
		this.ia = new IAGomoku();
		this.fila_ult_moviment = 0;
		this.columna_ult_moviment = 0;
	}

	/**
	 * Constructora on s'indica els atributs desitjats per a iniciar una partida des de zero
	 * 
	 * @param jugador_a Usuari que farà de jugador A
	 * @param jugador_b Usuari que farà de jugador B
	 * @param nom_partida Nom que se li vol assignar a la nova partida
	 */
	public ControladorPartidaEnJoc( UsuariGomoku jugador_a, UsuariGomoku jugador_b, String nom_partida )
	{
		this.partida = new PartidaGomoku( jugador_a, jugador_b, new TaulerGomoku(), nom_partida );
		this.ia = new IAGomoku();
		this.fila_ult_moviment = 0;
		this.columna_ult_moviment = 0;
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
	 */
	public int[] getMovimentMaquina()
	{
		int[] moviment_ia = new int[2];
		int mida = this.getPartida().getTauler().getMida();

		if ( this.getTornActual() == 1 )
		{
			moviment_ia[0] = ( mida / 2 );
			moviment_ia[1] = ( mida / 2 );
		}

		else if ( this.getTornActual() == 2 )
		{
			moviment_ia = ia.movimentAdjacentAleatori( this.fila_ult_moviment, this.columna_ult_moviment, mida );
		}

		else
		{
			moviment_ia = this.ia.minimax( this.partida, this.getColorActual(), this.profunditat_maxima );
		}

		if ( moviment_ia[0] == -1 && moviment_ia[1] == -1 )
		{
			System.out.print( this.getJugadorActual().getNom() + "s'ha rendit perque ja ha perdut matematicament." );
			System.out.println( " Fara un moviment aleatori:" );
			TaulerGomoku tauler = (TaulerGomoku) this.getPartida().getTauler();
			moviment_ia = ia.movimentAleatori( tauler );
		}

		this.fila_ult_moviment = moviment_ia[0];
		this.columna_ult_moviment = moviment_ia[1];

		return moviment_ia;
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
		// TODO
		this.getPartida().getTauler().mouFitxa( fitxa, fila, columna );
		this.fila_ult_moviment = fila;
		this.columna_ult_moviment = columna;

		EstatPartida estat_partida = this.partida.comprovaEstatPartida( fila, columna );
		if ( estat_partida == EstatPartida.NO_FINALITZADA )
		{
			this.partida.incrementaTornsJugats( 1 );
		}
		return estat_partida;
	}

	public boolean guardaPartida()
	{
		GestorPartidesGuardades gestor = new GestorPartidesGuardades();
		gestor.guardaPartida( partida );
		return false;
	}
}
