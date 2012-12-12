package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.experimental.theories.PotentialAssignment;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class IAGomokuSimple extends IAGomoku
{
	// private static final int[] puntuacio_jugador = { 0, 8, 7, 6, 5 };
	// private static final int[] puntuacio_oponent = { 0, 8, 7, 6, 5 };
	// Con esto se le gana de manera tonta, pero en el resto de situaciones se defiende relativament bien
	// private static final int[] puntuacio_jugador = { 0, 15, 7, 3, 2 };
	// private static final int[] puntuacio_oponent = { 0, 16, 8, 4, 4 };
	// private static final int[] puntuacio_jugador = { 0, 8, 8, 4, 4 };
	// private static final int[] puntuacio_oponent = { 0, 8, 8, 4, 4 };
	private static final int[] puntuacio_jugador = { 0, 4, 3, 2, 1 };
	private static final int[] puntuacio_oponent = { 0, 47 , 3, 2, 1 };
	private static final int factor_potencial = 5;
	private PartidaGomoku partida;
	private EstatCasella color;
	private int[][] analisi_jugador;
	private int[][] analisi_oponent;
	PriorityQueue<int[]> millors_caselles;

	public IAGomokuSimple( PartidaGomoku partida, EstatCasella color )
	{
		if ( color == EstatCasella.BUIDA )
		{
			throw new IllegalArgumentException( "color ha de representar a un jugador" );
		}
		this.partida = partida;
		this.color = color;

		millors_caselles = new PriorityQueue<int[]>();

		int mida = partida.getTauler().getMida();
		this.analisi_jugador = new int[mida][mida];
		this.analisi_oponent = new int[mida][mida];
	}

	// TODO documentar
	private class ComparadorIASimple implements Comparator<int[]>
	{

		@Override
		public int compare( int[] coord_a, int[] coord_b )
		{
			// int punts_a = analisi_jugador[coord_a[0]][coord_a[1]];
			// int punts_b = analisi_jugador[coord_b[0]][coord_b[1]];

			int punts_a = analisi_jugador[coord_a[0]][coord_a[1]] + analisi_oponent[coord_a[0]][coord_a[1]];
			int punts_b = analisi_jugador[coord_b[0]][coord_b[1]] + analisi_oponent[coord_b[0]][coord_b[1]];

			if ( punts_a < punts_b )
			{
				return 1;
			}
			else if ( punts_a > punts_b )
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}

	private boolean esMovimentValid( EstatCasella color, int fila, int columna, TaulerGomoku tauler )
	{
		try
		{
			tauler.esMovimentValid( color, fila, columna );
		} catch ( IllegalArgumentException e )
		{
			return false;
		} catch ( IndexOutOfBoundsException e )
		{
			return false;
		}
		return true;
	}

	private int[] classificaIDecideix()
	{
		millors_caselles = new PriorityQueue<int[]>( 20, new ComparadorIASimple() );

		int mida = partida.getTauler().getMida();
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				int[] coord = new int[2];
				coord[0] = i;
				coord[1] = j;
				millors_caselles.add( coord );
			}
		}

		int[] casella = millors_caselles.poll();

		while ( !esMovimentValid( color, casella[0], casella[1], partida.getTauler() ) )
		{
			casella = millors_caselles.poll();
		}

		return casella;
	}

	// TODO Atenció: treballa amb les dades passades a la constructora, s'ignoren aquestes dades
	@Override
	public int[] computaMoviment( Partida partida, EstatCasella estat_casella, int fila_ult_moviment,
			int columna_ult_moviment )
	{
		return computaMoviment();
	}

	public int[] computaMoviment()
	{
		// TODO si recalculem a cada cas?
		int mida = partida.getTauler().getMida();
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				analisi_jugador[i][j] = 0;
				analisi_oponent[i][j] = 0;
			}
		}
		actualitzaAnalisi();
		return classificaIDecideix();
	}

	private enum Direccio
	{
		HORITZONTAL, VERTICAL, DIAGONAL_DESC, DIAGONAL_ASC

	};

	private int comptaPotencialitatLinia( int fila, int columna, TaulerGomoku tauler, Direccio dir )
	{
		EstatCasella color_fitxa = tauler.getEstatCasella( fila, columna );
		int fitxes_color = 0;
		switch ( dir )
		{
			case HORITZONTAL:
			{
				for ( int i = fila - 5; i < fila + 5; i++ )
				{
					if ( tauler.esCasellaValida( i, columna ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( i, columna );
						if ( color_casella == color_fitxa )
						{
							fitxes_color++;
						}
					}
				}
				break;
			}
			case VERTICAL:
			{
				for ( int j = columna - 5; j < columna + 5; j++ )
				{
					if ( tauler.esCasellaValida( fila, j ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila, j );
						if ( color_casella == color_fitxa )
						{
							fitxes_color++;
						}
					}
				}
				break;
			}
			case DIAGONAL_DESC:
			{
				for ( int i = fila - 5, j = columna - 5; i < fila + 5 && j < columna + 5; i++, j++ )
				{
					if ( tauler.esCasellaValida( i, j ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( i, j );
						if ( color_casella == color_fitxa )
						{
							fitxes_color++;
						}
					}
				}
				break;
			}
			case DIAGONAL_ASC:
				for ( int i = fila + 5, j = columna - 5; i > fila - 5 && j < columna + 5; i--, j++ )
				{
					if ( tauler.esCasellaValida( i, j ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( i, j );
						if ( color_casella == color_fitxa )
						{
							fitxes_color++;
						}
					}
				}
		}
		
		switch(fitxes_color)
		{
			case 0:
				return 0;
			case 1:
				return 10;
			case 2:
				return 100;
			case 3:
				return 1000;
			case 4:
				return 10000;
		}
		return fitxes_color;
	}

	private boolean potCrearLinia( int fila, int columna, TaulerGomoku tauler, Direccio dir )
	{

		EstatCasella color_fitxa = tauler.getEstatCasella( fila, columna );
		// TODO
		System.out.println( "Mirant si " + color_fitxa + " pot crear linia" );
		switch ( dir )
		{
			case HORITZONTAL:
			{
				int fitxes_potencials = 0;
				for ( int i = fila - 5; i < fila + 5; i++ )
				{
					if ( tauler.esCasellaValida( i, columna ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( i, columna );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_potencials++;
							if ( fitxes_potencials >= 5 )
							{
								return true;
							}
						}
						else
						{
							fitxes_potencials = 0;
						}
					}
				}
				break;
			}
			case VERTICAL:
			{
				int fitxes_potencials = 0;
				for ( int j = columna - 5; j < columna + 5; j++ )
				{
					if ( tauler.esCasellaValida( fila, j ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila, j );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_potencials++;
							if ( fitxes_potencials >= 5 )
							{
								return true;
							}
						}
						else
						{
							fitxes_potencials = 0;
						}
					}
				}
				break;
			}
			case DIAGONAL_DESC:
			{
				int fitxes_potencials = 0;
				for ( int i = fila - 5, j = columna - 5; i < fila + 5 && j < columna + 5; i++, j++ )
				{
					if ( tauler.esCasellaValida( i, j ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( i, j );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_potencials++;
							if ( fitxes_potencials >= 5 )
							{
								return true;
							}
						}
						else
						{
							fitxes_potencials = 0;
						}
					}
				}
				break;
			}
			case DIAGONAL_ASC:
				int fitxes_potencials = 0;
				for ( int i = fila + 5, j = columna - 5; i > fila - 5 && j < columna + 5; i--, j++ )
				{
					if ( tauler.esCasellaValida( i, j ) )
					{
						EstatCasella color_casella = tauler.getEstatCasella( i, j );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_potencials++;
							if ( fitxes_potencials >= 5 )
							{
								return true;
							}
						}
						else
						{
							fitxes_potencials = 0;
						}
					}
				}
		}
		// TODO
		System.out.println( "Pot!" + color_fitxa + " " + dir );
		return false;
	}

	private void aplicaPuntuacions( int fila, int columna, TaulerGomoku tauler, int[] puntuacio, int[][] analisi )
	{
		// TODO llençar illegalargumentexception si es la casella és buida?
		EstatCasella color_consulta = tauler.getEstatCasella( fila, columna );
		if ( potCrearLinia( fila, columna, tauler, Direccio.HORITZONTAL ) )
		{
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.HORITZONTAL ) * factor_potencial;
			boolean es_util = true;
			for ( int i = fila; i > fila - 5 && es_util; i-- )
			{
				if ( tauler.esCasellaValida( i, columna ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, columna );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[i][columna] += puntuacio[fila - i] + potencial;
					}
				}
			}

			es_util = true;
			for ( int i = fila; i < fila + 5 && es_util; i++ )
			{
				if ( tauler.esCasellaValida( i, columna ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, columna );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[i][columna] += puntuacio[i - fila] + potencial;
					}
				}
			}
		}

		if ( potCrearLinia( fila, columna, tauler, Direccio.VERTICAL ) )
		{
			boolean es_util = true;
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.VERTICAL ) * factor_potencial;
			for ( int j = columna; j > columna - 5 && es_util; j-- )
			{
				if ( tauler.esCasellaValida( fila, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( fila, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[fila][j] += puntuacio[columna - j] + potencial;
					}
				}
			}

			es_util = true;
			for ( int j = columna; j < columna + 5 && es_util; j++ )
			{
				if ( tauler.esCasellaValida( fila, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( fila, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[fila][j] += puntuacio[j - columna] + potencial;
					}
				}
			}
		}
		if ( potCrearLinia( fila, columna, tauler, Direccio.DIAGONAL_DESC ) )
		{
			boolean es_util = true;
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.DIAGONAL_DESC ) * factor_potencial;
			for ( int i = fila, j = columna; i > fila - 5 && j > columna - 5 && es_util; i--, j-- )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[i][j] += puntuacio[columna - j] + potencial;
					}
				}
			}

			es_util = true;
			for ( int i = fila, j = columna; i < fila + 5 && j < columna + 5 && es_util; i++, j++ )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[i][j] += puntuacio[j - columna] + potencial;
					}
				}
			}

		}

		if ( potCrearLinia( fila, columna, tauler, Direccio.DIAGONAL_ASC ) )
		{
			boolean es_util = true;
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.DIAGONAL_ASC ) * factor_potencial;
			for ( int i = fila, j = columna; i > fila - 5 && j < columna + 5 && es_util; i--, j++ )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else
					{
						analisi[i][j] += puntuacio[j - columna] + potencial;
					}
				}
			}

			es_util = true;
			for ( int i = fila, j = columna; i < fila + 5 && j > columna - 5 && es_util; i++, j-- )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					{
						analisi[i][j] += puntuacio[columna - j] + potencial;
					}
				}
			}
		}
	}

	private void aplicaPuntsFitxa( int fila, int columna, TaulerGomoku tauler )
	{
		EstatCasella estat = tauler.getEstatCasella( fila, columna );
		int[] puntuacio = null;

		switch ( estat )
		{
			case BUIDA:
				return;
			default:
				int[][] analisi_actual;
				if ( estat == color )
				{
					puntuacio = puntuacio_jugador;
					analisi_actual = analisi_jugador;
				}
				else
				{
					puntuacio = puntuacio_oponent;
					analisi_actual = analisi_oponent;
				}
				aplicaPuntuacions( fila, columna, tauler, puntuacio, analisi_actual );
				break;
		}
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

		for ( int j = 0; j < analisi_jugador[0].length; j++ )
		{
			sortida += "\t" + j;
		}
		sortida += "\n";

		for ( int i = 0; i < analisi_jugador.length; i++ )
		{
			sortida += i + ":\t";
			for ( int j = 0; j < analisi_jugador[0].length; j++ )
			{
				if ( analisi_jugador[i][j] == 0 )
				{
					sortida += ".\t";
				}
				else
				{
					sortida += Integer.toString( analisi_jugador[i][j] ) + "\t";
				}
			}
			sortida += "\n";
		}

		sortida += "Analisi oponent\n";
		for ( int j = 0; j < analisi_oponent[0].length; j++ )
		{
			sortida += "\t" + j + " ";
		}
		sortida += "\n";

		for ( int i = 0; i < analisi_oponent.length; i++ )
		{
			sortida += i + ":\t";
			for ( int j = 0; j < analisi_oponent[0].length; j++ )
			{
				if ( analisi_oponent[i][j] == 0 )
				{
					sortida += ".\t";
				}
				else
				{
					sortida += Integer.toString( analisi_oponent[i][j] ) + "\t";
				}
			}
			sortida += "\n";
		}
		return sortida;
	}
}
