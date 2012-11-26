package prop.gomoku.domini.controladors;

import java.io.IOException;

import prop.gomoku.domini.controladors.excepcions.ContrasenyaIncorrecta;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorUsuaris;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

public class ControladorUsuari
{
	private static GestorUsuaris gestor_usuaris = new GestorUsuaris();

	public UsuariGomoku identificaUsuari( String nom, String contrasenya ) throws UsuariNoExisteix,
			ContrasenyaIncorrecta
	{
		UsuariGomoku usuari = null;
		try
		{
			usuari = gestor_usuaris.carregaUsuari( nom );
		} catch ( UsuariNoExisteix e )
		{
			throw new UsuariNoExisteix( "No existeix un usuari amb aquest nom" );
		}

		if ( usuari.getContrasenya().equals( contrasenya ) )
		{
			return usuari;
		}
		else
		{
			throw new ContrasenyaIncorrecta( "Contrasenya incorrecta" );
		}
	}

	public UsuariGomoku registraUsuari( String nom, String contrasenya ) throws UsuariJaExisteix, IOException
	{
		UsuariGomoku usuari_nou = new UsuariGomoku( nom, contrasenya );
		gestor_usuaris.guardaNouUsuari( usuari_nou );
		return usuari_nou;
	}
}
