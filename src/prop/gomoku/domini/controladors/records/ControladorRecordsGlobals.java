package prop.gomoku.domini.controladors.records;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorUsuaris;

/**
 * Controlador de records globals. Permet el seu càlcul i consulta en temps d'execució. Considera tots els usuaris
 * guardats a disc que hi hagi presents al sistema.
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ControladorRecordsGlobals
{
	/**
	 * Gestor d'usuaris utilitzat per a la càrrega d'aquests
	 */
	private GestorUsuaris gestor_usuaris;

	/**
	 * Constructora per defecte
	 */
	public ControladorRecordsGlobals()
	{
		gestor_usuaris = new GestorUsuaris();
	}

	/**
	 * Permet obtenir una llista dels 5 usuaris que més destaquen per a un criteri donat. Cada element de la llista que
	 * es retorna és una array de Strings on el primer element és el nom del usuari del rànquing i el segon és la dada
	 * rellevant d'aquest pel criteri seleccionat
	 * 
	 * @param criteri Criteri pel qual es vol obtenir la llista de records
	 * @return Llista que conté parelles de Strings amb el nom dels usuaris (primera posició) i les dades rellevants al
	 *         criteri especificat (segona posició)
	 */
	public List<String[]> getLlistaRecords( CriteriRecords criteri )
	{
		PriorityQueue<LlistaRecordsIndividuals> llista_records_individuals = new PriorityQueue<LlistaRecordsIndividuals>(
				5, new ComparadorRecords( criteri ) );

		List<UsuariGomoku> llista_usuaris = gestor_usuaris.carregaTots();
		for ( UsuariGomoku usuari : llista_usuaris )
		{
			llista_records_individuals.add( new LlistaRecordsIndividuals( usuari ) );
		}

		List<String[]> llista_dades = new ArrayList<String[]>();
		int i = 0;
		while ( i < 5 && !llista_records_individuals.isEmpty() )
		{
			String[] dades = new String[2];
			LlistaRecordsIndividuals record = llista_records_individuals.poll();
			dades[0] = record.getUsuari().getNom();
			dades[1] = "" + AccesRecords.get( record, criteri );
			llista_dades.add( dades );
			i++;
		}

		return llista_dades;
	}

	/**
	 * Comparador personalitzat per permetre comparar llistes de records individuals donat un criteri específic (ens
	 * permet utilitzar, per exemple, cues de prioritat)
	 * 
	 * @author Mauricio Ignacio Contreras Pinilla
	 * 
	 */
	private class ComparadorRecords implements Comparator<LlistaRecordsIndividuals>
	{

		/**
		 * Criteri pel qual es vol realitzar la comparació entre llistes de records individuals
		 */
		private CriteriRecords criteri;

		/**
		 * Creadora bàsica del comparador
		 * 
		 * @param criteri Criteri pel qual es vol realitzar la comparació
		 */
		public ComparadorRecords( CriteriRecords criteri )
		{
			this.criteri = criteri;
		}

		/**
		 * Mètode per comparar dues llistes de records individuals, segons el criteri especificat en crear el comparador
		 * 
		 * @param a Primera llista de records individuals de la comparació
		 * @param b Segona llista de records individuals de la comparació
		 * @return 1 si <em>b</em> supera a <em>a</em> en el criteri especificat, 0 si segons el criteri són iguals i -1
		 *         si <em>a</em> supera a <em>b<em>
		 */
		@Override
		public int compare( LlistaRecordsIndividuals a, LlistaRecordsIndividuals b )
		{
			int num_a = 0;
			int num_b = 0;

			// Una mica de DRY no aniria malament
			switch ( this.criteri )
			{
				case NUM_VICTORIES_HUMA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.HUMA, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.HUMA, Resultat.VICTORIA );
					break;
				case NUM_VICTORIES_FACIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.FACIL, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.FACIL, Resultat.VICTORIA );
					break;
				case NUM_VICTORIES_MITJA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.MITJA, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.MITJA, Resultat.VICTORIA );
					break;
				case NUM_VICTORIES_DIFICIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.DIFICIL, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.DIFICIL, Resultat.VICTORIA );
					break;
				case NUM_EMPATS_HUMA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.HUMA, Resultat.EMPAT );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.HUMA, Resultat.EMPAT );
					break;
				case NUM_EMPATS_FACIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.FACIL, Resultat.EMPAT );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.FACIL, Resultat.EMPAT );
					break;
				case NUM_EMPATS_MITJA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.MITJA, Resultat.EMPAT );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.MITJA, Resultat.EMPAT );
					break;
				case NUM_EMPATS_DIFICIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.DIFICIL, Resultat.EMPAT );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.DIFICIL, Resultat.EMPAT );
					break;
				case NUM_DERROTES_HUMA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.HUMA, Resultat.DERROTA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.HUMA, Resultat.DERROTA );
					break;
				case NUM_DERROTES_FACIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.FACIL, Resultat.DERROTA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.FACIL, Resultat.DERROTA );
					break;
				case NUM_DERROTES_MITJA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.MITJA, Resultat.DERROTA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.MITJA, Resultat.DERROTA );
					break;
				case NUM_DERROTES_DIFICIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.DIFICIL, Resultat.DERROTA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.DIFICIL, Resultat.DERROTA );
					break;
				case PER_VICTORIES_HUMA:
					num_a = AccesRecords.getPercentuals( a, Dificultat.HUMA, Resultat.VICTORIA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.HUMA, Resultat.VICTORIA );
					break;
				case PER_VICTORIES_FACIL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.FACIL, Resultat.VICTORIA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.FACIL, Resultat.VICTORIA );
					break;
				case PER_VICTORIES_MITJA:
					num_a = AccesRecords.getPercentuals( a, Dificultat.MITJA, Resultat.VICTORIA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.MITJA, Resultat.VICTORIA );
					break;
				case PER_VICTORIES_DIFICIL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.DIFICIL, Resultat.VICTORIA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.DIFICIL, Resultat.VICTORIA );
					break;
				case PER_EMPATS_HUMA:
					num_a = AccesRecords.getPercentuals( a, Dificultat.HUMA, Resultat.EMPAT );
					num_b = AccesRecords.getPercentuals( b, Dificultat.HUMA, Resultat.EMPAT );
					break;
				case PER_EMPATS_FACIL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.FACIL, Resultat.EMPAT );
					num_b = AccesRecords.getPercentuals( b, Dificultat.FACIL, Resultat.EMPAT );
					break;
				case PER_EMPATS_MITJA:
					num_a = AccesRecords.getPercentuals( a, Dificultat.MITJA, Resultat.EMPAT );
					num_b = AccesRecords.getPercentuals( b, Dificultat.MITJA, Resultat.EMPAT );
					break;
				case PER_EMPATS_DIFICIL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.DIFICIL, Resultat.EMPAT );
					num_b = AccesRecords.getPercentuals( b, Dificultat.DIFICIL, Resultat.EMPAT );
					break;
				case PER_DERROTES_HUMA:
					num_a = AccesRecords.getPercentuals( a, Dificultat.HUMA, Resultat.DERROTA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.HUMA, Resultat.DERROTA );
					break;
				case PER_DERROTES_FACIL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.FACIL, Resultat.DERROTA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.FACIL, Resultat.DERROTA );
					break;
				case PER_DERROTES_MITJA:
					num_a = AccesRecords.getPercentuals( a, Dificultat.MITJA, Resultat.DERROTA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.MITJA, Resultat.DERROTA );
					break;
				case PER_DERROTES_DIFICIL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.DIFICIL, Resultat.DERROTA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.DIFICIL, Resultat.DERROTA );
					break;
				case NUM_VICTORIES_TOTAL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.TOTES, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.TOTES, Resultat.EMPAT );
					break;
				case NUM_EMPATS_TOTAL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.TOTES, Resultat.EMPAT );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.TOTES, Resultat.EMPAT );
					break;
				case NUM_DERROTES_TOTAL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.TOTES, Resultat.DERROTA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.TOTES, Resultat.DERROTA );
					break;
				case PER_VICTORIES_TOTAL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.TOTES, Resultat.VICTORIA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.TOTES, Resultat.EMPAT );
					break;
				case PER_EMPATS_TOTAL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.TOTES, Resultat.EMPAT );
					num_b = AccesRecords.getPercentuals( b, Dificultat.TOTES, Resultat.EMPAT );
					break;
				case PER_DERROTES_TOTAL:
					num_a = AccesRecords.getPercentuals( a, Dificultat.TOTES, Resultat.DERROTA );
					num_b = AccesRecords.getPercentuals( b, Dificultat.TOTES, Resultat.DERROTA );
					break;
				case NUM_PARTIDES_HUMA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.HUMA, Resultat.TOTS );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.HUMA, Resultat.TOTS );
					break;
				case NUM_PARTIDES_FACIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.FACIL, Resultat.TOTS );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.FACIL, Resultat.TOTS );
					break;
				case NUM_PARTIDES_MITJA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.MITJA, Resultat.TOTS );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.MITJA, Resultat.TOTS );
					break;
				case NUM_PARTIDES_DIFICIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.DIFICIL, Resultat.TOTS );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.DIFICIL, Resultat.TOTS );
					break;
				case NUM_PARTIDES_TOTAL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.TOTES, Resultat.TOTS );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.TOTES, Resultat.TOTS );
					break;

			}

			if ( num_a < num_b )
			{
				return 1;
			}
			else if ( num_a > num_b )
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}

	/**
	 * Enumeració que representa les diferents dificultats a tenir en compte a l'hora de treballar amb els criteris
	 * 
	 * @author Mauricio Ignacio Contreras Pinilla
	 * 
	 */
	enum Dificultat
	{
		/**
		 * Dificultat d'una partida contra un jugador humà (ja sigui un usuari registrat o convidat)
		 */
		HUMA,
		/**
		 * Dificultat d'una partida contra la CPU fàcil
		 */
		FACIL,
		/**
		 * Dificultat d'una partida contra la CPU mitja
		 */
		MITJA,
		/**
		 * Dificultat d'una partida contra la CPU difícil
		 */
		DIFICIL,
		/**
		 * Totes les dificultats anteriors
		 */
		TOTES

	};

	/**
	 * Enumeració que representa els diferents resultat d'una partida (de cara al jugador) a tenir en compte a l'hora de
	 * treballar amb els criteris
	 * 
	 * @author Mauricio Ignacio Contreras Pinilla
	 * 
	 */
	enum Resultat
	{
		/**
		 * Partida guanyada en victoria del usuari
		 */
		VICTORIA,
		/**
		 * Partida acabada en empat
		 */
		EMPAT,
		/**
		 * Partida acabada en derrota del usuari
		 */
		DERROTA,
		/**
		 * Partida acabada en qualsevol resultat
		 */
		TOTS
	}
}
