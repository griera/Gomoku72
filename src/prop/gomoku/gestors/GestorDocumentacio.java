package prop.gomoku.gestors;

/**
 * Gestor de disc que ens permet obtenir la informació (ja sigui rutes, fitxers, etc.) necessària per a ensenyar la
 * documentació del programa al usuari (la que sigui rellevant per a ell)
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class GestorDocumentacio
{
	/**
	 * Mètode consultar de la ruta del manual en PDF
	 * @return Ruta on es trobaria el manual en PDF
	 */
	public static String getRutaManualPDF()
	{
		return System.getProperty( "user.dir" ) + "/Manual_usuari.pdf";
	}
}