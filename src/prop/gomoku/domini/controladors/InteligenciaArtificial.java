package prop.gomoku.domini.controladors;

import prop.gomoku.domini.models.EstatCasella;
import prop.gomoku.domini.models.EstatMoviment;
import prop.gomoku.domini.models.Tauler;

/**
 * 
 * La classe <code>InteligenciaArtificial</code> proporciona una implementació estàndard de l'algorisme MiniMax amb
 * l'optimització de poda alfa-beta. Per a més informació sobre el funcionament de l'algorisme poden consultar aquest <a
 * href=http://www.lsi.upc.edu/~bejar/heuristica/docmin.html>enllaç</a>
 * 
 */
public abstract class InteligenciaArtificial
{
	/**
	 * Calcula quin és el valor màxim dels dos paràmetres enters que rep el mètode
	 * 
	 * @param a variable de tipus enter
	 * @param b variable de tipus enter
	 * @return Retorna el valor màxim dels paràmetres enters <code>a</code> i <code>b</code>
	 */
	private int max( int a, int b )
	{
		if ( a < b )
		{
			return b;
		}
		return a;
	}

	/**
	 * Calcula quin és el valor mínim dels dos paràmetres enters que rep el mètode
	 * 
	 * @param a variable de tipus enter
	 * @param b variable de tipus enter
	 * @return Retorna el valor mínim dels paràmetres enters <code>a</code> i <code>b</code>
	 */
	private int min( int a, int b )
	{
		if ( a < b )
		{
			return a;
		}
		return b;
	}

	/**
	 * Intercanvia l'estat d'una casella ocupada del tauler
	 * 
	 * @param estat Representa l'estat de la casella ocupada que es vol intercanviar
	 * @return Retorna l'estat contrari a l'estat de la casella <em>estat</em>
	 */
	private EstatCasella intercanviaEstatCasella( EstatCasella estat )
	{
		if ( estat == EstatCasella.JUGADOR_A )
		{
			return EstatCasella.JUGADOR_B;
		}
		return EstatCasella.JUGADOR_A;
	}

	/**
	 * Avalua un objecte de la classe <code>Tauler</code> seguint l'heurísitca que s'implementi
	 * 
	 * @param tauler Objecte de la classe <code>Tauler</code> sobre el qual es disputa una partida
	 * @param estat_moviment Descriu en quin estat ha quedat <em>tauler</em> en funció de l'últim moviment efectuat
	 *        sobre aquest
	 * @param profunditat És la profunditat a la que s'ha arribat durant l'exploració de les diferents possibilitats de
	 *        moviment. Cada unitat de <em>profunditat</em> representa un torn jugat de la partida
	 * @param fitxa_jugador Indica el jugador de la partida a partir del qual avaluar <em>tauler</em>
	 * @return Retorna un enter indicant l'avaulació de <em>tauler</em>
	 */
	public abstract int funcioAvaluacio( Tauler tauler, EstatMoviment estat_moviment, int profunditat,
			EstatCasella fitxa_jugador );

	/**
	 * Donat un tauler amb una certa situació que pren durant una partida i la fitxa del jugador que ha de moure durant
	 * el torn actual, calcula quina és la millor posició del tauler on realitzar el següent moviment, seguint
	 * l'algorisme MiniMax. Com que per aconseguir aquest càlcul és necessari generar una estructura arbòria on cada
	 * nivell representa el pròxim torn i, en mateix nivell, es generen tots els possibles moviments a realtzar, també
	 * cal tenir un límit que trunqui la cerca, per evitar que el cost temporal del MiniMax augmenti exponencialment.
	 * 
	 * @param tauler Objecte de la classe <code>Tauler</code>, sobre el qual es disputa una partida.
	 * @param estat_casella Representa la fitxa del jugador que ha de disputar el torn actual de la partida.
	 * @param profunditat_max Representa el nivell límit en la cerca arbòria del moviment òptim.
	 * @return Retorna la posició del tauler òptima on el jugador controlat per aquesta intel·ligència artificial ha de
	 *         fer el seu moviment. La posició ve representada per les seves dues coordenades dins de <em>tauler</em>
	 *         (número de fila i número de col·lumna).
	 */

