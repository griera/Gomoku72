package prop.gomoku.tests;

import java.util.List;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.auxiliars.LecturaBuffers;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;
import prop.gomoku.domini.controladors.ControladorPartidesGuardades;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

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

	private static UsuariGomoku llegirJugador()
	{
		System.out.print( "Nom: " );
		String nom_jugador = dada.llegirString();

		System.out.print( "Contrasenya: " );
		String contrasenya_jugador = dada.llegirString();

		return new UsuariGomoku( nom_jugador, contrasenya_jugador );
	}

	private static void imprimeixResultat( EstatPartida estat_partida, String nom_jugador_actual )
	{
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

	public static PartidaGomoku creaPartida( UsuariGomoku jugador )
	{
		System.out.println( jugador.getNom() + ", esta a punt per iniciar una nova partida al joc Gomoku." );
		System.out.println( "Si us plau, indiqui el seu oponent:\n" );
		System.out.print( "1.- Jugador maquina\n2.- Jugador huma\nOponent (marqui 1 o 2): " );
		int tipus_oponent = dada.llegirInt();

		UsuariGomoku oponent = new UsuariGomoku( "CPU", "CPU" );
		switch ( tipus_oponent )
		{
			case 1:
				System.out.println( "\n" + jugador.getNom() + ", ha seleccionat jugar contra el jugador maquina." );
				System.out.println( "Aquestes son les dades del jugador maquina:\n" + oponent.toString() );
				break;

			case 2:
				System.out.println( "\n" + jugador.getNom() + ", ha seleccionat jugar contra un altre jugador huma." );
				System.out.print( "Si us plau " + jugador.getNom() + ", que el vostre oponent ompli el seguent "
						+ "formulari de registre al sistema per poder jugar partides\n" );
				oponent = llegirJugador();
				System.out.print( "\nEl registre temporal s'ha efectuat amb èxit. " );
				System.out.println( "Aquestes son les dades que el seu oponentha proporcionat al sistema:" );
				System.out.println( oponent.toString() );
				break;
		}

		System.out.print( "\nSi us plau " + jugador.getNom() + ", indiqui el nom que vol posar a la partida: " );
		LecturaBuffers dada_buffer = new LecturaBuffers();
		String nom_partida = dada_buffer.llegirString();
		System.out.println( "\nLa partida que s'esta a punt de disputar s'anomena " + nom_partida + "\n" );

		System.out.println( "\nSi us plau " + jugador.getNom() + ", indiqui amb quines fitxes vol jugar la partida:" );
		System.out.print( "1.- Fitxes negres\n2.- Fitxes blanques\nColor de les fitxes (marqui 1 o 2): " );

		int color_fitxes = dada.llegirInt();
		ControladorPartidaEnJoc controlador_partida;

		switch ( color_fitxes )
		{
			case 2:
				System.out.println( "\nHa seleccionat jugar amb fitxes blanques.\n" );
				controlador_partida = new ControladorPartidaEnJoc( oponent, jugador, nom_partida );
				break;

			default:
				// Inclou case 1
				System.out.println( "\nHa seleccionat jugar amb fitxes negres.\n" );
				controlador_partida = new ControladorPartidaEnJoc( jugador, oponent, nom_partida );
				break;
		}

		return controlador_partida.getPartida();
	}

	public static void main( String[] args )
	{
		dada = new LecturaScanners();
		imprimeixBenvinguda();
		System.out.print( "Si us plau, ompli el seguent formulari de registre al sistema per poder jugar partides\n" );
		UsuariGomoku jugador = llegirJugador();
		System.out.print( "\nEl registre temporal s'ha efectuat amb exit. " );
		System.out.println( "Aquestes son les dades que ha proporcionat al sistema:\n" + jugador.toString() + "\n" );

		boolean surt_programa = false;

		while ( !surt_programa )
		{

			/* Prova càrrega */
			ControladorPartidesGuardades ctlr_partides_guardades = new ControladorPartidesGuardades();
			List<PartidaGomoku> partides = ctlr_partides_guardades.carregaPartides( jugador );
			boolean es_nova_partida = true;
			PartidaGomoku partida = null;
			if ( !partides.isEmpty() )
			{
				System.out.println( "Ja existeixen partides guardades per aquest usuari" );
				for ( int i = 0; i < partides.size(); i++ )
				{
					System.out.println( i + ". " + partides.get( i ).getDataCreacio() );
				}
				System.out.println( partides.size() + ". Nova partida" );
				int opcio = dada.llegirInt();
				if ( opcio >= partides.size() )
				{
					es_nova_partida = true;
				}
				else
				{
					partida = partides.get( opcio );
					es_nova_partida = false;
				}
			}

			/* End prova càrrega */

			if ( es_nova_partida )
			{
				partida = creaPartida( jugador );
				partida.setJugadorPrincipal( jugador );
			}

			else
			{
				partida.getTauler().pinta();
			}

			ControladorPartidaEnJoc controlador_partida = new ControladorPartidaEnJoc( partida );
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
			int fila = 0;
			int columna = 0;

			boolean continua = true;
			while ( estat_partida == EstatPartida.NO_FINALITZADA && continua )
			{
				UsuariGomoku jugador_actual = controlador_partida.getJugadorActual();

				EstatCasella fitxa = controlador_partida.getColorActual();

				System.out.println( "/* TORN " + controlador_partida.getTornActual() + " */" );
				System.out.println( jugador_actual.getNom() + ", es el seu torn." );

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
					System.out.println( "Si us plau, indiqui quin sera el seu proxim moviment (fila (espai) columna):" );
					fila = dada.llegirInt();
					columna = dada.llegirInt();

					if ( fila == -3 && columna == -3 )
					{
						controlador_partida.guardaPartida();
					}
					if ( fila == -5 && columna == -5 )
					{
						continua = false;
					}
				}

				try
				{
					estat_partida = controlador_partida.mouFitxa( fitxa, fila, columna );
				} catch ( IndexOutOfBoundsException excepcio )
				{
					System.out.println( excepcio.getMessage() + ".\nSi us plau " + jugador_actual.getNom()
							+ ", torni a " + "moure la seva fitxa en una posicio valida del tauler.\n" );
					continue;
				} catch ( IllegalArgumentException excepcio )
				{
					System.out.println( excepcio.getMessage() + ".\nSi us plau " + jugador_actual.getNom()
							+ ", torni a moure al seva fitxa en una posicio valida del tauler.\n" );
					continue;
				}

				System.out.println( jugador_actual.getNom() + " ha mogut la seva fitxa a la posicio: " + "[" + fila
						+ "][" + columna + "]" );

				System.out.println( "Ara el tauler te " + controlador_partida.getPartida().getTauler().getTotalFitxes()
						+ " interseccio(ns) ocupada(es)\n" );
				controlador_partida.getPartida().getTauler().pinta();
				System.out
						.println( "\n-----------------------------------------------------------------------------\n" );

			}

			imprimeixResultat( estat_partida, controlador_partida.getJugadorActual().getNom() );
			System.out.println( jugador.getNom() + ", vol tornar a jugar una altra partida? [s/n]: " );
			surt_programa = ( dada.llegirString().trim().toLowerCase().equals( "s" ) ) ? false : true;
		}
	}
}
