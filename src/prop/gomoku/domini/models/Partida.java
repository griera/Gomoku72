package prop.gomoku.domini.models;

import java.util.Date;

/**
 * Representa una partida on juguen dos usuaris i que es desenvolupa a un tauler. S'identifica per la seva data i hora
 * de creaci�, per� tamb� t� un nom assignat per facilitar la seva identificaci� de cara als usuaris.
 * 
 * Cont� informaci� relativa al nombre de torns jugats i a l'estat de finalitzaci� de la partida
 */
public abstract class Partida
{
	/**
	 * Usuari que far� de jugador A
	 */
	protected Usuari jugador_a;
	/**
	 * Usuari que far� de jugador B
	 */
	protected Usuari jugador_b;
	/**
	 * Tauler on es desenvolupa la partida
	 */
	protected Tauler tauler;
	/**
	 * Nombre de torns completats
	 */
	protected int torns_jugats;
	/**
	 * Data (i hora) de creaci� de la partida, seveix com a identificador �nic
	 */
	protected Date data_creacio;
	/**
	 * Cadena de text que serveix per anomenar la partida
	 */
	protected String nom;
	/**
	 * Indica si la partida ha estat finalitzada o no
	 */
	protected boolean finalitzada;

	/**
	 * Constructora amb tots el par�metres
	 * 
	 * @param jugador_a Usuari que fa de jugador A
	 * @param jugador_b Usuari que fa de jugador B
	 * @param tauler Tauler on es desenvolupa la partida
	 * @param torns_jugats Torns completats a la partida
	 * @param data_creacio Data i hora de creaci� de la partida
	 * @param nom Nom de la partida
	 * @param finalitzada Indica si ha estat finalitzada o no
	 */
	public Partida( Usuari jugador_a, Usuari jugador_b, Tauler tauler, int torns_jugats, Date data_creacio, String nom,
			boolean finalitzada )
	{
		this.jugador_a = jugador_a;
		this.jugador_b = jugador_b;
		this.tauler = tauler;
		this.torns_jugats = torns_jugats;
		this.data_creacio = data_creacio;
		this.nom = nom;
		this.finalitzada = finalitzada;
	}

	/**
	 * Constructora alternativa per partides que no han estat jugades
	 * 
	 * @param jugador_a Usuari que far� de jugador A
	 * @param jugador_b Usuari que far� de jugador B
	 * @param tauler Tauler on es desenvolupar� la partida
	 * @param nom Nom de la partida
	 */
	public Partida( Usuari jugador_a, Usuari jugador_b, Tauler tauler, String nom )
	{
		this.jugador_a = jugador_a;
		this.jugador_b = jugador_b;
		this.tauler = tauler;
		this.torns_jugats = 0;
		this.data_creacio = new Date();
		this.nom = nom;
		this.finalitzada = false;
	};

	/**
	 * M�tode consultor del jugador A
	 * 
	 * @return Usuari que fa de jugador A
	 */
	public Usuari getJugadorA()
	{
		return this.jugador_a;
	}

	/**
	 * M�tode consultor del jugador B
	 * 
	 * @return Usuari que fa de jugador B
	 */
	public Usuari getJugadorB()
	{
		return this.jugador_b;
	}

	/**
	 * M�tode consultor del tauler
	 * 
	 * @return Tauler on es desenvolupa la partida
	 */
	public Tauler getTauler()
	{
		return this.tauler;
	}

	/**
	 * M�tode consultor del nombre de torns jugats
	 * 
	 * @return Nombre de torns jugats
	 */
	public int getTornsJugats()
	{
		return this.torns_jugats;
	}

	/**
	 * M�tode consultor de la data i hora de creaci� de la partida
	 * 
	 * @return Data i hora de creaci� de la partida
	 */
	public Date getDataCreacio()
	{
		return this.data_creacio;
	}

	/**
	 * M�tode consultor del nom de la partida
	 * 
	 * @return Nom de la partida
	 */
	public String getNom()
	{
		return this.nom;
	}

	/**
	 * M�tode consultor de si una partida ha estat finalitzada o no
	 * 
	 * @return <em>true</em> si la partida ha estat finalitzada; <em>false</em> en cas contrari
	 */
	public boolean estaFinalitzada()
	{
		return this.finalitzada;
	}

	/**
	 * M�tode consultor de l�estat de la partida. Els par�metres permeten aportar informaci� a sobre de l��ltim
	 * moviment d�inter�s realitzat (normalment l��ltim realitzat correctament).
	 * 
	 * @param fila Fila del moviment d'inter�s
	 * @param columna Col�lumna del moviment d'inter�s
	 * @return L'estat de la partida
	 * @throws IndexOutOfBoundsException si (fila, columna) no �s una coordenada dins dels l�mits del tauler on es
	 * desenvolupa la partida
	 */
	public abstract EstatPartida comprovaEstatPartida(int fila, int columna) throws IndexOutOfBoundsException;
	/**
	 * M�tode modificador del jugador A
	 * 
	 * @param jugador_a Usuari que far� de jugador A
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 * �s v�lid
	 */
	public boolean setJugadorA( Usuari jugador_a )
	{
		this.jugador_a = jugador_a;
		return true;
	}

	/**
	 * M�tode modificador del jugador B
	 * 
	 * @param jugador_b Usuari que far� de jugador B
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean setJugadorB( Usuari jugador_b )
	{
		this.jugador_b = jugador_b;
		return true;
	}

	/**
	 * M�tode modificador del tauler
	 * 
	 * @param tauler Tauler on es desenvolupar� la partida
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean setTauler( Tauler tauler )
	{
		this.tauler = tauler;
		return true;
	}

	/**
	 * M�tode modificador del nombre de torns jugats
	 * 
	 * @param torns_jugats Nombre de torns jugats
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean setTornsJugats( int torns_jugats )
	{
		this.torns_jugats = torns_jugats;
		return true;
	}

	/**
	 * M�tode modificador de la data i hora de creaci�
	 * 
	 * @param data_creacio Data i hora de creaci�
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean setDataCreacio( Date data_creacio )
	{
		this.data_creacio = data_creacio;
		return true;
	}

	/**
	 * M�tode modificador del nom de la partida
	 * 
	 * @param nom Nom de la partida
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean setNom( String nom )
	{
		this.nom = nom;
		return true;
	}

	/**
	 * M�tode modificador de l'estat de finalitzaci� de la partida
	 * 
	 * @param finalitzada Indica si la partida ha estat finalitzada o no
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean setFinalitzada( boolean finalitzada )
	{
		this.finalitzada = finalitzada;
		return true;
	}

	/**
	 * Incrementa el nombre de torns jugats en la quantitat indicada
	 * 
	 * @param quantitat Quantitat en la que incrementar el nombre actual de torns jugats
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
	 */
	public boolean incrementaTornsJugats( int quantitat )
	{
		this.torns_jugats += quantitat;
		return true;
	}
}
