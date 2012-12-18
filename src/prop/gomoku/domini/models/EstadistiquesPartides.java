package prop.gomoku.domini.models;

/**
 * 
 * Clase que conte els ResumResultats de victories, empats, derrotes i percentatges referents a les partides jugades
 * 
 * @author Alexander Gimenez Ortega
 * 
 */
public class EstadistiquesPartides
{
	/**
	 * Nombre de victories
	 */
	private ResumResultats victories;
	/**
	 * Nombre d'empats
	 */
	private ResumResultats empats;
	/**
	 * Nombre de derrotes
	 */
	private ResumResultats derrotes;
	/**
	 * Nombre de percentatge de derrotes
	 */
	private ResumResultats percentatge_derrotes;
	/**
	 * Nombre de percentatge de victories
	 */
	private ResumResultats percentatge_victories;
	/**
	 * Nombre de percentatge de empats
	 */
	private ResumResultats percentatge_empats;
	/**
	 * Nombre de partides disputades
	 */
	private ResumResultats total_disputades;

	/**
	 * Creadora per defecte, inicialitza totes les dades com si el jugador no hagues jugat mai una partida
	 */
	public EstadistiquesPartides()
	{
		victories = new ResumResultats();
		derrotes = new ResumResultats();
		empats = new ResumResultats();
		percentatge_derrotes = new ResumResultats();
		percentatge_empats = new ResumResultats();
		percentatge_victories = new ResumResultats();
		total_disputades = new ResumResultats();
	}

	/**
	 * Creadora que reb les dades de les partides jugades del jugador, i crea unes estadistiques a partir d'aquestes
	 * 
	 * @param num_victories Numero de victories per a cada nivell de dificultat
	 * @param num_derrotes Numero de derrotes per a cada nivell de dificultat
	 * @param num_empats Numero d'empats per a cada nivell de dificultat
	 */
	public EstadistiquesPartides( int[] num_victories, int[] num_derrotes, int[] num_empats )
	{
		victories = new ResumResultats( num_victories );
		derrotes = new ResumResultats( num_derrotes );
		empats = new ResumResultats( num_empats );
		percentatge_derrotes = new ResumResultats( num_victories, num_derrotes, num_empats, 1 );
		percentatge_victories = new ResumResultats( num_victories, num_derrotes, num_empats, 0 );
		percentatge_empats = new ResumResultats( num_victories, num_derrotes, num_empats, 2 );
		total_disputades = new ResumResultats( num_victories, num_derrotes, num_empats, 3 );
	}

	/**
	 * Metode per obtenir el ResumResultats referents a les victories
	 * 
	 * @return Retorna el ResumResultats referent a les victories
	 */
	public ResumResultats getVictories()
	{
		return victories;
	}

	/**
	 * Metode per obtenir el ResumResultats referents a les derrotes
	 * 
	 * @return Retorna el ResumResultats referent a les derrotes
	 */
	public ResumResultats getDerrotes()
	{
		return derrotes;
	}

	/**
	 * Metode per obtenir el ResumResultats referents als empats
	 * 
	 * @return Retorna el ResumResultats referent als empats
	 */
	public ResumResultats getEmpats()
	{
		return empats;
	}

	/**
	 * Metode per obtenir el ResumResultats referents al total de partides disputades
	 * 
	 * @return Retorna el ResumResultats referents al total de partides disputades
	 */
	public ResumResultats getTotalDisputades()
	{
		return total_disputades;
	}

	/**
	 * Metode per obtenir el ResumResultats referents al percentatge de derrotes
	 * 
	 * @return Retorna el ResumResultats referent al percentatge de derrotes
	 */
	public ResumResultats getpercentatgederrotes()
	{
		return percentatge_derrotes;
	}

	/**
	 * Metode per obtenir el ResumResultats referents al percentatge d'empats
	 * 
	 * @return Retorna el ResumResultats referent al percentatge d'empats
	 */
	public ResumResultats getpercentatgeempats()
	{
		return percentatge_empats;
	}

	/**
	 * Metode per obtenir el ResumResultats referents al percentatge de victories
	 * 
	 * @return Retorna el ResumResultats referent al percentatge de victories
	 */
	public ResumResultats getpercentatgevictories()
	{
		return percentatge_victories;
	}

}
