package prop.gomoku.auxiliars;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.controladors.ControladorPartida;
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;

/**
 * Aquesta classe permet executar un banc de proves per comparar diferents jugadors màquina, controlats per
 * intel·ligències artificials. Això permet estudiar quines són més robustes decisionalment, és a dir, més difícils de
 * guanyar, i quines són més eficients en quant a temps de resposta (temps que triguen a calcular els seus moviments)
 * 
 * @author Genís Riera Pérez
 * 
 */
public class ProvaJugadorsMaquina
{

	private static LecturaScanners dada = new LecturaScanners();

	private static void imprimeixBenvinguda()
	{
		System.out.println( "--------------------------------------------" );
		System.out.println( "| /* BENVINGUTS AL PROGRAMA TEST GOMOKU */ |" );
		System.out.println( "|                                          |" );
		System.out.println( "|    Aquest programa enfronta dos          |" );
		System.out.println( "|    jugadors maquina per veure quin       |" );
		System.out.println( "|    es mes bo i/o mes rapid en moure      |" );
		System.out.println( "--------------------------------------------" );
		System.out.println();
	}

	private static int seleccioNombrePartides()
	{
		int num_partides = 1;
		while ( num_partides % 2 == 1 )
		{
			System.out.print( "Si us plau, seleccioni quantes partides vol que duri la prova (ha de ser un nombre "
					+ "parell): " );
			num_partides = dada.llegirInt();
			System.out.println();
		}

		// Dividim per 2 perquè els dos jugadors juguin el mateix nombre de partides amb fitxes negres i blanques
		return num_partides / 2;
	}

	private static UsuariGomoku[] SeleccioJugadors( int num_partides )
	{
		System.out.println( "Si us plau, seleccioni quins jugadros maquina desitja enfrontar durant la prova:" );
		System.out.println( "1.- Jugador maquina TERCERA_ESTRATEGIA vs Jugador maquina PRIMERA_ESTRATEGIA" );
		System.out.println( "2.- Jugador maquina TERCERA_ESTRATEGIA vs Jugador maquina SEGONA_ESTRATEGIA" );
		System.out.println( "3.- Jugador maquina SEGONA_ESTRATEGIA vs Jugador maquina PRIMERA_ESTRATEGIA" );
		System.out.print( "Marqui l'opcio que desitja (1, 2 o 3): " );
		int opcio = dada.llegirInt();

		UsuariGomoku[] maquines = new UsuariGomoku[2];

		switch ( opcio )
		{
			case 1:
				maquines[0] = new UsuariGomoku( "TERCERA_ESTRATEGIA", "MAQUINA3", TipusUsuari.FACIL );
				maquines[1] = new UsuariGomoku( "PRIMERA_ESTRATEGIA", "MAQUINA1", TipusUsuari.MITJA );
				break;

			case 2:
				maquines[0] = new UsuariGomoku( "TERCERA_ESTRATEGIA", "MAQUINA3", TipusUsuari.FACIL );
				maquines[1] = new UsuariGomoku( "SEGONA_ESTRATEGIA", "MAQUINA2", TipusUsuari.DIFICIL );
				break;

			case 3:
				maquines[0] = new UsuariGomoku( "SEGONA_ESTRATEGIA", "MAQUINA2", TipusUsuari.DIFICIL );
				maquines[1] = new UsuariGomoku( "PRIMERA_ESTRATEGIA", "MAQUINA1", TipusUsuari.MITJA );
				break;
		}

		System.out.println( "\nAra s'enfrontaran el jugador maquina " + maquines[0].getNom() + " contra "
				+ maquines[1].getNom() + " durant " + 2 * num_partides + " partides seguides" );
		System.out.println( "El jugador " + maquines[0].getNom() + " disputara les " + num_partides
				+ " primeres partides amb fitxes de color negre, i les restants amb fitxes blanques" );
		System.out.println( "El jugador " + maquines[1].getNom() + " disputara les " + num_partides
				+ " primeres partides amb fitxes de color blanc, i les restants amb fitxes negres" );
		System.out.println( "\n-----------------------------------------------------------------------------------\n" );
		return maquines;
	}

	private static void imprimeixInformacioIniciPartida( PartidaGomoku partida )
	{
		System.out.println( "La " + partida.getNom() + " la disputen:" );
		System.out.println( "Jugador amb fitxes negres: " + partida.getJugadorA().getNom() );
		System.out.println( "Jugador amb fitxes blanques: " + partida.getJugadorB().getNom() + "\n" );
	}

