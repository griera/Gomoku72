package prop.gomoku.gestors.excepcions;

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
