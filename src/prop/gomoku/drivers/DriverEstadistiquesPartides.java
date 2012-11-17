package prop.gomoku.drivers;

import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.models.EstadistiquesPartides;

/**
 * Driver de la clase EstadistiquesPartidaRapida
 * 
 */
public class DriverEstadistiquesPartides
{
	/**
	 * Variable que permet gestionar mes comodement lectures de dades
	 */
	private static LecturaScanners lectura = new LecturaScanners();
	/**
	 * Estadistiques de partida rapida que proporcionen informacio sobre el nombre de victories,derrotes, empats i
	 * percentatges
	 */
	private static EstadistiquesPartides estadistiques;

	/**
	 * Aquest menu mostra les principals opcions de creació i consulta que es poden dur a terme a la classe
	 * EstadistiquesPartidaRapida L'usuari introduira per teclat la opció que vulgui probar
	 * 
	 * @return Retornara la opcio del menú escollida per l'usuari
	 */
	private static int menuPrincipal()
	{
		System.out.println( "Driver ResumResultats" );
		System.out.println( "====================" );
		System.out.println( "Opcions:" );
		System.out.println( "1. Crea ResumResultats" );
		System.out.println( "2. Consulta ResumResultats" );
		System.out.println();
		return lectura.llegirInt();
	}

