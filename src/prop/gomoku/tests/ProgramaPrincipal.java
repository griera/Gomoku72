package prop.gomoku.tests;

import java.util.List;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.auxiliars.LecturaBuffers;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.controladors.ControladorPartida;
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;
import prop.gomoku.domini.controladors.ControladorPartidesGuardades;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;

public class ProgramaPrincipal
{
	private static LecturaScanners dada;

	private static void imprimeixBenvinguda()
	{
		System.out.println( "BENVINGUTS AL JOC DE TAULA GOMOKU" );
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
				System.out.println( "PARTIDA FINALITZADA: EMPAT" );
				break;

			default:
				System.out.println( "PARTIDA FINALITZADA: GUANYA " + nom_jugador_actual );
				break;
		}
	}

	public static PartidaGomoku creaPartida( UsuariGomoku jugador )
	{
		System.out.println( "Indiqui el seu oponent:\n" );
		System.out.println( "1.- Jugador maquina" );
		System.out.println( "2.- Jugador huma" );
		int tipus_oponent = dada.llegirInt();

		UsuariGomoku oponent = new UsuariGomoku( "CPU", "CPUa", TipusUsuari.MITJA );
		switch ( tipus_oponent )
		{
			case 1:
				System.out.println( "Dades jugador maquina:\n" + oponent.toString() );
				break;

			case 2:
				System.out.println( "Registre oponent:" );
				oponent = llegirJugador();
				System.out.println( "Dades oponent humà: " + oponent.toString() );
				break;
		}

		System.out.println( "Nom de la partida: " );
		LecturaBuffers dada_buffer = new LecturaBuffers();
		String nom_partida = dada_buffer.llegirString();

		System.out.println( "Indiqui amb quines fitxes vol jugar la partida:" );
		System.out.println( "1.- Fitxes negres" );
		System.out.println( "2.- Fitxes blanques" );
		int color_fitxes = dada.llegirInt();

		ControladorPartida controlador_partida = new ControladorPartida();
		PartidaGomoku partida_nova = null;
		switch ( color_fitxes )
		{
			case 2:
				System.out.println( "Ha seleccionat jugar amb fitxes blanques." );
				partida_nova = controlador_partida.creaNovaPartida( jugador, oponent, jugador, nom_partida );
				break;

			default:
				// Inclou case 1
				System.out.println( "Ha seleccionat jugar amb fitxes negres." );
				partida_nova = controlador_partida.creaNovaPartida( jugador, jugador, oponent, nom_partida );
				break;
		}

		return partida_nova;
	}

	public static void main( String[] args )
	{
		dada = new LecturaScanners();
		imprimeixBenvinguda();
		System.out.println( "Registre inicial:" );
		UsuariGomoku jugador = llegirJugador();
		System.out.println( "El registre temporal s'ha efectuat amb exit. " );
		System.out.println( "Aquestes son les dades que ha proporcionat al sistema:\n" + jugador.toString() );

		boolean surt_programa = false;

		while ( !surt_programa )
		{

			/* Prova càrrega */
			ControladorPartidesGuardades ctlr_partides_guardades = new ControladorPartidesGuardades();
			List<PartidaGomoku> partides = ctlr_partides_guardades.carregaPartides( jugador );
			PartidaGomoku partida = null;
			if ( !partides.isEmpty() )
			{
				System.out.println( "Ja existeixen partides guardades per aquest usuari:" );
				for ( int i = 0; i < partides.size(); i++ )
				{
					System.out.println( i + ". " + partides.get( i ).getDataCreacio() + " - "
							+ partides.get( i ).getDataCreacio().toString() );
				}
				System.out.println( partides.size() + ". Nova partida" );
				int opcio = dada.llegirInt();
				if ( opcio >= partides.size() )
				{
					partida = creaPartida( jugador );
				}
				else
				{
					partida = partides.get( opcio );
				}
			}
			else
			{
				partida = creaPartida( jugador );
			}
			/* Final prova càrrega */

			partida.getTauler().pinta();

			ControladorPartidaEnJoc controlador_partida = new ControladorPartidaEnJoc( partida );
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
			int fila = 0;
			int columna = 0;

			boolean continua = true;
			while ( estat_partida == EstatPartida.NO_FINALITZADA && continua )
			{
				UsuariGomoku jugador_actual = controlador_partida.getJugadorActual();

				EstatCasella fitxa = controlador_partida.getColorActual();

				System.out.println( "TORN " + controlador_partida.getTornActual() );
				System.out.println( "És el torn de: " + jugador_actual.getNom() );

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

					// -3, -3 guarda la partida
					if ( fila == -3 && columna == -3 )
					{
						controlador_partida.guardaPartida();
					}
					// -5, -5 tanca la partida
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
			System.out.println( jugador.getNom() + ", vols tornar a jugar una altra partida? [s/n]: " );
			surt_programa = ( dada.llegirString().trim().toLowerCase().equals( "s" ) ) ? false : true;
		}
	}
}
