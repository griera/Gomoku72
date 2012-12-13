package prop.gomoku.domini.controladors.records;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorUsuaris;

// The Great TODO
public class ControladorRecordsGlobals
{
	private GestorUsuaris gestor_usuaris;

	public ControladorRecordsGlobals()
	{
		gestor_usuaris = new GestorUsuaris();
	}

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

	private class ComparadorRecords implements Comparator<LlistaRecordsIndividuals>
	{

		private CriteriRecords criteri;

		public ComparadorRecords( CriteriRecords criteri )
		{
			this.criteri = criteri;
		}

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

	enum Dificultat
	{
		HUMA, FACIL, MITJA, DIFICIL, TOTES
	};

	enum Resultat
	{
		VICTORIA, EMPAT, DERROTA, TOTS
	}
}
