package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class IAGomokuAlternativa2 extends IAGomoku
{
	private static final int[] puntuacio_jugador = { 0, 4, 3, 2, 1 };
	private static final int[] puntuacio_oponent = { 0, 4, 3, 2, 1 };
	private static final int factor_potencial = 5;
	private PartidaGomoku partida;
	private EstatCasella color;
	private int[][] analisi_jugador;
	private int[][] analisi_oponent;
	PriorityQueue<int[]> millors_caselles;

	/**
	 * Classe comparada que serveix per comparar dues coordenades d'un tauler de Gomoku basant-se en la utilitat que li
	 * té assignada a cadascuna d'aquestes
	 * 
	 * @author Ignacio
	 * 
	 */
	private class ComparadorIASimple implements Comparator<int[]>
	{

		/**
		 * Mètode encarregat de la comparació de dues coordenades basant-se en la utilitat d'aquestes segons els
		 * anàlisis que realitat la IA
		 * 
		 * @param coord_a Primera coordenada de la comparació
		 * @param coord_b Segona coordenada de la comparació
		 * @return El resultat de la comparació entre les dues coordenades paràmetre
		 */
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

	/**
	 * Mètode de conveniència que permet comprovar si un moviment és vàlid a un determinat tauler
	 * 
	 * @param color Color pel qual es vol realitzar el moviment
	 * @param fila Fila on es voldria realitzar el moviment
	 * @param columna Columna on es voldria realitzar el moviment
	 * @param tauler Tauler on es voldria realitzar el moviment
	 * @return <em>true</em> si el moviment es vàlid; <em>false</em> en cas contrari
	 */
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

	/**
	 * Mètode que avalua els anàlisis realitzats, classifica els possibles moviments i retorna les coordenades d'un
	 * possible moviment vàlid
	 * 
	 * @return Coordenades del moviment que la IA considera com a millor
	 */
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

	// TODO
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

		int puntuacio = 0;
		switch ( fitxes_color )
		{
			case 0:
				puntuacio = 0;
				break;
			case 1:
				puntuacio = 10;
				break;
			case 2:
				if ( color == EstatCasella.JUGADOR_A && color_fitxa == EstatCasella.JUGADOR_B )
				{
					puntuacio = 1000;
				}
				else
				{
					puntuacio = 100;
				}
				break;
			case 3:
				if ( color == EstatCasella.JUGADOR_A && color_fitxa == EstatCasella.JUGADOR_B )
				{
					puntuacio = 10000;
				}
				else
				{
					puntuacio = 1000;
				}
				break;
			case 4:
				puntuacio = 100000;
				break;
		}

		return puntuacio * factor_potencial;
	}

	private boolean potCrearLinia( int fila, int columna, TaulerGomoku tauler, Direccio dir )
	{

		EstatCasella color_fitxa = tauler.getEstatCasella( fila, columna );

		switch ( dir )
		{
			case VERTICAL:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

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
					return true;
				}
				break;
			}
			case HORITZONTAL:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

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
					return true;
				}
				break;
			}
			case DIAGONAL_DESC:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

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
					return true;
				}
				break;
			}
			case DIAGONAL_ASC:
			{
				int fitxes_1 = 0;
				int fitxes_2 = 0;

				boolean valid_1 = true;
				boolean valid_2 = true;

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
					return true;
				}
				break;
			}
		}
		return false;

	}

	
	private boolean potCrearLinia2( int fila, int columna, TaulerGomoku tauler, Direccio dir )
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
		// TODO llenÃ§ar illegalargumentexception si es la casella Ã©s buida?
		EstatCasella color_consulta = tauler.getEstatCasella( fila, columna );
		if ( potCrearLinia( fila, columna, tauler, Direccio.HORITZONTAL ) )
		{
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.HORITZONTAL );
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
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.VERTICAL );
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
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.DIAGONAL_DESC );
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
			int potencial = comptaPotencialitatLinia( fila, columna, tauler, Direccio.DIAGONAL_ASC );
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