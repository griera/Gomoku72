package prop.cluster.domini.models;

import java.io.Serializable;
import java.util.Date;

import prop.cluster.domini.models.estats.EstatPartida;

/**
 * Representa una partida on juguen dos usuaris i que es desenvolupa a un tauler. S'identifica per la seva data i hora
 * de creació, però també té un nom assignat per facilitar la seva identificació de cara als usuaris.
 * 
 * Conté informació relativa al nombre de torns jugats i a l'estat de finalització de la partida
 */
public abstract class Partida implements Serializable
{
	/**
	 * ID de serialitzaci�
	 */
	private static final long serialVersionUID = -969562011587079574L;
	/**
	 * Usuari que far� de jugador A
	 */
	protected Usuari jugador_a;
	/**
	 * Usuari que farà de jugador B
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
	 * Data (i hora) de creació de la partida, seveix com a identificador únic
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
	 * Constructora amb tots el paràmetres
	 * 
	 * @param jugador_a Usuari que fa de jugador A
	 * @param jugador_b Usuari que fa de jugador B
	 * @param tauler Tauler on es desenvolupa la partida
	 * @param torns_jugats Torns completats a la partida
	 * @param data_creacio Data i hora de creació de la partida
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
	 * @param jugador_a Usuari que farà de jugador A
	 * @param jugador_b Usuari que farà de jugador B
	 * @param tauler Tauler on es desenvoluparà la partida
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
	 * Mètode consultor del jugador A
	 * 
	 * @return Usuari que fa de jugador A
	 */
	public Usuari getJugadorA()
	{
		return this.jugador_a;
	}

	/**
	 * Mètode consultor del jugador B
	 * 
	 * @return Usuari que fa de jugador B
	 */
	public Usuari getJugadorB()
	{
		return this.jugador_b;
	}

	/**
	 * Mètode consultor del tauler
	 * 
	 * @return Tauler on es desenvolupa la partida
	 */
	public Tauler getTauler()
	{
		return this.tauler;
	}

	/**
	 * Mètode consultor del nombre de torns jugats
	 * 
	 * @return Nombre de torns jugats
	 */
	public int getTornsJugats()
	{
		return this.torns_jugats;
	}

	/**
	 * Mètode consultor de la data i hora de creació de la partida
	 * 
	 * @return Data i hora de creació de la partida
	 */
	public Date getDataCreacio()
	{
		return this.data_creacio;
	}

	/**
	 * Mètode consultor del nom de la partida
	 * 
	 * @return Nom de la partida
	 */
	public String getNom()
	{
		return this.nom;
	}

	/**
	 * Mètode consultor de si una partida ha estat finalitzada o no
	 * 
	 * @return <em>true</em> si la partida ha estat finalitzada; <em>false</em> en cas contrari
	 */
	public boolean estaFinalitzada()
	{
		return this.finalitzada;
	}

	/**
<<<<<<< HEAD:src/prop/cluster/domini/models/Partida.java
	 * M�tode consultor de l�estat de la partida. Els par�metres permeten aportar informaci� a sobre de l��ltim moviment
	 * d�inter�s realitzat (normalment l��ltim realitzat correctament).
=======
	 * Mètode consultor de l’estat de la partida. Els paràmetres permeten aportar informació a sobre de l’últim moviment
	 * d’interès realitzat (normalment l’últim realitzat correctament).
>>>>>>> remotes/genisrieraperez/genis-fork-gomoku/master:src/prop/cluster/domini/models/Partida.java
	 * 
	 * @param fila Fila del moviment d'interès
	 * @param columna Col·lumna del moviment d'interès
	 * @return L'estat de la partida
<<<<<<< HEAD:src/prop/cluster/domini/models/Partida.java
	 * @throws IndexOutOfBoundsException si (fila, columna) no �s una coordenada dins dels l�mits del tauler on es
=======
	 * @throws IndexOutOfBoundsException si (fila, columna) no és una coordenada dins dels límits del tauler on es
>>>>>>> remotes/genisrieraperez/genis-fork-gomoku/master:src/prop/cluster/domini/models/Partida.java
	 *         desenvolupa la partida
	 */
	public abstract EstatPartida comprovaEstatPartida( int fila, int columna ) throws IndexOutOfBoundsException;

	/**
	 * Mètode modificador del jugador A
	 * 
<<<<<<< HEAD:src/prop/cluster/domini/models/Partida.java
	 * @param jugador_a Usuari que far� de jugador A
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor prove�t no
	 *         �s v�lid
=======
	 * @param jugador_a Usuari que farà de jugador A
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
>>>>>>> remotes/genisrieraperez/genis-fork-gomoku/master:src/prop/cluster/domini/models/Partida.java
	 */
	public boolean setJugadorA( Usuari jugador_a )
	{
		this.jugador_a = jugador_a;
		return true;
	}

	/**
	 * Mètode modificador del jugador B
	 * 
	 * @param jugador_b Usuari que farà de jugador B
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
	 */
	public boolean setJugadorB( Usuari jugador_b )
	{
		this.jugador_b = jugador_b;
		return true;
	}

	/**
	 * Mètode modificador del tauler
	 * 
	 * @param tauler Tauler on es desenvoluparà la partida
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
	 */
	public boolean setTauler( Tauler tauler )
	{
		this.tauler = tauler;
		return true;
	}

	/**
	 * Mètode modificador del nombre de torns jugats
	 * 
	 * @param torns_jugats Nombre de torns jugats
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
	 */
	public boolean setTornsJugats( int torns_jugats )
	{
		this.torns_jugats = torns_jugats;
		return true;
	}

	/**
	 * Mètode modificador de la data i hora de creació
	 * 
	 * @param data_creacio Data i hora de creació
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
	 */
	public boolean setDataCreacio( Date data_creacio )
	{
		this.data_creacio = data_creacio;
		return true;
	}

	/**
	 * Mètode modificador del nom de la partida
	 * 
	 * @param nom Nom de la partida
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
	 */
	public boolean setNom( String nom )
	{
		this.nom = nom;
		return true;
	}

	/**
	 * Mètode modificador de l'estat de finalització de la partida
	 * 
	 * @param finalitzada Indica si la partida ha estat finalitzada o no
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
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
	 * @return <em>true</em> si el canvi s'ha realitzat, </em>false</em> si no s'ha realitzat ja que el valor proveït no
	 *         és vàlid
	 */
	public boolean incrementaTornsJugats( int quantitat )
	{
		this.torns_jugats += quantitat;
		return true;
	}
}
