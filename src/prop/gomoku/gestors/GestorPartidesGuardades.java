package prop.gomoku.gestors;

import prop.cluster.domini.models.Usuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class GestorPartidesGuardades
{
	
	@SuppressWarnings( "unused" )
	private static String ruta_partides_guardades = "Algun lloc del PC"; // TODO idea de cara serialitzacio
	private static int limit_partides = 3;

	public boolean guardaPartida( PartidaGomoku partida )
	{
		// TODO
		// Direm que sempre es guarden
		return true;
	}

	public PartidaGomoku[] carregaPartides( Usuari usuari )
	{
		// TODO
		// Crearem algunes i les tornarem
		// No es carregaran d'enlloc
		PartidaGomoku[] partides = new PartidaGomoku[limit_partides];
		for ( int i = 0; i < limit_partides; i++ )
		{
			if ( limit_partides % 2 == 0 )
			{
				partides[i] = new PartidaGomoku( usuari, new Usuari( "Oponent" + limit_partides, "passwd" + i, 2 ),
						new TaulerGomoku(), "PartidaEmmagatzemada" + i );
			}
			else
			{
				partides[i] = new PartidaGomoku( new Usuari( "Oponent" + limit_partides, "passwd" + i, 2 ), usuari,
						new TaulerGomoku(), "PartidaEmmagatzemada" + i );
			}
		}
		return partides;
	}
}
