package prop.gomoku.drivers;

import java.util.Date;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

public class DriverPartidaGomoku
{
	private static PartidaGomoku partida;
	private static UsuariGomoku jugador_a;
	private static UsuariGomoku jugador_b;
	private static TaulerGomoku tauler;
	private static LecturaScanners lectura = new LecturaScanners();

	private static int menuPrincipal()
	{
		System.out.println( "Driver PartidaGomoku" );
		System.out.println( "====================" );
		System.out.println( "Opcions:" );
		System.out.println( "1. Inicialitza" );
		System.out.println( "2. Consulta" );
		System.out.println( "3. Modifica" );
		System.out.println( "4. Surt" );
		return lectura.llegirInt();
	}

	private static void inicialitza()
	{
		jugador_a = new UsuariGomoku( "JugadorA", "passA", 2 );
		jugador_b = new UsuariGomoku( "JugadorB", "passB", 2 );
		tauler = new TaulerGomoku();
		partida = new PartidaGomoku( jugador_a, jugador_b, tauler, "PartidaGomokuDriver" );
		System.out.println( "Inicialitzat correctament" );
		System.out.println();
	}

	private static void consulta()
	{
		boolean surt = false;
		while ( !surt )
		{
			System.out.println( "Consulta PartidaGomoku" );
			System.out.println( "----------------------" );
			System.out.println( "1. Consulta rapida" );
			System.out.println( "2. Consulta jugador A" );
			System.out.println( "3. Consulta jugador B" );
			System.out.println( "4. Consulta tauler" );
			System.out.println( "5. Consulta torns jugats" );
			System.out.println( "6. Consulta data creacio" );
			System.out.println( "7. Consulta nom" );
			System.out.println( "8. Consulta estat finalitzacio" );
			System.out.println( "9. Comprova l'estat d'una partida en curs" );
			System.out.println( "10. Comprova l'estat d'una partida en empat" );
			System.out.println( "11. Comprova l'estat d'una partida on guanya jugador A" );
			System.out.println( "12. Comprova l'estat d'una partida on guanya jugador B" );
			System.out.println( "13. Torna al menu principal" );
			System.out.println();

			switch ( lectura.llegirInt() )
			{
				case 1:
					System.out.println( partida );
					break;
				case 2:
					System.out.println( partida.getJugadorA() );
					break;
				case 3:
					System.out.println( partida.getJugadorB() );
					break;
				case 4:
					System.out.println( partida.getTauler() );
					break;
				case 5:
					System.out.println( partida.getTornsJugats() );
					break;
				case 6:
					System.out.println( partida.getDataCreacio() );
					break;
				case 7:
					System.out.println( partida.getNom() );
					break;
				case 8:
					System.out.println( partida.estaFinalitzada() );
					break;

				case 9:
				{
					TaulerGomoku tauler_original = partida.getTauler();
					TaulerGomoku mock_tauler = new TaulerGomoku();
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 7, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 7, 8 );
					partida.setTauler( mock_tauler );
					System.out.println( "Si el tauler fos: " );
					partida.getTauler().pinta();
					System.out.println( "Estat partida: " + partida.comprovaEstatPartida( 7, 8 ) );
					partida.setTauler( tauler_original );
					System.out.println( "Tauler anterior restablert" );
					break;
				}
				case 10:
				{
					TaulerGomoku tauler_original = partida.getTauler();
					TaulerGomoku mock_tauler = new TaulerGomoku();

					for ( int i = 0; i < mock_tauler.getMida(); i++ )
					{

						EstatCasella color_x;
						EstatCasella color_y;

						if ( i % 2 == 0 )
						{
							color_x = EstatCasella.JUGADOR_A;
							color_y = EstatCasella.JUGADOR_B;
						}
						else
						{
							color_x = EstatCasella.JUGADOR_B;
							color_y = EstatCasella.JUGADOR_A;
						}

						mock_tauler.mouFitxa( color_x, i, 0 );
						mock_tauler.mouFitxa( color_x, i, 1 );
						mock_tauler.mouFitxa( color_x, i, 2 );
						mock_tauler.mouFitxa( color_y, i, 3 );
						mock_tauler.mouFitxa( color_y, i, 4 );
						mock_tauler.mouFitxa( color_x, i, 5 );
						mock_tauler.mouFitxa( color_x, i, 6 );
						mock_tauler.mouFitxa( color_x, i, 7 );
						mock_tauler.mouFitxa( color_y, i, 8 );
						mock_tauler.mouFitxa( color_y, i, 9 );
						mock_tauler.mouFitxa( color_x, i, 10 );
						mock_tauler.mouFitxa( color_x, i, 11 );
						mock_tauler.mouFitxa( color_x, i, 12 );
						mock_tauler.mouFitxa( color_y, i, 13 );
						mock_tauler.mouFitxa( color_y, i, 14 );

					}
					partida.setTauler( mock_tauler );
					System.out.println( "Si el tauler fos: " );
					partida.getTauler().pinta();
					System.out.println( "Estat partida: " + partida.comprovaEstatPartida( 11, 7 ) );
					partida.setTauler( tauler_original );
					System.out.println( "Tauler anterior restablert" );
					break;
				}
				case 11:
				{
					TaulerGomoku tauler_original = partida.getTauler();
					TaulerGomoku mock_tauler = new TaulerGomoku();
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 7, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 7, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 8, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 6, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 9, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 5, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 10, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 4, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 11, 7 );
					partida.setTauler( mock_tauler );
					System.out.println( "Si el tauler fos: " );
					partida.getTauler().pinta();
					System.out.println( "Estat partida: " + partida.comprovaEstatPartida( 11, 7 ) );
					partida.setTauler( tauler_original );
					System.out.println( "Tauler anterior restablert" );
					break;
				}
				case 12:
				{
					TaulerGomoku tauler_original = partida.getTauler();
					TaulerGomoku mock_tauler = new TaulerGomoku();
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 7, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 7, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 8, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 6, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 9, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 5, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 10, 7 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_A, 4, 8 );
					mock_tauler.mouFitxa( EstatCasella.JUGADOR_B, 11, 7 );
					partida.setTauler( mock_tauler );
					System.out.println( "Si el tauler fos: " );
					partida.getTauler().pinta();
					System.out.println( "Estat partida: " + partida.comprovaEstatPartida( 11, 7 ) );
					partida.setTauler( tauler_original );
					System.out.println( "Tauler anterior restablert" );
					break;
				}

