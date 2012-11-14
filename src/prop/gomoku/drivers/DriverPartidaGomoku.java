package prop.gomoku.drivers;

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

	// private enum OpcioPrincipal
	// {
	// INICILITZA, CONSULTA, MODIFICA
	// };

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
		System.out.println( "Inicialització PartidaGomoku" );
		System.out.println( "----------------------------" );

		System.out.println( "Introdueix el nom del jugador A: " );
		String nom_a = lectura.llegirString();
		System.out.println( "Introdueix el nom del jugador B: " );
		String nom_b = lectura.llegirString();
		System.out.println( "Introduex el nom de la partida: " );
		String nom_partida = lectura.llegirString();

		jugador_a = new Usuari( nom_a, "", 0 );
		jugador_b = new Usuari( nom_b, "", 0 );
		tauler = new TaulerGomoku();
		partida = new PartidaGomoku( jugador_a, jugador_b, tauler, nom_partida );
		System.out.println( "Inicialitzat correctament" );
		System.out.println();
	}

	private static void consulta()
	{
		System.out.println( "Consulta PartidaGomoku" );
		System.out.println( "----------------------" );
		System.out.println( " 1. Consulta ràpida" );
		System.out.println( "2. Consulta jugador A" );
		System.out.println( "3. Consulta jugador B" );
		System.out.println( "4. Consulta tauler" );
		System.out.println( "5. Consulta torns jugats" );
		System.out.println( "6. Consulta data creació" );
		System.out.println( "7. Consulta nom" );
		System.out.println( "8. Consulta estat finalització" );
		System.out.println( "9. Torna al menú principal" );
		System.out.println();

		boolean surt = false;
		while ( !surt )
		{
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
		System.out.println( "Modificació PartidaGomoku" );
		System.out.println( "-------------------------" );
		System.out.println( "1. Modifica jugador A" );
		System.out.println( "2. Modifica jugador B" );
		System.out.println( "3. Modifica tauler" );
		System.out.println( "4. Modifica torns jugats" );
		System.out.println( "5. Modifica data creació" );
		System.out.println( "6. Modifica nom" );
		System.out.println( "7. Modifica estat finalització" );
		System.out.println( " 8. Torna al menú principal" );
		System.out.println();

		boolean surt = false;
		while ( !surt )
		{
			switch ( lectura.llegirInt() )
			{
				case 1:
					System.out.println( "Introdueix el nom del nou jugador A: " );
					String nom_a = lectura.llegirString();
					System.out.println( "Introdueix la contrasenya del nou jugador A: " );
					String contrasenya_a = lectura.llegirString();
					partida.setJugadorA( new Usuari( nom_a, contrasenya_a, 0 ) );
					System.out.println("Nou jugador A assignat!");
					System.out.println();
					break;
				case 2:
					System.out.println( "Introdueix el nom del nou jugador B: " );
					String nom_b = lectura.llegirString();
					System.out.println( "Introdueix la contrasenya del nou jugador B: " );
					String contrasenya_b = lectura.llegirString();
					partida.setJugadorA( new Usuari( nom_b, contrasenya_b, 0 ) );
					System.out.println("Nou jugador B assignat!");
					break;
				case 3:
					partida.setTauler( new TaulerGomoku() );
					System.out.println("S'ha assignat un nou tauler de Gomoku!");
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					surt = true;
					break;
			}
		}
	}

	public static void main( String[] args )
	{
		boolean finalitza = false;
		while ( !finalitza )
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
					finalitza = true;
				default:
					break;
			}
		}
	}
}
