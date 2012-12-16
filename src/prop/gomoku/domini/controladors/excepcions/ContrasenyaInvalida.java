package prop.gomoku.domini.controladors.excepcions;

/**
 * Excepció pensada per representar la situació en la que es proporciona una contrasenya no vàlida (que no compleixi ja
 * sigui amb criteris de seguretat o que no sigui compatible amb el xifrador)
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ContrasenyaInvalida extends Exception
{

	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 6226779244545283744L;

	/**
	 * Constructora que requreix d'un missatge explicatiu
	 * 
	 * @param misstage
	 */
	public ContrasenyaInvalida( String misstage )
	{
		super( misstage );
	}
}
