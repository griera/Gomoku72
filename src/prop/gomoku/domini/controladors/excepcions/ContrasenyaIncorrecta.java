package prop.gomoku.domini.controladors.excepcions;

/**
 * Excepció a llençar quan una contrasenya sigui incorrecta, és a dir, que no equivalgui a la que està guardada per a un
 * usuari determinat que es vol carregar. És una excepció pensada de cara a la identificació d'usuaris
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ContrasenyaIncorrecta extends Exception
{

	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = -7984321542016930076L;

	/**
	 * Constructora que requereix d'un missatge explicatiu
	 * 
	 * @param misstage
	 */
	public ContrasenyaIncorrecta( String misstage )
	{
		super( misstage );
	}
}
