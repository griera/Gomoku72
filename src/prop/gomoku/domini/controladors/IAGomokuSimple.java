package prop.gomoku.domini.controladors;

import java.util.PriorityQueue;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class IAGomokuSimple extends IAGomoku
{
	private static final int[] puntuacio_jugador = { 4, 3, 2, 1 };
	private static final int[] puntuacio_oponent = { 4, 3, 2, 1 };
	private PartidaGomoku partida;
	private EstatCasella color;
	private int[][] analisi_jugador;
	private int[][] analisi_oponent;
	PriorityQueue<int[]> millors_caselles_jugador;
	PriorityQueue<int[]> millors_caselles_oponent;

	public IAGomokuSimple( PartidaGomoku partida, EstatCasella color )
	{
		this.partida = partida;
		this.color = color;

		millors_caselles_jugador = new PriorityQueue<int[]>();
		millors_caselles_oponent = new PriorityQueue<int[]>();

		int mida = partida.getTauler().getMida();
		this.analisi_jugador = new int[mida][mida];
		this.analisi_oponent = new int[mida][mida];

		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				analisi_jugador[i][j] = 0;
				analisi_oponent[i][j] = 0;
			}
		}

		this.actualitzaAnalisi();
	}

	private int[] classificaIDecideix()
	{
		millors_caselles_jugador = new PriorityQueue<int[]>();
		millors_caselles_oponent = new PriorityQueue<int[]>();

		int mida = partida.getTauler().getMida();
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				int[] coord = new int[2];
				coord[0] = i;
				coord[1] = j;
				millors_caselles_jugador.add( coord );
				millors_caselles_oponent.add( coord );
			}
		}

		return null;
	}

	public int[] computaMoviment()
	{
		actualitzaAnalisi();
		return classificaIDecideix();
	}

	private void aplicaPuntuacions( int fila, int columna, TaulerGomoku tauler, int[][] analisi, int[] puntuacio )
	{
		for ( int i = fila - 1; i > fila - 5; i-- )
		{
			if ( tauler.esCasellaValida( i, columna ) )
			{
				analisi[i][columna] += puntuacio[fila - i - 1];
			}
		}

		for ( int i = fila + 1; i < fila + 5; i++ )
		{
			if ( tauler.esCasellaValida( i, columna ) )
			{
				analisi[i][columna] += puntuacio[i - fila - 1];
			}
		}

		for ( int j = columna - 1; j > columna - 5; j-- )
		{
			if ( tauler.esCasellaValida( fila, j ) )
			{
				analisi[fila][j] += puntuacio[columna - j - 1];
			}
		}

		for ( int j = columna + 1; j < columna + 5; j++ )
		{
			if ( tauler.esCasellaValida( fila, j ) )
			{
				analisi[fila][j] += puntuacio[j - columna - 1];
			}
		}

		for ( int i = fila - 1, j = columna - 1; i > fila - 5 && j > columna - 5; i--, j-- )
		{
			if ( tauler.esCasellaValida( i, j ) )
			{
				analisi[i][j] += puntuacio[columna - j - 1];
			}
		}

		// TODO
		for ( int i = fila + 1, j = columna - 1; i < fila + 5 && j > columna - 5; i++, j-- )
		{
			if ( tauler.esCasellaValida( i, j ) )
			{
				analisi[i][j] += puntuacio[columna - j - 1];
			}
		}

		// TODO
		for ( int i = fila + 1, j = columna + 1; i < fila + 5 && j < columna + 5; i++, j++ )
		{
			if ( tauler.esCasellaValida( i, j ) )
			{
				analisi[i][j] += puntuacio[j - columna - 1];
			}
		}

		// TODO
		for ( int i = fila - 1, j = columna + 1; i > fila - 5 && j < columna + 5; i--, j++ )
		{
			if ( tauler.esCasellaValida( i, j ) )
			{
				analisi[i][j] += puntuacio[j - columna - 1];
			}
		}

	}

	private void aplicaPuntsFitxa( int fila, int columna, TaulerGomoku tauler )
	{
		EstatCasella estat = tauler.getEstatCasella( fila, columna );
		int[][] analisi = null;
		int[] puntuacio = null;

		switch ( estat )
		{
			case BUIDA:
				return;
			default:
				if ( estat == color )
				{
					analisi = analisi_jugador;
					puntuacio = puntuacio_jugador;
				}
				else
				{
					analisi = analisi_oponent;
					puntuacio = puntuacio_oponent;
				}
		}

		aplicaPuntuacions( fila, columna, tauler, analisi, puntuacio );
	}

	private void actualitzaAnalisi()
	{
		TaulerGomoku tauler = partida.getTauler();
		int mida = tauler.getMida();
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				aplicaPuntsFitxa( i, j, tauler );
			}
		}

	}

	@Override
	public String toString()
	{
		String sortida = "Analisi jugador - " + color.toString() + "\n";
		for ( int i = 0; i < analisi_jugador.length; i++ )
		{
			for ( int j = 0; j < analisi_jugador[0].length; j++ )
			{
				if ( analisi_jugador[i][j] == 0 )
				{
					sortida += " .";
				}
				else
				{
					sortida += " " + Integer.toString( analisi_jugador[i][j] );
				}
			}
			sortida += "\n";
		}
		sortida += "Analisi oponent\n";
		for ( int i = 0; i < analisi_oponent.length; i++ )
		{
			for ( int j = 0; j < analisi_oponent[0].length; j++ )
			{
				if ( analisi_oponent[i][j] == 0 )
				{
					sortida += " .";
				}
				else
				{
					sortida += " " + Integer.toString( analisi_oponent[i][j] );
				}
			}
			sortida += "\n";
		}
		return sortida;

	}
}
