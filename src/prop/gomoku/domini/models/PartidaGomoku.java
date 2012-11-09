package prop.gomoku.domini.models;

import java.util.Date;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.Usuari;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;

public class PartidaGomoku extends Partida
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = -2625983311773495229L;

	public PartidaGomoku( Usuari jugador_a, Usuari jugador_b, Tauler tauler, String nom)
	{
		super(jugador_a, jugador_b, tauler, nom);
		// TODO
	}
	
	public PartidaGomoku( Usuari jugador_a, Usuari jugador_b, Tauler tauler, int torns_jugats, Date data_creacio,
			String nom, boolean finalitzada )
	{
		super( jugador_a, jugador_b, tauler, torns_jugats, data_creacio, nom, finalitzada );
		// TODO Auto-generated constructor stub
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
