package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

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
	private static final int[] puntuacio_jugador = { 0, 4, 3, 2, 2 };
	private static final int[] puntuacio_oponent = { 0, 4, 3, 2, 2 };
	private static final int factor = 2;
	private PartidaGomoku partida;
	private EstatCasella color;
	private int[][] analisi_jugador;
	private int[][] analisi_oponent;
	PriorityQueue<int[]> millors_caselles;
	boolean ha_computat = false;

	public IAGomokuSimple( /* PartidaGomoku partida, EstatCasella color */)
	{
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
		if ( partida.getTornsJugats() == 0 )
		{
			int[] moviment = { 7, 7 };
			return moviment;
		}

		if ( estat_casella == EstatCasella.BUIDA )
		{
			throw new IllegalArgumentException( "color ha de representar a un jugador" );
		}

		this.partida = (PartidaGomoku) partida;
		this.color = estat_casella;

		millors_caselles = new PriorityQueue<int[]>();
		int mida = partida.getTauler().getMida();
		this.analisi_jugador = new int[mida][mida];
		this.analisi_oponent = new int[mida][mida];

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

		ha_computat = true;

		return classificaIDecideix();
	}

	private enum Direccio
	{
		HORITZONTAL, VERTICAL, DIAGONAL_DESC, DIAGONAL_ASC

	};

	private int[] potCrearLinia( int fila, int columna, TaulerGomoku tauler, Direccio dir )
	{

		int[] coord = new int[3];
		EstatCasella color_fitxa = tauler.getEstatCasella( fila, columna );
		// TODO
		// // // System.out.println( "Mirant si " + color_fitxa + " pot crear linia" );
		switch ( dir )
		{
			case VERTICAL:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

				int fitxes_color = 0;

				for ( int i = 0; i < 5; i++ )
				{
					int fila_1 = fila + i;
					int columna_1 = columna;

					int fila_2 = fila - i;
					int columna_2 = columna;

					if ( tauler.esCasellaValida( fila_1, columna_1 ) && valid_1 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_1, columna_1 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_1++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_1 = false;
						}
					}
					else
					{
						valid_1 = false;
					}
					if ( tauler.esCasellaValida( fila_2, columna_2 ) && valid_2 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_2, columna_2 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_2++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_2 = false;
						}
					}
					else
					{
						valid_2 = false;
					}
				}
				if ( fitxes_1 + fitxes_2 >= 5 )
				{
					coord[0] = fitxes_1;
					coord[1] = fitxes_2;
					coord[2] = fitxes_color;
				}
				else
				{
					coord = null;
				}
				break;
			}
			case HORITZONTAL:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

				int fitxes_color = 0;

				for ( int i = 0; i < 5; i++ )
				{
					int fila_1 = fila;
					int columna_1 = columna + i;

					int fila_2 = fila;
					int columna_2 = columna - i;

					if ( tauler.esCasellaValida( fila_1, columna_1 ) && valid_1 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_1, columna_1 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_1++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_1 = false;
						}
					}
					else
					{
						valid_1 = false;
					}
					if ( tauler.esCasellaValida( fila_2, columna_2 ) && valid_2 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_2, columna_2 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_2++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_2 = false;
						}
					}
					else
					{
						valid_2 = false;
					}
				}
				if ( fitxes_1 + fitxes_2 >= 5 )
				{
					coord[0] = fitxes_1;
					coord[1] = fitxes_2;
					coord[2] = fitxes_color;
				}
				else
				{
					coord = null;
				}
				break;
			}
			case DIAGONAL_DESC:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

				int fitxes_color = 0;

				for ( int i = 0; i < 5; i++ )
				{
					int fila_1 = fila + i;
					int columna_1 = columna + i;

					int fila_2 = fila - i;
					int columna_2 = columna - i;

					if ( tauler.esCasellaValida( fila_1, columna_1 ) && valid_1 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_1, columna_1 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_1++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_1 = false;
						}
					}
					else
					{
						valid_1 = false;
					}
					if ( tauler.esCasellaValida( fila_2, columna_2 ) && valid_2 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_2, columna_2 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_2++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_2 = false;
						}
					}
					else
					{
						valid_2 = false;
					}
				}
				if ( fitxes_1 + fitxes_2 >= 5 )
				{
					coord[0] = fitxes_1;
					coord[1] = fitxes_2;
					coord[2] = fitxes_color;
				}
				else
				{
					coord = null;
				}
				break;
			}
			case DIAGONAL_ASC:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

				int fitxes_color = 0;

				for ( int i = 0; i < 5; i++ )
				{
					int fila_1 = fila - i;
					int columna_1 = columna + i;

					int fila_2 = fila + i;
					int columna_2 = columna - i;

					if ( tauler.esCasellaValida( fila_1, columna_1 ) && valid_1 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_1, columna_1 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_1++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_1 = false;
						}
					}
					else
					{
						valid_1 = false;
					}
					if ( tauler.esCasellaValida( fila_2, columna_2 ) && valid_2 )
					{
						EstatCasella color_casella = tauler.getEstatCasella( fila_2, columna_2 );
						if ( color_casella == color_fitxa || color_casella == EstatCasella.BUIDA )
						{
							fitxes_2++;
							if ( color_casella == color_fitxa )
							{
								fitxes_color++;
							}
						}
						else
						{
							valid_2 = false;
						}
					}
					else
					{
						valid_2 = false;
					}
				}
				if ( fitxes_1 + fitxes_2 >= 5 )
				{
					coord[0] = fitxes_1;
					coord[1] = fitxes_2;
					coord[2] = fitxes_color;
				}
				else
				{
					coord = null;
				}
				break;
			}
		}

		if ( coord != null )
		{
			if ( color != color_fitxa )
			{
				coord[2] *= 20;
			}
			else
			{
				coord[2] *= 10;
			}
		}
		return coord;
		// TODO
		// // System.out.println( "Pot!" + color_fitxa + " " + dir );
	}

	private void aplicaPuntuacions( int fila, int columna, TaulerGomoku tauler, int[] puntuacio, int[][] analisi )
	{
		// TODO llençar illegalargumentexception si es la casella és buida?
		// EstatCasella color_consulta = tauler.getEstatCasella( fila, columna );
		int[] limits = potCrearLinia( fila, columna, tauler, Direccio.VERTICAL );
		if ( limits != null )
		{
			int potencial = limits[2];
			for ( int i = fila; i > fila - limits[1]; i-- )
			{
				if ( tauler.esCasellaValida( i, columna ) )
				{
					analisi[i][columna] += puntuacio[fila - i] + potencial;
				}
			}

			for ( int i = fila; i < fila + limits[0]; i++ )
			{
				analisi[i][columna] += puntuacio[i - fila] + potencial;
			}
		}

		limits = potCrearLinia( fila, columna, tauler, Direccio.HORITZONTAL );
		if ( limits != null )
		{
			int potencial = limits[2];
			for ( int j = columna; j > columna - limits[1]; j-- )
			{
				analisi[fila][j] += puntuacio[columna - j] + potencial;
			}

			for ( int j = columna; j < columna + limits[0]; j++ )
			{
				analisi[fila][j] += puntuacio[j - columna] + potencial;
			}
		}
		limits = potCrearLinia( fila, columna, tauler, Direccio.DIAGONAL_DESC );
		if ( limits != null )
		{
			int potencial = limits[2];
			for ( int i = fila, j = columna; i > fila - limits[1] && j > columna - limits[1]; i--, j-- )
			{
				analisi[i][j] += puntuacio[columna - j] + potencial;
			}

			for ( int i = fila, j = columna; i < fila + limits[0] && j < columna + limits[0]; i++, j++ )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					analisi[i][j] += puntuacio[j - columna] + potencial;
				}
			}

		}

		limits = potCrearLinia( fila, columna, tauler, Direccio.DIAGONAL_ASC );
		if ( limits != null )
		{
			int potencial = limits[2];
			for ( int i = fila, j = columna; i > fila - limits[0] && j < columna + limits[0]; i--, j++ )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					analisi[i][j] += puntuacio[j - columna] + potencial;
				}
			}

			for ( int i = fila, j = columna; i < fila + limits[1] && j > columna - limits[1]; i++, j-- )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					analisi[i][j] += puntuacio[columna - j] + potencial;
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
		if ( !ha_computat )
		{
			return "Encara no ha computat cap moviment";
		}
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
