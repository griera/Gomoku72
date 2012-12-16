package prop.gomoku.domini.controladors.records;

import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals.Dificultat;
import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals.Resultat;
import prop.gomoku.domini.models.EstadistiquesPartides;
import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.ResumResultats;

/**
 * Classe de conveniència que permet accedir de forma parametritzada a una llista de records individuals d'un usuari
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class AccesRecords
{
	/**
	 * Mètode que permet consultar el valor associat a una llista de records individuals determinada segons un criteri
	 * específic
	 * 
	 * @param llista_records Llista de records individuals a consultar
	 * @param criteri Criteri del qual obtenir la puntuació
	 * @return Puntuació associada al criteri especificat a la llista de records individuals donada
	 */
	public static int get( LlistaRecordsIndividuals llista_records, CriteriRecords criteri )
	{
		Dificultat dificultat;
		Resultat resultat;
		boolean percentual;

		if ( criteri.toString().startsWith( "NUM" ) )
		{
			percentual = false;
		}
		else
		{
			percentual = true;
		}

		if ( criteri.toString().contains( "VICTORIES" ) )
		{
			resultat = Resultat.VICTORIA;
		}
		else if ( criteri.toString().contains( "EMPATS" ) )
		{
			resultat = Resultat.EMPAT;
		}
		else if ( criteri.toString().contains( "DERROTES" ) )
		{
			resultat = Resultat.DERROTA;
		}
		else
		{
			resultat = Resultat.TOTS;
		}

		if ( criteri.toString().contains( "HUMA" ) )
		{
			dificultat = Dificultat.HUMA;
		}
		else if ( criteri.toString().contains( "FACIL" ) )
		{
			dificultat = Dificultat.FACIL;
		}
		else if ( criteri.toString().contains( "MITJA" ) )
		{
			dificultat = Dificultat.MITJA;
		}
		else if ( criteri.toString().contains( "DIFICIL" ) )
		{
			dificultat = Dificultat.DIFICIL;
		}
		else
		{
			dificultat = Dificultat.TOTES;
		}

		if ( percentual )
		{
			return getPercentuals( llista_records, dificultat, resultat );
		}
		else
		{
			return getAbsoluts( llista_records, dificultat, resultat );
		}
	}

	/**
	 * Mètode de consulta del valor absolut que conté una llista de records individuals donada una dificultat
	 * determinada i un resultat específic
	 * 
	 * @param llista_records Llista de records individuals a consultar
	 * @param dificultat Dificultat de la qual en volem saber la dada
	 * @param resultat Restultat del qual en volem saber la dada
	 * @return Dada rellevant (nombre absolut) de la llista de records per a una dificultat i resultats determinats
	 */
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
					case TOTES:
						return vict.getNumTotal();
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
					case TOTES:
						return emp.getNumTotal();
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
					case TOTES:
						return der.getNumTotal();
				}
				break;
			}

			case TOTS:
				ResumResultats tots = est.getTotalDisputades();
				switch ( dificultat )
				{
					case HUMA:
						return tots.getNumHumans();
					case FACIL:
						return tots.getNumFacils();
					case MITJA:
						return tots.getNumMitja();
					case DIFICIL:
						return tots.getNumDificils();
					case TOTES:
						return tots.getNumTotal();
				}
				break;
		}
		return 0;
	}

	/**
	 * Mètode de consulta del valor percentual que conté una llista de records individuals donada una dificultat
	 * determinada i un resultat específic
	 * 
	 * @param llista_records Llista de records individuals a consultar
	 * @param dificultat Dificultat de la qual en volem saber la dada
	 * @param resultat Restultat del qual en volem saber la dada
	 * @return Dada rellevant (percentatge) de la llista de records per a una dificultat i resultats determinats
	 */
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
					case TOTES:
						return vict.getNumTotal();
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
					case TOTES:
						return emp.getNumTotal();
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
					case TOTES:
						return der.getNumTotal();
				}
				break;
			}
			case TOTS:
				// No té sentit de que es doni aquest cas
				ResumResultats tots = est.getTotalDisputades();
				switch ( dificultat )
				{
					case HUMA:
						return tots.getNumHumans();
					case FACIL:
						return tots.getNumFacils();
					case MITJA:
						return tots.getNumMitja();
					case DIFICIL:
						return tots.getNumDificils();
					case TOTES:
						return tots.getNumTotal();
				}
				break;
		}
		return 0;
	}
}
