package prop.gomoku.gestors.excepcions;

/**
 * Excepció ideada per llençar-se quan una operació necessiti notificar d'errada en cas de que un usuari sol·licitat no
 * existeixi
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class UsuariNoExisteix extends Exception
{
	/**
	 * ID serialització
	 */
	private static final long serialVersionUID = 576454311220893890L;

	public UsuariNoExisteix( String message )
	{
		super( message );
	}
}
