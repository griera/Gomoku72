package prop.gomoku.drivers;

import java.util.Date;

import prop.cluster.domini.models.Usuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class DriverPartidaGomoku
{
	private static PartidaGomoku partida;
	private static Usuari jugador_a;
	private static Usuari jugador_b;
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
		jugador_a = new Usuari( "JugadorA", "passA", 2 );
		jugador_b = new Usuari( "JugadorB", "passB", 2 );
		tauler = new TaulerGomoku();
		partida = new PartidaGomoku( jugador_a, jugador_b, tauler, nom_partida );
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
			System.out.println( "1. Consulta ràpida" );
			System.out.println( "2. Consulta jugador A" );
			System.out.println( "3. Consulta jugador B" );
			System.out.println( "4. Consulta tauler" );
			System.out.println( "5. Consulta torns jugats" );
			System.out.println( "6. Consulta data creació" );
			System.out.println( "7. Consulta nom" );
			System.out.println( "8. Consulta estat finalització" );
			System.out.println( "9. Torna al menú principal" );
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
			System.out.println( "Modificació PartidaGomoku" );
			System.out.println( "-------------------------" );
			System.out.println( "1. Modifica jugador A" );
			System.out.println( "2. Modifica jugador B" );
			System.out.println( "3. Modifica tauler (assigna un de nou)" );
			System.out.println( "4. Modifica torns jugats" );
			System.out.println( "5. Incrementa el nombre de torns jugats" );
			System.out.println( "6. Modifica data creació (actualitza a aquest moment)" );
			System.out.println( "7. Modifica nom" );
			System.out.println( "8. Modifica estat finalització" );
			System.out.println( "9. Torna al menú principal" );
			System.out.println();

			switch ( lectura.llegirInt() )
			{
				case 1:
					System.out.println( "Introdueix el nom del nou jugador A: " );
					String nom_a = lectura.llegirString();
					System.out.println( "Introdueix la contrasenya del nou jugador A: " );
					String contrasenya_a = lectura.llegirString();
					partida.setJugadorA( new Usuari( nom_a, contrasenya_a, 0 ) );
					System.out.println( "Nou jugador A assignat" );
					System.out.println();
					break;
				case 2:
					System.out.println( "Introdueix el nom del nou jugador B: " );
					String nom_b = lectura.llegirString();
					System.out.println( "Introdueix la contrasenya del nou jugador B: " );
					String contrasenya_b = lectura.llegirString();
					partida.setJugadorA( new Usuari( nom_b, contrasenya_b, 0 ) );
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
					System.out.println( "Hora de creació actualitzada" );
					break;
				case 7:
					System.out.println( "Introdueix el nou nom de la partida: " );
					String nou_nom = lectura.llegirString();
					partida.setNom( nou_nom );
					System.out.println( "Nom actualitzat" );
					break;
				case 8:
					System.out.println( "Introdueix el nou estat de finalització [s/n]" );
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
						System.out.println( "Resposta no vàlida" );
						break;
					}
					System.out.println( "Estat de finalització modificat" );
					break;
				case 9:
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
