package prop.gomoku.domini.controladors;

import java.util.List;

import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class ControladorPartidesGuardades
{
	GestorPartidesGuardades gestor = new GestorPartidesGuardades();

	public List<PartidaGomoku> carregaPartides( UsuariGomoku usuari )
	{
		// TODO
		return this.gestor.carregaPartides( usuari );
	}

	public boolean guardaPartida( PartidaGomoku partida )
	{
		// TODO
		if ( this.gestor.guardaPartida( partida ) != null )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
