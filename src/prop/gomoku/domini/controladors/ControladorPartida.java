package prop.gomoku.domini.controladors;

import java.util.List;

//import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

public class ControladorPartida
{
	/**
	 * Mètode que permet la creació d'una nova partida indicant només els jugadors implicats i el nom que es vol
	 * assignar a la partida
	 * 
	 * @param jugador_principal Usuari al qual pertany la partida
	 * @param jugador_negres Usuari que jugarà amb les fitxes negres
	 * @param jugador_blanques Usuari que jugarà amb les fitxes blanques
	 * @param nom_partida Nos que es vol assignar a la partida
	 * @return Partida de gomoku que s'ha creat
	 */
	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, UsuariGomoku jugador_negres,
			UsuariGomoku jugador_blanques, String nom_partida )
	{
		PartidaGomoku partida = new PartidaGomoku( jugador_principal, jugador_negres, jugador_blanques,
				new TaulerGomoku(), nom_partida );
		return partida;
	}

	// TODO potser aquestes no cal fer-les, ja que complicaran la gestió de les partides. Si des de CtlrUsuari ja podem
	// carregar-ne els de sistema, no crec que faci falta fer servir un altre mètode que no sigui el de creació
	// estàndard de partida
	/**
	 * Mètode que permet la creació d'una `p
	 * 
	 * @param jugador_principal
	 * @param jugador_negres
	 * @param ia_blanques
	 * @param nom_partida
	 * @return
	 */
//	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, UsuariGomoku jugador_negres,
//			TipusUsuari ia_blanques, String nom_partida )
//	{
//		// TODO
//		return null;
//	}
//
//	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, TipusUsuari ia_negres,
//			UsuariGomoku jugador_blanques, String nom_partida )
//	{
//		// TODO
//		return null;
//	}
//
//	public PartidaGomoku creaNovaPartida( UsuariGomoku jugador_principal, TipusUsuari ia_negres,
//			TipusUsuari ia_blanques, String nom_partida )
//	{
//		// TODO
//		return null;
//	}

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
