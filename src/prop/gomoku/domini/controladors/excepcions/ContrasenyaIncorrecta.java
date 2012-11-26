package prop.gomoku.domini.controladors.excepcions;

public class ContrasenyaIncorrecta extends Exception
{

	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = -7984321542016930076L;

	public ContrasenyaIncorrecta(String misstage)
	{
		super(misstage);
	}
}
