package prop.gomoku.tests;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.controladors.IAGomoku;
import prop.gomoku.domini.controladors.IAGomokuOptimitzada;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

public class ProvaCPUvsCPU
{

	public static void main( String[] args )
	{
		int num_partides = 25;
		int num_empats = 0;
		int num_vict_cpu_opt_negre = 0;
		int num_vict_cpu_negre = 0;
		int num_vict_cpu_opt_blanc = 0;
		int num_vict_cpu_blanc = 0;
		int num_vict_negre = 0;
		int num_vict_blanc = 0;
		int num_partides_negre_cpu = 0;
		int num_partides_blanc_cpu = 0;
		int num_partides_negre_cpu_opt = 0;
		int num_partides_blanc_cpu_opt = 0;
		System.out.println( "Ara s'enfrontaran la CPU contra la CPU_OPTIMITZADA durant " + num_partides
				+ " partides seguides" );
		System.out.println( "\n-------------------------------------------------------------------------------\n" );
		UsuariGomoku cpu = new UsuariGomoku( "CPU", "CPU" );
		UsuariGomoku cpu_opt = new UsuariGomoku( "CPU_OPTIMITZADA", "CPU" );

		long temps_prova_ini = System.nanoTime();
		long[] duracio_partida = new long[2 * num_partides];
		double[] mitjana_temps_torn_negre = new double[2 * num_partides];
		double[] mitjana_temps_torn_blanc = new double[2 * num_partides];

		IAGomoku ia = new IAGomoku();
		IAGomokuOptimitzada ia_opt = new IAGomokuOptimitzada();
		int i;
		for ( i = 1; i <= num_partides; ++i )
		{
			String nom_partida = "Partida numero " + i;
			PartidaGomoku partida;
			partida = new PartidaGomoku( cpu_opt, cpu_opt, cpu, new TaulerGomoku(), nom_partida );
			++num_partides_negre_cpu_opt;
			++num_partides_blanc_cpu;

			System.out.println( "La " + partida.getNom() + " la disputen:" );
			System.out.println( "Jugador amb fitxes negres: " + partida.getJugadorA().getNom() );
			System.out.println( "Jugador amb fitxes blanques: " + partida.getJugadorB().getNom() + "\n" );
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
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
					System.out.print( " amb profunditat maxima de 4" );

					long temps_ini_torn_negre = System.nanoTime();

					if ( partida.getTornsJugats() + 1 == 1 )
					{
						moviment = ia_opt.movimentAleatori( partida.getTauler() );
					}

					else if ( partida.getTornsJugats() + 1 == 2 )
					{
						moviment = ia_opt.movimentAdjacentAleatori( moviment[0], moviment[1], partida.getTauler()
								.getMida() );
					}

					else
					{
						moviment = ia_opt.minimax( partida, estat_casella, 4 );
						if ( moviment[0] == -1 && moviment[1] == -1 )
						{
							System.out.print( partida.getJugadorA().getNom() + " s'ha rendit perque ja ha perdut "
									+ "matematicament." );
							System.out.println( " Fara un moviment aleatori:" );
							moviment = ia.movimentAleatori( partida.getTauler() );
						}
					}

					temps_torn_negre += ( ( System.nanoTime() - temps_ini_torn_negre ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_negre );
				}

				else
				{
					System.out.print( " juga " + partida.getJugadorB().getNom() );
					estat_casella = EstatCasella.JUGADOR_B;
					System.out.print( " amb profunditat maxima de 3" );

					long temps_ini_torn_blanc = System.nanoTime();

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
						moviment = ia.minimax( partida, estat_casella, 3 );
						if ( moviment[0] == -1 && moviment[1] == -1 )
						{
							System.out.print( partida.getJugadorB().getNom() + " s'ha rendit perque ja ha perdut "
									+ "matematicament." );
							System.out.println( " Fara un moviment aleatori:" );
							moviment = ia_opt.movimentAleatori( partida.getTauler() );
						}
					}

					temps_torn_blanc += ( ( System.nanoTime() - temps_ini_torn_blanc ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_blanc );
				}

				partida.getTauler().mouFitxa( estat_casella, moviment[0], moviment[1] );
				partida.incrementaTornsJugats( 1 );
				estat_partida = partida.comprovaEstatPartida( moviment[0], moviment[1] );

				partida.getTauler().pinta();
				System.out.println();
			}

			long temps_partida_fi = System.nanoTime();
			duracio_partida[i - 1] = temps_partida_fi - temps_partida_ini;

			mitjana_temps_torn_negre[i - 1] = ( temps_torn_negre / partida.getTauler().getNumFitxesA() );
			mitjana_temps_torn_blanc[i - 1] = ( temps_torn_blanc / partida.getTauler().getNumFitxesB() );

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
					++num_vict_cpu_opt_negre;
					break;

				case GUANYA_JUGADOR_B:
					++num_vict_blanc;
					System.out.println( "Guanya " + partida.getJugadorB().getNom() + " amb fitxes blanques" );
					++num_vict_cpu_blanc;
					break;

				default:
					break;
			}
			System.out.println( "\n-------------------------------------------------------------------------------\n" );
		}

		int j;
		for ( j = 1; j <= num_partides; ++j )
		{
			String nom_partida = "Partida numero " + ( j + i - 1 );
			PartidaGomoku partida;
			partida = new PartidaGomoku( cpu, cpu, cpu_opt, new TaulerGomoku(), nom_partida );
			++num_partides_negre_cpu;
			++num_partides_blanc_cpu_opt;

			System.out.println( "La " + partida.getNom() + " la disputen:" );
			System.out.println( "Jugador amb fitxes negres: " + partida.getJugadorA().getNom() );
			System.out.println( "Jugador amb fitxes blanques: " + partida.getJugadorB().getNom() + "\n" );
			EstatPartida estat_partida = EstatPartida.NO_FINALITZADA;
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
					System.out.print( " amb profunditat maxima de 3" );

					long temps_ini_torn_negre = System.nanoTime();

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
						moviment = ia.minimax( partida, estat_casella, 3 );
						if ( moviment[0] == -1 && moviment[1] == -1 )
						{
							System.out.print( partida.getJugadorA().getNom() + " s'ha rendit perque ja ha perdut "
									+ "matematicament." );
							System.out.println( " Fara un moviment aleatori:" );
							moviment = ia.movimentAleatori( partida.getTauler() );
						}
					}

					temps_torn_negre += ( ( System.nanoTime() - temps_ini_torn_negre ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_negre );
				}

				else
				{
					System.out.print( " juga " + partida.getJugadorB().getNom() );
					estat_casella = EstatCasella.JUGADOR_B;
					System.out.print( " amb profunditat maxima de 4" );

					long temps_ini_torn_blanc = System.nanoTime();

					if ( partida.getTornsJugats() + 1 == 1 )
					{
						moviment = ia_opt.movimentAleatori( partida.getTauler() );
					}

					else if ( partida.getTornsJugats() + 1 == 2 )
					{
						moviment = ia_opt.movimentAdjacentAleatori( moviment[0], moviment[1], partida.getTauler()
								.getMida() );
					}

					else
					{
						moviment = ia_opt.minimax( partida, estat_casella, 4 );
						if ( moviment[0] == -1 && moviment[1] == -1 )
						{
							System.out.print( partida.getJugadorB().getNom() + " s'ha rendit perque ja ha perdut "
									+ "matematicament." );
							System.out.println( " Fara un moviment aleatori:" );
							moviment = ia_opt.movimentAleatori( partida.getTauler() );
						}
					}

					temps_torn_blanc += ( ( System.nanoTime() - temps_ini_torn_blanc ) / 1000000000.0 );

					System.out.println( " a la posicio (" + moviment[0] + ", " + moviment[1] + ")" );
					System.out.println( "Temps acumulat en moure: " + temps_torn_blanc );
				}

				partida.getTauler().mouFitxa( estat_casella, moviment[0], moviment[1] );
				partida.incrementaTornsJugats( 1 );
				estat_partida = partida.comprovaEstatPartida( moviment[0], moviment[1] );

				partida.getTauler().pinta();
				System.out.println();
			}

			long temps_partida_fi = System.nanoTime();
			duracio_partida[i + j - 2] = temps_partida_fi - temps_partida_ini;

			mitjana_temps_torn_negre[i + j - 2] = ( temps_torn_negre / (long) partida.getTauler().getNumFitxesA() );
			mitjana_temps_torn_blanc[i + j - 2] = ( temps_torn_blanc / (long) partida.getTauler().getNumFitxesB() );

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
					++num_vict_cpu_negre;
					break;

				case GUANYA_JUGADOR_B:
					++num_vict_blanc;
					System.out.println( "Guanya " + partida.getJugadorB().getNom() + " amb fitxes blanques" );
					++num_vict_cpu_opt_blanc;
					break;

				default:
					break;
			}
			System.out.println( "\n-------------------------------------------------------------------------------\n" );
		}

		long temps_prova_fi = System.nanoTime();

		System.out.println( "\n------------------------FINAL DE LA PROVA------------------------------------------\n" );
		System.out.println( "El temps total de duracio de la prova ha estat de "
				+ ( ( temps_prova_fi - temps_prova_ini ) / 1000000000.0 ) );

		System.out.println( "Victories " + cpu.getNom() + " amb negres: " + num_vict_cpu_negre );
		System.out.println( "Victories " + cpu.getNom() + " amb blanques: " + num_vict_cpu_blanc );
		System.out.println( "Partides jugades de " + cpu.getNom() + " amb negres: " + num_partides_negre_cpu );
		System.out.println( "Partides jugades de " + cpu.getNom() + " amb blanques: " + num_partides_blanc_cpu + "\n" );

		System.out.println( "Victories " + cpu_opt.getNom() + " amb negres: " + num_vict_cpu_opt_negre );
		System.out.println( "Victories " + cpu_opt.getNom() + " amb blanques: " + num_vict_cpu_opt_blanc );
		System.out.println( "Partides jugades de " + cpu_opt.getNom() + " amb negreses: " + num_partides_negre_cpu_opt );
		System.out.println( "Partides jugades de " + cpu_opt.getNom() + " amb blanques: " + num_partides_blanc_cpu_opt
				+ "\n" );

		System.out.println( "Victories totals " + cpu.getNom() + ": " + ( num_vict_cpu_negre + num_vict_cpu_blanc ) );
		System.out.println( "Victories totals " + cpu_opt.getNom() + ": "
				+ ( num_vict_cpu_opt_negre + num_vict_cpu_opt_blanc ) + "\n" );

		System.out.println( "Victories negre: " + num_vict_negre );
		System.out.println( "Victories blanc: " + num_vict_blanc + "\n" );

		System.out.println( "Empats: " + num_empats + "\n" );

		System.out.println( "Partides_totals: " + 2 * num_partides + "\n" );

		System.out.println( "La duracio de cada partida es el seguent:" );
		for ( int k = 0; k < 2 * num_partides; ++k )
		{
			System.out.println( "Duracio Partida numero " + ( k + 1 ) + ": " + ( duracio_partida[k] / 1000000000.0 ) );
		}

		System.out.println( "La mitjana de temps de resposta per torn de " + cpu_opt.getNom()
				+ "de cada partida es el seguent: " );
		for ( int k = 0; k < num_partides; ++k )
		{
			System.out.println( "Mijana del temmps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_negre[k] );
		}
		for ( int k = num_partides; k < 2 * num_partides; ++k )
		{
			System.out.println( "Mijana del temmps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_blanc[k] );
		}

		System.out.println();

		System.out.println( "La mitjana de temps de resposta per torn de " + cpu.getNom()
				+ "de cada partida es el seguent: " );
		for ( int k = 0; k < num_partides; ++k )
		{
			System.out.println( "Mijana del temmps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_blanc[k] );
		}
		for ( int k = num_partides; k < 2 * num_partides; ++k )
		{
			System.out.println( "Mijana del temmps de resposta per torn a la Partida numero " + ( k + 1 ) + ": "
					+ mitjana_temps_torn_negre[k] );
		}
	}
}
