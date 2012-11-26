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
		this.llista_victories = new PriorityQueue<LlistaRecordsIndividuals>( 5, new ComparadorRecords( 0 ) );
	}

	private class ComparadorRecords implements Comparator<LlistaRecordsIndividuals>
	{

		private int criteri;

		public ComparadorRecords( int criteri )
		{
			this.criteri = criteri;
		}

		@Override
		public int compare( LlistaRecordsIndividuals record_0, LlistaRecordsIndividuals record_1 )
		{
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

	private class AccesRecords
	{
		public int getAbsoluts( LlistaRecordsIndividuals llista_records, Dificultat dificultat, Resultat resultat )
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

		public int getPercentuals( LlistaRecordsIndividuals llista_records, Dificultat dificultat, Resultat resultat )
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
