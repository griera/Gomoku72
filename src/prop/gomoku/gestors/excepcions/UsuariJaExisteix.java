package prop.gomoku.gestors.excepcions;

/**
 * Excepció ideada per llençar-se en cas de donar-se la situació de que un usuari ja existeixi a disc i es necessiti
 * notificar que un canvi no es pot realitzar si aquest ja hi és, com per exemple a una operació de creació i no
 * d'actualització
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class UsuariJaExisteix extends Exception
{

	/**
	 * ID serialització
	 */
	private static final long serialVersionUID = -8309103333198952717L;

	public UsuariJaExisteix( String missatge )
	{
		super( missatge );
	}
}
