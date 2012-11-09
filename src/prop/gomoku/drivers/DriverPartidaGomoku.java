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
		System.out.println( "Opcions:" );
		System.out.println( "1. Inicialitza" );
		System.out.println( "2. Consulta" );
		System.out.println( "3. Modifica" );
		return lectura.llegirInt();
	}

	private static void inicialitza()
	{
		jugador_a = new Usuari( "A", "", 0 );
		jugador_b = new Usuari( "B", "", 0 );
		tauler = new TaulerGomoku();
		partida = new PartidaGomoku( jugador_a, jugador_b, tauler, "prova_driver" );
		System.out.println( "Inicialitzat correctament" );
	}

	private static void consulta()
	{
		System.out.println( "nom: " + partida.getNom() );
		System.out.println( "data_creacio: " + partida.getDataCreacio() );
		System.out.println( "jugador_a: " + partida.getJugadorA().getNom() );
		System.out.println( "jugador_b: " + partida.getJugadorB().getNom() );
		System.out.println( "tauler - mida: " + partida.getTauler().getMida() );
		System.out.println( "finalitzada: " + partida.estaFinalitzada() );
	}

	private static void modifica()
	{

	}

	public static void main( String[] args )
	{
		while ( true )
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
				default:
					break;
			}

		}

	}
}
