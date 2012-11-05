package prop.gomoku.domini.models;

import java.util.Date;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.Usuari;
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
		if (fila >= this.tauler.getMida()) {
			throw new IndexOutOfBoundsException("Fila indicada fora del tauler");
		}
		if (columna >= this.tauler.getMida()) {
			throw new IndexOutOfBoundsException("Col·lumna indicada fora del tauler");
		}
		
		return null;
	}

}
