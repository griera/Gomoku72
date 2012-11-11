package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.Usuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class ControladorPartidesGuardades
{
	GestorPartidesGuardades gestor = new GestorPartidesGuardades();
	
	public PartidaGomoku[] getLlistaPartides(Usuari usuari) {
		// TODO
		return null;
	}
	
	public boolean guardaPartida(PartidaGomoku partida) {
		// TODO
		return false;
	}
}
