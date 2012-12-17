package prop.gomoku.domini.controladors;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import prop.gomoku.gestors.GestorDocumentacio;

/**
 * Controlador de sistema encarregat d'interactuar amb el sistema operatiu per tal d'ensenyar la documentació rellevant
 * al usuari
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ControladorDocumentacio
{
	/**
	 * Mètode per enviar l'ordre (si és possible) al S.O. per tal de que obri el manual en PDF del programa.
	 * 
	 * Aquesta informació podria bé estar a la Capa de Presentació també, però l'encapsulament en un controlador ens
	 * permet abstraure el 'com' de cara a presentació
	 * 
	 * @return <em>true</em> si s'ha pogut enviar l'ordre al S.O.; <em>false</em> si hi ha hagut algun problema a l'hora
	 *         d'enviar l'ordre o bé el S.O. no admet aquest tipus d'ordres via l'API de Java
	 */
	public static boolean obreManualPDF()
	{
		String ruta_manual = GestorDocumentacio.getRutaManualPDF();
		boolean problema = false;
		if ( Desktop.isDesktopSupported() )
		{
			try
			{
				File manual_pdf = new File( ruta_manual );
				Desktop.getDesktop().open( manual_pdf );
			} catch ( IOException e )
			{
				// No hi ha aplicació per defecte al sistema per obrir PDFs
				problema = true;
			}
		}
		else
		{
			problema = true;
		}
		return problema;
	}
}
