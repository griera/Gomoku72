package prop.gomoku.domini.controladors;

import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;

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

}