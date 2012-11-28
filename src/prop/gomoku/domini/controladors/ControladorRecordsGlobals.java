package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import prop.gomoku.domini.models.EstadistiquesPartides;
import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.ResumResultats;

public class ControladorRecordsGlobals
{
	private PriorityQueue<LlistaRecordsIndividuals> llista_victories = new PriorityQueue<LlistaRecordsIndividuals>();

	public ControladorRecordsGlobals()
	{
		this.llista_victories = new PriorityQueue<LlistaRecordsIndividuals>( 5, new ComparadorRecords(
				Criteri.NUM_VICTORIES_HUMA ) );
	}

	private enum Criteri
	{
		NUM_VICTORIES_HUMA, NUM_VICTORIES_FACIL, NUM_VICTORIES_MITJA, NUM_VICTORIES_DIFICIL,
		NUM_EMPATS_HUMA, NUM_EMPATS_FACIL, NUM_EMPATS_MITJA, NUM_EMPATS_DIFICIL,
		NUM_DERROTES_HUMA, NUM_DERROTES_FACIL, NUM_DERROTES_MITJA, NUM_DERROTES_DIFICIL,
		PER_VICTORIES_HUMA, PER_VICTORIES_FACIL, PER_VICTORIES_MITJA, PER_VICTORIES_DIFICIL,
		PER_EMPATS_HUMA, PER_EMPATS_FACIL, PER_EMPATS_MITJA, PER_EMPATS_DIFICIL,
		PER_DERROTES_HUMA, PER_DERROTES_FACIL, PER_DERROTES_MITJA, PER_DERROTES_DIFICIL
	};

	private class ComparadorRecords implements Comparator<LlistaRecordsIndividuals>
	{

		private Criteri criteri;

		public ComparadorRecords( Criteri criteri )
		{
			this.criteri = criteri;
		}

		@Override
		public int compare( LlistaRecordsIndividuals a, LlistaRecordsIndividuals b )
		{
			int num_a = 0;
			int num_b = 0;
			switch ( this.criteri )
			{
			// HUMA ABS
				case NUM_VICTORIES_HUMA:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.HUMA, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.HUMA, Resultat.VICTORIA );
					break;
				case NUM_VICTORIES_FACIL:
					num_a = AccesRecords.getAbsoluts( a, Dificultat.FACIL, Resultat.VICTORIA );
					num_b = AccesRecords.getAbsoluts( b, Dificultat.FACIL, Resultat.VICTORIA );
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
			}
			
			if ( num_a > num_b )
			{
				return 1;
			}
			else if ( num_a < num_b )
			{
				return -1;
			}
			else
			{
				return 0;
			}
			// TODO Auto-generated method stub
			// if (record_0.getStatsPartidaRapida().getDerrotes().getNumFacils())
			// {
			//
			// }
			return 0;
		}
	}

	private enum Dificultat
	{
		HUMA, FACIL, MITJA, DIFICIL
	};

	private enum Resultat
	{
		VICTORIA, EMPAT, DERROTA
	};

	private static class AccesRecords
	{
		public static int getAbsoluts( LlistaRecordsIndividuals llista_records, Dificultat dificultat, Resultat resultat )
		{
			EstadistiquesPartides est = llista_records.getStatsPartidaRapida();
			switch ( resultat )
			{
				case VICTORIA:
				{
					ResumResultats vict = est.getVictories();
					switch ( dificultat )
					{
						case HUMA:
							return vict.getNumHumans();
						case FACIL:
							return vict.getNumFacils();
						case MITJA:
							return vict.getNumMitja();
						case DIFICIL:
							return vict.getNumDificils();
					}
					break;
				}
				case EMPAT:
				{
					ResumResultats emp = est.getEmpats();
					switch ( dificultat )
					{
						case HUMA:
							return emp.getNumHumans();
						case FACIL:
							return emp.getNumFacils();
						case MITJA:
							return emp.getNumMitja();
						case DIFICIL:
							return emp.getNumDificils();
					}
					break;
				}
				case DERROTA:
				{
					ResumResultats der = est.getDerrotes();
					switch ( dificultat )
					{
						case HUMA:
							return der.getNumHumans();
						case FACIL:
							return der.getNumFacils();
						case MITJA:
							return der.getNumMitja();
						case DIFICIL:
							return der.getNumDificils();
					}
					break;
				}
			}
			return 0;
		}

		public static int getPercentuals( LlistaRecordsIndividuals llista_records, Dificultat dificultat,
				Resultat resultat )
		{
			EstadistiquesPartides est = llista_records.getStatsPartidaRapida();
			switch ( resultat )
			{
				case VICTORIA:
				{
					ResumResultats vict = est.getpercentatgevictories();
					switch ( dificultat )
					{
						case HUMA:
							return vict.getNumHumans();
						case FACIL:
							return vict.getNumFacils();
						case MITJA:
							return vict.getNumMitja();
						case DIFICIL:
							return vict.getNumDificils();
					}
					break;
				}
				case EMPAT:
				{
					ResumResultats emp = est.getpercentatgeempats();
					switch ( dificultat )
					{
						case HUMA:
							return emp.getNumHumans();
						case FACIL:
							return emp.getNumFacils();
						case MITJA:
							return emp.getNumMitja();
						case DIFICIL:
							return emp.getNumDificils();
					}
					break;
				}
				case DERROTA:
				{
					ResumResultats der = est.getpercentatgederrotes();
					switch ( dificultat )
					{
						case HUMA:
							return der.getNumHumans();
						case FACIL:
							return der.getNumFacils();
						case MITJA:
							return der.getNumMitja();
						case DIFICIL:
							return der.getNumDificils();
					}
					break;
				}
			}
			return 0;
		}
	}
}
