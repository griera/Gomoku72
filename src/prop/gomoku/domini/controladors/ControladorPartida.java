package prop.gomoku.domini.controladors;

import java.util.List;

import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class ControladorPartida
{
	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, UsuariGomoku jugador_negres,
			UsuariGomoku jugador_blanques, String nom_partida )
	{
		PartidaGomoku partida = new PartidaGomoku( jugador_principal, jugador_negres, jugador_blanques,
				new TaulerGomoku(), nom_partida );
		return partida;
	}

	// Partides amb inteligencia artificial
	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, UsuariGomoku jugador_negres,
			TipusUsuari ia_blanques, String nom_partida )
	{
		
		return null;
	}

	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, TipusUsuari ia_negres,
			UsuariGomoku jugador_blanques, String nom_partida )
	{
		return null;
	}

	// TODO duplica funcionalitat ja existent
	public List<PartidaGomoku> carregaPartides( UsuariGomoku usuari )
	{
		GestorPartidesGuardades gestor = new GestorPartidesGuardades();
		return gestor.carregaPartides( usuari );
	}

	// TODO duplica funcionalitat ja existent
	public String guardaPartida( PartidaGomoku partida )
	{
		GestorPartidesGuardades gestor = new GestorPartidesGuardades();
		// Presuposa que partida conté informació a sobre de l'amo d'aquesta
		return gestor.guardaPartida( partida );
	}
}
