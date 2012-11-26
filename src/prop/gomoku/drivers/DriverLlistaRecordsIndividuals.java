package prop.gomoku.drivers;

import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.UsuariGomoku;

/**
 * Driver de LlistaRecordsIndividuals, permet probar les diferents funcionalitats que pot oferir-nos la classe i
 * comprovar el seu correcte funcionament
 * 
 */
public class DriverLlistaRecordsIndividuals
{
	/**
	 * Indica el nombre de dificultats que podem trobar en el nostre joc. Una persona també compta com a possible
	 * dificultat.
	 */
	private static final int nombre_dificultats = 4;
	/**
	 * Conjunt d'estadistiques individuals d'un jugador
	 */
	private static LlistaRecordsIndividuals estadistiques;
	/**
	 * Variable que permet gestionar mes comodement lectures de dades
	 */
	private static LecturaScanners lectura = new LecturaScanners();

	/**
	 * Aquest menu mostra les principals opcions de creació i consulta que es poden dur a terme a la classe
	 * LlistaRecordIndividuals L'usuari introduira per teclat la opció que vulgui probar
	 * 
	 * @return Retornara la opcio del menú escollida per l'usuari
	 */
	private static int menuPrincipal()
	{
		System.out.println( "Driver LlistaRecordsIndividuals" );
		System.out.println( "===============================" );
		System.out.println( "Opcions:" );
		System.out.println( "1. Crea LlistaRecordsIndividuals" );
		System.out.println( "2. Consulta LlistaRecordsIndividuals" );
		System.out.println( "3. Surt" );
		System.out.println();
		return lectura.llegirInt();

	}

	/**
	 * Menu per a probar la funcionalitat de creacio de noves estadistiques
	 */
	private static void creaEstadistiques()
	{
		System.out.println( "Menu de Crear LlistarecordsIndividuals" );
		System.out.println( "====================" );
		System.out.println( "Escull si vols crear una llista amb tots els valors a 0 o a partir de dades ja existents" );
		System.out.println( "1. Totes les dades a 0" );
		System.out.println( "2. A partir de dades ja existents" );
		switch ( lectura.llegirInt() )
		{
			case 1:
				estadistiques = new LlistaRecordsIndividuals( new UsuariGomoku( "pablo", "afk" ) );
				break;
			case 2:
				UsuariGomoku usuari = new UsuariGomoku( "pablo", "afk" );
				System.out.println( "Introdueix els valors per a victories per a 0=facil 1=mitja 2=dificil 3=huma:" );
				for ( int iterador = 0; iterador < nombre_dificultats; ++iterador )
				{
					usuari.incrementaVictories( iterador, lectura.llegirInt() );
				}
				System.out.println( "Introdueix els valors per a derrotes per a 0=facil 1=mitja 2=dificil 3=huma:" );
				for ( int iterador = 0; iterador < nombre_dificultats; ++iterador )
				{
					usuari.incrementaDerrotes( iterador, lectura.llegirInt() );
				}
				System.out.println( "Introdueix els valors per a empats per a 0=facil 1=mitja 2=dificil 3=huma:" );
				for ( int iterador = 0; iterador < nombre_dificultats; ++iterador )
				{
					usuari.incrementaEmpats( iterador, lectura.llegirInt() );
				}
				estadistiques = new LlistaRecordsIndividuals( usuari );
				break;
		}
		System.out.println();
	}

	/**
	 * Funcio per a consultar estadistiques
	 */
	public static void consultaEstadistiques()
	{
		System.out.println( "Menu de Consultar Estadistiques" );
		System.out.println( "====================" );
		Print_Estadistiques();
	}

