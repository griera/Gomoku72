package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import prop.cluster.domini.models.Partida;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.models.TaulerGomoku;

/**
 * La classe <code>IAGomokuOptimitzada</code> representa el comportament d'un jugador del joc de taula Gomoku seguint
 * l'algorisme MiniMax de la seva classe pare <code>InteligenciaArtificial</code>.
 * <p>
 * Avalua un estat de tauler de la mateixa manera que ho fa la classe <code>IAGomoku</code>, però a diferència d'aquesta
 * implementa una optimització per aprofitar la poda alfa-beta implementada en el mètode <code>minimax</code> de la seva
 * classe pare <code>InteligenciaArtificial</code>. Aquesta consisteix en ordenar els nodes d'un mateix nivell segons el
 * seu valor una vegada aplicada sobre aquest la funció d'avaluació. D'aquesta manera només explorarem aquells nodes més
 * prometedors per acabar arribant a la victòria de la partida.
 * <p>
 * A més, aquesta classe incorpora una altra optimització per reduïr el nombre de possibles moviments a explorar. Es
 * basa en centrar el focus d'estudi en el nucli del tauler on estan dipositades les fitxes. Limitant el tauler a un nou
 * subtauler d'aquest, estem reduïnt moviments superflus d'estudiar i ens centrem només en aquells on té sentit fer el
 * següent moviment.
 * <p>
 * Si el jugador controlat per aquesta intel·ligència artificial veu que ja ha perdut matemàticament, com que en aquest
 * cas no exiteix cap moviment òptim a efectuar, es fa un moviment aleatori gràcies a uns mètodes que ho permeten fer.
 * 
 * @author Genís Riera Pérez
 */
public class IAGomokuOptimitzada extends IAGomoku
{
	/**
	 * Indica a quin nivell de profunditat tallem l'exploració recursiva de l'arbre de moviments generat
	 */
	private static final int profunditat_maxima = 4;

	@Override
	public int[] computaMoviment( Partida partida, EstatCasella estat_casella, int fila_ult_moviment,
			int columna_ult_moviment )
	{
		System.out.println("XUNGU");
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
			moviment = this.minimax( partida, estat_casella, IAGomokuOptimitzada.profunditat_maxima );
		}

