package prop.gomoku.domini.models;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.controladors.IAGomoku;

public class InteligenciaCPU
{
	private TipusUsuari nivell;
	private EstatCasella color;
	private int profunditat;

	public InteligenciaCPU( TipusUsuari nivell, EstatCasella color )
	{
		if ( nivell == TipusUsuari.HUMA || nivell == TipusUsuari.CONVIDAT )
		{
			throw new IllegalArgumentException( "El nivell  ha de correspondre al d'una CPU" );
		}
		
		if (color == EstatCasella.BUIDA)
		{
			throw new IllegalArgumentException("No es pot indicar EstatCasella.BUIDA com a 'color'");
		}

		this.nivell = nivell;
		this.color = color;
		
		switch ( nivell )
		{
			case FACIL:
				profunditat = 2;
				break;
			case MITJA:
				profunditat = 3;
				break;
			case DIFICIL:
				profunditat = 4;
				break;
			default:
				break;
		}
	}
	
	// TODO documentar
	public int[] getMoviment( PartidaGomoku partida, int fila_ult_moviment, int columna_ult_moviment )
	{
		// TODO
		IAGomoku ia = new IAGomoku();
		
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
			moviment_ia = ia.minimax( partida, color, profunditat );
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
