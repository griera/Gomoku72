package prop.gomoku.domini.controladors;

import java.util.Random;

import prop.cluster.domini.controladors.InteligenciaArtificial;
import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.models.TaulerGomoku;

/**
 * La classe <code>IAGomoku</code> representa el comportament d'un jugador del joc de taula Gomoku seguint l'algorisme
 * MiniMax de la seva classe pare <code>InteligenciaArtificial</code>. Redefineix el mètode per a poder avaular un
 * tauler durant una partida al Gomoku, el qual ajuda a guiar l'execució de l'algorisme MiniMax i fa que es comporti
 * seguint una certa lògica.
 * <p>
 * Si el jugador controlat per aquesta intel·ligència artificial veu que ja ha perdut matemàticament, com que en aquest
 * cas no exiteix cap moviment òptim a efectuar, es fa un moviment aleatori gràcies a uns mètodes que ho permeten fer.
 * 
 * @author Genís Riera Pérez
 * 
 */
public class IAGomoku extends InteligenciaArtificial
{
	/**
	 * Indica a quin nivell de profunditat tallem l'exploració recursiva de l'arbre de moviments generat
	 */
	private static final int profunditat_maxima = 3;

	/**
	 * Donada una partida amb una certa situació i la fitxa del jugador que ha de moure durant el torn actual, calcula
	 * quina és la millor posició del tauler on realitzar el següent moviment.
	 * 
	 * @param partida Objecte de la classe <code>Partida</code> que representa la partida actual en joc.
	 * @param estat_casella Representa la fitxa del jugador que ha de disputar el torn actual de la partida.
	 * @return La posició òptima on el jugador amb fitxes <em>fitxa_jugador</em> ha de fer el seu moviment al seu torn a
	 *         <em>partida</em>. La posició ve representada per les seves dues coordenades (número de fila i número de
	 *         columna).
	 */
	public int[] computaMoviment( Partida partida, EstatCasella estat_casella, int fila_ult_moviment,
			int columna_ult_moviment )
	{
		int[] moviment = new int[2];
		int torn_actual = partida.getTornsJugats() + 1;
		int mida = partida.getTauler().getMida();

		// Si s'ha de jugar el primer torn de la partida, aleshores el moviment més òptim és col·locar una fitxa al
		// centre del tauler
		if ( torn_actual == 1 )
		{
			moviment[0] = ( mida / 2 );
			moviment[1] = ( mida / 2 );
		}

		// Si s'ha de jugar el segon torn de la partida, aleshores el moviment més òptim és col·locar una fitxa al
		// al voltant de la primera fitxa col·locada per l'oponent, de manera aleatòria perquè no hi ha cap costat
		// adjacent a aquesta a priori més o menys favorable a bloquejar
		else if ( torn_actual == 2 )
		{
			moviment = this.movimentAdjacentAleatori( fila_ult_moviment, columna_ult_moviment, mida );
		}

		// Per a la resta de casos, cal calcuar el moviment més òptim seguint els algorismes i estratègies implementats
		else
		{
			moviment = this.minimax( partida, estat_casella, IAGomoku.profunditat_maxima );
		}

		// Si la partida ja l'ha guanyada matemàticament l'oponent, es realitza un moviment aleatori, ja que en aquest
		// no existeix cap moviment òptim que pugui revocar aquesta situació i evitar la derrota
		if ( moviment[0] == -1 && moviment[1] == -1 )
		{
			moviment = this.movimentAleatori( (TaulerGomoku) partida.getTauler() );

		}
		return moviment;
	}

