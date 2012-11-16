package prop.gomoku.drivers;

import prop.gomoku.domini.models.UsuariGomoku;

/**
 * 
 * Aquesta clase es el driver de usuari gomoku i ens permet probar les seves funcionalitats per comprovar el seu
 * correcte funcionament
 * 
 */
public class DriverUsuariGomoku
{
	/**
	 * Indica el nombre de dificultats que podem trobar en el nostre joc. Una persona també compta com a possible
	 * dificultat
	 */
	private static final int nombre_dificultats = 4;
	/**
	 * Representa l'usuari del sistema gomoku
	 */
	private static UsuariGomoku usuari;
	/**
	 * Variable que permet gestionar mes comodement lectures de dades
	 */
	private static LecturaScanners lectura = new LecturaScanners();

	/**
	 * Aquest menu mostra les principals opcions de consulta,creació i modificació que es poden dur a terme a la classe
	 * UsuariGomoku L'usuari introduira per teclat la opció que vulgui probar
	 * 
	 * @return Retornara la opcio del menú escollida per l'usuari
	 */
	private static int menuPrincipal()
	{
		System.out.println( "Driver UsuariGomoku" );
		System.out.println( "====================" );
		System.out.println( "Opcions:" );
		System.out.println( "1. Crea Usuari" );
		System.out.println( "2. Consultar dades Usuari" );
		System.out.println( "3. Modifica" );
		System.out.println();
		return lectura.llegirInt();
	}

	/**
	 * Driver de la funcio creadora de UsuariGomoku
	 */
	private static void Crea_Usuari()
	{
		System.out.println( "Introdueix Nom Usuari" );
		String nom = lectura.llegirString();
		System.out.println( "Introdueix Contrasenya Usuari" );
		String pass = lectura.llegirString();
		usuari = new UsuariGomoku( nom, pass, nombre_dificultats );
	}

	/**
	 * Driver de les funcions consultores de UsuariGomoku, amb les que podem veure totes les dades guardades a
	 * UsuariGomoku
	 */
	private static void consulta()
	{
		System.out.println( "Consulta UsuariGomoku" );
		System.out.println( "====================" );
		System.out.println( "Nom :" + usuari.getNom() );
		System.out.println( "Contrasenya :" + usuari.getContrasenya() );
		System.out.println( "Victories contra IA Facil :" + usuari.getVictories( 0 ) );
		System.out.println( "Victories contra IA Mitjana :" + usuari.getVictories( 1 ) );
		System.out.println( "Victories contra IA Dificil :" + usuari.getVictories( 2 ) );
		System.out.println( "Victories contra Humans :" + usuari.getVictories( 3 ) );
		System.out.println( "Derrotes contra IA Facil :" + usuari.getDerrotes( 0 ) );
		System.out.println( "Derrotes contra IA Mitjana :" + usuari.getDerrotes( 1 ) );
		System.out.println( "Derrotes contra IA Dificil :" + usuari.getDerrotes( 2 ) );
		System.out.println( "Derrotes contra Humans :" + usuari.getDerrotes( 3 ) );
		System.out.println( "Empats contra IA Facil :" + usuari.getEmpats( 0 ) );
		System.out.println( "Empats contra IA Mitjana :" + usuari.getEmpats( 1 ) );
		System.out.println( "Empats contra IA Dificil :" + usuari.getEmpats( 2 ) );
		System.out.println( "Empats contra Humans :" + usuari.getEmpats( 3 ) );
		System.out.println();
	}

	/**
	 * Driver que ens permet probar les diferents funcions modificadores que disposa UsuariGomoku
	 */
	private static void modifica()
	{
		System.out.println( "Modificació UsuariGomoku" );
		System.out.println( "-------------------------" );
		System.out.println( "1. Modifica nom UsuariGomoku" );
		System.out.println( "2. Modifica contrasenya UsuariGomoku" );
		System.out.println( "3. Incrementa num_victories" );
		System.out.println( "4. Incrementa num_derrotes" );
		System.out.println( "5. Incrementa num_empats" );
		System.out.println( "6. Reseteja num_victories,empats i derrotes" );
		switch ( lectura.llegirInt() )
		{
			case 1:
				System.out.println( "Introdueix el nou nom d'usuari" );
				usuari.setNom( lectura.llegirString() );
				break;
			case 2:
				System.out.println( "Introdueix la nova contrasenya" );
				usuari.setContrasenya( lectura.llegirString() );
				break;
			case 3:
				System.out
						.println( "Introdueix la dificultat per incrementar el nombre de victories(0=facil 1=dificil 2=huma)" );
				System.out.println( "Seguit del nombre de victories en que el vols incrementar" );
				usuari.incrementaVictories( lectura.llegirInt(), lectura.llegirInt() );
				break;
			case 4:
				System.out
						.println( "Introdueix la dificultat per incrementar el nombre de derrotes(0=facil 1=dificil 2=huma)" );
				System.out.println( "Seguit del nombre de derrotes en que el vols incrementar" );
				usuari.incrementaDerrotes( lectura.llegirInt(), lectura.llegirInt() );
				break;
			case 5:
				System.out
						.println( "Introdueix la dificultat per incrementar el nombre de empats(0=facil 1=dificil 2=huma)" );
				System.out.println( "Seguit del nombre de empats en que el vols incrementar" );
				usuari.incrementaEmpats( lectura.llegirInt(), lectura.llegirInt() );
				break;
			case 6:
				System.out
						.println( "Introdueix la dificultat per posar a 0 el nombre de victories/empats/derrotes(0=facil 1=dificil 2=huma)" );
				usuari.reiniciaEstadistiques( lectura.llegirInt() );
				break;
		}
		System.out.println();
	}

	/**
	 * Gestiona quines funcions probarem en funcio de la dada llegida previament, introduida per l'usuari
	 * 
	 * @param args Indica quina opcio tractarem, si la creadora, la modificadora o la consultora
	 */
	public static void main( String[] args )
	{
		while ( true )
		{
			switch ( menuPrincipal() )
			{
				case 1:
					Crea_Usuari();
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
