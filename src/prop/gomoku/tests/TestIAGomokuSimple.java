package prop.gomoku.tests;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.controladors.IAGomokuSimple;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

public class TestIAGomokuSimple
{
	public static void main( String[] args )
	{
		UsuariGomoku jugador_a = new UsuariGomoku( "A", "passwordA" );
		UsuariGomoku jugador_b = new UsuariGomoku( "B", "passwordB" );
		PartidaGomoku partida = new PartidaGomoku( jugador_a, jugador_a, jugador_b, new TaulerGomoku(), "C" );
		IAGomokuSimple simple_b = new IAGomokuSimple( partida, EstatCasella.JUGADOR_B );

		LecturaScanners lectura = new LecturaScanners();

		boolean toca_huma = true;
		while ( true )
		{
			if ( toca_huma )
			{
				System.out.print( "Coordenades moviment huma: " );
				int fila = lectura.llegirInt();
				int columna = lectura.llegirInt();

				try
				{
					partida.getTauler().mouFitxa( EstatCasella.JUGADOR_A, fila, columna );
					toca_huma = false;
				} catch ( Exception e )
				{
					System.out.println( "Moviment incorrecte" );
				}
			}
			else
			{
				int[] moviment = simple_b.computaMoviment();
				System.out.println(simple_b);
				System.out.println("Moviment decidit: " + moviment[0] + ", " + moviment[1]);
				try{
					partida.getTauler().mouFitxa( EstatCasella.JUGADOR_B, moviment[0], moviment[1] );
				}
				catch (Exception e)
				{
					System.out.println("Error de decisió de IA");
				}
				toca_huma = true;
			}
		}
	}
}
