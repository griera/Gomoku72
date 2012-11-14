package prop.gomoku.domini.models;

import java.util.Date;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.Usuari;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;

public class PartidaGomoku extends Partida
{
	/**
	 * Mètode contructor d'un objecte de la classe <code>PartidaGomoku</code>. Aquest nou objecte representa una nova
	 * partida al joc de taula Gomoku en la qual juga l'usuari <em>jugador_a</em> contra l'usuari <em>jugador_b</em>,
	 * en un <em>tauler</em> prèviament definit, amb un nombre de <em>torns_jugats</em> ja jugats, creada exactament el
	 * dia i hora <em>data_creacio</em>, amb el nom <em>nom</em>, i si ja ha finalitzat o no segons el valor booleà de
	 * <em>finalitzada</em>.
	 * 
	 * @param jugador_a Objecte de la classe <code>Usuari</code> que juga la partida.
	 * @param jugador_b Objecte de la classe <code>Usuari</code> que juga la partida.
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> on es juga la partida.
	 * @param torns_jugats Nombre de torns que ja s'han disputat en la partida.
	 * @param data_creacio Data en la que s'ha creat la partida.
	 * @param nom Nom identificatiu de la partida.
	 * @param finalitzada Indica si la partida ha acabat o encara continua en joc.
	 */
	public PartidaGomoku( Usuari jugador_a, Usuari jugador_b, TaulerGomoku tauler, int torns_jugats, Date data_creacio,
			String nom, boolean finalitzada )
	{
		super( jugador_a, jugador_b, tauler, torns_jugats, data_creacio, nom, finalitzada );
	}

	/**
	 * Mètode contructor alternatiu d'un objecte de la classe <code>PartidaGomoku</code>. Aquest nou objecte representa
	 * un nova partida al joc de taula Gomoku en la qual juga l'usuari <em>jugador_a</em> contra l'usuari
	 * <em>jugador_b</em>, en un <em>tauler</em> prèviament definit, amb el nom <em>nom</em>. Inicialment aquesta
	 * partida no té cap torn jugat, la data de creació és la mateixa que quan es crea l'objecte de la classe
	 * <code>PartidaGomoku</code> i tampoc està finalitzada.
	 * 
	 * @param jugador_a Objecte de la classe <code>Usuari</code> que juga la partida.
	 * @param jugador_b Objecte de la classe <code>Usuari</code> que juga la partida.
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> on es juga la partida.
	 * @param nom Nom identificatiu de la partida.
	 */
	public PartidaGomoku( Usuari jugador_a, Usuari jugador_b, TaulerGomoku tauler, String nom )
	{
		super( jugador_a, jugador_b, tauler, nom );
	}

	@Override
	public EstatPartida comprovaEstatPartida( int fila, int columna ) throws IndexOutOfBoundsException
	{
		TaulerGomoku tauler = (TaulerGomoku) this.tauler;
		EstatCasella estat = tauler.getEstatCasella( fila, columna );
		if ( fila < 0 || fila >= this.tauler.getMida() || columna < 0 || columna >= this.tauler.getMida() )
		{
			throw new IndexOutOfBoundsException( "Posició indicada fora del tauler" );
		}

		else if ( tauler.teFitxesSeguides( fila, columna, 5, estat ) )
		{
			if ( estat == EstatCasella.JUGADOR_A )
			{
				return EstatPartida.GUANYA_JUGADOR_A;
			}

			return EstatPartida.GUANYA_JUGADOR_B;
		}

		else if ( tauler.getTotalFitxes() == tauler.getMida() * tauler.getMida() )
		{
			return EstatPartida.EMPAT;
		}

		return EstatPartida.NO_FINALITZADA;
	}

}
