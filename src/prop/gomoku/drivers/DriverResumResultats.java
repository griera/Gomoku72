package prop.gomoku.drivers;

import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.models.ResumResultats;

/**
 * Driver de ResumResultats, permet provar les diferent funcionalitats que presenta la clase, crear i consultar
 * resultats
 * 
 */
public class DriverResumResultats
{
	/**
	 * Variable que permet gestionar mes comodement lectures de dades
	 */
	private static LecturaScanners lectura = new LecturaScanners();
	/**
	 * Representa un conjunt de resultats
	 */
	private static ResumResultats resultats;

	/**
	 * Aquest menu mostra les principals opcions de creació i consulta que es poden dur a terme a la classe
	 * ResumResultats L'usuari introduira per teclat la opció que vulgui probar
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
		System.out.println( "3. Surt" );
		System.out.println();
		return lectura.llegirInt();
	}

	/**
	 * Metode que serveix per a provar les diferents maneres de crear ResumResultats, ja sigui uns resultats a 0, o
	 * parting de dades previes
	 */
	public static void creaResultats()
	{
		System.out.println( "Menu de crear resultats" );
		System.out.println( "====================" );
		System.out.println( "Opcions:" );
		System.out.println( "1. Crear ResumResultats a 0" );
		System.out.println( "2. Crear ResumResultats a partir de dades ja existents" );
		System.out.println( "3. Crear ResumResultats a partir de dades ja existents per crear percentatges" );
		switch ( lectura.llegirInt() )
		{
			case 1:
				resultats = new ResumResultats();
				break;
			case 2:
				System.out.println( "Introdueix els valors corresponents al diferents tipus de dificultats(4)" );
				int[] dades = new int[4];
				for ( int iterador = 0; iterador < 4; ++iterador )
				{
					dades[iterador] = lectura.llegirInt();
				}
				resultats = new ResumResultats( dades );
				break;
			case 3:
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
				System.out.println( "Introdueix el tipus de percentatge que vols calcular" );
				System.out.println( "0. Victories" );
				System.out.println( "1. Derrotes" );
				System.out.println( "2. Empats" );
				int tipus = lectura.llegirInt();
				resultats = new ResumResultats( victories, derrotes, empats, tipus );
				break;
		}
	}

	/**
	 * Metode que invoca les funcions consultores de ResumResultats per poder veure les dades que conte i comprovar el
	 * seu correcte funcionament
	 */
	public static void consultaResultats()
	{
		System.out.println( "Aquestes son les dades de ResumResultats:" );
		System.out.println( "=========================" );
		System.out.println( "Facil: " + resultats.getNumFacils() );
		System.out.println( "Mitja: " + resultats.getNumMitja() );
		System.out.println( "Dificil: " + resultats.getNumDificils() );
		System.out.println( "Persones: " + resultats.getNumHumans() );
		System.out.println( "Total: " + resultats.getNumTotal() );
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
					creaResultats();
					break;
				case 2:
					consultaResultats();
					break;
				case 3:
					surt = true;
					break;
				default:
					break;
			}

		}

	}

}
