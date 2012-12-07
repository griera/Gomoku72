package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;

/**
 * Classe que permet preparar una partida ràpidament, proveïnt una interfície que facilita el posicionament de fitxes en
 * el tauler de la partida
 * 
 * @author Ignacio
 * 
 */
public class ControladorPreparacioPartida
{
	/**
	 * Partida en procés de preparació
	 */
	private PartidaGomoku partida;
	/**
	 * Color de la següent fitxa a col·locar en cas de crida a <em>mouFitxa</em>
	 */
	private EstatCasella color;

	/**
	 * Mètode constructor
	 * 
	 * @param partida Partida la qual es vol preparar
	 */
	public ControladorPreparacioPartida( PartidaGomoku partida )
	{
		this.partida = partida;
		if (partida.getTornsJugats() % 2 == 0)
		{
			this.color = EstatCasella.JUGADOR_A;
		}
		else
		{
			this.color = EstatCasella.JUGADOR_B;
		}
	}

	/**
	 * Mou una fitxa al tauler de la partida a preparar. El jugador associat a la fitxa (el "color") dependrà del nombre
	 * de torns jugats fins el moment
	 * 
	 * @param fila Fila on es vol posicionar la fitxa
	 * @param columna Columna on es vol posiciona la fitxa
	 * @return <em>true</em> si el moviment s'ha pogut realitzar, <em>false</em> en cas contrari
	 */
	public boolean mouFitxa( int fila, int columna )
	{
		try
		{
			partida.getTauler().mouFitxa( color, fila, columna );
		} catch ( IllegalArgumentException e )
		{
			return false;
		} catch ( IndexOutOfBoundsException e )
		{
			return false;
		}

		partida.incrementaTornsJugats( 1 );
		
		if ( this.color == EstatCasella.JUGADOR_A )
		{
			this.color = EstatCasella.JUGADOR_B;
		}
		else
		{
			this.color = EstatCasella.JUGADOR_A;
		}

		return true;
	}
}
