package prop.cluster.domini.models;

import java.io.Serializable;
import java.util.Arrays;

public class Usuari implements Serializable
{
	private static final long serialVersionUID = 4464024144620009329L;

	protected String nom;
	protected String contrasenya;
	protected int[] num_victories;
	protected int[] num_empats;
	protected int[] num_derrotes;

	public Usuari( String nom, String contrasenya, int dificultat )
	{
		this.nom = nom;
		this.contrasenya = contrasenya;
		this.num_victories = new int[dificultat];
		this.num_empats = new int[dificultat];
		this.num_derrotes = new int[dificultat];
	}

	public String getNom()
	{
		return this.nom;
	}

	public String getContrasenya()
	{
		return this.contrasenya;
	}

	public int getVictories( int contrincant )
	{
		return this.num_victories[contrincant];
	}

	public int getEmpats( int contrincant )
	{
		return this.num_empats[contrincant];
	}

	public int getDerrotes( int contrincant )
	{
		return this.num_derrotes[contrincant];
	}

	public boolean setNom( String nom )
	{
		this.nom = nom;
		return true;
	}

	public boolean setContrasenya( String contrasenya )
	{
		this.contrasenya = contrasenya;
		return true;
	}

	public boolean incrementaVictories( int contrincant )
	{
		this.num_victories[contrincant] += 1;
		return true;
	}

	public boolean incrementaEmpats( int contrincant )
	{
		this.num_empats[contrincant] += 1;
		return true;
	}

	public boolean incrementaDerrotes( int contrincant )
	{
		this.num_derrotes[contrincant] += 1;
		return true;
	}

	@Override
	public String toString()
	{
		String text = "[" + this.nom + ", " + this.contrasenya + ", Victories: " + Arrays.toString( this.num_victories )
				+ ", Empats: " + Arrays.toString( this.num_empats ) + ", Derrotes: "
				+ Arrays.toString( this.num_derrotes ) + "]";
		return text;
	}

	public void reiniciaEstadistiques()
	{
		int mida = this.num_victories.length;
		for ( int i = 0; i < mida; i++ )
		{
			this.num_derrotes[i] = 0;
			this.num_empats[i] = 0;
			this.num_victories[i] = 0;
		}
	}
}
