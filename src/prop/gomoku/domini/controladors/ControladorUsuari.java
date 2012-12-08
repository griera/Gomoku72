package prop.gomoku.domini.controladors;

import java.io.IOException;

import prop.gomoku.auxiliars.Vigenere;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaIncorrecta;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaInvalida;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorUsuaris;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

/**
 * Controlador de domini dels objectes <em>UsuariGomoku</em>. Ofereix una interfície per a identificar, carregar,
 * guardar i actualitzar aquests al sistema. També tracta amb el xifrat de les contrasenyes
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ControladorUsuari
{
	/**
	 * Clau de 4 caracters utilitzada pel xifrat
	 */
	private static final String clau = "prop";
	/**
	 * Gestor de disc utilitzat per carregar i guardar usuaris
	 */
	private static GestorUsuaris gestor_usuaris = new GestorUsuaris();

	/**
	 * Mètode per identificar un usuari un usuari existent a disc a partir dels paràmetres identificatius
	 * 
	 * @param nom Nom del usuari a identificar
	 * @param contrasenya Contrasenya del usuari a identificar
	 * @return Usuari identificat pels paràmetres facilitats
	 * @throws UsuariNoExisteix Si no existeix un usuari amb nom <em>nom</em>
	 * @throws ContrasenyaIncorrecta Si existeix el usuari amb nom <em>nom</em> però la contrasenya no coincideix amb la
	 *         proporcioada
	 * @throws IllegalArgumentException Si l'usuari que es vol carregar és del sistema
	 */
	public UsuariGomoku identificaUsuari( String nom, String contrasenya ) throws UsuariNoExisteix,
			ContrasenyaIncorrecta, ContrasenyaInvalida, IllegalArgumentException
	{
		UsuariGomoku usuari = null;
		try
		{
			usuari = gestor_usuaris.carregaUsuari( nom );
		} catch ( UsuariNoExisteix e )
		{
			throw new UsuariNoExisteix( "No existeix un usuari amb aquest nom" );
		}

		// No identificarem usuaris del sistema, aquests es carreguen amb els corresponents mètodes
		if ( usuari.getTipus() != TipusUsuari.HUMA )
		{
			throw new IllegalArgumentException( "No és un usuari HUMA" );
		}

		/* Les comparacions es realitzen entre contrasenyes xifrades. Els objectes UsuariGomoku carregats amb l'ajut del
		 * gestor de disc contenen les contrasenyes xifrades */
		String contrasenya_xifrada = xifraContrasenya( contrasenya );

		if ( usuari.getContrasenya().equals( contrasenya_xifrada ) )
		{
			return usuari;
		}
		else
		{
			throw new ContrasenyaIncorrecta( "Contrasenya incorrecta" );
		}

	}

	/**
	 * Mètode per registrar un usuari nou al sistema, amb els paràmetres bàsics proporcionats
	 * 
	 * @param nom Nom del nou usuari
	 * @param contrasenya Contrasenya del nou usuari
	 * @return La instància resultat de crear el nou usuari
	 * @throws ContrasenyaInvalida Si la contrasenya no compleix els requisits dels sistema (mida mínima 4 i cap
	 *         caràcter invàlid)
	 * @throws UsuariJaExisteix Si ja existeix un usuari al sistema identificat per <em>nom</em>
	 * @throws IOException
	 */
	public UsuariGomoku registraUsuari( String nom, String contrasenya ) throws ContrasenyaInvalida, UsuariJaExisteix,
			IOException
	{
		String contrasenya_xifrada = xifraContrasenya( contrasenya );
		UsuariGomoku usuari_nou = new UsuariGomoku( nom, contrasenya_xifrada );
		gestor_usuaris.guardaNouUsuari( usuari_nou );
		return usuari_nou;
	}

	/**
	 * Mètode que permet actualitzar un usuari al sistema, és a dir, sobreescriu les seves dades si ja existeixen
	 * 
	 * @param usuari Usuari amb les dades que es volen actualitzar
	 * @return <em>true</em> en cas d'èxit, <em>false</em> en cas contrari
	 */
	public boolean actualitzaUsuari( UsuariGomoku usuari )
	{
		try
		{
			gestor_usuaris.guardaUsuari( usuari );
		} catch ( IOException e )
		{
			return false;
		}
		return true;
	}

	/**
	 * Mètode privat per facilitar-ne el xifrat (i la modificació del funcionament intern) de contrasenyes
	 * 
	 * @param contrasenya Contrasenya original sense xifrar
	 * @return String amb la contrasenya ja xifrada pel sistema
	 * @throws ContrasenyaInvalida Si la contrasenya no compleix els requisits mínims (cap caràcter invàlid i longitud
	 *         mínima 4)
	 */
	private String xifraContrasenya( String contrasenya ) throws ContrasenyaInvalida
	{
		try
		{
			return new Vigenere( contrasenya, clau ).getMissatgeXifrat();
		} catch ( IndexOutOfBoundsException e )
		{
			throw new ContrasenyaInvalida( "Contrasenya invàlida, no té prous caracters o conté caracters invàlids" );
		}
	}

	/**
	 * Mètode per carregar els diferents tipus d'usuaris del sitema (jugadors màquina i convidat). En cas de voler
	 * carregar un usuari del tipus <em>TipusUsuari.HUMA</em> llençarà una <em>IllegalArgumentException</em>. No forcem
	 * la captura d'aquesta excepció ja que en cas de fer-se aquesta crida de forma incorrecta estem davant un error de
	 * programació, no d'execució o alié al nostre programa
	 * 
	 * @param tipus Tipus del usuari a carregar, e.g. <em>TipusUsuari.CONVIDAT</em> o <em>TipusUsuari.FACIL</em>
	 * @return El usuari del sistema demanat
	 * @throws IOException Si hi ha algun problema d'accés al sistema de fitxers per tal de carregar o crear algun
	 *         usuari
	 */
	public UsuariGomoku carregaUsuariSistema( TipusUsuari tipus ) throws IOException
	{
		if ( tipus == TipusUsuari.HUMA )
		{
			throw new IllegalArgumentException( "Usuari HUMA no és del sistema" );
		}

		UsuariGomoku usuari = null;
		switch ( tipus )
		{
			case CONVIDAT:
				// Es tracta d'un usuari nou, no té estadístiques
				usuari = new UsuariGomoku( "Convidat", "Convidat", TipusUsuari.CONVIDAT );
				break;

			case FACIL:
			case MITJA:
			case DIFICIL:
				// Si hi ha algun problema, és a dir: a efectes pràctics el usuari màquina no existeix, s'ha de crear
				boolean requereix_creacio = false;
				try
				{
					usuari = gestor_usuaris.carregaUsuariSistema( tipus );
				} catch ( IllegalArgumentException e )
				{
					requereix_creacio = true;
				} catch ( UsuariNoExisteix e )
				{
					requereix_creacio = true;
				}
				if ( requereix_creacio )
				{
					usuari = new UsuariGomoku( "CPU" + tipus.toString().toUpperCase(), "password", tipus );
					try
					{
						gestor_usuaris.guardaUsuari( usuari );
					} catch ( IOException e )
					{
						throw new IOException( "No s'ha pogut crear el usuari de tipus "
								+ tipus.toString().toUpperCase() + " al sistema" );
					}
				}
				break;

			default:
				// Aquest cas no s'hauria de donar
				break;
		}
		return usuari;
	}
}