	/**
	 * Gestiona quines funcions probarem en funcio de la dada llegida previament, introduida per l'usuari
	 * 
	 * @param args Indica quina opcio tractarem, si la creadora o la consultora
	 */
	public static void main( String[] args )
	{
		boolean surt = false;
		while ( !surt )
		{
			switch ( menuPrincipal() )
			{
				case 1:
					creaEstadistiques();
					break;
				case 2:
					consultaEstadistiques();
					break;
				case 3:
					surt = true;
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Funcio que imprimieix totes les estadistiques
	 */
	public static void Print_Estadistiques()
	{
		System.out.println( "Victories contra IA  Facil "
				+ estadistiques.getStatsPartidaRapida().getVictories().getNumFacils() );
		System.out.println( "Victories contra IA  Mitjana "
				+ estadistiques.getStatsPartidaRapida().getVictories().getNumMitja() );
		System.out.println( "Victories contra IA  Dificil "
				+ estadistiques.getStatsPartidaRapida().getVictories().getNumDificils() );
		System.out.println( "Victories contra Humans "
				+ estadistiques.getStatsPartidaRapida().getVictories().getNumHumans() );
		System.out.println( "Victories Totals " + estadistiques.getStatsPartidaRapida().getVictories().getNumTotal() );
		System.out.println( "Derrotes contra IA Facil "
				+ estadistiques.getStatsPartidaRapida().getDerrotes().getNumFacils() );
		System.out.println( "Derrotes contra IA Mitjana "
				+ estadistiques.getStatsPartidaRapida().getDerrotes().getNumMitja() );
		System.out.println( "Derrotes contra IA Dificil "
				+ estadistiques.getStatsPartidaRapida().getDerrotes().getNumDificils() );
		System.out.println( "Derrotes contra Humans "
				+ estadistiques.getStatsPartidaRapida().getDerrotes().getNumHumans() );
		System.out.println( "Derrotes Totals " + estadistiques.getStatsPartidaRapida().getDerrotes().getNumTotal() );
		System.out.println( "Empats contra IA Facil "
				+ estadistiques.getStatsPartidaRapida().getEmpats().getNumFacils() );
		System.out.println( "Empats contra IA Mitjana "
				+ estadistiques.getStatsPartidaRapida().getEmpats().getNumMitja() );
		System.out.println( "Empats contra IA Dificil "
				+ estadistiques.getStatsPartidaRapida().getEmpats().getNumDificils() );
		System.out.println( "Empats contra Humans " + estadistiques.getStatsPartidaRapida().getEmpats().getNumHumans() );
		System.out.println( "Empats Totals " + estadistiques.getStatsPartidaRapida().getEmpats().getNumTotal() );
		System.out.println( "Percentatge de victories contra IA Facil "
				+ estadistiques.getStatsPartidaRapida().getpercentatgevictories().getNumFacils() + "%" );
		System.out.println( "Percentatge de victories contra IA Mitjana "
				+ estadistiques.getStatsPartidaRapida().getpercentatgevictories().getNumMitja() + "%" );
		System.out.println( "Percentatge de victories contra IA Dificil "
				+ estadistiques.getStatsPartidaRapida().getpercentatgevictories().getNumDificils() + "%" );
		System.out.println( "Percentatge de victories contra Humans "
				+ estadistiques.getStatsPartidaRapida().getpercentatgevictories().getNumHumans() + "%" );
		System.out.println( "Percentatge de victories Total "
				+ estadistiques.getStatsPartidaRapida().getpercentatgevictories().getNumTotal() + "%" );
		System.out.println( "Percentatge de derrotes contra IA Facil "
				+ estadistiques.getStatsPartidaRapida().getpercentatgederrotes().getNumFacils() + "%" );
		System.out.println( "Percentatge de derrotes contra IA Mitja "
				+ estadistiques.getStatsPartidaRapida().getpercentatgederrotes().getNumMitja() + "%" );
		System.out.println( "Percentatge de derrotes contra IA Dificil "
				+ estadistiques.getStatsPartidaRapida().getpercentatgederrotes().getNumDificils() + "%" );
		System.out.println( "Percentatge de derrotes contra Humans "
				+ estadistiques.getStatsPartidaRapida().getpercentatgederrotes().getNumHumans() + "%" );
		System.out.println( "Percentatge de derrotes Total "
				+ estadistiques.getStatsPartidaRapida().getpercentatgederrotes().getNumTotal() + "%" );
		System.out.println( "Percentatge de empats contra IA Facil "
				+ estadistiques.getStatsPartidaRapida().getpercentatgeempats().getNumFacils() + "%" );
		System.out.println( "Percentatge de empats contra IA Mitja "
				+ estadistiques.getStatsPartidaRapida().getpercentatgeempats().getNumMitja() + "%" );
		System.out.println( "Percentatge de empats contra IA Dificil "
				+ estadistiques.getStatsPartidaRapida().getpercentatgeempats().getNumDificils() + "%" );
		System.out.println( "Percentatge de empats contra Humans "
				+ estadistiques.getStatsPartidaRapida().getpercentatgeempats().getNumHumans() + "%" );
		System.out.println( "Percentatge de empats Total "
				+ estadistiques.getStatsPartidaRapida().getpercentatgeempats().getNumTotal() + "%" );
		System.out.println( "Total partides disputades contra IA Facil "
				+ estadistiques.getStatsPartidaRapida().getTotalDisputades().getNumFacils() );
		System.out.println( "Total partides disputades contra IA Mitja "
				+ estadistiques.getStatsPartidaRapida().getTotalDisputades().getNumMitja() );
		System.out.println( "Total partides disputades contra IA Dificil "
				+ estadistiques.getStatsPartidaRapida().getTotalDisputades().getNumDificils() );
		System.out.println( "Total partides disputades contra Humans "
				+ estadistiques.getStatsPartidaRapida().getTotalDisputades().getNumHumans() );
		System.out.println( "Total partides disputades contra tot tipus d'oponents "
				+ estadistiques.getStatsPartidaRapida().getTotalDisputades().getNumTotal() );
	}

}
