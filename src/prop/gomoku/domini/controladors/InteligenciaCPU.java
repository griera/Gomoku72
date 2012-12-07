package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.TipusUsuari;

public class InteligenciaCPU
{
	private TipusUsuari nivell;
	private EstatCasella color;
	private IAGomoku ia;

	public InteligenciaCPU( TipusUsuari nivell, EstatCasella color )
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

	// TODO documentar
	public int[] getMoviment( PartidaGomoku partida, int fila_ult_moviment, int columna_ult_moviment )
	{
		int[] moviment_ia = new int[2];
		int mida = partida.getTauler().getMida();

		int torn_actual = partida.getTornsJugats() + 1;

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

	// TODO documentar
	public TipusUsuari getNivellCPU()
	{
		return this.nivell;
	}
}
