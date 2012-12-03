package prop.gomoku.drivers;

import java.util.List;

import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class DriverGestorPartidesGuardades
{
	private static GestorPartidesGuardades gestor_partides_guardades;

	public static void main( String[] args )
	{
		gestor_partides_guardades = new GestorPartidesGuardades();
		System.out.println( gestor_partides_guardades.getRuta() );

		UsuariGomoku jugador_principal = new UsuariGomoku( "A", "a" );
		PartidaGomoku partida = new PartidaGomoku( jugador_principal, jugador_principal, new UsuariGomoku( "B", "b" ),
				new TaulerGomoku(), "C" );
		System.out.println( "Partida creada:" );
		System.out.println( partida );
		String ruta = gestor_partides_guardades.guardaPartida( partida );

		PartidaGomoku partida_carregada = gestor_partides_guardades.carregaPartidaDeFitxer( ruta );
		System.out.println( "Partida carregada:" );
		System.out.println( partida_carregada );

		List<PartidaGomoku> partides = gestor_partides_guardades.carregaTotes();
		for ( int i = 0; i < partides.size(); i++ )
		{
			System.out.println( partides.get( i ) );
		}
	}
}
