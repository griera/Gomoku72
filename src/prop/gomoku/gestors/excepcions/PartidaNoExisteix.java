package prop.gomoku.gestors.excepcions;

/**
 * Excepció ideada per llençar-se en cas de que no existeixi una partida a disc
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class PartidaNoExisteix extends Exception
{

	/**
	 * ID serialització
	 */
	private static final long serialVersionUID = 3111483429963097677L;

	public PartidaNoExisteix( String missatge )
	{
		super( missatge );
	}
}
