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
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

public class GestorPartidesGuardades
{

	private static final String ruta_partides_guardades = System.getProperty( "user.home" ) + "\\gomoku\\partides\\";
	private static int limit_partides = 3;

	// TODO de moment retorna string
	public String guardaPartida( PartidaGomoku partida )
	{
		creaArbreDirectoris();

		String nom_fitxer = partida.getDataCreacio().toString().replace( " ", "" ).replace( ":", "" )
				.replace( ".", "." )
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

	public PartidaGomoku[] carregaPartides( UsuariGomoku usuari )
	{
		// TODO
		// Crearem algunes i les tornarem
		// No es carregaran d'enlloc
		PartidaGomoku[] partides = new PartidaGomoku[limit_partides];
		for ( int i = 0; i < limit_partides; i++ )
		{
			if ( limit_partides % 2 == 0 )
			{
				partides[i] = new PartidaGomoku( usuari,
						new UsuariGomoku( "Oponent" + limit_partides, "passwd" + i, 4 ), new TaulerGomoku(),
						"PartidaEmmagatzemada" + i );
			}
			else
			{
				partides[i] = new PartidaGomoku( new UsuariGomoku( "Oponent" + limit_partides, "passwd" + i, 4 ),
						usuari, new TaulerGomoku(), "PartidaEmmagatzemada" + i );
			}
		}
		return partides;
	}

	public String getRuta()
	{
		return ruta_partides_guardades;
	}
}
