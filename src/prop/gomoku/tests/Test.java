package prop.gomoku.tests;

import prop.cluster.domini.models.Usuari;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.auxiliars.LecturaBuffers;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.controladors.IAGomoku;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class Test
{
	public static void main( String[] args )
	{
		System.out.println( "/*************************************/" );
		System.out.println( "/*                                   */" );
		System.out.println( "/* BENVINGUTS AL JOC DE TAULA GOMOKU */" );
		System.out.println( "/*                                   */" );
		System.out.println( "/*************************************/\n" );
		System.out.print( "Si us plau, ompli el següent formulari de registre " );
		System.out.println( "al sistema per poder jugar partides\n" );

		LecturaScanners dada = new LecturaScanners();
		System.out.print( "Nom: " );
		String nom_jugador = dada.llegirString();

		System.out.print( "Contrasenya: " );
		String contrasenya_jugador = dada.llegirString();

		Usuari jugador = new Usuari( nom_jugador, contrasenya_jugador, 2 );
		System.out.print( "\nEl registre temporal s'ha efectuat amb èxit. " );
		System.out.println( "Aquestes són les dades que ha proporcionat al sistema:\n" + jugador.toString() + "\n" );

		System.out.println( jugador.getNom() + ", està a punt per començar una nova partida al Gomoku." );
		System.out.println( "Si us plau, indiqui el seu oponent:\n" );
		System.out.print( "1.- Jugador màquina\n2.- Jugador humà\nOponent (marqui 1 ó 2): " );
		int tipus_oponent = dada.llegirInt();

		Usuari oponent = new Usuari( "CPU", "CPU", 1 );
		String nom_oponent;
		String contrasenya_oponent;
		switch ( tipus_oponent )
		{
			case 1:
				System.out.println( "\n" + jugador.getNom() + ", ha seleccionat jugar contra el jugador màquina." );
				System.out.println( "Aquestes són les dades del jugador màquina:\n" + oponent.toString() );
				break;

			case 2:
				System.out.println( "\n" + jugador.getNom() + ", ha seleccionat jugar contra un altre jugador humà." );
				System.out.print( "Si us plau " + jugador.getNom() + ", que el vostre oponent ompli el següent "
						+ "formulari de registre " );
				System.out.println( "al sistema per poder jugar partides\n" );

				System.out.print( "Nom: " );
				nom_oponent = dada.llegirString();

				System.out.print( "Contrasenya: " );
				contrasenya_oponent = dada.llegirString();

				oponent = new Usuari( nom_oponent, contrasenya_oponent, 2 );
				System.out.print( "\nEl registre temporal s'ha efectuat amb èxit. " );
				System.out.println( "Aquestes són les dades que el seu oponentha proporcionat al sistema:" );
				System.out.println( oponent.toString() );
				break;
		}

		System.out.println( "\nSi us plau " + jugador.getNom() + ", indiqui amb quines fitxes vol jugar la partida:" );
		System.out.print( "1.- Fitxes negres\n2.- Fitxes blanques\nColor de les fitxes (marqui 1 ó 2): " );
		int color_fitxes = dada.llegirInt();
		switch ( color_fitxes )
		{
			case 1:
				System.out.println( "\nHa seleccionat jugar amb fitxes negres." );
				break;

			case 2:
				System.out.println( "\nHa seleccionat jugar amb fitxes blanques." );
				break;
		}

		System.out.print( "\nSi us plau " + jugador.getNom() + ", indiqui el nom que vol posar a la partida: " );
		LecturaBuffers dada_buffer = new LecturaBuffers();
		String nom_partida = dada_buffer.llegirString();
		PartidaGomoku partida = new PartidaGomoku( jugador, oponent, new TaulerGomoku(), nom_partida );
		System.out.println( "\nLa partida que s'està a punt de disputar s'anomena " + partida.getNom() + "\n" );

		EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
		EstatCasella fitxa = EstatCasella.BUIDA;
		IAGomoku maquina = new IAGomoku();
		TaulerGomoku tauler = (TaulerGomoku) partida.getTauler();
		int fila = 0;
		int columna = 0;
		String nom_jugador_actual = "s/n";
		while ( estat_partida == EstatPartida.NO_FINALITZADA )
		{
			int torn_actual = partida.getTornsJugats() + 1;
			fitxa = ( torn_actual % 2 == 1 ) ? EstatCasella.JUGADOR_A : EstatCasella.JUGADOR_B;
			if ( color_fitxes == 1 && torn_actual % 2 == 1 )
			{
				nom_jugador_actual = nom_jugador;
			}

			else if ( color_fitxes == 1 && torn_actual % 2 == 0 )
			{
				nom_jugador_actual = oponent.getNom();
			}

			else if ( color_fitxes == 2 && torn_actual % 2 == 1 )
			{
				nom_jugador_actual = oponent.getNom();
			}

			else
			{
				nom_jugador_actual = nom_jugador;
			}

			System.out.println( "/* TORN " + torn_actual + " */" );
			System.out.println( nom_jugador_actual + ", és el seu torn." );

			if ( nom_jugador_actual.equals( "CPU" ) )
			{
				int[] moviment_maquina = new int[2];
				int mida = partida.getTauler().getMida();

				if ( torn_actual == 1 )
				{
					moviment_maquina[0] = ( mida / 2 );
					moviment_maquina[1] = ( mida / 2 );
				}

				else if ( torn_actual == 2 )
				{
					moviment_maquina = maquina.movimentAdjacentAleatori( fila, columna, mida );
				}

				else
				{
					long temps_inical = System.nanoTime();
					moviment_maquina = maquina.minimax( partida, fitxa, 2 );
					long temps_final = System.nanoTime();
					System.out.println( "Temps que ha tardat " + nom_jugador_actual + " en moure: "
							+ ( ( temps_final - temps_inical ) / 1000000000.0 ) + " segons" );

					if ( moviment_maquina[0] == -1 && moviment_maquina[1] == -1 )
					{
						System.out.print( nom_jugador_actual + "s'ha rendit perquè ja ha perdut matemàticament." );
						System.out.println( " Farà un moviment aleatori:" );
						moviment_maquina = maquina.movimentAleatori( tauler );
						System.out.println( "fila: " + moviment_maquina[0] + ", col·lumna: " + moviment_maquina[1] );
					}
				}

				fila = moviment_maquina[0];
				columna = moviment_maquina[1];
				System.out.println( nom_jugador_actual + " ha mogut la seva fitxa a la posicio: " + "[" + fila + "]["
						+ columna + "]" );
			}

			else
			{
				System.out.println( "Si us plau, indiqui quin serà el seu pròxim moviment (fila (espai) col·lumna):" );
				fila = dada.llegirInt();
				columna = dada.llegirInt();
			}

			try
			{
				tauler.mouFitxa( fitxa, fila, columna );
			} catch ( IndexOutOfBoundsException excepcio )
			{
				System.out.println( excepcio.getMessage() + ".\nSi us plau " + nom_jugador_actual + ", torni a "
						+ "moure al seva fitxa en una posició vàlida del tauler.\n" );
				continue;
			} catch ( IllegalArgumentException excepcio )
			{
				System.out.println( excepcio.getMessage() + ".\nSi us plau " + nom_jugador_actual
						+ ", torni a moure al seva fitxa en una posició vàlida del tauler.\n" );
				continue;
			}

			estat_partida = partida.comprovaEstatPartida( fila, columna );
			partida.incrementaTornsJugats( 1 );
			System.out.println( "Ara el tauler te " + tauler.getTotalFitxes() + " interseccio(ns) ocupada(es)\n" );
			tauler.pinta();
			System.out.println( "\n------------------------------------------------------------------------------\n" );
		}

		switch ( estat_partida )
		{
			case EMPAT:
				System.out.println( "/********************************/" );
				System.out.println( "      PARTIDA FINALITZADA         " );
				System.out.println( "       RESULTAT => EMPAT          " );
				System.out.println( "/********************************/" );
				break;

			default:
				System.out.println( "/********************************/" );
				System.out.println( "      PARTIDA FINALITZADA         " );
				System.out.println( "    RESULTAT => GUANYA " + nom_jugador_actual + "      " );
				System.out.println( "/********************************/" );
				break;
		}
	}
}
