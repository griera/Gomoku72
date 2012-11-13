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
		System.out.println( "4. Surt");
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
		System.out.println( "nom: " + partida.getNom() );
		System.out.println( "data_creacio: " + partida.getDataCreacio() );
		System.out.println( "jugador_a: " + partida.getJugadorA().getNom() );
		System.out.println( "jugador_b: " + partida.getJugadorB().getNom() );
		System.out.println( "tauler - mida: " + partida.getTauler().getMida() );
		System.out.println( "finalitzada: " + partida.estaFinalitzada() );
		System.out.println();
	}

	private static void modifica()
	{
		System.out.println( "Modificació PartidaGomoku" );
		System.out.println( "-------------------------" );
		System.out.println( "1. Modifica" );
		System.out.println();
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
