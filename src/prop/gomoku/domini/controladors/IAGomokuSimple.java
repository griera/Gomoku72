package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class IAGomokuSimple extends IAGomoku
{
//	private static final int[] puntuacio_jugador = { 0, 8, 7, 6, 5 };
//	private static final int[] puntuacio_oponent = { 0, 8, 7, 6, 5 };
	private static final int[] puntuacio_jugador = { 0, 8, 7, 5, 5 };
	private static final int[] puntuacio_oponent = { 0, 8, 7, 5, 5 };
	private PartidaGomoku partida;
	private EstatCasella color;
	private int[][] analisi;
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
		this.analisi = new int[mida][mida];

		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				analisi[i][j] = 0;
			}
		}
		this.actualitzaAnalisi();
	}

	// TODO documentar
	private class ComparadorIASimple implements Comparator<int[]>
	{

		@Override
		public int compare( int[] coord_a, int[] coord_b )
		{
			int punts_a = analisi[coord_a[0]][coord_a[1]];
			int punts_b = analisi[coord_b[0]][coord_b[1]];

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

	public int[] computaMoviment()
	{
		// TODO si recalculem a cada cas?
		int mida = partida.getTauler().getMida();
		for ( int i = 0; i < mida; i++ )
		{
			for ( int j = 0; j < mida; j++ )
			{
				analisi[i][j] = 0;
			}
		}
		actualitzaAnalisi();
		// actualitzaAnalisi();
		return classificaIDecideix();
	}

	private enum Direccio
	{
		HORITZONTAL, VERTICAL, DIAGONAL_DESC, DIAGONAL_ASC

	};

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
				for ( int i = fila + 5, j = columna - 5; i < fila - 5 && j < columna + 5; i--, j++ )
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
		return true;
	}

	private void aplicaPuntuacions( int fila, int columna, TaulerGomoku tauler, int[] puntuacio )
	{
		// TODO llençar illegalargumentexception si es la casella és buida?
		EstatCasella color_consulta = tauler.getEstatCasella( fila, columna );
		if ( potCrearLinia( fila, columna, tauler, Direccio.HORITZONTAL ) )
		{
			boolean es_util = true;
			int estalvi = 0;
			for ( int i = fila; i > fila - 5 && es_util; i-- )
			{
				if ( tauler.esCasellaValida( i, columna ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, columna );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[fila - i];
					}
					else
					{
						analisi[i][columna] += puntuacio[fila - i] + estalvi;
						estalvi = 0;
					}
				}
			}

			es_util = true;
			estalvi = 0;
			for ( int i = fila; i < fila + 5 && es_util; i++ )
			{
				if ( tauler.esCasellaValida( i, columna ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, columna );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[i - fila];
					}
					else
					{
						analisi[i][columna] += puntuacio[i - fila] + estalvi;
						estalvi = 0;
					}
				}
			}
		}

		if ( potCrearLinia( fila, columna, tauler, Direccio.VERTICAL ) )
		{
			boolean es_util = true;
			int estalvi = 0;
			for ( int j = columna; j > columna - 5 && es_util; j-- )
			{
				if ( tauler.esCasellaValida( fila, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( fila, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[columna - j];
					}
					else
					{
						analisi[fila][j] += puntuacio[columna - j] + estalvi;
						estalvi = 0;
					}
				}
			}

			es_util = true;
			estalvi = 0;
			for ( int j = columna; j < columna + 5 && es_util; j++ )
			{
				if ( tauler.esCasellaValida( fila, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( fila, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[j - columna];
					}
					else
					{
						analisi[fila][j] += puntuacio[j - columna] + estalvi;
						;
						estalvi = 0;
					}
				}
			}
		}
		if ( potCrearLinia( fila, columna, tauler, Direccio.DIAGONAL_DESC ) )
		{
			boolean es_util = true;
			int estalvi = 0;
			for ( int i = fila, j = columna; i > fila - 5 && j > columna - 5 && es_util; i--, j-- )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[columna - j];
					}
					else
					{
						analisi[i][j] += puntuacio[columna - j] + estalvi;
						estalvi = 0;
					}
				}
			}

			es_util = true;
			estalvi = 0;
			for ( int i = fila, j = columna; i > fila - 5 && j < columna + 5 && es_util; i--, j++ )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[j - columna];
					}
					else
					{
						analisi[i][j] += puntuacio[j - columna] + estalvi;
						estalvi = 0;
					}
				}
			}
		}

		if ( potCrearLinia( fila, columna, tauler, Direccio.DIAGONAL_ASC ) )
		{
			boolean es_util = true;
			int estalvi = 0;
			for ( int i = fila, j = columna; i < fila + 5 && j < columna + 5 && es_util; i++, j++ )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[j - columna];
					}
					else
					{
						analisi[i][j] += puntuacio[j - columna] + estalvi;
						estalvi = 0;
					}
				}
			}

			es_util = true;
			estalvi = 0;
			for ( int i = fila, j = columna; i < fila + 5 && j > columna - 5 && es_util; i++, j-- )
			{
				if ( tauler.esCasellaValida( i, j ) )
				{
					EstatCasella color_iteracio = tauler.getEstatCasella( i, j );
					if ( color_iteracio != color_consulta && color_iteracio != EstatCasella.BUIDA )
					{
						es_util = false;
					}
					else if ( color_iteracio == color_consulta )
					{
						estalvi += puntuacio[columna - j];
					}
					{
						analisi[i][j] += puntuacio[columna - j] + estalvi;
						estalvi = 0;
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
				if ( estat == color )
				{
					puntuacio = puntuacio_jugador;
				}
				else
				{
					puntuacio = puntuacio_oponent;
				}
				aplicaPuntuacions( fila, columna, tauler, puntuacio );
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
		for ( int i = 0; i < analisi.length; i++ )
		{
			for ( int j = 0; j < analisi[0].length; j++ )
			{
				if ( analisi[i][j] == 0 )
				{
					sortida += " . ";
				}
				else
				{
					sortida += " " + Integer.toString( analisi[i][j] ) + " ";
				}
			}
			sortida += "\n";
		}
		return sortida;

	}
}
