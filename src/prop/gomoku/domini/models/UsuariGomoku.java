package prop.gomoku.domini.models;

import prop.cluster.domini.models.Usuari;

/**
 * Representa tota la informació d'un usuari. Conté les dades principals del perfil i les seves estadístiques en els
 * diferents nivells de dificultat. Cada usuari té un nom com a identificador, en particular aquest tipus d'usuari esta
 * adaptat al joc de Gomoku
 * 
 */
public class UsuariGomoku extends Usuari
{
	/**
	 * Nombre de dificultats per defecte
	 */
	private static final int nombre_dificultats = 4;

	/**
	 * ID serialització
	 */
	private static final long serialVersionUID = -4414787291777756469L;

	// TODO
	private TipusUsuari tipus;

	// TODO
	private static final int longitud_mininima_contrasenya = 4;
	
	/**
	 * Creadora d'un usuari Gomoku
	 * 
	 * @param nom Nom identificador de l'usuari
	 * @param contrasenya Contrasenya de l'usuari
	 */
	public UsuariGomoku( String nom, String contrasenya )
	{
		super( nom, contrasenya, nombre_dificultats );
		if ( !validaContrasenya( contrasenya ) )
		{
			throw new IllegalArgumentException( "La contrasenya no pot incloure caracters invalids" );
		}
		tipus = TipusUsuari.HUMA;
	}

	// TODO
	public UsuariGomoku( String nom, String contrasenya, TipusUsuari tipus )
	{
		super( nom, contrasenya, nombre_dificultats );
		if ( !validaContrasenya( contrasenya ) )
		{
			throw new IllegalArgumentException( "Contrasenya conté caracters invalids o és massa curta" );
		}
		this.tipus = tipus;
	}

	// TODO
	private boolean validaContrasenya( String contrasenya )
	{
		if ( contrasenya.contains( " " ) || contrasenya.length() < longitud_mininima_contrasenya)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// TODO
	public TipusUsuari getTipus()
	{
		return this.tipus;
	}

	/**
	 * Metode per obtenir el numero de victories per als diferents modes de dificultat
	 * 
	 * @return Un array d'enters on cada enter representa el numero de victories i la seva posicio el nivell en que
	 *         s'han dut a terme les partides
	 */
	public int[] getNumVictories()
	{
		return super.num_victories;
	}

	/**
	 * Metode per obtenir el numero de derrotes per als diferents modes de dificultat
	 * 
	 * @return Un array d'enters on cada enter representa el numero de derrotes i la seva posicio el nivell en que s'han
	 *         dut a terme les partides
	 */
	public int[] getNumDerrotes()
	{
		return super.num_derrotes;
	}

	/**
	 * Metode per obtenir el numero d'empats per als diferents modes de dificultat
	 * 
	 * @return Un array d'enters on cada enter representa el numero d'empats i la seva posicio el nivell en que s'han
	 *         dut a terme les partides
	 */
	public int[] getNumEmpats()
	{
		return super.num_empats;
	}

	/**
	 * Incrementador del nombre de victories en un cert nivell de dificultat en una quantitat determinada
	 * 
	 * @param Nivell_oponent Nivell de l'oponent
	 * @param Quantitat Quantitat en que es vol incrementar
	 * @return Retorna true ja que que sempre es produira l'increment
	 */
	public boolean incrementaVictories( int nivell_oponent, int quantitat )
	{
		num_victories[nivell_oponent] = num_victories[nivell_oponent] + quantitat;
		return true;
	}

	/**
	 * Incrementador del nombre de derrotes en un cert nivell de dificultat en una quantitat determinada
	 * 
	 * @param nivell_oponent Nivell_oponent Nivell de l'oponent
	 * @param quantitat Quantitat Quantitat en que es vol incrementar
	 * @return Retorna true ja que que sempre es produira l'increment
	 */
	public boolean incrementaDerrotes( int nivell_oponent, int quantitat )
	{
		num_derrotes[nivell_oponent] = num_derrotes[nivell_oponent] + quantitat;
		return true;
	}

	/**
	 * Modificador del nombre d'empats en un cert nivell de dificultat en una quantitat determinada
	 * 
	 * @param nivell_oponent Nivell_oponent Nivell de l'oponent
	 * @param quantitat Quantitat Quantitat en que es vol incrementar
	 * @return Retorna true ja que que sempre es produira l'increment
	 */
	public boolean incrementaEmpats( int nivell_oponent, int quantitat )
	{
		num_empats[nivell_oponent] = num_empats[nivell_oponent] + quantitat;
		return true;
	}

	/**
	 * Sobrecarrega del mètode de la superclasse per tal de incloure comprovació de la validesa de la contrasenya
	 */
	public boolean setContrasenya( String contrasenya )
	{
		if ( contrasenya.contains( " " ) )
		{
			return false;
		}
		return super.setContrasenya( contrasenya );
	}
}
