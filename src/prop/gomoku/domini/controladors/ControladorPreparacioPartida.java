package prop.gomoku.domini.controladors;

import prop.gomoku.domini.models.PartidaGomoku;

public class ControladorPreparacioPartida
{
	private PartidaGomoku partida;

	public ControladorPreparacioPartida( PartidaGomoku partida )
	{
		this.partida = partida;
	}

	// TODO
	public boolean mouFitxa( int fila, int columna ) throws IllegalArgumentException
	{
		String nom = "nom";
		if ( nom != "blah" )
		{
			throw new IllegalArgumentException("blah");
		}
		return false;
	}
}
