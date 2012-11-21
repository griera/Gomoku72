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

public class GestorPartidesGuardades
{

	private static final String ruta_partides_guardades = System.getProperty( "user.home" ) + "/gomoku/partides/";

	public String guardaPartida( PartidaGomoku partida )
	{
		creaArbreDirectoris();

		String nom_fitxer = partida.getDataCreacio().toString().replace( " ", "" ).replace( ":", "" ).replace( ".", "" )
				+ ".ser";

		String ruta_fitxer = ruta_partides_guardades + nom_fitxer;

		try
		{
			FileOutputStream fitxer = new FileOutputStream( ruta_fitxer );
			ObjectOutputStream sortida = new ObjectOutputStream( fitxer );
			sortida.writeObject( partida );
			sortida.close();
			fitxer.close();
		} catch ( IOException io_e )
		{
			io_e.printStackTrace();
		}

		return ruta_fitxer;
	}

	public PartidaGomoku carregaPartida( String ruta_fitxer )
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

	private void creaArbreDirectoris()
	{
		File dir_partides = new File( ruta_partides_guardades );

		if ( !dir_partides.exists() )
		{
			if ( dir_partides.mkdirs() )
			{
				System.out.println( "Directori " + ruta_partides_guardades + " creat" );
			}
			else
			{
				System.out.println( "No s'ha pogut crear! " + ruta_partides_guardades );
			}
		}
	}

	public List<PartidaGomoku> carregaTotes()
	{
		List<PartidaGomoku> llista_partides = new ArrayList<PartidaGomoku>();
		File dir = new File( ruta_partides_guardades );
		String[] llista_fitxers = dir.list();
		int nombre_fitxers = llista_fitxers.length;
		for ( int i = 0; i < nombre_fitxers; i++ )
		{
			llista_partides.add( this.carregaPartida( ruta_partides_guardades + llista_fitxers[i] ) );

		}
		return llista_partides;
	}

	public List<PartidaGomoku> carregaPartides( UsuariGomoku usuari )
	{
		// TODO
		List<PartidaGomoku> llista_partides = this.carregaTotes();
		List<PartidaGomoku> partides_usuari = new ArrayList<PartidaGomoku>();
		for ( int i = 0; i < llista_partides.size(); i++ )
		{
			PartidaGomoku partida = llista_partides.get( i );
			if ( partida.getJugadorPrincipal().getNom().equals( usuari.getNom() ) )
			{
				partides_usuari.add( partida );
			}
		}
		return llista_partides;
	}

	public String getRuta()
	{
		return ruta_partides_guardades;
	}
}
