package prop.gomoku.domini.models;

import prop.cluster.domini.models.Usuari;

public class UsuariGomoku extends Usuari
{

	public UsuariGomoku( String nom, String contrasenya, int dificultat )
	{
		super( nom, contrasenya, dificultat );
	}

	public int[] getNumVictories()
	{
		return super.num_victories;
	}

	public int[] getNumDerrotes()
	{
		return super.num_derrotes;
	}

	public int[] getNumEmpats()
	{
		return super.num_empats;
	}

	public boolean incrementaVictories( int nivell_oponent, int quantitat )
	{
		num_victories[nivell_oponent] = num_victories[nivell_oponent] + quantitat;
		return true;
	}

	public boolean incrementaDerrotes( int nivell_oponent, int quantitat )
	{
		num_derrotes[nivell_oponent] = num_derrotes[nivell_oponent] + quantitat;
		return true;
	}

	public boolean incrementaEmpats( int nivell_oponent, int quantitat )
	{
		num_empats[nivell_oponent] = num_empats[nivell_oponent] + quantitat;
		return true;
	}
}