		// Si la partida ja l'ha guanyada matemàticament l'oponent, es realitza un moviment aleatori, ja que en aquest
		// no existeix cap moviment òptim que pugui revocar aquesta situació i evitar la derrota
		if ( moviment[0] == -1 && moviment[1] == -1 )
		{
			moviment = this.movimentAleatori( (TaulerGomoku) partida.getTauler() );

		}
		return moviment;
	}

	/**
	 * Classe privada i interna a la classe <code>IAGomokuOptimitzada</code> que serveix per representar la informació
	 * necessaria que l'algorisme necessita per poder ordenar els nodes d'un nivell recursiu durant la generació de
	 * possibles moviment vàlids.
	 * 
	 * @author Genís Riera Pérez
	 * 
	 */
	private class Informacio
	{
		/**
		 * Indica la valoració d'un cert estat d'un tauler.
		 */
		int valor;

		/**
		 * Indica la fila de l'útlim moviment efectuat sobre el tauler, el qual ha provocat que aquest adquireixi el seu
		 * estat.
		 */
		int fila;

		/**
		 * Indica la columna de l'útlim moviment efectuat sobre el tauler, el qual ha provocat que aquest adquireixi el
		 * seu estat.
		 */
		int columna;

		/**
		 * Mètode constructor de la classe
		 * 
		 * @param valor Indica la valoració d'un cert estat del tauler a estudiar.
		 * @param fila Indica la fila de l'útlim moviment efectuat sobre el tauler.
		 * @param columna Indica la columna de l'útlim moviment efectuat sobre el tauler.
		 */
		public Informacio( int valor, int fila, int columna )
		{
			this.valor = valor;
			this.fila = fila;
			this.columna = columna;
		}
	}

	/**
	 * Classe privada i interna a la classe <code>IAGomokuOptimitzada</code> que proporciona la manera de com comparar
	 * dos objectes de la classe <code>Informacio</code>, de més prioritari a menys prioritari.
	 * 
	 * @author Genís Riera Pérez
	 * 
	 */
	private class ComparadorInformacio implements Comparator<Informacio>
	{
		@Override
		public int compare( Informacio info_a, Informacio info_b )
		{
			if ( info_a.valor > info_b.valor )
			{
				return -1;
			}

			else if ( info_a.valor < info_b.valor )
			{
				return 1;
			}

			return 0;
		}
	}

	/**
	 * Classe privada i interna a la classe <code>IAGomokuOptimitzada</code> que proporciona la manera de com comparar
	 * dos objectes de la classe <code>Informacio</code>, de menys prioritari a més prioritari.
	 * 
	 * @author Genís Riera Pérez
	 * 
	 */
	private class ComparadorInformacioInvers implements Comparator<Informacio>
	{
		@Override
		public int compare( Informacio info_a, Informacio info_b )
		{
			if ( info_a.valor > info_b.valor )
			{
				return 1;
			}

			else if ( info_a.valor < info_b.valor )
			{
				return -1;
			}

			return 0;
		}
	}

	/**
	 * Intercanvia l'estat d'una casella ocupada del tauler
	 * 
	 * @param estat Representa l'estat de la casella ocupada que es vol intercanviar
	 * @return L'estat contrari a l'estat de la casella <em>estat</em>
	 */
	private EstatCasella intercanviaEstatCasella( EstatCasella estat )
	{
		if ( estat == EstatCasella.JUGADOR_A )
		{
			return EstatCasella.JUGADOR_B;
		}
		return EstatCasella.JUGADOR_A;
	}

	/**
	 * Mètode privat que delimita (calcula els punts límit) un subtauler dins d'un tauler. En aquest subtauler és on es
	 * troben totes les fitxes de la partida en transcurs.
	 * 
	 * @param tauler Objecte de la classe <code>TaulerGomoku</code> sobre el qual es delimitarà un subtauler
	 * @return Array de quatre posicions que representa les coordenades de les caselles que marquen els límits del
	 *         subtauler calculat. Les dues primeres posicions del array indiquen la fila i la columna de la casella
	 *         situada a l'escaire superior esquerre del subtauler, respectivament. Les dues darreres posicions del
	 *         array indiquen la fila i la columna de la casella situada a l'escaire inferior dret del subtauler,
	 *         respectivament.
	 */
	private int[] ubicaFocus( TaulerGomoku tauler )
	{
		int[] limits = new int[4];
		int mida = tauler.getMida();

		// Variables booleanes que indiquen si s'ha trobat la fila i la columna de la primera casella que
		// delimita el subtauler a calcular per el seu escaire superior esquerre
		boolean inici_fila = false;
		boolean inici_columna = false;

		for ( int fila = 0; fila < mida; ++fila )
		{
			for ( int columna = 0; columna < mida; ++columna )
			{
				// Aquest condicional prova si s'ha trobat la primera fila ocupada per alguna fitxa en el tauler global,
				// recorrent-lo d'esquerre a dreta per columnes
				if ( !inici_fila && tauler.getEstatCasella( fila, columna ) != EstatCasella.BUIDA )
				{
					inici_fila = true;

					/* 
					 * El subtauler deixa un marge de dos caselles per tenir en compte les jugades en que es deixa un
					 * forat entre formació d'estructures. Si deixant aquest marge sortim dels límits del tauler global,
					 * vol dir que el límit que s'està calculant coincideix amb el seu homòleg del tauler global (0 o
					 * mida - 1 segons la coordenada límit a calcular).
					 */
					limits[0] = ( fila - 2 >= 0 ) ? fila - 2 : 0;
				}

				// Aquest condicional prova si s'ha trobat la primera columna ocupada per alguna fitxa en el tauler
				// global, recorrent-lo de dalt a baix per files
				if ( !inici_columna && tauler.getEstatCasella( columna, fila ) != EstatCasella.BUIDA )
				{
					inici_columna = true;
					limits[1] = ( fila - 2 >= 0 ) ? fila - 2 : 0;
				}

				// Les dues sentències condicionals següents van actualitzant la fila i la columna de la casella que
				// acabarà delimitant el subtauler, situada a l'escaire inferior dret d'aquest
				if ( tauler.getEstatCasella( fila, columna ) != EstatCasella.BUIDA )
				{
					limits[2] = ( fila + 2 < mida ) ? fila + 2 : mida - 1;
				}

				if ( tauler.getEstatCasella( columna, fila ) != EstatCasella.BUIDA )
				{
					limits[3] = ( fila + 2 < mida ) ? fila + 2 : mida - 1;
				}
			}
		}

		return limits;
	}

	/**
	 * Mètode privat que actualitza els nous límits d'un subtauler ja definit, en funció de l'útlima fitxa col·locada en
	 * el tauler global sobre el qual està definit l'actaul subtauler.
	 * 
	 * @param limits Array de quatre posicions que representa les coordenades de les caselles que marquen els límits del
	 *        subtauler calculat. Les dues primeres posicions del array indiquen la fila i la columna de la casella
	 *        situada a l'escaire superior esquerre del subtauler, respectivament. Les dues darreres posicions del array
	 *        indiquen la fila i la columna de la casella situada a l'escaire inferior dret del subtauler,
	 *        respectivament.
	 * 
	 * @param fila Índex de la fila on s'ha col·locat l'última fitxa en el tauler global (sobre el qual s'ha definit el
	 *        subtauler actual).
	 * 
	 * @param columna Índex de la columna on s'ha col·locat l'última fitxa en el tauler global (sobre el qual s'ha
	 *        definit el subtauler actual).
	 * 
	 * @param mida Mida (nombre de files i de columnes) del tauler global (sobre el qual s'ha definit el subtauler
	 *        actual).
	 * 
	 * @return Array de quatre posicions que representa les coordenades de les caselles que marquen els límits del
	 *         subtauler actualitzat. Les dues primeres posicions del array indiquen la fila i la columna de la casella
	 *         situada a l'escaire superior esquerre del subtauler, respectivament. Les dues darreres posicions del
	 *         array indiquen la fila i la columna de la casella situada a l'escaire inferior dret del subtauler,
	 *         respectivament.
	 */
	private int[] actualitzaFocus( int[] limits, int fila, int columna, int mida )
	{
		/* 
		 * Si les coordemades de l'útlim moviment realitzat sobre le tauler global no es troben dins del subtauler
		 * actual, alsehores cal actualitzar el subtauler de forma correcte per encabir el nou focus central de la
		 * partida. Notar que aquesta actualització sempre farà que el nou subtauler sigui més gran que l'actual.
		 */

		// Les dues primeres sentències condicionals comproven si s'han d'actualitzar les coordenades referents a la
		// casella que marca l'escaire superior esquerre del subtauler a actualitzar
		if ( fila - limits[0] <= 1 )
		{
			limits[0] = ( fila - limits[0] == 1 ) ? limits[0] - 1 : limits[0] - 2;

			// Si la nova coordenada límit per la fila que marca l'escaire superior esquerre del nou subtauler sobresurt
			// dels límits del tauler global, aleshores cal reescalat-la amb el límit corresponent del tauler global
			if ( limits[0] < 0 )
			{
				limits[0] = 0;
			}
		}

		if ( columna - limits[1] <= 1 )
		{
			limits[1] = ( columna - limits[1] == 1 ) ? limits[1] - 1 : limits[1] - 2;
			if ( limits[1] < 0 )
			{
				limits[1] = 0;
			}
		}

		// Les dues darreres sentències condicionals comproven si s'han d'actualitzar les coordenades referents a la
		// casella que marca l'escaire inferior dret del subtauler a actualitzar
		if ( limits[2] - fila <= 1 )
		{
			limits[2] = ( fila - limits[2] == 1 ) ? limits[2] + 1 : limits[2] + 2;
			if ( limits[2] >= mida )
			{
				limits[2] = mida - 1;
			}
		}

		if ( limits[3] - columna <= 1 )
		{
			limits[3] = ( columna - limits[3] == 1 ) ? limits[3] + 1 : limits[3] + 2;
			if ( limits[3] >= mida )
			{
				limits[3] = mida - 1;
			}
		}

		return limits;
	}

	@Override
	public int[] minimax( Partida partida, EstatCasella estat_casella, int profunditat_maxima )
	{
		int[] millor_moviment = { -1, -1 };
		int maxim_actual;
		int maxim = Integer.MIN_VALUE;
		TaulerGomoku tauler = (TaulerGomoku) partida.getTauler();
		int mida = tauler.getMida();
		int profunditat = 0;
		EstatCasella fitxa_jugador = EstatCasella.JUGADOR_B;
		if ( estat_casella == EstatCasella.JUGADOR_A )
		{
			fitxa_jugador = EstatCasella.JUGADOR_A;
		}

		/*
		 * S'instancia el comparador adient per, posteriorment, crear una cua de prioritat, que anirà ordenant els nodes
		 * generats (estats que pren el tauler per a cada possible moviment vàlid) a mesura que es van emmagatzemant
		 * dins la cua de prioritat. A la cua de prioritat s'emmagatzema la informació relativa al node a avaular amb la
		 * funció d'avaluació de l'estat del tauler, i la fila i la columna de l'últim moviment que ha generat aquest
		 * estat. La prioritat l'estableix el comparador, segons si el node actual és de nivell Max o de nivell Min. Com
		 * que en aquest cas el node és de nivell Max, els elements de la cua de prioritat s'han d'ordenar segons el
		 * valor que pren l'estat del tauler un cop avaluat amb la funció d'avaluació, de major a menor.
		 */
		ComparadorInformacio comparador = new ComparadorInformacio();
		PriorityQueue<Informacio> cua_prioritat = new PriorityQueue<Informacio>( mida * mida, comparador );

		// Es calculc el subtauler a partir del qual es generaren tots els possibles moviments (tots els possibles
		// estats vàlids que pot prendre el tauler global)
		int limits[] = this.ubicaFocus( tauler );

		// Per cada possible moviment dins del subtauler, calculem el valor de l'estat del tauler global amb la funció
		// d'avaluació i aquesta informació s'emmagatzema a la cua de prioritat
		for ( int fila = limits[0]; fila <= limits[2]; ++fila )
		{
			for ( int columna = limits[1]; columna <= limits[3]; ++columna )
			{
				try
				{
					tauler.mouFitxa( estat_casella, fila, columna );
				} catch ( IllegalArgumentException excepcio )
				{
					continue;
				}
				EstatPartida estat_partida = partida.comprovaEstatPartida( fila, columna );
				int valor = this.funcioAvaluacio( tauler, estat_partida, profunditat + 1, estat_casella );
				cua_prioritat.add( new Informacio( valor, fila, columna ) );
				tauler.treuFitxa( fila, columna );
			}
		}

		/*
		 * Ara només s'expandeixen aquells estats que, a priori, són més prometedors per aconseguir la victòria. Com que
		 * la cua de prioritat ja té ben ordenats els estats de més a menys prometedors segons el jugador maximitzant
		 * (el que està conttrolat per aquesta intel·ligència artificial), només cal expandir un quants dels que estan
		 * al principi de la cua. Fent proves d'execució s'ha definit que un valor prou òptim, tant per cost temporal
		 * com per bona robustesa decisional de l'algorisme, és de 12. Per tant, només s'expandeixen els 12 estats més
		 * prometedors a priori. Expandint-los donarà informació sobre si els pronòstics inicials eren certs o, si per
		 * contra, arribar a aquest estat pot comportar conseqüències nefastes per al jugador maximitzant, com perdre la
		 * partida.
		 */

		// Variable que actua com a comptador dels estats emmagatzemats a la cua de prioritat, i que ajuda a truncar les
		// consultes fins, en aquest cas, als 12 millors estats a priori per la jugador maximitzant
		int cont = 0;
		while ( !cua_prioritat.isEmpty() && cont < 12 )
		{
			Informacio informacio = cua_prioritat.poll();
			tauler.mouFitxa( estat_casella, informacio.fila, informacio.columna );
			EstatPartida estat_partida = partida.comprovaEstatPartida( informacio.fila, informacio.columna );
			estat_casella = this.intercanviaEstatCasella( estat_casella );
			maxim_actual = this.valorMax( partida, estat_partida, Integer.MIN_VALUE, Integer.MAX_VALUE, estat_casella,
					profunditat + 1, profunditat_maxima, fitxa_jugador, limits, informacio.fila, informacio.columna );
			if ( maxim_actual > maxim )
			{
				maxim = maxim_actual;
				millor_moviment[0] = informacio.fila;
				millor_moviment[1] = informacio.columna;
			}
			tauler.treuFitxa( informacio.fila, informacio.columna );
			estat_casella = intercanviaEstatCasella( estat_casella );
			++cont;
		}
		return millor_moviment;
	}

	/**
	 * Mètode privat i recursiu que genera tots els possibles moviments d'un torn en una certa partida. De tots els
	 * moviments, se selecciona el més favorable als interessos del jugador controlat per l'algorisme MiniMax, que a la
	 * vegada és el més desfavorable als interessos del seu oponent.
	 * 
	 * @param partida Objecte de la classe <code>Partida</code> que representa la partida actual en joc.
	 * 
	 * @param estat_partida Indica en quin estat es troba actualment <em>partida</em>.
	 * 
	 * @param alfa Valor de la millor opció (el més alt) que s'ha trobat fins al moment durant la cerca de l'arbre pel
	 *        jugador controlat per l'algorisme MiniMax.
	 * 
	 * @param beta Valor de la millor opció (el més baix) que s'ha trobat fins al moment durant la cerca de l'arbre per
	 *        l'oponent del jugador controlat per l'algorisme MiniMax.
	 * 
	 * @param estat_casella Representa la fitxa del jugador que ha de disputar el torn actual de la partida.
	 * 
	 * @param profunditat Representa el nivell actual d'exploració en l'àrbre de moviments generat.
	 * 
	 * @param profunditat_maxima Representa el nivell límit en la cerca arbòria del moviment òptim.
	 * 
	 * @param fitxa_jugador Indica el jugador de la partida controlat per l'algorisme MiniMax.
	 * 
	 * @param limits Coordenades de les caselles que marquen els límits del subtauler sobre el qual es generaran els
	 *        possibles moviments a efectuar. Les dues primeres posicions del array indiquen la fila i la columna de la
	 *        casella situada a l'escaire superior esquerre del subtauler, respectivament. Les dues darreres posicions
	 *        del array indiquen la fila i la columna de la casella situada a l'escaire inferior dret del subtauler,
	 *        respectivament.
	 * 
	 * @param ultima_fila Índex de la fila on s'ha col·locat l'última fitxa en el tauler de <em>partida<em>.
	 * 
	 * @param ultima_columna Índex de la columna on s'ha col·locat l'última fitxa en el tauler de <em>partida<em>.
	 * 
	 * @return Valor de la millor opció (el més alt) un cop generat tots els possibles moviments pel torn on es troba
	 *         <em>partida</em>.
	 */
	private int valorMax( Partida partida, EstatPartida estat_partida, int alfa, int beta, EstatCasella estat_casella,
			int profunditat, int profunditat_maxima, EstatCasella fitxa_jugador, int[] limits, int ultima_fila,
			int ultima_columna )
	{
		TaulerGomoku tauler = (TaulerGomoku) partida.getTauler();
		if ( profunditat == profunditat_maxima || estat_partida == EstatPartida.GUANYA_JUGADOR_A
				|| estat_partida == EstatPartida.GUANYA_JUGADOR_B || estat_partida == EstatPartida.EMPAT )
		{
			return funcioAvaluacio( tauler, estat_partida, profunditat, fitxa_jugador );
		}
		else
		{
			int mida = tauler.getMida();

			/*
			 * S'instancia el comparador adient per, posteriorment, crear una cua de prioritat, que anirà ordenant els
			 * nodes generats (estats que pren el tauler per a cada possible moviment vàlid) a mesura que es van
			 * emmagatzemant dins la cua de prioritat. A la cua de prioritat s'emmagatzema la informació relativa al
			 * node a avaular amb la funció d'avaluació de l'estat del tauler, i la fila i la columna de l'últim
			 * moviment que ha generat aquest estat. La prioritat l'estableix el comparador, segons si el node actual és
			 * de nivell Max o de nivell Min. Com que en aquest cas el node és de nivell Min, els elements de la cua de
			 * prioritat s'han d'ordeninar segons el valor que pren l'estat del tauler un cop avaluat amb la funció
			 * d'avaluació, de menor a major.
			 */
			ComparadorInformacioInvers comparador = new ComparadorInformacioInvers();
			PriorityQueue<Informacio> cua_prioritats = new PriorityQueue<Informacio>( mida * mida, comparador );

			// Cal actualitar els límits del subtauler actual en base a l'últim moviment efectaut sobre el tauler global
			// Així se sap si el subtauler es queda intacte o, si per contra, cal ampliar-lo
			limits = this.actualitzaFocus( limits, ultima_fila, ultima_columna, mida );

			// Per cada possible moviment dins del subtauler, calculem el valor de l'estat del tauler global amb la
			// funció d'avaluació i aquesta informació s'emmagatzema a la cua de prioritat
			for ( int fila = limits[0]; fila < limits[2]; ++fila )
			{
				for ( int columna = limits[1]; columna < limits[3]; ++columna )
				{
					try
					{
						tauler.mouFitxa( estat_casella, fila, columna );
					} catch ( IllegalArgumentException excepcio )
					{
						continue;
					}
					EstatPartida estat_partida_aux = partida.comprovaEstatPartida( fila, columna );
					int valor = this.funcioAvaluacio( tauler, estat_partida_aux, profunditat + 1, estat_casella );
					cua_prioritats.add( new Informacio( valor, fila, columna ) );
					tauler.treuFitxa( fila, columna );
				}
			}

			/* 
			 * Ara només s'expandeixen aquells estats que, a priori, són més prometedors per aconseguir la victòria. Com
			 * que la cua de prioritat ja té ben ordenats els estats de més a menys prometedors segons el jugador
			 * miniimitzant (l'oponent del jugador que està conttrolat per aquesta intel·ligència artificial), només cal
			 * expandir un quants dels que estan al principi de la cua. Fent proves d'execució s'ha definit que un valor
			 * prou òptim, tant per cost temporal com per bona robustesa decisional de l'algorisme, és de 12. Per tant,
			 * només s'expandeixen els 12 estats més prometedors a priori. Expandint-los donarà informació sobre si els
			 * pronòstics inicials eren certs o, si per contra, arribar a aquest estat pot comportar conseqüències
			 * nefastes per al jugador minimitzant, com perdre la partida.
			 */

			// Variable que actua com a comptador dels estats emmagatzemats a la cua de prioritat, i que ajuda a
			// truncar les consultes fins, en aquest cas, als 12 millors estats a priori per la jugador minimitzant
			int cont = 0;
			while ( !cua_prioritats.isEmpty() && cont < 12 )
			{
				Informacio informacio = cua_prioritats.poll();
				tauler.mouFitxa( estat_casella, informacio.fila, informacio.columna );
				EstatPartida estat_partida_aux = partida.comprovaEstatPartida( informacio.fila, informacio.columna );
				estat_casella = intercanviaEstatCasella( estat_casella );
				alfa = Math.max( alfa, this.valorMin( partida, estat_partida_aux, alfa, beta, estat_casella,
						( profunditat + 1 ), profunditat_maxima, fitxa_jugador, limits, informacio.fila,
						informacio.columna ) );
				tauler.treuFitxa( informacio.fila, informacio.columna );
				if ( alfa >= beta )
				{
					return beta;
				}
				estat_casella = intercanviaEstatCasella( estat_casella );
				++cont;
			}
		}
		return alfa;
	}

	/**
	 * Mètode privat i recursiu que genera tots els possibles moviments d'un torn en una certa partida. De tots els
	 * moviments, se selecciona el més favorable als interessos de l'oponent, que a la vegada és el més desfavorable pel
	 * jugador controlat per l'algorisme MiniMax.
	 * 
	 * @param partida Objecte de la classe <code>Partida</code> que representa la partida actual en joc.
	 * 
	 * @param estat_partida Indica en quin estat es troba actualment <em>partida</em>.
	 * 
	 * @param alfa Valor de la millor opció (el més alt) que s'ha trobat fins al moment durant la cerca de l'arbre pel
	 *        jugador controlat per l'algorisme MiniMax.
	 * 
	 * @param beta Valor de la millor opció (el més baix) que s'ha trobat fins al moment durant la cerca de l'arbre per
	 *        l'oponent del jugador controlat per l'algorisme MiniMax.
	 * 
	 * @param estat_casella Representa la fitxa del jugador que ha de disputar el torn actual de la partida.
	 * 
	 * @param profunditat Representa el nivell actual d'exploració en l'àrbre de moviments generat.
	 * 
	 * @param profunditat_maxima Representa el nivell límit en la cerca arbòria del moviment òptim.
	 * 
	 * @param fitxa_jugador Indica el jugador de la partida controlat per l'algorisme MiniMax.
	 * 
	 * @param limits Coordenades de les caselles que marquen els límits del subtauler sobre el qual es generaran els
	 *        possibles moviments a efectuar. Les dues primeres posicions del array indiquen la fila i la columna de la
	 *        casella situada a l'escaire superior esquerre del subtauler, respectivament. Les dues darreres posicions
	 *        del array indiquen la fila i la columna de la casella situada a l'escaire inferior dret del subtauler,
	 *        respectivament.
	 * 
	 * @param ultima_fila Índex de la fila on s'ha col·locat l'última fitxa en el tauler de <em>partida<em>.
	 * 
	 * @param ultima_columna Índex de la columna on s'ha col·locat l'última fitxa en el tauler de <em>partida<em>.
	 * 
	 * @return Valor de la millor opció (el més alt) un cop generat tots els possibles moviments pel torn on es troba
	 *         <em>partida</em>.
	 */
	private int valorMin( Partida partida, EstatPartida estat_partida, int alfa, int beta, EstatCasella estat_casella,
			int profunditat, int profunditat_maxima, EstatCasella fitxa_jugador, int[] limits, int ultima_fila,
			int ultima_columna )
	{
		TaulerGomoku tauler = (TaulerGomoku) partida.getTauler();
		if ( profunditat == profunditat_maxima || estat_partida == EstatPartida.GUANYA_JUGADOR_A
				|| estat_partida == EstatPartida.GUANYA_JUGADOR_B || estat_partida == EstatPartida.EMPAT )
		{
			return funcioAvaluacio( tauler, estat_partida, profunditat, fitxa_jugador );
		}
		else
		{
			int mida = tauler.getMida();

			/* 
			 * S'instancia el comparador adient per, posteriorment, crear una cua de prioritat, que anirà ordenant els
			 * nodes generats (estats que pren el tauler per a cada possible moviment vàlid) a mesura que es van
			 * emmagatzemant dins la cua de prioritat. A la cua de prioritat s'emmagatzema la informació relativa al
			 * node a avaular amb la funció d'avaluació de l'estat del tauler, i la fila i la columna de l'últim
			 * moviment que ha generat aquest estat. La prioritat l'estableix el comparador, segons si el node actual és
			 * de nivell Max o de nivell Min. Com que en aquest cas el node és de nivell Max, els elements de la cua de
			 * prioritat s'han d'ordenar segons el valor que pren l'estat del tauler un cop avaluat amb la funció
			 * d'avaluació, de major a menor.
			 */
			ComparadorInformacio comparador = new ComparadorInformacio();
			PriorityQueue<Informacio> cua_prioritats = new PriorityQueue<Informacio>( mida * mida, comparador );

			// Cal actualitar els límits del subtauler actual en base a l'últim moviment efectaut sobre el tauler global
			// Així se sap si el subtauler es queda intacte o, si per contra, cal ampliar-lo
			limits = this.actualitzaFocus( limits, ultima_fila, ultima_columna, mida );

			// Per cada possible moviment dins del subtauler, calculem el valor de l'estat del tauler global amb la
			// funció d'avaluació i aquesta informació s'emmagatzema a la cua de prioritat
			for ( int fila = limits[0]; fila < limits[2]; ++fila )
			{
				for ( int columna = limits[1]; columna < limits[3]; ++columna )
				{
					try
					{
						tauler.mouFitxa( estat_casella, fila, columna );
					} catch ( IllegalArgumentException excepcio )
					{
						continue;
					}
					EstatPartida estat_partida_aux = partida.comprovaEstatPartida( fila, columna );
					int valor = this.funcioAvaluacio( tauler, estat_partida_aux, profunditat + 1, estat_casella );
					cua_prioritats.add( new Informacio( valor, fila, columna ) );
					tauler.treuFitxa( fila, columna );
				}
			}

			/* 
			 * Ara només s'expandeixen aquells estats que, a priori, són més prometedors per aconseguir la victòria. Com
			 * que la cua de prioritat ja té ben ordenats els estats de més a menys prometedors segons el jugador
			 * maximitzant (el que està conttrolat per aquesta intel·ligència artificial), només cal expandir un quants
			 * dels que estan al principi de la cua. Fent proves d'execució s'ha definit que un valor prou òptim, tant
			 * per cost temporal com per bona robustesa decisional de l'algorisme, és de 12. Per tant, només
			 * s'expandeixen els 12 estats més prometedors a priori. Expandint-los donarà informació sobre si els
			 * pronòstics inicials eren certs o, si per contra, arribar a aquest estat pot comportar conseqüències
			 * nefastes per al jugador maximitzant, com perdre la partida.
			 */

			// Variable que actua com a comptador dels estats emmagatzemats a la cua de prioritat, i que ajuda a
			// truncar les consultes fins, en aquest cas, als 12 millors estats a priori per la jugador maximitzant
			int cont = 0;
			while ( !cua_prioritats.isEmpty() && cont < 12 )
			{
				Informacio informacio = cua_prioritats.poll();
				tauler.mouFitxa( estat_casella, informacio.fila, informacio.columna );
				EstatPartida estat_partida_aux = partida.comprovaEstatPartida( informacio.fila, informacio.columna );
				estat_casella = intercanviaEstatCasella( estat_casella );
				beta = Math.min( beta, this.valorMax( partida, estat_partida_aux, alfa, beta, estat_casella,
						( profunditat + 1 ), profunditat_maxima, fitxa_jugador, limits, informacio.fila,
						informacio.columna ) );
				tauler.treuFitxa( informacio.fila, informacio.columna );
				if ( alfa >= beta )
				{
					return alfa;
				}
				estat_casella = this.intercanviaEstatCasella( estat_casella );
				++cont;
			}
		}
		return beta;
	}
}