	private static void imprimeixDuracioProva( long temps_prova_fi, long temps_prova_ini, int num_partides )
	{
		System.out.println( "La prova ha estat constituida per un total de " + 2 * num_partides
				+ " partides disputades" );
		System.out.println( "El temps total de duracio de la prova ha estat de "
				+ ( ( temps_prova_fi - temps_prova_ini ) / 1000000000.0 ) + " segons\n" );
	}

	private static void imprimeixResultatsMaquina2( UsuariGomoku maquina2, int num_vict_maquina2_negre,
			int num_vict_maquina2_blanc, int num_partides_negre_maquina2, int num_partides_blanc_maquina2 )

	{
		System.out.println( "Victories " + maquina2.getNom() + " amb negres: " + num_vict_maquina2_negre );
		System.out.println( "Victories " + maquina2.getNom() + " amb blanques: " + num_vict_maquina2_blanc );
		System.out.println( "Victories totals " + maquina2.getNom() + ": "
				+ ( num_vict_maquina2_negre + num_vict_maquina2_blanc ) );
		System.out.println( "Partides jugades de " + maquina2.getNom() + " amb negres: " + num_partides_negre_maquina2 );
		System.out.println( "Partides jugades de " + maquina2.getNom() + " amb blanques: "
				+ num_partides_blanc_maquina2 + "\n" );
	}

	private static void imprimeixResultatsMaquina1( UsuariGomoku maquina1, int num_vict_maquina1_negre,
			int num_vict_maquina1_blanc, int num_partides_negre_maquina1, int num_partides_blanc_maquina1 )
	{
		System.out.println( "Victories " + maquina1.getNom() + " amb negres: " + num_vict_maquina1_negre );
		System.out.println( "Victories " + maquina1.getNom() + " amb blanques: " + num_vict_maquina1_blanc );
		System.out.println( "Victories totals " + maquina1.getNom() + ": "
				+ ( num_vict_maquina1_negre + num_vict_maquina1_blanc ) );
		System.out.println( "Partides jugades de " + maquina1.getNom() + " amb negres: " + num_partides_negre_maquina1 );
		System.out.println( "Partides jugades de " + maquina1.getNom() + " amb blanques: "
				+ num_partides_blanc_maquina1 + "\n" );
	}

	private static void imprimeixResultatsPerColor( int num_empats, int num_vict_negre, int num_vict_blanc )
	{
		System.out.println( "Victories negre: " + num_vict_negre );
		System.out.println( "Victories blanc: " + num_vict_blanc );
		System.out.println( "Empats: " + num_empats + "\n" );
	}

	private static void imprimeixDuracioDeCadaPartida( long[] duracio_partida, int num_partides )
	{
		System.out.println( "La duracio de cada partida es el seguent:" );
		for ( int k = 0; k < 2 * num_partides; ++k )
		{
			System.out.println( "Duracio Partida numero " + ( k + 1 ) + ": " + ( duracio_partida[k] / 1000000000.0 )
					+ " segons" );
		}
		System.out.println();
	}

