package prop.gomoku.drivers;

import java.io.IOException;

import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorUsuaris;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

public class DriverGestorUsuaris
{
	public static void main(String[] args)
	{
		GestorUsuaris gestor_usuaris = new GestorUsuaris();
		
		UsuariGomoku usuari_1 = new UsuariGomoku( "Usuari_I", "this_is_password!¿?!", 4 );
		try
		{
			gestor_usuaris.guardaUsuari( usuari_1 );
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
		
		System.out.println("Guardat usuari: " + usuari_1.toString());
		
		UsuariGomoku usuari_2 = null;
		try
		{
			usuari_2 = gestor_usuaris.carregaUsuari( "Usuari_I" );
		} catch ( UsuariNoExisteix e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Carregat usuari: " + usuari_2.toString());
		
		
	}
}
