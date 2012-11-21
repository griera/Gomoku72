package prop.gomoku.tests;

import java.util.Random;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.controladors.IAGomoku;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

public class ProvaCPUvsCPU
{

	public static void main( String[] args )
	{
		int num_partides = 50;
		int num_empats = 0;
		int num_vict_cpu_prof_2_negre = 0;
		int num_vict_cpu_prof_3_negre = 0;
		int num_vict_cpu_prof_2_blanc = 0;
		int num_vict_cpu_prof_3_blanc = 0;
		int num_vict_negre = 0;
		int num_vict_blanc = 0;
		int num_partides_negre_cpu_prof_3 = 0;
		int num_partides_blanc_cpu_prof_3 = 0;
		int num_partides_negre_cpu_prof_2 = 0;
		int num_partides_blanc_cpu_prof_2 = 0;
		System.out.println( "Ara s'enfrontaran la CPU_PROFUNDITAT_3 contra la CPU_PROFUNDITAT_2 durant " + num_partides
				+ " partides seguides" );
		System.out.println( "\n-------------------------------------------------------------------------------\n" );
		UsuariGomoku cpu_prof_3 = new UsuariGomoku( "CPU_PROFUNDITAT_3", "CPU", 4 );
		UsuariGomoku cpu_prof_2 = new UsuariGomoku( "CPU_PROFUNDITAT_2", "CPU", 4 );

		long temps_prova_ini = System.nanoTime();
		long[] duracio_partida = new long[num_partides];
		for ( int i = 1; i <= num_partides; ++i )
		{
			String nom_partida = "Partida numero " + i;
			int color_aleatori = (int) Math.round( ( new Random() ).nextDouble() * 2 );
			PartidaGomoku partida;
			if ( color_aleatori == 1 )
			{
				partida = new PartidaGomoku( cpu_prof_2, cpu_prof_3, new TaulerGomoku(), nom_partida );
				++num_partides_negre_cpu_prof_2;
				++num_partides_blanc_cpu_prof_3;
			}

			else
			{
				partida = new PartidaGomoku( cpu_prof_3, cpu_prof_2, new TaulerGomoku(), nom_partida );
				++num_partides_negre_cpu_prof_3;
				++num_partides_blanc_cpu_prof_2;
			}

			System.out.println( "La " + partida.getNom() + " la disputen:" );
			System.out.println( "Jugador amb fitxes negres: " + partida.getJugadorA().getNom() );
			System.out.println( "Jugador amb fitxes blanques: " + partida.getJugadorB().getNom() + "\n" );
			IAGomoku ia = new IAGomoku();
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
			int[] moviment = new int[2];

			long temps_partida_ini = System.nanoTime();
			while ( estat_partida == EstatPartida.NO_FINALITZADA )
			{
				System.out.print( "PARTIDA " + i + " TORN " + ( partida.getTornsJugats() + 1 ) );
				EstatCasella estat_casella;
				if ( ( partida.getTornsJugats() + 1 ) % 2 == 1 )
				{
					System.out.print( " juga " + partida.getJugadorA().getNom() );
					estat_casella = EstatCasella.JUGADOR_A;
					int prof_max = ( partida.getJugadorA().getNom().equals( cpu_prof_3.getNom() ) ) ? 3 : 2;
					System.out.print( " amb profunditat maxima de " + prof_max );

					if ( partida.getTornsJugats() + 1 == 1 )
					{
						moviment = ia.movimentAleatori( partida.getTauler() );
					}

					else if ( partida.getTornsJugats() + 1 == 2 )
					{
						moviment = ia
								.movimentAdjacentAleatori( moviment[0], moviment[1], partida.getTauler().getMida() );
					}

					else
					{
						moviment = ia.minimax( partida, estat_casella, prof_max );
						if ( moviment[0] == -1 && moviment[1] == -1 )
						{
							System.out.print( partida.getJugadorA().getNom() + " s'ha rendit perque ja ha perdut "
									+ "matematicament." );
							System.out.println( " Fara un moviment aleatori:" );
							moviment = ia.movimentAleatori( partida.getTauler() );
						}
					}
					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
				}

				else
				{
					System.out.print( " juga " + partida.getJugadorB().getNom() );
					estat_casella = EstatCasella.JUGADOR_B;
					int prof_max = ( partida.getJugadorB().getNom().equals( cpu_prof_3.getNom() ) ) ? 3 : 2;
					System.out.print( " amb profunditat maxima de " + prof_max );
					if ( partida.getTornsJugats() + 1 == 1 )
					{
						moviment = ia.movimentAleatori( partida.getTauler() );
					}

					else if ( partida.getTornsJugats() + 1 == 2 )
					{
						moviment = ia
								.movimentAdjacentAleatori( moviment[0], moviment[1], partida.getTauler().getMida() );
					}

					else
					{
						moviment = ia.minimax( partida, estat_casella, prof_max );
						if ( moviment[0] == -1 && moviment[1] == -1 )
						{
							System.out.print( partida.getJugadorB().getNom() + " s'ha rendit perque ja ha perdut "
									+ "matematicament." );
							System.out.println( " Fara un moviment aleatori:" );
							moviment = ia.movimentAleatori( partida.getTauler() );
						}
					}
					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
				}

				partida.getTauler().mouFitxa( estat_casella, moviment[0], moviment[1] );
				partida.incrementaTornsJugats( 1 );
				estat_partida = partida.comprovaEstatPartida( moviment[0], moviment[1] );

				partida.getTauler().pinta();
				System.out.println();
			}

			long temps_partida_fi = System.nanoTime();
			duracio_partida[i - 1] = temps_partida_fi - temps_partida_ini;
			System.out.println( "s'ha acabat la " + partida.getNom() );
			System.out.println( "El tauler ha acabat amb la seguent pinta:" );
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
					if ( partida.getJugadorA().getNom().equals( cpu_prof_3.getNom() ) )
					{
						++num_vict_cpu_prof_3_negre;
					}

					else
					{
						++num_vict_cpu_prof_2_negre;
					}

					break;

				case GUANYA_JUGADOR_B:
					++num_vict_blanc;
					System.out.println( "Guanya " + partida.getJugadorB().getNom() + " amb fitxes blanques" );
					if ( partida.getJugadorB().getNom().equals( cpu_prof_3.getNom() ) )
					{
						++num_vict_cpu_prof_3_blanc;
					}

					else
					{
						++num_vict_cpu_prof_2_blanc;
					}

					break;
			}
			System.out.println( "\n-------------------------------------------------------------------------------\n" );
		}

