package prop.gomoku.drivers;

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

		PartidaGomoku partida = new PartidaGomoku( new UsuariGomoku( "A", "a", 4 ), new UsuariGomoku( "B", "b", 4 ),
				new TaulerGomoku(), "C" );
		System.out.println("Partida creada:");
		System.out.println(partida);
		String ruta = gestor_partides_guardades.guardaPartida( partida );
		
		PartidaGomoku partida_carregada = gestor_partides_guardades.carregaPartida(ruta);
		System.out.println("Partida carregada:");
		System.out.println(partida_carregada);
	}
}