	public int[] minimax( Tauler tauler, EstatCasella estat_casella, int profunditat_max )
	{
		int[] millorMoviment = { -1, -1 };
		int maxim, maxim_actual;
		maxim = Integer.MIN_VALUE;
		int m = tauler.getMida();
		int profunditat = 0;
		EstatCasella fitxa_jugador = EstatCasella.JUGADOR_B;
		if ( estat_casella == EstatCasella.JUGADOR_A )
		{
			fitxa_jugador = EstatCasella.JUGADOR_A;
		}

		for ( int i = 0; i < m; ++i )
		{
			for ( int j = 0; j < m; ++j )
			{
				EstatMoviment em = tauler.setCasella( i, j, estat_casella );
				if ( em == EstatMoviment.GUANYADOR || em == EstatMoviment.EMPAT || em == EstatMoviment.VALID )
				{
					estat_casella = this.intercanviaEstatCasella( estat_casella );
					maxim_actual = this.valorMax( tauler, em, Integer.MIN_VALUE, Integer.MAX_VALUE, estat_casella,
							profunditat + 1, profunditat_max, fitxa_jugador );
					if ( maxim_actual > maxim )
					{
						maxim = maxim_actual;
						millorMoviment[0] = i;
						millorMoviment[1] = j;
					}
					tauler.setCasella( i, j, EstatCasella.BUIT );
					estat_casella = intercanviaEstatCasella( estat_casella );
				}
			}
		}
		return millorMoviment;
	}

	private int valorMax( Tauler tauler, EstatMoviment estat_moviment, int alfa, int beta, EstatCasella estat_casella,
			int profunditat, int profunditat_max, EstatCasella fitxa_jugador )
	{
		if ( profunditat == profunditat_max || estat_moviment == EstatMoviment.GUANYADOR
				|| estat_moviment == EstatMoviment.EMPAT )
		{
			return funcioAvaluacio( tauler, estat_moviment, profunditat, fitxa_jugador );
		}
		else
		{
			int m = tauler.getMida();
			for ( int i = 0; i < m; ++i )
			{
				for ( int j = 0; j < m; ++j )
				{
					EstatMoviment em = tauler.setCasella( i, j, estat_casella );
					if ( em == EstatMoviment.GUANYADOR || em == EstatMoviment.EMPAT || em == EstatMoviment.VALID )
					{
						estat_casella = intercanviaEstatCasella( estat_casella );
						alfa = max( alfa, this.valorMin( tauler, em, alfa, beta, estat_casella, ( profunditat + 1 ),
								profunditat_max, fitxa_jugador ) );
						tauler.setCasella( i, j, EstatCasella.BUIT );
						if ( alfa >= beta )
						{
							return beta;
						}
						estat_casella = intercanviaEstatCasella( estat_casella );
					}
				}
			}
			return alfa;
		}
	}

	private int valorMin( Tauler tauler, EstatMoviment estat_moviment, int alfa, int beta, EstatCasella estat_casella,
			int profunditat, int profunditat_max, EstatCasella fitxa_jugador )
	{
		if ( profunditat == profunditat_max || estat_moviment == EstatMoviment.GUANYADOR
				|| estat_moviment == EstatMoviment.EMPAT )
		{
			return funcioAvaluacio( tauler, estat_moviment, profunditat, fitxa_jugador );
		}
		else
		{
			int m = tauler.getMida();
			for ( int i = 0; i < m; ++i )
			{
				for ( int j = 0; j < m; ++j )
				{
					EstatMoviment em = tauler.setCasella( i, j, estat_casella );
					if ( em == EstatMoviment.GUANYADOR || em == EstatMoviment.EMPAT || em == EstatMoviment.VALID )
					{
						estat_casella = this.intercanviaEstatCasella( estat_casella );
						beta = min( beta, this.valorMax( tauler, em, alfa, beta, estat_casella, ( profunditat + 1 ),
								profunditat_max, fitxa_jugador ) );
						tauler.setCasella( i, j, EstatCasella.BUIT );
						if ( alfa >= beta )
						{
							return alfa;
						}
						estat_casella = this.intercanviaEstatCasella( estat_casella );
					}
				}
			}
			return beta;
		}
	}
}
