package prop.gomoku.domini.models;

import java.util.Date;

public class PartidaGomoku extends Partida
{
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
