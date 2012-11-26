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

public class GestorUsuaris
{
	private static final String ruta_usuaris = System.getProperty( "user.home" ) + "/gomoku/usuaris/";
	private static final String extensio = ".usr";

	public UsuariGomoku carregaUsuari( String nom ) throws UsuariNoExisteix
	{
		UsuariGomoku usuari = null;
		String ruta_fitxer = ruta_usuaris + nom + extensio;
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

	public void guardaUsuari( UsuariGomoku usuari ) throws IOException
	{
		this.creaArbreDirectoris();
		this.salvaUsuari( usuari );
		// no ha d'haver error si no hi era l'usuari
	}

	private boolean comprovaUsuariJaExisteix( UsuariGomoku usuari )
	{
		File dir = new File( ruta_usuaris );
		String[] llista_fitxers = dir.list();

		for ( String nom_fitxer : llista_fitxers )
		{
			if ( nom_fitxer.equals( usuari.getNom() + extensio ) )
			{
				return true;
			}
		}

		return false;
	}

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
			throw new IOException("No s'ha pogut guardar al sistema el usuari de nom " + usuari.getNom());
		}
	}

	private void creaArbreDirectoris()
	{
		File dir_usuaris = new File( ruta_usuaris );

		if ( !dir_usuaris.exists() )
		{
			if ( dir_usuaris.mkdirs() )
			{
				System.out.println( "Directori " + ruta_usuaris + " creat" );
			}
			else
			{
				System.out.println( "No s'ha pogut crear " + ruta_usuaris );
			}
		}
	}

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
