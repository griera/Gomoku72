package prop.gomoku.domini.controladors;

import prop.gomoku.domini.models.EstatCasella;
import prop.gomoku.domini.models.EstatPartida;
import prop.gomoku.domini.models.Partida;
import prop.gomoku.domini.models.Tauler;

/**
 * 
 * La classe <code>InteligenciaArtificial</code> proporciona una implementaci� est�ndard de l'algorisme MiniMax amb
 * l'optimitzaci� de poda alfa-beta. Per a m�s informaci� sobre el funcionament de l'algorisme poden consultar aquest <a
 * href=http://www.lsi.upc.edu/~bejar/heuristica/docmin.html>enlla�</a>
 * 
 */
public abstract class InteligenciaArtificial
{
	/**
	 * Calcula quin �s el valor m�xim dels dos par�metres enters que rep el m�tode
	 * 
	 * @param a variable de tipus enter
	 * @param b variable de tipus enter
	 * @return Retorna el valor m�xim dels par�metres enters <code>a</code> i <code>b</code>
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
	 * Calcula quin �s el valor m�nim dels dos par�metres enters que rep el m�tode
	 * 
	 * @param a variable de tipus enter
	 * @param b variable de tipus enter
	 * @return Retorna el valor m�nim dels par�metres enters <code>a</code> i <code>b</code>
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
	 * Avalua un objecte de la classe <code>Tauler</code> seguint l'heur�sitca que s'implementi
	 * 
	 * @param tauler Objecte de la classe <code>Tauler</code> sobre el qual es disputa una partida
	 * @param estat_moviment Descriu en quin estat ha quedat <em>tauler</em> en funci� de l'�ltim moviment efectuat
	 *        sobre aquest
	 * @param profunditat �s la profunditat a la que s'ha arribat durant l'exploraci� de les diferents possibilitats de
	 *        moviment. Cada unitat de <em>profunditat</em> representa un torn jugat de la partida
	 * @param fitxa_jugador Indica el jugador de la partida a partir del qual avaluar <em>tauler</em>
	 * @return Retorna un enter indicant l'avaulaci� de <em>tauler</em>
	 */
	public abstract int funcioAvaluacio( Tauler tauler, EstatPartida estat_moviment, int profunditat,
			EstatCasella fitxa_jugador );

	/**
	 * Donat un tauler amb una certa situaci� que pren durant una partida i la fitxa del jugador que ha de moure durant
	 * el torn actual, calcula quina �s la millor posici� del tauler on realitzar el seg�ent moviment, seguint
	 * l'algorisme MiniMax. Com que per aconseguir aquest c�lcul �s necessari generar una estructura arb�ria on cada
	 * nivell representa el pr�xim torn i, en mateix nivell, es generen tots els possibles moviments a realtzar, tamb�
	 * cal tenir un l�mit que trunqui la cerca, per evitar que el cost temporal del MiniMax augmenti exponencialment.
	 * 
	 * @param tauler Objecte de la classe <code>Tauler</code>, sobre el qual es disputa una partida.
	 * @param estat_casella Representa la fitxa del jugador que ha de disputar el torn actual de la partida.
	 * @param profunditat_max Representa el nivell l�mit en la cerca arb�ria del moviment �ptim.
	 * @return Retorna la posici� del tauler �ptima on el jugador controlat per aquesta intel�lig�ncia artificial ha de
	 *         fer el seu moviment. La posici� ve representada per les seves dues coordenades dins de <em>tauler</em>
	 *         (n�mero de fila i n�mero de col�lumna).
	 */

