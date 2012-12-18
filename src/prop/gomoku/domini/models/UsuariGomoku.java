package prop.gomoku.domini.models;

import prop.cluster.domini.models.Usuari;

/**
 * Representa tota la informació d'un usuari. Conté les dades principals del perfil i les seves estadístiques en els
 * diferents nivells de dificultat. Cada usuari té un nom com a identificador, en particular aquest tipus d'usuari esta
 * adaptat al joc de Gomoku
 * 
 * @author Alexander Gimenez Ortega
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

	/**
	 * Tipus del usuari, proporciona informació important i facilita la gestió de convidats, usuaris CPU, etc.
	 */
	private TipusUsuari tipus;

	/**
	 * Longitud mínima de la contrasenya, com a mesura de seguretat i per evitar possibles conflictes amb el xifratge
	 */
	private static final int longitud_mininima_contrasenya = 4;

	/**
	 * Constructora d'un usuari Gomoku de tipus humà
	 * 
	 * @param nom Nom identificador del usuari
	 * @param contrasenya Contrasenya del usuari
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

	/**
	 * Contructora bàsica per un usuari nou, permetent especificar el tipus
	 * 
	 * @param nom Nom identificardor del usuari
	 * @param contrasenya Contrasenya del usuari
	 * @param tipus Tipus del usuari a crear
	 */
	public UsuariGomoku( String nom, String contrasenya, TipusUsuari tipus )
	{
		super( nom, contrasenya, nombre_dificultats );
		if ( !validaContrasenya( contrasenya ) )
		{
			throw new IllegalArgumentException( "Contrasenya conté caracters invalids o és massa curta" );
		}
		this.tipus = tipus;
	}

	/**
	 * Comprovació bàsica de la validesa d'una contrasenya
	 * 
	 * @param contrasenya Contrasenya a comprovar
	 * @return <em>true</em> si la contrasenya sembla vàlida; <em>false</em> si és incorrecte (e.g. conté un espai o no
	 *         conté prous caracters
	 */
	private boolean validaContrasenya( String contrasenya )
	{
		// TODO potser fer una verificació més bèstia
		if ( contrasenya.contains( " " ) || contrasenya.length() < longitud_mininima_contrasenya )
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Consultora del tipus del usuaria paràmetre implícit
	 * 
	 * @return El tipus del usuari gomoku
	 */
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
	 * @param nivell_oponent Nivell de l'oponent
	 * @param quantitat Quantitat en que es vol incrementar
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

	/**
	 * Modificadora per incrementar el nombre de victòries del usuari contra el tipus de contrincant especificat
	 * 
	 * @param tipus_contrincant Tipus d'usuari del contrincant
	 * @return <em>true</em> si la modificació ha estat efectiva, <em>false</em> en cas contrari
	 */
	public boolean incrementaVictories( TipusUsuari tipus_contrincant )
	{
		return incrementaVictories( UsuariGomoku.getIndexDeTipus( tipus_contrincant ) );
	}

	/**
	 * Modificadora per incrementar el nombre d'empats del usuari contra el tipus de contrincant especificat
	 * 
	 * @param tipus_contrincant Tipus d'usuari del contrincant
	 * @return <em>true</em> si la modificació ha estat efectiva, <em>false</em> en cas contrari
	 */
	public boolean incrementaEmpats( TipusUsuari tipus_contrincant )
	{
		return incrementaEmpats( UsuariGomoku.getIndexDeTipus( tipus_contrincant ) );
	}

	/**
	 * Modificadora per incrementar el nombre de derrotes del usuari contra el tipus de contrincant especificat
	 * 
	 * @param tipus_contrincant Tipus d'usuari del contrincant
	 * @return <em>true</em> si la modificació ha estat efectiva, <em>false</em> en cas contrari
	 */
	public boolean incrementaDerrotes( TipusUsuari tipus_contrincant )
	{
		return incrementaDerrotes( UsuariGomoku.getIndexDeTipus( tipus_contrincant ) );
	}

	/**
	 * Mètode auxiliar de la classe que permet obtenir l'índex corresponent als nivells de dificultat (necessaris per a
	 * modificar les estadístiques d'un Usuari) a partir d'un tipus d'usuari Gomoku
	 * 
	 * @param tipus Tipus del qual es vol obtenir l'índex
	 * @return Índex corresponent al tipus d'usuari Gomoku indicat
	 */
	private static int getIndexDeTipus( TipusUsuari tipus )
	{
		int index = 0;
		switch ( tipus )
		{
			case FACIL:
				index = 0;
				break;
			case MITJA:
				index = 1;
				break;
			case DIFICIL:
				index = 2;
				break;
			case CONVIDAT:
			case HUMA:
				index = 3;
				break;
		}
		return index;
	}
}
