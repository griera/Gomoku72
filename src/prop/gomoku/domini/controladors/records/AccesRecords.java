package prop.gomoku.domini.controladors.records;

import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals.Dificultat;
import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals.Resultat;
import prop.gomoku.domini.models.EstadistiquesPartides;
import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.ResumResultats;

public class AccesRecords
{
	public static int get(LlistaRecordsIndividuals llista_records, CriteriRecords criteri)
	{
		Dificultat dificultat;
		Resultat resultat;
		boolean percentual;
		
		if (criteri.toString().startsWith( "NUM" ))
		{
			percentual = false;
		}
		else
		{
			percentual = true;
		}
		
		if (criteri.toString().contains( "VICTORIES" ))
		{
			resultat = Resultat.VICTORIA;
		}
		else if (criteri.toString().contains( "EMPATS" ))
		{
			resultat = Resultat.EMPAT;
		}
		else
		{
			resultat = Resultat.DERROTA;
		}
	
		if (criteri.toString().contains( "HUMA" ))
		{
			dificultat = Dificultat.HUMA;
		}
		else if (criteri.toString().contains( "FACIL" ))
		{
			dificultat = Dificultat.FACIL;
		}
		else if (criteri.toString().contains( "MITJA" ))
		{
			dificultat = Dificultat.MITJA;
		}
		else
		{
			dificultat = Dificultat.DIFICIL;
		}
		
		if (percentual)
		{
			return getPercentuals( llista_records, dificultat, resultat );
		}
		else
		{
			return getAbsoluts( llista_records, dificultat, resultat );
		}
	}

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

	public static int getPercentuals( LlistaRecordsIndividuals llista_records, Dificultat dificultat, Resultat resultat )
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
