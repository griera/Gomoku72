package prop.gomoku.tests;

import prop.cluster.domini.models.Usuari;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.auxiliars.LecturaBuffers;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;

public class ProgramaPrincipal
{
	private static LecturaScanners dada;

	private static void imprimeixBenvinguda()
	{
		System.out.println( "/*************************************/" );
		System.out.println( "/*                                   */" );
		System.out.println( "/* BENVINGUTS AL JOC DE TAULA GOMOKU */" );
		System.out.println( "/*                                   */" );
		System.out.println( "/*************************************/\n" );
	}

	private static Usuari llegirJugador()
	{
		System.out.print( "Nom: " );
		String nom_jugador = dada.llegirString();

		System.out.print( "Contrasenya: " );
		String contrasenya_jugador = dada.llegirString();

		return new Usuari( nom_jugador, contrasenya_jugador, 4 );
	}

	public static void main( String[] args )
	{
		dada = new LecturaScanners();

		imprimeixBenvinguda();

		System.out.print( "Si us plau, ompli el següent formulari de registre " );
		System.out.println( "al sistema per poder jugar partides\n" );
		Usuari jugador = llegirJugador();
		System.out.print( "\nEl registre temporal s'ha efectuat amb èxit. " );
		System.out.println( "Aquestes són les dades que ha proporcionat al sistema:\n" + jugador.toString() + "\n" );

		boolean surt_programa = false;

		while ( !surt_programa )
		{

			System.out.println( jugador.getNom() + ", està a punt per començar una nova partida al Gomoku." );
			System.out.println( "Si us plau, indiqui el seu oponent:\n" );
			System.out.print( "1.- Jugador màquina\n2.- Jugador humà\nOponent (marqui 1 ó 2): " );
			int tipus_oponent = dada.llegirInt();

			Usuari oponent = new Usuari( "CPU", "CPU", 4 );
			switch ( tipus_oponent )
			{
				case 1:
					System.out.println( "\n" + jugador.getNom() + ", ha seleccionat jugar contra el jugador màquina." );
					System.out.println( "Aquestes són les dades del jugador màquina:\n" + oponent.toString() );
					break;

				case 2:
					System.out.println( "\n" + jugador.getNom()
							+ ", ha seleccionat jugar contra un altre jugador humà." );
					System.out.print( "Si us plau " + jugador.getNom() + ", que el vostre oponent ompli el següent "
							+ "formulari de registre al sistema per poder jugar partides\n" );
					oponent = llegirJugador();
					System.out.print( "\nEl registre temporal s'ha efectuat amb èxit. " );
					System.out.println( "Aquestes són les dades que el seu oponentha proporcionat al sistema:" );
					System.out.println( oponent.toString() );
					break;
			}

			System.out.print( "\nSi us plau " + jugador.getNom() + ", indiqui el nom que vol posar a la partida: " );
			LecturaBuffers dada_buffer = new LecturaBuffers();
			String nom_partida = dada_buffer.llegirString();
			System.out.println( "\nLa partida que s'està a punt de disputar s'anomena " + nom_partida + "\n" );

			System.out.println( "\nSi us plau " + jugador.getNom()
					+ ", indiqui amb quines fitxes vol jugar la partida:" );
			System.out.print( "1.- Fitxes negres\n2.- Fitxes blanques\nColor de les fitxes (marqui 1 ó 2): " );

			// PartidaGomoku partida;
			int color_fitxes = dada.llegirInt();

			ControladorPartidaEnJoc controlador_partida;

			switch ( color_fitxes )
			{
				case 2:
					System.out.println( "\nHa seleccionat jugar amb fitxes blanques." );
					controlador_partida = new ControladorPartidaEnJoc( oponent, jugador, nom_partida );
					break;

				default:
					// Inclou case 1
					System.out.println( "\nHa seleccionat jugar amb fitxes negres." );
					controlador_partida = new ControladorPartidaEnJoc( jugador, oponent, nom_partida );
					break;
			}

			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
			// TaulerGomoku tauler = partida.getTauler();
			int fila = 0;
			int columna = 0;

			while ( estat_partida == EstatPartida.NO_FINALITZADA )
			{
				Usuari jugador_actual = controlador_partida.getJugadorActual();

				EstatCasella fitxa = controlador_partida.getColorActual();

				System.out.println( "/* TORN " + controlador_partida.getTornActual() + " */" );
				System.out.println( jugador_actual.getNom() + ", és el seu torn." );

				if ( jugador_actual.getNom().equals( "CPU" ) )
				{
					long temps_inical = System.nanoTime();

					int[] moviment_maquina = controlador_partida.getMovimentMaquina();

					fila = moviment_maquina[0];
					columna = moviment_maquina[1];

					long temps_final = System.nanoTime();
					System.out.println( "Temps que ha tardat " + jugador_actual.getNom() + " en moure: "
							+ ( ( temps_final - temps_inical ) / 1000000000.0 ) + " segons" );
				}
				else
				{
					System.out
							.println( "Si us plau, indiqui quin serà el seu pròxim moviment (fila (espai) col·lumna):" );
					fila = dada.llegirInt();
					columna = dada.llegirInt();
				}

				try
				{
					estat_partida = controlador_partida.mouFitxa( fitxa, fila, columna );
				} catch ( IndexOutOfBoundsException excepcio )
				{
					System.out.println( excepcio.getMessage() + ".\nSi us plau " + jugador_actual.getNom()
							+ ", torni a " + "moure al seva fitxa en una posició vàlida del tauler.\n" );
					continue;
				} catch ( IllegalArgumentException excepcio )
				{
					System.out.println( excepcio.getMessage() + ".\nSi us plau " + jugador_actual.getNom()
							+ ", torni a moure al seva fitxa en una posició vàlida del tauler.\n" );
					continue;
				}

				System.out.println( jugador_actual.getNom() + " ha mogut la seva fitxa a la posicio: " + "[" + fila
						+ "][" + columna + "]" );

				System.out.println( "Ara el tauler te " + controlador_partida.getPartida().getTauler().getTotalFitxes()
						+ " interseccio(ns) ocupada(es)\n" );
				controlador_partida.getPartida().getTauler().pinta();
				System.out
						.println( "\n------------------------------------------------------------------------------\n" );

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
					System.out.println( "    RESULTAT => GUANYA " + controlador_partida.getJugadorActual().getNom()
							+ "      " );
					System.out.println( "/********************************/" );
					break;
			}

			System.out.println( "Vols tornar a jugar una altra partida? [s/n]: " );
			surt_programa = ( dada.llegirString().trim().toLowerCase().equals( "s" ) ) ? false : true;
		}
	}
}
