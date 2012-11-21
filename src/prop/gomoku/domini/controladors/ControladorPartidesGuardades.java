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

	public String guardaPartida( PartidaGomoku partida )
	{
		// TODO
		// Presuposa que partida conté informació a sobre de l'amo d'aquesta
		return this.gestor.guardaPartida( partida );
	}
}