				case 13:
					surt = true;
					break;
			}
		}
	}

	private static void modifica()
	{
		boolean surt = false;
		while ( !surt )
		{
			System.out.println( "Modificacio PartidaGomoku" );
			System.out.println( "-------------------------" );
			System.out.println( "1. Modifica jugador A" );
			System.out.println( "2. Modifica jugador B" );
			System.out.println( "3. Modifica tauler (assigna un de nou)" );
			System.out.println( "4. Modifica torns jugats" );
			System.out.println( "5. Incrementa el nombre de torns jugats" );
			System.out.println( "6. Modifica data creacio (actualitza a aquest moment)" );
			System.out.println( "7. Modifica nom" );
			System.out.println( "8. Modifica estat finalitzacio" );
			System.out.println( "9. Torna al menu principal" );
			System.out.println();

			switch ( lectura.llegirInt() )
			{
				case 1:
					System.out.println( "Introdueix el nom del nou jugador A: " );
					String nom_a = lectura.llegirString();
					System.out.println( "Introdueix la contrasenya del nou jugador A: " );
					String contrasenya_a = lectura.llegirString();
					partida.setJugadorA( new UsuariGomoku( nom_a, contrasenya_a, 2 ) );
					System.out.println( "Nou jugador A assignat" );
					System.out.println();
					break;
				case 2:
					System.out.println( "Introdueix el nom del nou jugador B: " );
					String nom_b = lectura.llegirString();
					System.out.println( "Introdueix la contrasenya del nou jugador B: " );
					String contrasenya_b = lectura.llegirString();
					partida.setJugadorA( new UsuariGomoku( nom_b, contrasenya_b, 2 ) );
					System.out.println( "Nou jugador B assignat" );
					System.out.println();
					break;
				case 3:
					partida.setTauler( new TaulerGomoku() );
					System.out.println( "S'ha assignat un nou tauler de Gomoku" );
					System.out.println();
					break;
				case 4:
					System.out.println( "Introdueix el nou nombre de torns jugats: " );
					int nou_nombre_torns = lectura.llegirInt();
					partida.setTornsJugats( nou_nombre_torns );
					System.out.println( "Nombre de torns modificat amb èxit" );
					System.out.println();
					break;
				case 5:
					System.out.println( "Introdueix la quantitat en la que vols incrementar el nombre de torns jugats" );
					int quantitat = lectura.llegirInt();
					partida.incrementaTornsJugats( quantitat );
					System.out.println( "Nombre de torns jugats incrementat" );
					System.out.println();
					break;
				case 6:
					partida.setDataCreacio( new Date() );
					System.out.println( "Hora de creacio actualitzada" );
					break;
				case 7:
					System.out.println( "Introdueix el nou nom de la partida: " );
					String nou_nom = lectura.llegirString();
					partida.setNom( nou_nom );
					System.out.println( "Nom actualitzat" );
					break;
				case 8:
					System.out.println( "Introdueix el nou estat de finalitzacio [s/n]" );
					String resposta = lectura.llegirString();
					if ( resposta.toLowerCase().equals( "s" ) )
					{
						partida.setFinalitzada( true );
					}
					else if ( resposta.toLowerCase().equals( "n" ) )
					{
						partida.setFinalitzada( false );
					}
					else
					{
						System.out.println( "Resposta no valida" );
						break;
					}
					System.out.println( "Estat de finalitzacio modificat" );
					break;

				case 13:
					surt = true;
					break;
			}
		}
	}

	public static void main( String[] args )
	{
		boolean surt = false;
		while ( !surt )
		{
			switch ( menuPrincipal() )
			{
				case 1:
					inicialitza();
					break;
				case 2:
					consulta();
					break;
				case 3:
					modifica();
					break;
				case 4:
					surt = true;
				default:
					break;
			}
		}
	}
}
