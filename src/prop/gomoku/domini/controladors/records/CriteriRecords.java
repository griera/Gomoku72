package prop.gomoku.domini.controladors.records;

/**
 * Enumeració dels diferents criteris que es contemplen a l'hora de classificar els usuaris a nivell global així com per
 * a l'obtenció d'estadístiques a nivell individual
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public enum CriteriRecords
{
	/**
	 * Nombre de victòries contra humà
	 */
	NUM_VICTORIES_HUMA,
	/**
	 * Nombre de victòries contra CPU fàcil
	 */
	NUM_VICTORIES_FACIL,
	/**
	 * Nombre de victòries contra CPU mitja
	 */
	NUM_VICTORIES_MITJA,
	/**
	 * Nombre de victòries contra CPU difícil
	 */
	NUM_VICTORIES_DIFICIL,
	/**
	 * Nombre de victòries totals
	 */
	NUM_VICTORIES_TOTAL,
	/**
	 * Nombre d'empats contra humà
	 */
	NUM_EMPATS_HUMA,
	/**
	 * Nombre d'empats contra CPU fàcil
	 */
	NUM_EMPATS_FACIL,
	/**
	 * Nombre d'empats contra CPU mitja
	 */
	NUM_EMPATS_MITJA,
	/**
	 * Nombre d'empats contra CPU difícil
	 */
	NUM_EMPATS_DIFICIL,
	/**
	 * Nombre d'empats totals
	 */
	NUM_EMPATS_TOTAL,
	/**
	 * Nombre de derrotes contra humà
	 */
	NUM_DERROTES_HUMA,
	/**
	 * Nombre de derrotes contra CPU fàcil
	 */
	NUM_DERROTES_FACIL,
	/**
	 * Nombre de derrotes contra CPU mitja
	 */
	NUM_DERROTES_MITJA,
	/**
	 * Nombre de derrotes contra CPU difícil
	 */
	NUM_DERROTES_DIFICIL,
	/**
	 * Nombre de derrotes totals
	 */
	NUM_DERROTES_TOTAL,
	/**
	 * Percentatge de victòries contra humà
	 */
	PER_VICTORIES_HUMA,
	/**
	 * Percentatge de victòries contra CPU fàcil
	 */
	PER_VICTORIES_FACIL,
	/**
	 * Percentatge de victòries contra CPU mitja
	 */
	PER_VICTORIES_MITJA,
	/**
	 * Percentatge de victòries contra CPU difícil
	 */
	PER_VICTORIES_DIFICIL,
	/**
	 * Percentatge de victòries total
	 */
	PER_VICTORIES_TOTAL,
	/**
	 * Percentatge d'empats contra humà
	 */
	PER_EMPATS_HUMA,
	/**
	 * Percentatge d'empats contra CPU fàcil
	 */
	PER_EMPATS_FACIL,
	/**
	 * Percentatge d'empatts contra CPU mitja
	 */
	PER_EMPATS_MITJA,
	/**
	 * Percentatge d'empats contra CPU difícil
	 */
	PER_EMPATS_DIFICIL,
	/* Percentatge d'empats totals */
	PER_EMPATS_TOTAL,
	/**
	 * Percentatge de derrotes contra humà
	 */
	PER_DERROTES_HUMA,
	/**
	 * Percentatge de derrotes contra CPU fàcil
	 */
	PER_DERROTES_FACIL,
	/**
	 * Percentatge de derrotes contra CPU mitja
	 */
	PER_DERROTES_MITJA,
	/**
	 * Percentatge de derrotes contra CPU difícil
	 */
	PER_DERROTES_DIFICIL,
	/**
	 * Percentatge de derrotes total
	 */
	PER_DERROTES_TOTAL,
	/**
	 * Nombre de partides contra humà
	 */
	NUM_PARTIDES_HUMA,
	/**
	 * Nombre de partides contra CPU fàcil
	 */
	NUM_PARTIDES_FACIL,
	/**
	 * Nombre de partides contra CPU mitja
	 */
	NUM_PARTIDES_MITJA,
	/**
	 * Nombre de partides contra CPU difícil
	 */
	NUM_PARTIDES_DIFICIL,
	/**
	 * Nombre de partides total
	 */
	NUM_PARTIDES_TOTAL;

	/**
	 * Mètode que retorna un text descriptiu més entenible que no el propi nom dels elements de la enumeració
	 * 
	 * @return Text descriptiu corresponent a l'element de l'enumeració sobre el qual es crida
	 */
	public String getTextDescriptiu()
	{
		String text = "";
		switch ( this )
		{
			case NUM_VICTORIES_FACIL:
				text = "Nombre de victòries contra CPU Fàcil";
				break;
			case NUM_VICTORIES_MITJA:
				text = "Nombre de victòries contra CPU Mitjà";
				break;
			case NUM_VICTORIES_DIFICIL:
				text = "Nombre de victòries contra CPU Difícil";
				break;
			case NUM_VICTORIES_HUMA:
				text = "Nombre de victòries contra Humà";
				break;
			case NUM_VICTORIES_TOTAL:
				text = "Nombre total de victòries";

				break;
			case NUM_EMPATS_FACIL:
				text = "Nombre d'empats contra CPU Fàcil";
				break;
			case NUM_EMPATS_MITJA:
				text = "Nombre d'empats contra CPU Mitjà";
				break;
			case NUM_EMPATS_DIFICIL:
				text = "Nombre d'empats contra CPU Difícil";
				break;
			case NUM_EMPATS_HUMA:
				text = "Nombre d'empats contra CPU Humà";
				break;
			case NUM_EMPATS_TOTAL:
				text = "Nombre total d'empats";

				break;
			case NUM_DERROTES_FACIL:
				text = "Nombre de derrotes contra CPU Fàcil";
				break;
			case NUM_DERROTES_MITJA:
				text = "Nombre de derrotes contra CPU Mitjà";
				break;
			case NUM_DERROTES_DIFICIL:
				text = "Nombre de derrotes contra CPU Difícil";
				break;
			case NUM_DERROTES_HUMA:
				text = "Nombre de derrotes contra Humà";
				break;
			case NUM_DERROTES_TOTAL:
				text = "Nombre total de derrotes";

				break;
			case PER_VICTORIES_FACIL:
				text = "Percentatge de victòries contra CPU Fàcil";
				break;
			case PER_VICTORIES_MITJA:
				text = "Percentatge de victòries contra CPU Mitjà";
				break;
			case PER_VICTORIES_DIFICIL:
				text = "Percentatge de victòries contra CPU Difícil";
				break;
			case PER_VICTORIES_HUMA:
				text = "Percentatge de victòries contra Humà";
				break;
			case PER_VICTORIES_TOTAL:
				text = "Percentatge total de victories";

				break;
			case PER_EMPATS_FACIL:
				text = "Percentatge d'empats contra CPU Fàcil";
				break;
			case PER_EMPATS_MITJA:
				text = "Percentatge d'empats contra CPU Mitjà";
				break;
			case PER_EMPATS_DIFICIL:
				text = "Percentatge d'empats contra CPU Difícil";
				break;
			case PER_EMPATS_HUMA:
				text = "Percentatge d'empats contra Humà";
				break;
			case PER_EMPATS_TOTAL:
				text = "Percentatge total d'empats";

				break;
			case PER_DERROTES_FACIL:
				text = "Percentatge de derrotes contra CPU Fàcil";
				break;
			case PER_DERROTES_MITJA:
				text = "Percentatge de derrotes contra CPU Mitjà";
				break;
			case PER_DERROTES_DIFICIL:
				text = "Percentatge de derrotes contra CPU Difícil";
				break;
			case PER_DERROTES_HUMA:
				text = "Percentatge de derrotes contra Humà";
				break;
			case PER_DERROTES_TOTAL:
				text = "Percentatge total de derrotes";

				break;
			case NUM_PARTIDES_FACIL:
				text = "Nombre de partides disputades contra CPU Fàcil";
				break;
			case NUM_PARTIDES_MITJA:
				text = "Nombre de partides disputades contra CPU Mitjà";
				break;
			case NUM_PARTIDES_DIFICIL:
				text = "Nombre de partides disputades contra CPU Difícil";
				break;
			case NUM_PARTIDES_HUMA:
				text = "Nombre de partides disputades contra Humà";
				break;
			case NUM_PARTIDES_TOTAL:
				text = "Nombre total de partides disputades";
		}
		return text;
	}
}
