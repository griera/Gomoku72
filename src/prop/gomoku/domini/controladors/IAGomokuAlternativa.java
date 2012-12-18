package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

/**
 * Classe que intenta modelar el comportament d'un jugador de Gomoku amb una competència mínima implementant un
 * algorisme que avalua el tauler en el seu estat actual i pondera l'utilitat de posicionar una fitxa en cada casella
 * del tauler.
 * 
 * Es basa principalment en el principi de que una posició que resulta molt profitosa pel jugador (generar més
 * oportunitats de crear una línia de 5 fitxes), també ho serà pel contrincant (disminuir les possibilitats de
 * l'oponent).
 * 
 * A alt nivell, es podria descriure l'algorisme en pocs passos:
 * 
 * 1. Per cada fitxa d'un jugador al tauler
 * 
 * 2. Analitzar si pot arribar a formar part d'una línia de 5 fitxes en alguna direcció
 * 
 * 3. Si en una direcció la creació d'una línia de 5 és possible, s'atorga una puntuació a la fitxa segons la
 * <em>potencialitat</em> de la línia i s'aplica aquesta puntuació base a totes les caselles amb les que la casella
 * analitzada podria arribar a crear una línia de 5 en aquella direcció.
 * 
 * 4. Tornem al pas 2 fins a haver analitzat totes les direccions donada a una fitxa posicionada al tauler
 * 
 * 5. Se sumen els anàlisis del jugador i del oponent i s'obté el primer moviment vàlid que tingui la puntuació més alta
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class IAGomokuAlternativa extends IAGomoku
{
	/**
	 * Array d'integers que permet matitzar les puntuacions del jugador segons la distància respecte certa fitxa. La
	 * posició 0 corresòn a la fitxa en sí (distància zero).
	 */
	private static final int[] puntuacio_jugador = { 0, 4, 3, 2, 1 };
	/**
	 * Array d'integers que com l'anterior permet matitzar puntuacions, però en aquest cas per l'oponent
	 */
	private static final int[] puntuacio_oponent = { 0, 4, 3, 2, 1 };
	/**
	 * Factor multiplicatiu del potencial calculat amb <em>calculaPotencialLinia</em>. D'aquesta forma es podria evitar
	 * treballar amb nombres molts grans dins del mètode en sí
	 */
	private static final int factor_potencial = 5;
	/**
	 * Partida per la qual es vol trobar un moviment efectiu
	 */
	private PartidaGomoku partida;
	/**
	 * Color (EstatCasella.JUGADOR_A/B) pel qual es vol trobar el moviment adequat
	 */
	private EstatCasella color;
	/**
	 * Matriu amb les mateixes dimensions que el tauler de la partida que conté l'anàlisi pel jugador
	 */
	private int[][] analisi_jugador;
	/**
	 * Matriu amb les mateixes dimensions que el tauler de la partida que conté l'anàlisi per l'oponent
	 */
	private int[][] analisi_oponent;
	/**
	 * Cua de prioritat utilitzada per la comparació de les posicions del tauler
	 */
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
			/* Es realitza la suma, la separació de puntuacions permet més flexibilitat a l'hora de decidir com comparar
			 * les caselles (en cas de voler expandir o canviar els criteris) */
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

	@Override
	public int[] computaMoviment( Partida partida, EstatCasella estat_casella, int fila_ult_moviment,
			int columna_ult_moviment )
	{
		if ( estat_casella == EstatCasella.BUIDA )
		{
			throw new IllegalArgumentException( "color ha de representar a un jugador" );
		}
		// Si ens trobem al primer torn, mourem al centre del tauler
		if ( partida.getTornsJugats() == 0 )
		{
			int[] moviment = { 7, 7 };
			return moviment;
		}

		this.partida = (PartidaGomoku) partida;
		this.color = estat_casella;

		millors_caselles = new PriorityQueue<int[]>();
		int mida = partida.getTauler().getMida();
		this.analisi_jugador = new int[mida][mida];
		this.analisi_oponent = new int[mida][mida];

		return computaMoviment();
	}

	/**
	 * Mètode auxiliar que fa la crida s'encarrega de reiniciar els anàlisis abans de cridar l'anàlisi del tauler i la
	 * decisió del millor moviment
	 * 
	 * @return Coordenades del millor moviment segons aquesta IA per al tauler donat al mètode <em>computaMoviment</em>
	 *         amb paràmetres
	 */
	public int[] computaMoviment()
	{
		/* Es reinicien els anàlisis a cada crida a computaMoviment, simplifica el plantejament dels algorismes que
		 * actualitzen i puntuen caselles */
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

	/**
	 * Enumeració auxiliar que representa una direcció al tauler
	 * 
	 * @author Mauricio Ignacio Contreras Pinilla
	 * 
	 */
	private enum Direccio
	{
		HORITZONTAL, VERTICAL, DIAGONAL_DESC, DIAGONAL_ASC

	};

	/**
	 * Mètode encarregat de retonar una puntuació d'oportunitat donada una casella i una direcció a analitzar
	 * 
	 * @param fila Fila de la casella a tenir en compte
	 * @param columna Fila de la col·lumna a tenir en compte
	 * @param tauler Tauler a analitzar la casella senyalada per (<em>fila</em>, <em>col·lumna</em>
	 * @param dir Direcció en la qual es comptarà el <em>potencial</em> de la línia
	 * @return <em>Potencialitat</em> de (o puntuació associada a) la línia que passa per (<em>fila</em>,
	 *         <em>col·lumna<em>) en la direcció <em>dir</em>
	 */
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

	/**
	 * Mètode auxiliar que permet saber si per una posició ocupada específica a un tauler, es podria crear,
	 * potencialment, una línia de 5 fitxes del mateix jugador al qual pertany la fitxa indicada
	 * 
	 * @param fila Fila de la posició d'interès
	 * @param columna Col·lumna de la posició d'interès
	 * @param tauler Tauler on s'analitza la posició indicada
	 * @param dir Direcció en la qual es vol comprovar la possibilitat de creació de línia de 5 fitxes
	 * @return <em>true</em> si es pot crear una línia de 5 fitxes on participi la posició indicada en la direcció
	 *         proporcionada
	 */
	private boolean potCrearLinia( int fila, int columna, TaulerGomoku tauler, Direccio dir )
	{

		EstatCasella color_fitxa = tauler.getEstatCasella( fila, columna );
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
		return false;
	}

	/**
	 * Mètode encarregat d'analitzar una casella ocupada i aplicar la puntuació corresponent en el seu camp d'influència
	 * 
	 * @param fila Fila de la posició a tractar
	 * @param columna Col·lumna de la posició a tractar
	 * @param tauler Tauler de l'anàlisi
	 * @param puntuacio Vector amb puntuacions que permeten la matització dels valors assignats a les caselles de la
	 *        zona d'influència segons la seva distància amb la casella especificada
	 * @param analisi Matriu on s'emmagatzema l'anàlisi
	 */
	private void aplicaPuntuacions( int fila, int columna, TaulerGomoku tauler, int[] puntuacio, int[][] analisi )
	{
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

	/**
	 * Mètode auxiliar intermig que donada una posició a un tauler pren les decisions necessàries per cridar
	 * <em>aplicaPuntuacions</em> amb tots els paràmetres possibles (quina puntuació assignar a l'oponent, anàlisi
	 * destí, etc.)
	 * 
	 * @param fila Fila de la casella al voltant de la qual es volen aplicar les puntuacions
	 * @param columna Col·lumna de la casella al voltant de la qual es volen aplicar les puntuacions
	 * @param tauler Tauler on es realitza l'anàlisi
	 */
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

	/**
	 * Mètode encarregat de gestionar l'actualització dels anàlisis. Recòrrer el tauler i disparar els mètodes adequats
	 * segons les fitxes de cada casella
	 */
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
}
