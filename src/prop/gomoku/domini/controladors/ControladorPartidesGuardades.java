package prop.gomoku.domini.controladors;

import java.util.Date;

import prop.cluster.domini.models.Usuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class ControladorPartidesGuardades
{
	GestorPartidesGuardades gestor = new GestorPartidesGuardades();
	
	private static String ruta_partides_guardades = "Algun lloc del PC";
	
	public boolean salvaPartida(PartidaGomoku partida) {
		// TODO
		return false;
	}
	
	public PartidaGomoku[] carregaPartides(Usuari usuari) {
		// TODO
		return null;
	}
	
	public PartidaGomoku[] carregaPartides(String nom) {
		// TODO
		return null;
	}
	
	public PartidaGomoku[] carregaPartides(Date data_creacio) {
		// TODO
		return null;
	}
}