	private static void imprimeixTempsRespostaPerTornMaquina1( double[] mitjana_temps_torn_negre,
			double[] mitjana_temps_torn_blanc, int num_partides, UsuariGomoku maquina1 )
	{
		System.out.println( "La mitjana de temps de resposta per torn de " + maquina1.getNom()
				+ " de cada partida es el seguent: " );
		for ( int k = 0; k < num_partides; ++k )
		{
			System.out.println( "Mijana del temps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_negre[k] + " segons" );
		}
		for ( int k = num_partides; k < 2 * num_partides; ++k )
		{
			System.out.println( "Mijana del temps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_blanc[k] + " segons" );
		}
		System.out.println();
	}

	private static void imprimeixTempsRespostaPerTornMaquina2( double[] mitjana_temps_torn_negre,
			double[] mitjana_temps_torn_blanc, int num_partides, UsuariGomoku maquina2 )
	{
		System.out.println( "La mitjana de temps de resposta per torn de " + maquina2.getNom()
				+ " de cada partida es el seguent: " );
		for ( int k = 0; k < num_partides; ++k )
		{
			System.out.println( "Mijana del temps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_blanc[k] + " segons" );
		}
		for ( int k = num_partides; k < 2 * num_partides; ++k )
		{
			System.out.println( "Mijana del temps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_negre[k] + " segons" );
		}
	}

	public static void main( String[] args )
	{
		// Inicialitzacions per al recompte d'esatdístiques durant la prova
		int num_empats = 0;
		int num_vict_maquina1_negre = 0;
		int num_vict_maquina2_negre = 0;
		int num_vict_maquina1_blanc = 0;
		int num_vict_maquina2_blanc = 0;
		int num_vict_negre = 0;
		int num_vict_blanc = 0;
		int num_partides_negre_maquina2 = 0;
		int num_partides_blanc_maquina2 = 0;
		int num_partides_negre_maquina1 = 0;
		int num_partides_blanc_maquina1 = 0;

		imprimeixBenvinguda();

		int num_partides = seleccioNombrePartides();

		UsuariGomoku[] maquines = SeleccioJugadors( num_partides );
		UsuariGomoku maquina1 = maquines[0];
		UsuariGomoku maquina2 = maquines[1];

		long[] duracio_partida = new long[2 * num_partides];
		double[] mitjana_temps_torn_negre = new double[2 * num_partides];
		double[] mitjana_temps_torn_blanc = new double[2 * num_partides];
		long temps_prova_ini = System.nanoTime();

		// Ara es disputen les primeres num_partides partides de la prova
		int i;
		for ( i = 1; i <= num_partides; ++i )
		{
			String nom_partida = "Partida numero " + i;
			ControladorPartida ctrl_partida = new ControladorPartida();
			PartidaGomoku partida = ctrl_partida.creaNovaPartida( maquina1, maquina1, maquina2, nom_partida );
			ControladorPartidaEnJoc ctrl_en_joc = new ControladorPartidaEnJoc( partida );
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;

			++num_partides_negre_maquina1;
			++num_partides_blanc_maquina2;

			imprimeixInformacioIniciPartida( partida );

			int[] moviment = new int[2];

			double temps_torn_negre = 0.0;
			double temps_torn_blanc = 0.0;
			long temps_partida_ini = System.nanoTime();
			while ( estat_partida == EstatPartida.NO_FINALITZADA )
			{
				System.out.print( "PARTIDA " + i + " TORN " + ( partida.getTornsJugats() + 1 ) );
				EstatCasella estat_casella;
				if ( ( partida.getTornsJugats() + 1 ) % 2 == 1 )
				{
					System.out.print( " juga " + partida.getJugadorA().getNom() );
					estat_casella = EstatCasella.JUGADOR_A;

					long temps_ini_torn_negre = System.nanoTime();

					moviment = ctrl_en_joc.getMovimentMaquina();

					temps_torn_negre += ( ( System.nanoTime() - temps_ini_torn_negre ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_negre );
				}

				else
				{
					System.out.print( " juga " + partida.getJugadorB().getNom() );
					estat_casella = EstatCasella.JUGADOR_B;

					long temps_ini_torn_blanc = System.nanoTime();

					moviment = ctrl_en_joc.getMovimentMaquina();

					temps_torn_blanc += ( ( System.nanoTime() - temps_ini_torn_blanc ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_blanc );
				}

				estat_partida = ctrl_en_joc.mouFitxa( estat_casella, moviment[0], moviment[1] );

				partida.getTauler().pinta();
				System.out.println();
			}

			long temps_partida_fi = System.nanoTime();
			duracio_partida[i - 1] = temps_partida_fi - temps_partida_ini;

			mitjana_temps_torn_negre[i - 1] = ( temps_torn_negre / partida.getTauler().getNumFitxesA() );
			mitjana_temps_torn_blanc[i - 1] = ( temps_torn_blanc / partida.getTauler().getNumFitxesB() );

			System.out.println( "S'ha acabat la " + partida.getNom() );
			System.out.println( "El tauler ha acabat amb la seguent distribucio de fitxes:" );
			partida.getTauler().pinta();
			System.out.println();

			switch ( estat_partida )
			{
				case EMPAT:
					++num_empats;
					break;

				case GUANYA_JUGADOR_A:
					++num_vict_negre;
					System.out.println( "Guanya " + partida.getJugadorA().getNom() + " amb fitxes negres" );
					++num_vict_maquina1_negre;
					break;

				case GUANYA_JUGADOR_B:
					++num_vict_blanc;
					System.out.println( "Guanya " + partida.getJugadorB().getNom() + " amb fitxes blanques" );
					++num_vict_maquina2_blanc;
					break;

				default:
					break;
			}
			System.out.println( "\n-------------------------------------------------------------------------------\n" );
		}

		// Ara es disputen les num_partides partides restants
		int j;
		for ( j = 1; j <= num_partides; ++j )
		{
			String nom_partida = "Partida numero " + ( j + i - 1 );
			ControladorPartida ctrl_partida = new ControladorPartida();
			PartidaGomoku partida = ctrl_partida.creaNovaPartida( maquina2, maquina2, maquina1, nom_partida );
			ControladorPartidaEnJoc ctrl_en_joc = new ControladorPartidaEnJoc( partida );
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;

			++num_partides_negre_maquina2;
			++num_partides_blanc_maquina1;

			imprimeixInformacioIniciPartida( partida );

			int[] moviment = new int[2];

			double temps_torn_negre = 0.0;
			double temps_torn_blanc = 0.0;
			long temps_partida_ini = System.nanoTime();
			while ( estat_partida == EstatPartida.NO_FINALITZADA )
			{
				System.out.print( "PARTIDA " + ( i + j - 1 ) + " TORN " + ( partida.getTornsJugats() + 1 ) );
				EstatCasella estat_casella;
				if ( ( partida.getTornsJugats() + 1 ) % 2 == 1 )
				{
					System.out.print( " juga " + partida.getJugadorA().getNom() );
					estat_casella = EstatCasella.JUGADOR_A;

					long temps_ini_torn_negre = System.nanoTime();

					moviment = ctrl_en_joc.getMovimentMaquina();

					temps_torn_negre += ( ( System.nanoTime() - temps_ini_torn_negre ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_negre );
				}

				else
				{
					System.out.print( " juga " + partida.getJugadorB().getNom() );
					estat_casella = EstatCasella.JUGADOR_B;

					long temps_ini_torn_blanc = System.nanoTime();

					moviment = ctrl_en_joc.getMovimentMaquina();

					temps_torn_blanc += ( ( System.nanoTime() - temps_ini_torn_blanc ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_blanc );
				}

				estat_partida = ctrl_en_joc.mouFitxa( estat_casella, moviment[0], moviment[1] );

				partida.getTauler().pinta();
				System.out.println();
			}

			long temps_partida_fi = System.nanoTime();
			duracio_partida[i + j - 2] = temps_partida_fi - temps_partida_ini;

			mitjana_temps_torn_negre[i + j - 2] = ( temps_torn_negre / (long) partida.getTauler().getNumFitxesA() );
			mitjana_temps_torn_blanc[i + j - 2] = ( temps_torn_blanc / (long) partida.getTauler().getNumFitxesB() );

			System.out.println( "S'ha acabat la " + partida.getNom() );
			System.out.println( "El tauler ha acabat amb la seguent distribucio de fitxes:" );
			partida.getTauler().pinta();
			System.out.println();

			switch ( estat_partida )
			{
				case EMPAT:
					++num_empats;
					break;

				case GUANYA_JUGADOR_A:
					++num_vict_negre;
					System.out.println( "Guanya " + partida.getJugadorA().getNom() + " amb fitxes negres" );
					++num_vict_maquina2_negre;
					break;

				case GUANYA_JUGADOR_B:
					++num_vict_blanc;
					System.out.println( "Guanya " + partida.getJugadorB().getNom() + " amb fitxes blanques" );
					++num_vict_maquina1_blanc;
					break;

				default:
					break;
			}
			System.out.println( "\n-------------------------------------------------------------------------------\n" );
		}

		long temps_prova_fi = System.nanoTime();

		// Imprimeix tots els resultats de la prova una vegada obtinguts
		System.out.println( "\n------------------------FINAL DE LA PROVA------------------------------------------\n" );
		imprimeixDuracioProva( temps_prova_fi, temps_prova_ini, num_partides );

		imprimeixResultatsMaquina1( maquina1, num_vict_maquina1_negre, num_vict_maquina1_blanc,
				num_partides_negre_maquina1, num_partides_blanc_maquina1 );
		imprimeixResultatsMaquina2( maquina2, num_vict_maquina2_negre, num_vict_maquina2_blanc,
				num_partides_negre_maquina2, num_partides_blanc_maquina2 );
		imprimeixResultatsPerColor( num_empats, num_vict_negre, num_vict_blanc );

		imprimeixDuracioDeCadaPartida( duracio_partida, num_partides );
		imprimeixTempsRespostaPerTornMaquina1( mitjana_temps_torn_negre, mitjana_temps_torn_blanc, num_partides,
				maquina1 );
		imprimeixTempsRespostaPerTornMaquina2( mitjana_temps_torn_negre, mitjana_temps_torn_blanc, num_partides,
				maquina2 );

	}
}
