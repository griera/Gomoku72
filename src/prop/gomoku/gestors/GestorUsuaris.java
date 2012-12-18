package prop.gomoku.gestors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

/**
 * Class de gestió de dades a disc que permet guardar, actualitzar i carregar <em>UsuariGomoku</em>s. Aprofita la
 * serialització de dades que proporciona Java, de forma que permet treballar directament amb els objectes
 * <em>UsuariGomoku</em>. Conté la informació corresponent als directoris del sistema on s'emmagatzemaran i es
 * carregaran les dades
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class GestorUsuaris
{
	private static final String ruta_usuaris = System.getProperty( "user.dir" ) + "/gomoku72/usuaris/";
	private static final String extensio = ".usr";

	/**
	 * Mètode que permet carregar un usuari donat el seu nom (identificador únic)
	 * 
	 * @param nom Nom que identifica al usuari que es vols carregar
	 * @return Usuari carregat, identificat per <em>nom</em>
	 * @throws UsuariNoExisteix Si no existeix cap usuari guardat al sistema amb el nom <em>nom</em>
	 */
	public UsuariGomoku carregaUsuari( String nom ) throws UsuariNoExisteix
	{
		UsuariGomoku usuari = null;
		String ruta_fitxer = ruta_usuaris + nom.toLowerCase() + extensio;
		try
		{
			usuari = this.carregaUsuariDeFitxer( ruta_fitxer );
		} catch ( IOException e )
		{
			throw new UsuariNoExisteix( "No s'ha pogut accedir a cap fitxer per aquest usuari" );
		} catch ( ClassNotFoundException e )
		{
			throw new UsuariNoExisteix( "S'ha trobat un possible fitxer, però no és compatible" );
		}
		return usuari;
	}

	/**
	 * Mètode que permet carregar un usuari a partir de la ruta del fitxer on es troba serialitzat
	 * 
	 * @param ruta_fitxer Ruta completa del fitxer on es troben les dades del usuari
	 * @return Usuari emmagatzemat al fitxer de la ruta indicada
	 * @throws IOException En cas de problemes d'accés al fitxer o si aquest no existeix
	 * @throws ClassNotFoundException Si s'ha trobat el fitxer però aquest resulta no ser compatible
	 */
	private UsuariGomoku carregaUsuariDeFitxer( String ruta_fitxer ) throws IOException, ClassNotFoundException
	{
		UsuariGomoku usuari = null;
		try
		{
			FileInputStream fitxer = new FileInputStream( ruta_fitxer );
			ObjectInputStream entrada = new ObjectInputStream( fitxer );
			usuari = (UsuariGomoku) entrada.readObject();
			entrada.close();
			fitxer.close();
		} catch ( IOException ex )
		{
			throw new IOException( "No s'ha pogut carregar l'usuari des de " + ruta_fitxer );
		} catch ( ClassNotFoundException ex )
		{
			throw new ClassNotFoundException( "Fitxer d'objecte incompatible a la ruta " + ruta_fitxer );
		}
		return usuari;
	}

	/**
	 * Mètode per guardar o actualitzar un usuari al sistem. Si aquest ja existeix, se sobreescriu.
	 * 
	 * @param usuari Usuari que es vol guardar
	 * @throws IOException Si hi ha algun problema d'accés als fitxers
	 */
	public void guardaUsuari( UsuariGomoku usuari ) throws IOException
	{
		this.creaArbreDirectoris();
		this.salvaUsuari( usuari );
	}

	/**
	 * Mètode que comprova si ja existeix un usuari al sistema de fitxers
	 * 
	 * @param usuari Usuari del qual es vol saber si ja existeix al sistema
	 * @return <em>true</em> si ja existeix un usuari guardat amb el mateix nom del proporcionat; <em>false</em> en cas
	 *         contrari
	 */
	private boolean comprovaUsuariJaExisteix( UsuariGomoku usuari )
	{
		File dir = new File( ruta_usuaris );
		String[] llista_fitxers = dir.list();

		// Com cada fitxer té el nom del usuari com a nom de fitxer, ens estalviem deserialitzer i comprovem el nom dels
		// fitxers només
		for ( String nom_fitxer : llista_fitxers )
		{
			if ( nom_fitxer.toLowerCase().equals( usuari.getNom().toLowerCase() + extensio ) )
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Mètode que permet guardar un usuario <em>nou</em> al sistema
	 * 
	 * @param usuari Usuari que es vol guardar
	 * @throws UsuariJaExisteix Si ja existeix un usuari amb el mateix nom al sistema
	 * @throws IOException Si hi ha hagut algun problema d'accés al sistema de fitxers
	 */
	public void guardaNouUsuari( UsuariGomoku usuari ) throws UsuariJaExisteix, IOException
	{
		this.creaArbreDirectoris();

		// Ha de llençar excepció si ja existeix l'usuari
		if ( this.comprovaUsuariJaExisteix( usuari ) )
		{
			throw new UsuariJaExisteix( "Ja existeix l'usuari amb nom " + usuari.getNom() );
		}
		try
		{
			this.salvaUsuari( usuari );
		} catch ( IOException e )
		{
			throw new IOException( "No s'ha pogut guardar al sistema el usuari de nom " + usuari.getNom() );
		}
	}

	/**
	 * Mètode que crea l'arbre de fitxers, si no existeix, on es guarden els fitxers que serialitzen els usuaris
	 * guardats
	 */
	private void creaArbreDirectoris()
	{
		File dir_usuaris = new File( ruta_usuaris );

		if ( !dir_usuaris.exists() )
		{
			if ( dir_usuaris.mkdirs() )
			{
				// System.out.println( "Directori " + ruta_usuaris + " creat" );
			}
			else
			{
				// System.out.println( "No s'ha pogut crear " + ruta_usuaris );
			}
		}
	}

	/**
	 * Mètode que permet salvar un usuari al sistema de fitxers
	 * 
	 * @param usuari Usuari que es preten guardar
	 * @return Ruta del fitxer on s'ha serialitzat la instància de <em>UsuariGomoku</em>
	 * @throws IOException Si hi ha hagut algun problema d'accés al sistema de fitxers
	 */
	private String salvaUsuari( UsuariGomoku usuari ) throws IOException
	{
		String ruta_fitxer = ruta_usuaris + usuari.getNom() + extensio;
		try
		{
			FileOutputStream fitxer = new FileOutputStream( ruta_fitxer );
			ObjectOutputStream sortida = new ObjectOutputStream( fitxer );
			sortida.writeObject( usuari );
			sortida.close();
			fitxer.close();
		} catch ( IOException io_e )
		{
			throw new IOException( "No s'ha pogut guardar l'usuari: " + usuari.toString() + " a " + ruta_fitxer );
		}
		return ruta_fitxer;
	}

	/**
	 * Mètode per carregar tots els usuaris existents al sistema de fitxers
	 * 
	 * @return Llista amb tots els usuaris que actualment estan guardats al sistema
	 */
	public List<UsuariGomoku> carregaTots()
	{
		List<UsuariGomoku> llista_usuaris = new ArrayList<UsuariGomoku>();
		File dir = new File( ruta_usuaris );
		String[] llista_fitxers = dir.list();
		int nombre_fitxers = llista_fitxers.length;
		for ( int i = 0; i < nombre_fitxers; i++ )
		{
			String ruta_fitxer = ruta_usuaris + llista_fitxers[i];
			try
			{
				llista_usuaris.add( this.carregaUsuariDeFitxer( ruta_fitxer ) );
			} catch ( IOException e )
			{
				System.err.println( "Problema llegint fitxer " + ruta_fitxer );
			} catch ( ClassNotFoundException e )
			{
				System.err.println( "Trobat fitxer incompatible " + ruta_fitxer );
			}

		}
		return llista_usuaris;
	}

}
