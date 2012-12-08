package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.TipusUsuari;

/**
 * Classe que permet abstraure les diferents possibilitats de inteligències artificials al sistema. Cada instància
 * d'aquesta classe té associada un nivell de CPU i el paper que juguen a la partida.
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class InteligenciaCPU
{
	private TipusUsuari nivell;
	private EstatCasella color;
	private IAGomoku ia;

	/**
	 * Mètode constructor
	 * 
	 * @param nivell Nivell de dificultat de la CPU que s'associa
	 * @param color Paper amb el que ha la IA hauria de jugar la partida
	 * @throws IllegalArgumentException Si el <em>nivell</em> no identifica a una dificultat de CPU o si <em>color</em>
	 *         és <em>EstatCasella.BUIDA</em>
	 */
	public InteligenciaCPU( TipusUsuari nivell, EstatCasella color ) throws IllegalArgumentException
	{
		if ( nivell == TipusUsuari.HUMA || nivell == TipusUsuari.CONVIDAT )
		{
			throw new IllegalArgumentException( "El nivell  ha de correspondre al d'una CPU" );
		}

		if ( color == EstatCasella.BUIDA )
		{
			throw new IllegalArgumentException( "No es pot indicar EstatCasella.BUIDA com a 'color'" );
		}

		this.nivell = nivell;
		this.color = color;

		switch ( nivell )
		{
			case FACIL:
				// TODO no és la que tocaria
				ia = new IAGomoku();
				break;
			case MITJA:
				ia = new IAGomoku();
				break;
			case DIFICIL:
				ia = new IAGomokuOptimitzada();
				break;
			default:
				// Es contemplen tots el casos
				break;
		}
	}

	/**
	 * Donada una partida específica i l'últim moviment realitzat, aquest mètode permet computar el moviment de la IA
	 * segons el nivell de dificultat del paràmetre implícit
	 * 
	 * Les coordenades de l'últim moviment d'interès (<em>fila</em>, <em>columna</em>), és informació extra que pot
	 * interessar a les diferents implementacions de IA
	 * 
	 * @param partida Partida a la qual es vol realitzar un moviment
	 * @param fila_ult_moviment Fila de l'últim moviment d'interès
	 * @param columna_ult_moviment Columna de l'últim moviment d'interès
	 * @return Millor moviment a realitzar segons la implementació de la IA per a cada nivell de dificultat
	 */
	public int[] getMoviment( PartidaGomoku partida, int fila_ult_moviment, int columna_ult_moviment )
	{
		int[] moviment_ia = new int[2];
		int mida = partida.getTauler().getMida();

		int torn_actual = partida.getTornsJugats() + 1;

		// TODO moviments incials. Potser aquesta lògica hauria d'estar a les pròpies IAs?
		if ( torn_actual == 1 )
		{
			moviment_ia[0] = ( mida / 2 );
			moviment_ia[1] = ( mida / 2 );
		}

		else if ( torn_actual == 2 )
		{
			moviment_ia = ia.movimentAdjacentAleatori( fila_ult_moviment, columna_ult_moviment, mida );
		}

		else
		{
			moviment_ia = ia.computaMoviment( partida, color );
		}

		if ( moviment_ia[0] == -1 && moviment_ia[1] == -1 )
		{
			TaulerGomoku tauler = partida.getTauler();
			moviment_ia = ia.movimentAleatori( tauler );
		}
		return moviment_ia;
	}

	/**
	 * Mètode consultor del nivell de dificultat associat al paràmetre implícit
	 * 
	 * @return Nivell de la CPU amb el que aquesta InteligenciaCPU juga
	 */
	public TipusUsuari getNivellCPU()
	{
		return this.nivell;
	}
}
