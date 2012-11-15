package prop.gomoku.domini.models;

public class EstadistiquesPartidaRapida
{
	private ResumResultats victories;
	private ResumResultats empats;
	private ResumResultats derrotes;
	private ResumResultats percentatge_derrotes;
	private ResumResultats percentatge_victories;
	private ResumResultats percentatge_empats;

	public EstadistiquesPartidaRapida()
	{
		victories = new ResumResultats();
		derrotes = new ResumResultats();
		empats = new ResumResultats();
		percentatge_derrotes = new ResumResultats();
		percentatge_empats = new ResumResultats();
		percentatge_victories = new ResumResultats();
	}

	public EstadistiquesPartidaRapida( int[] num_victories, int[] num_derrotes, int[] num_empats )
	{
		victories = new ResumResultats( num_victories );
		derrotes = new ResumResultats( num_derrotes );
		empats = new ResumResultats( num_empats );
		percentatge_derrotes = new ResumResultats( num_victories, num_derrotes, num_empats, 1 );
		percentatge_victories = new ResumResultats( num_victories, num_derrotes, num_empats, 0 );
		percentatge_empats = new ResumResultats( num_victories, num_derrotes, num_empats, 3 );
	}

	public ResumResultats getVictories()
	{
		return victories;
	}

	public ResumResultats getDerrotes()
	{
		return derrotes;
	}

	public ResumResultats getEmpats()
	{
		return empats;
	}

	public ResumResultats getpercentatgederrotes()
	{
		return percentatge_derrotes;
	}

	public ResumResultats getpercentatgeempats()
	{
		return percentatge_empats;
	}

	public ResumResultats getpercentatgevictories()
	{
		return percentatge_victories;
	}

}