	public int[] minimax( Partida partida, EstatCasella estat_casella, int profunditat_max )
	{
		int[] millorMoviment = { -1, -1 };
		int maxim, maxim_actual;
		Tauler tauler = partida.getTauler();
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
				if (tauler.mouFitxa( estat_casella, i, j)) {
					EstatPartida estat_partida = partida.comprovaEstatPartida( i, j );
					if ( estat_partida == EstatPartida.GUANYA_JUGADOR_A
							|| estat_partida == EstatPartida.GUANYA_JUGADOR_B
							|| estat_partida == EstatPartida.EMPAT )
					{
						estat_casella = this.intercanviaEstatCasella( estat_casella );
						maxim_actual = this.valorMax( tauler, estat_partida, Integer.MIN_VALUE, Integer.MAX_VALUE, estat_casella,
								profunditat + 1, profunditat_max, fitxa_jugador );
						if ( maxim_actual > maxim )
						{
							maxim = maxim_actual;
							millorMoviment[0] = i;
							millorMoviment[1] = j;
						}
						tauler.treuFitxa( i, j);
						estat_casella = intercanviaEstatCasella( estat_casella );
					}
				}
			}
		}
		return millorMoviment;
	}

	private int valorMax( Tauler tauler, EstatPartida estat_partida, int alfa, int beta, EstatCasella estat_casella,
			int profunditat, int profunditat_max, EstatCasella fitxa_jugador )
	{
		if ( profunditat == profunditat_max || estat_partida == EstatPartida.GUANYA_JUGADOR_A
				|| estat_partida == EstatPartida.GUANYA_JUGADOR_B
				|| estat_partida == EstatPartida.EMPAT )
		{
			return funcioAvaluacio( tauler, estat_partida, profunditat, fitxa_jugador );
		}
		else
		{
			int m = tauler.getMida();
			for ( int i = 0; i < m; ++i )
			{
				for ( int j = 0; j < m; ++j )
				{
					if (tauler.mouFitxa( estat_casella, i, j)) {
						if ( estat_partida == EstatPartida.GUANYA_JUGADOR_A
								|| estat_partida == EstatPartida.GUANYA_JUGADOR_B
								|| estat_partida == EstatPartida.EMPAT )
						{
							estat_casella = intercanviaEstatCasella( estat_casella );
							alfa = max( alfa, this.valorMin( tauler, estat_partida, alfa, beta, estat_casella, ( profunditat + 1 ),
									profunditat_max, fitxa_jugador ) );
							tauler.treuFitxa( i, j);
							if ( alfa >= beta )
							{
								return beta;
							}
							estat_casella = intercanviaEstatCasella( estat_casella );
						}
					}
				}
			}
			return alfa;
		}
	}

	private int valorMin( Tauler tauler, EstatPartida estat_partida, int alfa, int beta, EstatCasella estat_casella,
			int profunditat, int profunditat_max, EstatCasella fitxa_jugador )
	{
		if ( profunditat == profunditat_max || estat_partida == EstatPartida.GUANYA_JUGADOR_A
				|| estat_partida == EstatPartida.GUANYA_JUGADOR_B
				|| estat_partida == EstatPartida.EMPAT )
		{
			return funcioAvaluacio( tauler, estat_partida, profunditat, fitxa_jugador );
		}
		else
		{
			int m = tauler.getMida();
			for ( int i = 0; i < m; ++i )
			{
				for ( int j = 0; j < m; ++j )
				{
					if(tauler.mouFitxa( estat_casella, i, j )) {
							
						if ( estat_partida == EstatPartida.GUANYA_JUGADOR_A
								|| estat_partida == EstatPartida.GUANYA_JUGADOR_B
								|| estat_partida == EstatPartida.EMPAT )
						{
							estat_casella = this.intercanviaEstatCasella( estat_casella );
							beta = min( beta, this.valorMax( tauler, estat_partida, alfa, beta, estat_casella, ( profunditat + 1 ),
									profunditat_max, fitxa_jugador ) );
							tauler.treuFitxa( i, j);
							if ( alfa >= beta )
							{
								return alfa;
							}
							estat_casella = this.intercanviaEstatCasella( estat_casella );
						}
					}
				}
			}
			return beta;
		}
	}
}
