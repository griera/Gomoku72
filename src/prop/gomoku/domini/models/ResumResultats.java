package prop.gomoku.domini.models;

/**
 * Clase que representa
 * 
 */
public class ResumResultats
{
	/**
	 * 
	 */
	private static final int nombre_dificultats = 4;
	private int facil;
	private int mitja;
	private int dificil;
	private int persona;
	private int numero_total; // Sumatori de tots els atributs anteriors

	public ResumResultats()
	{
		facil = 0;
		dificil = 0;
		persona = 0;
		mitja = 0;
		numero_total = 0;
	}

	public ResumResultats( int[] estadistiques )
	{

		facil = estadistiques[0];
		dificil = estadistiques[2];
		persona = estadistiques[3];
		mitja = estadistiques[1];
		numero_total = facil + dificil + persona + mitja;
	}

	/**
	 * 
	 * @param numero_victories
	 * @param numero_derrotes
	 * @param numero_empats
	 * @param tipus_percentatge
	 */
	public ResumResultats( int[] numero_victories, int[] numero_derrotes, int[] numero_empats, int tipus_percentatge )
	{
		if ( tipus_percentatge == 0 )
		{
			if ( numero_victories[0] + numero_derrotes[0] + numero_empats[0] == 0 )
			{
				facil = 0;
			}
			else
			{
				facil = numero_victories[0] * 100 / ( numero_victories[0] + numero_derrotes[0] + numero_empats[0] );
			}
			if ( numero_victories[2] + numero_derrotes[2] + numero_empats[2] == 0 )
			{
				dificil = 0;
			}
			else
			{
				dificil = numero_victories[2] * 100 / ( numero_victories[2] + numero_derrotes[2] + numero_empats[2] );
			}
			if ( numero_victories[3] + numero_derrotes[3] + numero_empats[3] == 0 )
			{
				persona = 0;
			}
			else
			{
				persona = numero_victories[3] * 100 / ( numero_victories[3] + numero_derrotes[3] + numero_empats[3] );
			}
			if ( numero_victories[1] + numero_derrotes[1] + numero_empats[1] == 0 )
			{
				mitja = 0;
			}
			else
			{
				mitja = numero_victories[1] * 100 / ( numero_victories[1] + numero_derrotes[1] + numero_empats[1] );
			}
			int total = 0;
			for ( int i = 0; i < nombre_dificultats; ++i )
			{
				total += numero_victories[i];
				total += numero_derrotes[i];
				total += numero_empats[i];
			}
			if ( total == 0 )
			{
				numero_total = 0;
			}
			else
			{
				numero_total = ( ( numero_victories[0] + numero_victories[1] + numero_victories[2] + numero_victories[3] ) * 100 / total );
			}
		}
		else if ( tipus_percentatge == 1 )
		{
			if ( numero_victories[0] + numero_derrotes[0] + numero_empats[0] == 0 )
				facil = 0;
			else
				facil = numero_derrotes[0] * 100 / ( numero_victories[0] + numero_derrotes[0] + numero_empats[0] );
			if ( numero_victories[2] + numero_derrotes[2] + numero_empats[2] == 0 )
				dificil = 0;
			else
				dificil = numero_derrotes[2] * 100 / ( numero_victories[2] + numero_derrotes[2] + numero_empats[2] );
			if ( numero_victories[1] + numero_derrotes[1] + numero_empats[1] == 0 )
				mitja = 0;
			else
				mitja = numero_derrotes[1] * 100 / ( numero_victories[1] + numero_derrotes[1] + numero_empats[1] );
			if ( numero_victories[3] + numero_derrotes[3] + numero_empats[3] == 0 )
				persona = 0;
			else
				persona = numero_derrotes[3] * 100 / ( numero_victories[3] + numero_derrotes[3] + numero_empats[3] );
			int total = 0;
			for ( int i = 0; i < nombre_dificultats; ++i )
			{
				total += numero_victories[i];
				total += numero_derrotes[i];
				total += numero_empats[i];
			}
			if ( total == 0 )
				numero_total = 0;
			else
				numero_total = ( ( numero_derrotes[0] + numero_derrotes[1] + numero_derrotes[2] + numero_derrotes[3] ) * 100 / total );
		}
		else
		{
			if ( numero_victories[0] + numero_derrotes[0] + numero_empats[0] == 0 )
				facil = 0;
			else
				facil = numero_empats[0] * 100 / ( numero_victories[0] + numero_derrotes[0] + numero_empats[0] );
			if ( numero_victories[2] + numero_derrotes[2] + numero_empats[2] == 0 )
			{
				dificil = 0;
			}
			else
				dificil = numero_empats[2] * 100 / ( numero_victories[2] + numero_derrotes[2] + numero_empats[2] );
			if ( numero_victories[3] + numero_derrotes[3] + numero_empats[3] == 0 )
				persona = 0;
			else
				persona = numero_empats[3] * 100 / ( numero_victories[3] + numero_derrotes[3] + numero_empats[3] );
			if ( numero_victories[1] + numero_derrotes[1] + numero_empats[1] == 0 )
				mitja = 0;
			else
				mitja = numero_victories[1] * 100 / ( numero_victories[1] + numero_derrotes[1] + numero_empats[1] );
			int total = 0;
			for ( int i = 0; i < nombre_dificultats; ++i )
			{
				total += numero_victories[i];
				total += numero_derrotes[i];
				total += numero_empats[i];
			}
			if ( total == 0 )
				numero_total = 0;
			else
				numero_total = ( ( numero_empats[0] + numero_empats[1] + numero_empats[2] + numero_empats[3] ) * 100 / total );
		}
	}

	public int getNumFacils()
	{
		return this.facil;
	}

	public int getNumDificils()
	{
		return this.dificil;
	}

	public int getNumMitja()
	{
		return this.mitja;
	}

	public int getNumHumans()
	{
		return this.persona;
	}

	public int getNumTotal()
	{
		return this.numero_total;
	}
}
