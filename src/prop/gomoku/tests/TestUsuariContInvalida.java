package prop.gomoku.tests;

import java.io.IOException;

import prop.gomoku.domini.controladors.ControladorUsuari;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaIncorrecta;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaInvalida;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

public class TestUsuariContInvalida
{
	public static void main( String[] args )
	{
		String nom = "ignacio2";
		String cont = "ignacio2";
		ControladorUsuari ctrl_usuari = new ControladorUsuari();
		UsuariGomoku usuari_registrat = null;
		try
		{
			usuari_registrat = ctrl_usuari.registraUsuari( nom, cont );
		} catch ( ContrasenyaInvalida e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( UsuariJaExisteix e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(usuari_registrat);

		UsuariGomoku usuari_identificat = null;
		try
		{
			usuari_identificat = ctrl_usuari.identificaUsuari( nom, cont );
		} catch ( IllegalArgumentException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( UsuariNoExisteix e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( ContrasenyaIncorrecta e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( ContrasenyaInvalida e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println( usuari_identificat );

	}
}