		long temps_prova_fi = System.nanoTime();

		System.out.println( "\n------------------------FINAL DE LA PROVA------------------------------------------\n" );
		System.out.println( "El temps total de duracio de la prova ha estat de "
				+ ( ( temps_prova_fi - temps_prova_ini ) / 1000000000.0 ) );

		System.out.println( "Victories " + cpu_prof_3.getNom() + " amb negres: " + num_vict_cpu_prof_3_negre );
		System.out.println( "Victories " + cpu_prof_3.getNom() + " amb blanques: " + num_vict_cpu_prof_3_blanc );
		System.out.println( "Partides jugades de " + cpu_prof_3.getNom() + " amb negreses: "
				+ num_partides_negre_cpu_prof_3 );
		System.out.println( "Partides jugades de " + cpu_prof_3.getNom() + " amb blanques: "
				+ num_partides_blanc_cpu_prof_3 + "\n" );

		System.out.println( "Victories " + cpu_prof_2.getNom() + " amb negres: " + num_vict_cpu_prof_2_negre );
		System.out.println( "Victories " + cpu_prof_2.getNom() + " amb blanques: " + num_vict_cpu_prof_2_blanc );
		System.out.println( "Partides jugades de " + cpu_prof_2.getNom() + " amb negreses: "
				+ num_partides_negre_cpu_prof_2 );
		System.out.println( "Partides jugades de " + cpu_prof_2.getNom() + " amb blanques: "
				+ num_partides_blanc_cpu_prof_2 + "\n" );

		System.out.println( "Victories totals " + cpu_prof_3.getNom() + ": "
				+ ( num_vict_cpu_prof_3_negre + num_vict_cpu_prof_3_blanc ) );
		System.out.println( "Victories totals " + cpu_prof_2.getNom() + ": "
				+ ( num_vict_cpu_prof_2_negre + num_vict_cpu_prof_2_blanc ) + "\n" );

		System.out.println( "Victories negre: " + num_vict_negre );
		System.out.println( "Victories blanc: " + num_vict_blanc + "\n" );

		System.out.println( "Empats: " + num_empats + "\n" );

		System.out.println( "Partides_totals: " + num_partides + "\n" );

		System.out.println( "La duracio de cada partida es el seguent:" );
		for ( int i = 0; i < num_partides; ++i )
		{
			System.out.println( "Duracio Partida numero " + ( i + 1 ) + ": " + ( duracio_partida[i] / 1000000000.0 ) );
		}

	}

}