	@Override
	/* 
	 * Aquesta funció d'avaluació està especialment implementada per al joc de taula Gomoku. Per avaluar un cert estat
	 * d'un tauler es basa en el recompte d'estructures potencialment guanyadores que tenen cada jugador.
	 * 
	 * Cada estructura està valorada amb una puntuació. Les que més punts tenen són aquelles que estan més a prop de
	 * conseguir l'objectiu final (muntar una línia de cinc fitxes seguides d'un mateix color). Una línia de cinc val la
	 * màxima puntuació (Integer.MAX:VALUE o Integer.MIN_VALUE en funció dels interessos del jugador controlat per
	 * aquesta intel·ligència artificial).
	 * 
	 * Aleshores el que cal comptar és, per cada fitxa d'un color trobada en el tauler, totes les seves estructures
	 * potencialment guanyadores, després multiplicar-les per la seva valoració establerta, i finalment sumar totes les
	 * puntuacions de cada estructura.
	 * 
	 * Aquest càlcul s'ha de realitzar per cada jugador que disputa la partida en el tauler donat, és a dir, tindrem la
	 * suma total de totes les estructures potencialment guanyadores per al jugador amb fitxes negres, i la suma
	 * homòloga per al jugador amb fitxes blanques.
	 * 
	 * Per acabar haurem de restar les dues sumes anteriors per conèixer quant de bo és l'estat que presenta el tauler
	 * per als interessos del jugador controlat per aquesta intel·ligència artificial. Depenent de quin color de fitxes
	 * tingui aquest jugador, haurem de restar la suma de total de les estructures potencialment guanyadores pertanyents
	 * a les fitxes del seu color menys la suma total homòloga de les fitxes del jugador oponent.
	 * 
	 * Abans cal conèixer la següent terminologia per entendre els pròxims comentaris:
	 * 
	 * - Estructura simple: línia d'un cert nombre de fitxes seguides i del mateix color.
	 * 
	 * - Estructura complexa: Fusió de dues estructures simples formades a partir d'una fitxa central, que representa el
	 * punt de connexió de les dues estructures simples.
	 * 
	 * - Estructura oberta: Estructura els extrems de la qual no estan bloquejats per cap fitxa de l'oponent ni es
	 * troben just als límits del tauler.
	 * 
	 * - Estructura semioberta: Estructura que un dels seus extrems no està bloquejats per cap fitxa de l'oponent ni es
	 * troba just als límits del tauler.
	 */
	public int funcioAvaluacio( Tauler tauler, EstatPartida estat_moviment, int profunditat, EstatCasella fitxa_jugador )
	{
		if ( estat_moviment == EstatPartida.EMPAT )
		{
			return 0;
		}

		/* Si la partida ja ha finalitzat, i no ha acabat en empat, segons la paritat de la profunditat aconseguida
		 * durant l'execució de l'algorisme MiniMax, ja es pot conèixer el guanyador. Per tant, ja es pot retornar la
		 * puntuació màxima.
		 * 
		 * Si la paritat és senar, voldrà dir que hem arribat a un node Max segons l'algorisme MiniMax, és a dir, que el
		 * jugador controlat per aquesta intel·ligència artificial haurà guanyat la partida.
		 * 
		 * Si la paritat és parella, voldrà dir tot el contrari (per això en aquest cas retorna Integer.MIN_VALUE,
		 * perquè és la puntuació màxima des del punt de vista de l'oponent). */

		else if ( estat_moviment != EstatPartida.NO_FINALITZADA && profunditat % 2 == 1 )
		{
			return Integer.MAX_VALUE;
		}

		else if ( estat_moviment != EstatPartida.NO_FINALITZADA && profunditat % 2 == 0 )
		{
			return Integer.MIN_VALUE;
		}

		else
		{
			int mida = tauler.getMida();

			/* 
			 * Array que guarda les diferents puntuacions per a cadascuna de les estructures potencialment guanyadores.
			 * Les estructures que es tenen en compte per puntuar són les següents (classificades per l'índex de la
			 * columna del array al qual fan referència):
			 * 
			 * 0.- Estructura simple de cinc fitxes seguides del mateix color (estructura guanyadora).
			 * 
			 * 1.- Estructura simple oberta de quatre fitxes.
			 * 
			 * 2.- Estructura complexa 4x4 semioberta (dues estructures simples semiobertes de quatre fitxes).
			 * 
			 * 3.- Estructura complexa d'una línia de quatre fitxes semioberta més una línia de tres fitxes oberta.
			 * 
			 * 4.- Estructura complexa 3x3 oberta (dues estructures simples obertes de tres fitxes).
			 * 
			 * 5.- Estructura complexa d'una línia de tres fitxes semioberta més una línia de tres fitxes oberta.
			 * 
			 * 6.- Estructura simple semioberta de quatre fitxes.
			 * 
			 * 7.- Estructura simple oberta de tres fitxes.
			 * 
			 * 8.- Estructura complexa 2x2 oberta (dues estructures simples obertes de dues fitxes).
			 * 
			 * 9.- Estructura simple semioberta de tres fitxes.
			 * 
			 * 10.- Estructura complexa 2x2 semioberta (dues estructures simples semiobertes de dues fitxes).
			 * 
			 * 11.- Estructura simple oberta de dues fitxes.
			 * 
			 * 12.- Estructura simple semioberta de dues fitxes.
			 * 
			 * 13.- Estructura simple oberta de tres fitxes quasi seguides (conté una casella buida emmig de
			 * l'estructura).
			 * 
			 * 14.- Estructura oberta o semioberta de quatre fitxes quasi seguides (conté una casella buida emmig de
			 * l'estructura). 
			 */
			int[] puntuacions = { Integer.MAX_VALUE, 10000, 10000, 10000, 5000, 1000, 500, 200, 100, 50, 10, 5, 3,
					5000, 10000 };

			/* 
			 * Array bidimensional on es guarden totes les estructures potencialment guanyadores que es formen en el
			 * tauler donat. La primera fila conté el recompte de totes les estructures potencialment guanyadores per a
			 * les fitxes de color negre, i la segona fila conté el mateix recompte però per a les fitxes de color
			 * blanc. L'ordre en que es guarden és el mateix que l'ordre de les seves puntuacions en el array
			 * puntuacions.
			 */
			int opcions_linia_total[][] = new int[2][15];

			// Variables que guarden la suma total de les puntuacions de totes les estructures potencialment
			// guanyadores per a les fitxes negres i per a les fitxes blanques respectivament
			int avaluacio_negre = 0;
			int avaluacio_blanc = 0;
			for ( int fila = 0; fila < mida; ++fila )
			{
				for ( int columna = 0; columna < mida; ++columna )
				{
					// Si ens trobem una fitxa, hem de comptar i avaluar totes les estructures potencialment
					// guanyadores que es formen al voltant seu
					if ( tauler.getEstatCasella( fila, columna ) != EstatCasella.BUIDA )
					{
						EstatCasella estat = tauler.getEstatCasella( fila, columna );

						/* 
						 * Array bidimensional on es guarden totes les estructures potencialment guanyadores que es
						 * formen donada una fitxa col·locada dins del tauler. La primera fila compta totes les
						 * estructures potencialment guanyadores si la fitxa actual és de color negra, i la segona fila
						 * ho fa si la fitxa actual és de color blanc. L'ordre en que es guarden les estructuresés el
						 * mateix que l'ordre de les seves puntuacions en el array puntuacions.
						 */
						int[][] opcions_linia = new int[2][15];

						this.computaFila( (TaulerGomoku) tauler, estat, fila, columna, opcions_linia );
						this.computaColumna( (TaulerGomoku) tauler, estat, fila, columna, opcions_linia );
						this.computaDiagonalNoSe( (TaulerGomoku) tauler, estat, fila, columna, opcions_linia );
						this.computaDiagonalNeSo( (TaulerGomoku) tauler, estat, fila, columna, opcions_linia );
						this.fusionaEstructures( estat, opcions_linia );

						// Acumulem les estructures potencialment guanyadores comptades a partir de l'actual fitxa dins
						// del array bidimensional de recompte global opcions_linia_total
						for ( int i = 0; i < opcions_linia_total.length; ++i )
						{
							for ( int j = 0; j < opcions_linia_total[0].length; ++j )
							{
								opcions_linia_total[i][j] += opcions_linia[i][j];
							}
						}

						/* 
						 * Les següents sentències condicionals comproven si s'ha trobat alguna línia de cinc fitxes
						 * seguides d'un mateix color (estructura ja guanyadora, l'objectiu del joc Gomoku). En aquest
						 * cas, es retorna la puntuació màxima segons el color de les fitxes del jugador controlat per
						 * aquesta intel·ligència artificial.
						 */
						if ( opcions_linia[0][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_A )
						{
							return Integer.MAX_VALUE;
						}

						if ( opcions_linia[0][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_B )
						{
							return Integer.MIN_VALUE;
						}

						if ( opcions_linia[1][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_A )
						{
							return Integer.MIN_VALUE;
						}

						if ( opcions_linia[1][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_B )
						{
							return Integer.MAX_VALUE;
						}

						/* 
						 * Si no hi ha encara cap estructura guanyadora (cap guanyador de la partida), aleshores s'ha de
						 * calcular la puntuació acumulada per les estructures potencialment guanyadores formades al
						 * voltant de la fitxa actual (càlcul incremental per cadascun dels jugadors), i sumar-les a les
						 * variables que acumulen la puntuació total donat un tauler (avaluacio_negre i avaluacio_blanc)
						 */
						for ( int i = 0; i < puntuacions.length; ++i )
						{
							avaluacio_negre += ( opcions_linia[0][i] * puntuacions[i] );
							avaluacio_blanc += ( opcions_linia[1][i] * puntuacions[i] );
						}

						// Final del recompte i avaluació d'opcions per a una fitxa trobada en el tauler
						// Continuem recorrent el tauler en busca de més fitxes per realitzar els mateixos càlculs
					}
				}
			}

			/* 
			 * Les següents sentències condicionals comproven que no hi hagi ja un guanyador matemàtic de la partida
			 * segons l'estat del tauler donat. Si és aquest cas, cal donar la puntuació màxima en funció del jugador
			 * controlat per aquesta intel·ligència artificial. Retornarem la màxima puntuació (Integer.MAX_VALUE) si és
			 * ell el que s'ha assegurat la victòria abans d'hora, i retornarem (Integer.MIN_VALUE) si és el seu oponent
			 * qui ja té la victòria assegurada.
			 * 
			 * Aquesta selecció es basa en les estructures potencialment guanyadores que tenen cadascun dels jugadors en
			 * el tauler indicat. 
			 */

			if ( fitxa_jugador == EstatCasella.JUGADOR_A )
			{
				if ( opcions_linia_total[1][1] > 0 || opcions_linia_total[1][2] > 0 || opcions_linia_total[1][3] > 0
						|| opcions_linia_total[1][6] > 0 || opcions_linia_total[1][14] > 0 )
				{
					return Integer.MIN_VALUE;
				}

				if ( opcions_linia_total[0][1] > 0 || opcions_linia_total[0][2] > 0 || opcions_linia_total[0][3] > 0 )
				{
					/* 
					 * Restem una unitat al valor màxim dels enters per evitar que, si el jugador controlat per aquesta
					 * intel·ligència artificial ja té la victòria garantida, faci moviments sense lògica fins que el
					 * seu oponent no intenti contrarestar aquesta situació.
					 */
					return Integer.MAX_VALUE - 1;
				}

				if ( opcions_linia_total[1][4] > 0 || opcions_linia_total[1][5] > 0 || opcions_linia_total[1][6] > 0
						|| opcions_linia_total[1][7] > 0 || opcions_linia_total[1][13] > 0 )
				{
					return Integer.MIN_VALUE;
				}
			}

			if ( fitxa_jugador == EstatCasella.JUGADOR_B )
			{
				if ( opcions_linia_total[0][1] > 0 || opcions_linia_total[0][2] > 0 || opcions_linia_total[0][3] > 0
						|| opcions_linia_total[0][6] > 0 || opcions_linia_total[0][14] > 0 )
				{
					return Integer.MIN_VALUE;
				}

				if ( opcions_linia_total[1][1] > 0 || opcions_linia_total[1][2] > 0 || opcions_linia_total[1][3] > 0 )
				{
					/* 
					 * Restem una unitat al valor màxim dels enters per evitar que, si el jugador controlat per aquesta
					 * intel·ligència artificial ja té la victòria garantida, faci moviments sense lògica fins que el
					 * seu oponent no intenti contrarestar aquesta situació.
					 */
					return Integer.MAX_VALUE - 1;
				}

				if ( opcions_linia_total[0][4] > 0 || opcions_linia_total[0][5] > 0 || opcions_linia_total[0][6] > 0
						|| opcions_linia_total[0][7] > 0 || opcions_linia_total[0][13] > 0 )
				{
					return Integer.MIN_VALUE;
				}
			}

			/* 
			 * Ja hem recorregut tot el tauler, per tant, ja hem fet totes les avaluacions Ara només cal retornar els
			 * resultats obtinguts en funció del color de les fitxes del jugador controlat per aquesta intel·ligència
			 * artificial. Si té fitxes negres cal retornar la resta entre: avaluació_negre - avaluacio_blanc
			 * 
			 * Si, en canvi, si té les fitxes blanques cal retornar: avaluacio_blanc - avaluacio_negre
			 */

			if ( fitxa_jugador == EstatCasella.JUGADOR_A )
			{
				return avaluacio_negre - avaluacio_blanc;
			}
			return avaluacio_blanc - avaluacio_negre;
		}
	}

	/**
	 * Mètode que fusiona estructures potencialment guanyadores simples (comptabilitzades en el array <em>opcions_linia
	 * </em>) en estructures potencialment guanyadores complexes, actualitzant d'aquesta manera el recompte acumulat en
	 * el mateix array.
	 * <p>
	 * Això significa que, si al voltant d'una fitxa hi ha dues línies de dos fitxes del mateix color seguides, llavors
	 * passem de tenir dues estructures simples de duess fitxes a tenir una estructura complexa 2x2. Aquesta estructura
	 * té una valoarció molt més bona que la suma de tenir dues estructures simples de dos fitxes situades en diferents
	 * posicions dins el tauler (entre elles no existeix cap fitxa en comú que les uneixi).
	 * 
	 * @param estat Representa el color de la fitxa a partir de la qual realitzar la fusió.
	 * @param opcions_linia Array bidimensional que guarda el recompte de totes les estructures potencialment
	 *        guanyadores simples formades a partir d'una fitxa de color <em>estat</em>.
	 */
	protected void fusionaEstructures( EstatCasella estat, int[][] opcions_linia )
	{
		int color = ( estat == EstatCasella.JUGADOR_A ) ? 0 : 1;

		if ( opcions_linia[color][6] > 1 )
		{
			++opcions_linia[color][2];
			opcions_linia[color][6] = 0;
		}

		else if ( opcions_linia[color][6] >= 1 && opcions_linia[color][7] >= 1 )
		{
			++opcions_linia[color][3];
			opcions_linia[color][6] = 0;
			opcions_linia[color][7] = 0;
		}

		else if ( opcions_linia[color][7] > 1 )
		{
			++opcions_linia[color][4];
			opcions_linia[color][7] = 0;
		}

		else if ( opcions_linia[color][7] >= 1 && opcions_linia[color][9] >= 1 )
		{
			++opcions_linia[color][5];
			opcions_linia[color][7] = 0;
			opcions_linia[color][9] = 0;
		}

		else if ( opcions_linia[color][11] > 1 )
		{
			++opcions_linia[color][8];
			opcions_linia[color][11] = 0;
		}

		else if ( opcions_linia[color][12] > 1 )
		{
			++opcions_linia[color][10];
			opcions_linia[color][12] = 0;
		}
	}

	/**
	 * Mètode que classifica l'estructura potencialment guanyadora segons el nombre de fitxes seguides que la formen, i
	 * segons si és una estructura oberta o semioberta.
	 * <p>
	 * Un cop analitzada de quina estructura es tracta, aleshores es guarda dins del array <em>opcions_linia</em> en la
	 * seva posició corresponent, segons el color de la fitxa a partir de la qual s'ha llegit l'estructura potencialment
	 * guanyadora i segons quina puntuació tingui associada.
	 * 
	 * @param estat Representa el color de la fitxa a partir de la qual realitzar la classificació.
	 * @param opcions_linia Array bidimensional que guarda el recompte de totes les estructures potencialment
	 *        guanyadores simples formades a partir d'una fitxa de color <em>estat</em>.
	 * @param oberta Indica si l'estructura potencialment guanyadora a classificar és oberta.
	 * @param semioberta Indica si l'estructura potencialment guanyadora a classificar és oberta.
	 * @param linia_amb_forat Indica si l'estructura potencialment guanyadora a classificar conté una casella buida
	 *        enmig (estructura que les fitxes que la formen no estan completament seguides).
	 * @param fitxes_seguides Indica el nombre de fitxes seguides que té l'estructura potencialment guanyadora a
	 *        classificar.
	 */
	private void classificaEstructura( EstatCasella estat, int[][] opcions_linia, boolean oberta, boolean semioberta,
			boolean linia_amb_forat, int fitxes_seguides )
	{
		// Indica quina estructura s'ha analitzat per classificar-la correctament dins del array opcions_linia.
		int estructura = 0;

		switch ( fitxes_seguides )
		{
			case 4:
				estructura = ( oberta ) ? 1 : 6;
				break;

			case 3:
				if ( linia_amb_forat )
				{
					estructura = 14;
				}

				else
				{
					estructura = ( oberta ) ? 7 : 9;
				}
				break;

			case 2:
				if ( linia_amb_forat && oberta )
				{
					estructura = 13;
				}

				else
				{
					estructura = ( oberta ) ? 11 : 12;
				}
				break;

			default:
				break;
		}

		int color = ( estat == EstatCasella.JUGADOR_A ) ? 0 : 1;
		++opcions_linia[color][estructura];
	}

	/**
	 * Compta el nombre d'estructures potencialment guanyadores que es troben recorrent la fila (en els dos sentits
	 * possibles) on està situada la fitxa actual sobre <em>tauler</em> (en la posició (<em>fila</em>, <em>columna</em>
	 * )).
	 * 
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> sobre el qual realitzar el recompte.
	 * @param estat Representa el color de la fitxa a partir de la qual realitzar el recompte.
	 * @param fila Índex de la fila on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param columna Índex de la columna on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param opcions_linia Array bidimensional on es guarden totes les estructures potencialment guanyadores formades
	 *        donada una fitxa col·locada dins de <em>tauler</em>.
	 */
	private void computaFila( TaulerGomoku tauler, EstatCasella estat, int fila, int columna, int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		int mida = tauler.getMida();
		int k = columna + 1;
		boolean linia_oberta_dreta = false;
		boolean linia_oberta_esquerra = false;
		boolean linia_amb_forat = false;
		while ( k < mida && estat == tauler.getEstatCasella( fila, k ) )
		{
			++fitxes_seguides;
			++k;
		}

		if ( k < mida && tauler.getEstatCasella( fila, k ) == EstatCasella.BUIDA )
		{
			linia_oberta_dreta = true;
			if ( k + 1 < mida && tauler.getEstatCasella( fila, k + 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = columna - 1;
		while ( k >= 0 && estat == tauler.getEstatCasella( fila, k ) )
		{
			++fitxes_seguides;
			--k;
		}
		if ( k >= 0 && tauler.getEstatCasella( fila, k ) == EstatCasella.BUIDA )
		{
			linia_oberta_esquerra = true;
			if ( k - 1 >= 0 && tauler.getEstatCasella( fila, k - 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_dreta && linia_oberta_esquerra; // linia completament oberta?
		boolean semioberta = linia_oberta_dreta || linia_oberta_esquerra; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			this.classificaEstructura( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	/**
	 * Compta el nombre d'estructures potencialment guanyadores que es troben recorrent la columna (en els dos sentits
	 * possibles) on està situada la fitxa actual sobre <em>tauler</em> (en la posició (<em>fila</em>, <em>columna</em>
	 * )).
	 * 
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> sobre el qual realitzar el recompte.
	 * @param estat Representa el color de la fitxa a partir de la qual realitzar el recompte.
	 * @param fila Índex de la fila on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param columna Índex de la columna on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param opcions_linia Array bidimensional on es guarden totes les estructures potencialment guanyadores formades
	 *        donada una fitxa col·locada dins de <em>tauler</em>.
	 */
	private void computaColumna( TaulerGomoku tauler, EstatCasella estat, int fila, int columna, int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		int mida = tauler.getMida();
		int k = fila + 1;
		boolean linia_oberta_inferior = false;
		boolean linia_oberta_superior = false;
		boolean linia_amb_forat = false;
		while ( k < mida && estat == tauler.getEstatCasella( k, columna ) )
		{
			++fitxes_seguides;
			++k;
		}

		if ( k < mida && tauler.getEstatCasella( k, columna ) == EstatCasella.BUIDA )
		{
			linia_oberta_inferior = true;
			if ( k + 1 < mida && tauler.getEstatCasella( k + 1, columna ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = fila - 1;
		while ( k >= 0 && estat == tauler.getEstatCasella( k, columna ) )
		{
			++fitxes_seguides;
			--k;
		}

		if ( k >= 0 && tauler.getEstatCasella( k, columna ) == EstatCasella.BUIDA )
		{
			linia_oberta_superior = true;
			if ( k - 1 >= 0 && tauler.getEstatCasella( k - 1, columna ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_superior && linia_oberta_inferior; // linia completament oberta?
		boolean semioberta = linia_oberta_superior || linia_oberta_inferior; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			this.classificaEstructura( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	/**
	 * Compta el nombre d'estructures potencialment guanyadores que es troben recorrent la diagonal nord-oest cap a
	 * sud-est (i viceversa) on està situada la fitxa actual sobre <em>tauler</em> (en la posició (<em>fila</em>, <em>
	 * columna</em>)).
	 * 
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> sobre el qual realitzar el recompte.
	 * @param estat Representa el color de la fitxa a partir de la qual realitzar el recompte.
	 * @param fila Índex de la fila on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param columna Índex de la columna on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param opcions_linia Array bidimensional on es guarden totes les estructures potencialment guanyadores formades
	 *        donada una fitxa col·locada dins de <em>tauler</em>.
	 */
	private void computaDiagonalNoSe( TaulerGomoku tauler, EstatCasella estat, int fila, int columna,
			int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		int mida = tauler.getMida();
		int k = fila + 1;
		int l = columna + 1;
		boolean linia_oberta_sudest = false;
		boolean linia_oberta_nordoest = false;
		boolean linia_amb_forat = false;
		while ( k < mida && l < mida && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			++k;
			++l;
		}

		if ( k < mida && l < mida && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_sudest = true;
			if ( k + 1 < mida && l + 1 < mida && tauler.getEstatCasella( k + 1, l + 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = fila - 1;
		l = columna - 1;
		while ( k >= 0 && l >= 0 && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			--k;
			--l;
		}

		if ( k >= 0 && l >= 0 && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_nordoest = true;
			if ( k - 1 >= 0 && l - 1 >= 0 && tauler.getEstatCasella( k - 1, l - 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_sudest && linia_oberta_nordoest; // linia completament oberta?
		boolean semioberta = linia_oberta_sudest || linia_oberta_nordoest; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			this.classificaEstructura( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	/**
	 * Compta el nombre d'estructures potencialment guanyadores que es troben recorrent la diagonal nord-est cap a
	 * sud-oest (i viceversa) on està situada la fitxa actual sobre <em>tauler</em> (en la posició (<em>fila</em>, <em>
	 * columna</em>)).
	 * 
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> sobre el qual realitzar el recompte.
	 * @param estat Representa el color de la fitxa a partir de la qual realitzar el recompte.
	 * @param fila Índex de la fila on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param columna Índex de la columna on es troba situada la fitxa a avaluar dins de <em>tauler</em>.
	 * @param opcions_linia Array bidimensional on es guarden totes les estructures potencialment guanyadores formades
	 *        donada una fitxa col·locada dins de <em>tauler</em>.
	 */
	private void computaDiagonalNeSo( TaulerGomoku tauler, EstatCasella estat, int fila, int columna,
			int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		int mida = tauler.getMida();
		int k = fila - 1;
		int l = columna + 1;
		boolean linia_oberta_nordest = false;
		boolean linia_oberta_sudoest = false;
		boolean linia_amb_forat = false;
		while ( k >= 0 && l < mida && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			--k;
			++l;
		}

		if ( k >= 0 && l < mida && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_nordest = true;
			if ( k - 1 >= 0 && l + 1 < mida && tauler.getEstatCasella( k - 1, l + 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = fila + 1;
		l = columna - 1;
		while ( k < mida && l >= 0 && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			++k;
			--l;
		}

		if ( k < mida && l >= 0 && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_sudoest = true;
			if ( k + 1 < mida && l - 1 >= 0 && tauler.getEstatCasella( k + 1, l - 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_nordest && linia_oberta_sudoest; // linia completament oberta?
		boolean semioberta = linia_oberta_nordest || linia_oberta_sudoest; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			this.classificaEstructura( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	/**
	 * Genera un moviment adjacent a la posició indicada per (<em>fila</em>, <em>columna</em>) dins el tauler. El tauler
	 * sobre el qual s'aplica està totalment buit excepte la casella representada per la posició (<em>fila</em>,
	 * <em>columna</em>).
	 * 
	 * @param fila Índex de la fila a on es vol efectuar el moviment adjacent.
	 * @param columna Índex de la columna a on es vol efectuar el moviment adjacent.
	 * @param mida Mida del tauler a on es juga la partida al Gomoku (ha de ser un tauler quadrat, amb el mateix nombre
	 *        de files que de columnes).
	 * @return Una posició adjacent a la posició dins el taulerindicada per (<em>fila</em>, <em>columna</em>).
	 */
	public int[] movimentAdjacentAleatori( int fila, int columna, int mida )
	{
		int k;
		int l;
		int[] coordenades_adjacents = { -1, 0, 1 };
		do
		{
			Random aleatori = new Random();
			k = (int) Math.round( aleatori.nextDouble() * 2 );
			l = (int) Math.round( aleatori.nextDouble() * 2 );
			k = fila + coordenades_adjacents[k];
			l = columna + coordenades_adjacents[l];
		} while ( k < 0 || l < 0 || k >= mida || l >= mida || ( k == fila && l == columna ) );
		int[] moviment_aleatori = { k, l };
		return moviment_aleatori;
	}

	/**
	 * Genera un moviment aleatori dins del tauler <em>tauler</em> durant una partida al joc Gomoku.
	 * 
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> que representa el tauler d'una partida al Gomoku.
	 * @return Una posició aleatoria dins del tauler <em>tauler</em> representada per un array d'enters de mida dos. El
	 *         primer element representa l'índex de la fila i el segon l'índex de la columna de <em>tauler</em>
	 */
	public int[] movimentAleatori( TaulerGomoku tauler )
	{
		int[] moviment_aleatori = new int[2];
		int mida = tauler.getMida();
		EstatCasella estat;
		do
		{
			Random aleatori = new Random();
			moviment_aleatori[0] = (int) Math.round( aleatori.nextDouble() * ( mida - 1 ) );
			moviment_aleatori[1] = (int) Math.round( aleatori.nextDouble() * ( mida - 1 ) );
			estat = tauler.getEstatCasella( moviment_aleatori[0], moviment_aleatori[1] );
		} while ( estat != EstatCasella.BUIDA );
		return moviment_aleatori;
	}
}