	/**
	 * Gestiona quines funcions probarem en funcio de la dada llegida previament, introduida per l'usuari
	 * 
	 * @param args Indica quina opcio tractarem, si la creadora o la consultora
	 */
	public static void main( String[] args )
	{
		while ( true )
		{
			switch ( menuPrincipal() )
			{
				case 1:
					creaEstadistiques();
					break;
				case 2:
					consultaEstadistiques();
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Consulta les estadistiques, mostrant totes les dades referents a victories,derrotes,empats i percentatges i
	 * partides disputades
	 */

	private static void consultaEstadistiques()
	{
		System.out.println( "Les estadistiques actuals son les seguents:" );
		System.out.println( "====================" );
		System.out.println( "Victories contra IA  Facil " + estadistiques.getVictories().getNumFacils() );
		System.out.println( "Victories contra IA  Mitjana " + estadistiques.getVictories().getNumMitja() );
		System.out.println( "Victories contra IA  Dificil " + estadistiques.getVictories().getNumDificils() );
		System.out.println( "Victories contra Humans " + estadistiques.getVictories().getNumHumans() );
		System.out.println( "Victories Totals " + estadistiques.getVictories().getNumTotal() );
		System.out.println( "Derrotes contra IA Facil " + estadistiques.getDerrotes().getNumFacils() );
		System.out.println( "Derrotes contra IA Mitjana " + estadistiques.getDerrotes().getNumMitja() );
		System.out.println( "Derrotes contra IA Dificil " + estadistiques.getDerrotes().getNumDificils() );
		System.out.println( "Derrotes contra Humans " + estadistiques.getDerrotes().getNumHumans() );
		System.out.println( "Derrotes Totals " + estadistiques.getDerrotes().getNumTotal() );
		System.out.println( "Empats contra IA Facil " + estadistiques.getEmpats().getNumFacils() );
		System.out.println( "Empats contra IA Mitjana " + estadistiques.getEmpats().getNumMitja() );
		System.out.println( "Empats contra IA Dificil " + estadistiques.getEmpats().getNumDificils() );
		System.out.println( "Empats contra Humans " + estadistiques.getEmpats().getNumHumans() );
		System.out.println( "Empats Totals " + estadistiques.getEmpats().getNumTotal() );
		System.out.println( "Percentatge de victories contra IA Facil "
				+ estadistiques.getpercentatgevictories().getNumFacils() + "%" );
		System.out.println( "Percentatge de victories contra IA Mitjana "
				+ estadistiques.getpercentatgevictories().getNumMitja() + "%" );
		System.out.println( "Percentatge de victories contra IA Dificil "
				+ estadistiques.getpercentatgevictories().getNumDificils() + "%" );
		System.out.println( "Percentatge de victories contra Humans "
				+ estadistiques.getpercentatgevictories().getNumHumans() + "%" );
		System.out.println( "Percentatge de victories Total " + estadistiques.getpercentatgevictories().getNumTotal()
				+ "%" );
		System.out.println( "Percentatge de empats contra IA Facil "
				+ estadistiques.getpercentatgeempats().getNumFacils() + "%" );
		System.out.println( "Percentatge de empats contra IA Mitja "
				+ estadistiques.getpercentatgeempats().getNumMitja() + "%" );
		System.out.println( "Percentatge de empats contra IA Dificil "
				+ estadistiques.getpercentatgeempats().getNumDificils() + "%" );
		System.out.println( "Percentatge de empats contra Humans "
				+ estadistiques.getpercentatgeempats().getNumHumans() + "%" );
		System.out.println( "Percentatge de empats Total " + estadistiques.getpercentatgeempats().getNumTotal() + "%" );
		System.out.println( "Percentatge de derrotes contra IA Facil "
				+ estadistiques.getpercentatgederrotes().getNumFacils() + "%" );
		System.out.println( "Percentatge de derrotes contra IA Mitja "
				+ estadistiques.getpercentatgederrotes().getNumMitja() + "%" );
		System.out.println( "Percentatge de derrotes contra IA Dificil "
				+ estadistiques.getpercentatgederrotes().getNumDificils() + "%" );
		System.out.println( "Percentatge de derrotes contra Humans "
				+ estadistiques.getpercentatgederrotes().getNumHumans() + "%" );
		System.out.println( "Percentatge de derrotes Total " + estadistiques.getpercentatgederrotes().getNumTotal()
				+ "%" );
		System.out.println( "Total partides disputades contra IA Facil "
				+ estadistiques.getTotalDisputades().getNumFacils() );
		System.out.println( "Total partides disputades contra IA Mitja "
				+ estadistiques.getTotalDisputades().getNumMitja() );
		System.out.println( "Total partides disputades contra IA Dificil "
				+ estadistiques.getTotalDisputades().getNumDificils() );
		System.out.println( "Total partides disputades contra Humans "
				+ estadistiques.getTotalDisputades().getNumHumans() );
		System.out.println( "Total partides disputades contra tot tipus d'oponents "
				+ estadistiques.getTotalDisputades().getNumTotal() );

	}

	/**
	 * Menu per crear estadistiques desde zero o a partir de dades existents
	 */

	private static void creaEstadistiques()
	{
		System.out.println( "Menu de crear Estadistiques" );
		System.out.println( "====================" );
		System.out.println( "Opcions:" );
		System.out.println( "1. Crear Estadistiques a 0" );
		System.out.println( "2. Crear Estadistiques a partir de dades ja existents" );
		switch ( lectura.llegirInt() )
		{
			case 1:
				estadistiques = new EstadistiquesPartides();
				break;
			case 2:
				System.out
						.println( "Introdueix els valors corresponents al diferents tipus de dificultats(4) referents al numero de victories" );
				int[] victories = new int[4];
				for ( int iterador = 0; iterador < 4; ++iterador )
				{
					victories[iterador] = lectura.llegirInt();
				}
				System.out
						.println( "Introdueix els valors corresponents al diferents tipus de dificultats(4) referents al numero de derrotes" );
				int[] derrotes = new int[4];
				for ( int iterador = 0; iterador < 4; ++iterador )
				{
					derrotes[iterador] = lectura.llegirInt();
				}
				System.out
						.println( "Introdueix els valors corresponents al diferents tipus de dificultats(4) referents al numero d'empats" );
				int[] empats = new int[4];
				for ( int iterador = 0; iterador < 4; ++iterador )
				{
					empats[iterador] = lectura.llegirInt();
				}
				estadistiques = new EstadistiquesPartides( victories, derrotes, empats );
				break;
		}
	}
}
