package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.Usuari;
import prop.gomoku.domini.models.PartidaGomoku;

public class ControladorPartidaEnJoc
{
	private PartidaGomoku partida;
	private Usuari jugador_principal;

	public ControladorPartidaEnJoc( Usuari jugador_principal, Usuari jugador_secundari, PartidaGomoku partida )
			throws IllegalArgumentException
	{
		if ( ( jugador_principal.getNom().equals( partida.getJugadorA().getNom() ) || ( jugador_principal.getNom()
				.equals( partida.getJugadorB().getNom() ) ) ) )
		{
			this.jugador_principal = jugador_principal;
			this.partida = partida;
		}
		else
		{
			throw new IllegalArgumentException( "Els jugadors principal proporcionat no és a la partida" );
		}
	}

	public PartidaGomoku getPartida()
	{
		return this.partida;
	}

	public Usuari getJugadorPrincipal()
	{
		return this.jugador_principal;
	}

}
