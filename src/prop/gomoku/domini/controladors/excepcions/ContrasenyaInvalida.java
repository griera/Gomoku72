package prop.gomoku.domini.controladors.excepcions;

public class ContrasenyaInvalida extends Exception
{

	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 6226779244545283744L;

	public ContrasenyaInvalida( String misstage )
	{
		super( misstage );
	}
}
