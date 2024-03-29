package prop.gomoku.gestors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

/**
 * Class de gestió de dades a disc que permet guardar, carregar i esborrar partides. Aprofita la serialització de dades
 * que proporciona Java, de forma que permet treballar directament amb els objectes <em>PartidaGomoku</em>. Conté la
 * informació corresponent als directoris del sistema on s'emmagatzemaran i es carregaran les dades
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class GestorPartidesGuardades
{

	/**
	 * Ruta del sistema de fitxers on residiran els fitxers de les partides serialitzades
	 */
	private static final String ruta_partides_guardades = System.getProperty( "user.dir" ) + "/gomoku72/partides/";

	/**
	 * Extensió dels fitxers de partida al sistema
	 */
	private static final String extensio = ".par";

	/**
	 * Mètode per guardar al sistema de fitxers una partida
	 * 
	 * @param partida Partida que es vol guardar
	 * @return Ruta completa del fitxer on s'ha guardat (si no hi ha hagut cap problema) la partida. <em>null</em> si hi
	 *         ha hagut algun problema
	 */
	public String guardaPartida( PartidaGomoku partida )
	{
		creaArbreDirectoris();
		String nom_fitxer = getNomFitxer( partida );
		String ruta_fitxer = null;
		try
		{
			FileOutputStream fitxer = new FileOutputStream( ruta_partides_guardades + nom_fitxer );
			ObjectOutputStream sortida = new ObjectOutputStream( fitxer );
			sortida.writeObject( partida );
			sortida.close();
			fitxer.close();
			ruta_fitxer = ruta_partides_guardades + nom_fitxer;
		} catch ( IOException io_e )
		{
			io_e.printStackTrace();
		}
		return ruta_fitxer;
	}

	/**
	 * Mètode per carregar una partida indicant la ruta del fitxer on es troba
	 * 
	 * @param ruta_fitxer Ruta completa del fitxer on es troba serialitzada la partida a carregar
	 * @return La partida carregada en cas d'èxit, <em>null</em> en cas contrari
	 */
	public PartidaGomoku carregaPartidaDeFitxer( String ruta_fitxer )
	{
		PartidaGomoku partida = null;
		try
		{
			FileInputStream fitxer = new FileInputStream( ruta_fitxer );
			ObjectInputStream entrada = new ObjectInputStream( fitxer );
			partida = (PartidaGomoku) entrada.readObject();
			entrada.close();
			fitxer.close();
		} catch ( IOException ex )
		{
			ex.printStackTrace();
		} catch ( ClassNotFoundException ex )
		{
			ex.printStackTrace();
		}
		return partida;
	}

	/**
	 * Mètode auxiliar que permet crear l'estructura necessària de fitxers a la <em>home</em> de l'usuari que executa
	 * l'aplicació
	 */
	private void creaArbreDirectoris()
	{
		File dir_partides = new File( ruta_partides_guardades );

		if ( !dir_partides.exists() )
		{
			if ( dir_partides.mkdirs() )
			{
				// System.out.println( "Directori " + ruta_partides_guardades + " creat" );
			}
			else
			{
				// System.out.println( "No s'ha pogut crear! " + ruta_partides_guardades );
			}
		}
	}

	/**
	 * Mètode per carregar totes les partides guardades al sistema
	 * 
	 * @return Llista de totes les partides que hi ha al sistema
	 */
	public List<PartidaGomoku> carregaTotes()
	{
		List<PartidaGomoku> llista_partides = new ArrayList<PartidaGomoku>();
		File dir = new File( ruta_partides_guardades );
		if ( !dir.exists() )
		{
			this.creaArbreDirectoris();
		}
		String[] llista_fitxers = dir.list();
		int nombre_fitxers = llista_fitxers.length;
		for ( int i = 0; i < nombre_fitxers; i++ )
		{
			PartidaGomoku partida = this.carregaPartidaDeFitxer( ruta_partides_guardades + llista_fitxers[i] );
			if ( partida == null )
			{
				continue;
			}
			llista_partides.add( partida );
		}
		return llista_partides;
	}

	/**
	 * Mètode per carregar les partides específiques d'un usuari
	 * 
	 * @param usuari Usuari del qual es volen obtenir les partides
	 * @return Llista de totes les partides que hi ha al sistema que pertanyen a l'usuari <em>usuari</em>
	 */
	public List<PartidaGomoku> carregaPartides( UsuariGomoku usuari )
	{
		List<PartidaGomoku> llista_partides = this.carregaTotes();
		List<PartidaGomoku> partides_usuari = new ArrayList<PartidaGomoku>();
		// System.out.println( "Usuari actiu carregant: " + usuari );
		for ( PartidaGomoku partida : llista_partides )
		{
			// System.out.println( partida );
			if ( partida.getJugadorPrincipal().getNom().equals( usuari.getNom() ) )
			{
				partides_usuari.add( partida );
			}
		}
		return partides_usuari;
	}

	/**
	 * Mètode que permet consultar la ruta del directori on es guarden les partides al sistema
	 * 
	 * @return Ruta on s'emmagatzemen les partides al sistema
	 */
	public String getRuta()
	{
		return ruta_partides_guardades;
	}

	/**
	 * Mètode per esborrar una partida del sistema només si una còpia d'aquesta amb el mateix identificador de data de
	 * creació ja existeix a disc i no hi ha cap tipus de problema d'accés
	 * 
	 * @param partida Partida que volem eliminar del sistema
	 * @return <em>true</em> si l'eliminació es realitza amb èxit; <em>false</em> en cas contrari
	 */
	public boolean esborraPartida( PartidaGomoku partida )
	{
		File fitxer_partida = new File( ruta_partides_guardades + getNomFitxer( partida ) );
		try
		{
			return fitxer_partida.delete();
		} catch ( SecurityException e )
		{
			return false;
		}
	}

	private String getNomFitxer( PartidaGomoku partida )
	{
		return partida.getDataCreacio().toString().replace( " ", "" ).replace( ":", "" ).replace( ".", "" ) + extensio;
	}
}
