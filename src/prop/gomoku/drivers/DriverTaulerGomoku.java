package prop.gomoku.drivers;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.models.TaulerGomoku;

public class DriverTaulerGomoku
{
	private static TaulerGomoku tauler;
	private static LecturaScanners lectura = new LecturaScanners();

	private static int menuPrincipal()
	{
		System.out.println( "Driver TauerGomoku" );
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
		tauler = new TaulerGomoku();
		System.out.println( "Inicialitzat correctament" );
		System.out.println();
	}

	private static void consulta()
	{
		boolean surt = false;
		while ( !surt )
		{
			System.out.println( "Consulta TaulerGomoku" );
			System.out.println( "----------------------" );
			System.out.println( "1. Consulta ràpida del tauler" );
			System.out.println( "2. Consulta ràpida alternativa del tauler (només l'estat del tauler)" );
			System.out.println( "3. Consulta si el tauler està buit" );
			System.out.println( "4. Consulta el nombre de fitxes del jugador A (fitxes negres) sobre el tauler" );
			System.out.println( "5. Consulta el nombre de fitxes del jugador B (fitxes blanques) sobre el tauler" );
			System.out.println( "6. Consulta el nombre total de fitxes sobre el tauler" );
			System.out.println( "7. Consulta l'estat d'una casella dins del tauler" );
			System.out.println( "8. Consulta si un moviment és vàlid (segons el reglament del Gomoku) abans de "
					+ "realitzar-lo" );
			System.out.println( "9. Torna al menú principal" );
			System.out.println();

			switch ( lectura.llegirInt() )
			{
				case 1:
					System.out.println( tauler );
					break;
				case 2:
					tauler.pinta();
					break;
				case 3:
					String resposta = ( tauler.esBuit() ) ? "El tauler està buit" : "El tauler no està buit";
					System.out.println( resposta );
					break;
				case 4:
					System.out.println( "El jugador A (fitxes negres) té " + tauler.getNumFitxesA() + " fitxa/es" );
					break;
				case 5:
					System.out.println( "El jugador B (fitxes blanques) té " + tauler.getNumFitxesB() + " fitxa/es" );
					break;
				case 6:
					System.out.println( "Al tauler hi ha un total de " + tauler.getTotalFitxes() + " fitxa/es" );
					break;
				case 7:
					System.out.println( "Si us plau, indiqui les coordenades de la casella que vol consultar:" );
					System.out.print( "Índex de la fila: " );
					int fila = lectura.llegirInt();
					System.out.println();

					System.out.print( "Índex de la col·lumna: " );
					int columna = lectura.llegirInt();
					System.out.println();
					EstatCasella estat_casella;
					try
					{
						estat_casella = tauler.getEstatCasella( fila, columna );
					} catch ( IndexOutOfBoundsException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					}
					System.out.println( "La casella (" + fila + ", " + columna + ") té el següent estat: "
							+ estat_casella.toString() );
					break;
				case 8:
					System.out.println( "Si us plau, indiqui les coordenades de la casella on voldria fer el "
							+ "moviment:" );
					System.out.print( "Índex de la fila: " );
					fila = lectura.llegirInt();
					System.out.println();

					System.out.print( "Índex de la col·lumna: " );
					columna = lectura.llegirInt();
					System.out.println();

					System.out.print( "Si us plau, indiqui el color de la fitxa que voldria moure (1 - negre / "
							+ "2 - blanc): " );
					int color = lectura.llegirInt();
					System.out.println();
					EstatCasella estat_casella_aux = ( color == 1 ) ? EstatCasella.JUGADOR_A : EstatCasella.JUGADOR_B;

					try
					{
						tauler.esMovimentValid( estat_casella_aux, fila, columna );
					} catch ( IndexOutOfBoundsException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						System.out.println( "Per tant, el moviment no es podria efectuar" );
						continue;
					} catch ( IllegalArgumentException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						System.out.println( "Per tant, el moviment no es podria efectuar" );
						continue;
					}
					System.out.println( " El moviment que voldria efectuar no viola el reglament del Gomoku " );
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
			System.out.println( "Modifica TaulerGomoku" );
			System.out.println( "----------------------" );
			System.out.println( "1. Col·loca una fitxa negra al tauler" );
			System.out.println( "2. Col·loca una fitxa blanca al tauler" );
			System.out.println( "3. Treu una fitxa del tauler" );
			System.out.println( "4. Torna al menú principal" );

			switch ( lectura.llegirInt() )
			{
				case 1:
					System.out.println( "Si us plau, indiqui les coordenades de la casella on vol col·locar una fitxa "
							+ "negra:" );
					System.out.print( "Índex de la fila: " );
					int fila = lectura.llegirInt();
					System.out.println();

					System.out.print( "Índex de la col·lumna: " );
					int columna = lectura.llegirInt();
					System.out.println();

					try
					{
						tauler.mouFitxa( EstatCasella.JUGADOR_A, fila, columna );
					} catch ( IndexOutOfBoundsException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					} catch ( IllegalArgumentException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					}
					System.out.println( " La fitxa s'ha col·locat correctament " );
					break;

				case 2:
					System.out.println( "Si us plau, indiqui les coordenades de la casella on vol col·locar una fitxa "
							+ "blanca:" );
					System.out.print( "Índex de la fila: " );
					fila = lectura.llegirInt();
					System.out.println();

					System.out.print( "Índex de la col·lumna: " );
					columna = lectura.llegirInt();
					System.out.println();

					try
					{
						tauler.mouFitxa( EstatCasella.JUGADOR_B, fila, columna );
					} catch ( IndexOutOfBoundsException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					} catch ( IllegalArgumentException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					}
					System.out.println( " La fitxa s'ha col·locat correctament " );
					break;
				case 3:
					System.out.println( "Si us plau, indiqui les coordenades de la fitxa que vol treure del tauler:" );
					System.out.print( "Índex de la fila: " );
					fila = lectura.llegirInt();
					System.out.println();

					System.out.print( "Índex de la col·lumna: " );
					columna = lectura.llegirInt();
					System.out.println();

					try
					{
						tauler.treuFitxa( fila, columna );
					} catch ( IndexOutOfBoundsException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					} catch ( IllegalArgumentException excepcio )
					{
						System.out.println( excepcio.getMessage() );
						continue;
					}
					System.out.println( " La fitxa s'ha tret correctament " );
					break;
				case 4:
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
