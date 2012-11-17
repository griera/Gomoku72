package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.Usuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class ControladorPartidaGuardada
{
	GestorPartidesGuardades gestor = new GestorPartidesGuardades();

	public PartidaGomoku[] getLlistaPartides( Usuari usuari )
	{
		// TODO
		return this.gestor.carregaPartides( usuari );
	}

	public boolean guardaPartida( PartidaGomoku partida )
	{
		// TODO
		// Presuposa que partida conté informació a sobre de l'amo d'aquesta
		return this.gestor.guardaPartida( partida );
	}
}