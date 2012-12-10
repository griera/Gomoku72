package prop.gomoku.domini.controladors;

import java.util.List;

import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;

/**
 * Controlador que permet guardar i carregar partides
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ControladorPartidesGuardades
{
	/**
	 * Gestor de partides guardades encarregat de gestionar la interacció amb disc
	 */
	private GestorPartidesGuardades gestor = new GestorPartidesGuardades();

	/**
	 * Mètode per carregar totes les partides d'un usuari
	 * 
	 * @param usuari Usuari al qual pertanyen les partides que es volen carregar
	 * @return Llista de les partides que pertanyen al usuari indicat
	 */
	public List<PartidaGomoku> carregaPartides( UsuariGomoku usuari )
	{
		return this.gestor.carregaPartides( usuari );
	}

	/**
	 * Mètode per guardar una partida específica
	 * 
	 * @param partida Partida que es es vol guardar
	 * @return <em>true</em> si s'ha guardat la partida amb èxit; <em>false</em> en cas contrari
	 */
	public boolean guardaPartida( PartidaGomoku partida )
	{
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
