package prop.gomoku.domini.controladors;

import java.io.IOException;

import prop.gomoku.auxiliars.Vigenere;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaIncorrecta;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaInvalida;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorUsuaris;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

public class ControladorUsuari
{
	private static final String clau = "`'?¿";
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

		// TODO
		if ( usuari.getTipus() != TipusUsuari.HUMA )
		{
			throw new IllegalArgumentException( "No és un usuari HUMA" );
		}
		// TODO s'hauria de xifrar abans de comparar

		if ( usuari.getContrasenya().equals( contrasenya ) )
		{
			return usuari;
		}
		else
		{
			throw new ContrasenyaIncorrecta( "Contrasenya incorrecta" );
		}

	}

	public UsuariGomoku registraUsuari( String nom, String contrasenya ) throws ContrasenyaInvalida, UsuariJaExisteix, IOException
	{
		String contrasenya_xifrada = null;
		try
		{
			contrasenya_xifrada = new Vigenere( contrasenya, clau).getMissatgeXifrat();
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ContrasenyaInvalida( "Contrasenya invàlida" );
		}
		UsuariGomoku usuari_nou = new UsuariGomoku( nom, contrasenya_xifrada );
		gestor_usuaris.guardaNouUsuari( usuari_nou );
		return usuari_nou;
	}

	// TODO Aqui es retornaran els usuaris de diferents tipus
	public UsuariGomoku carregaUsuariSistema( TipusUsuari tipus )
	{
		if ( tipus == TipusUsuari.HUMA )
		{
			throw new IllegalArgumentException( "Usuari HUMA no és del sistema" );
		}

		UsuariGomoku usuari = null;
		switch ( tipus )
		{
			case CONVIDAT:
				usuari = new UsuariGomoku( "Convidat", "Convidat" );
				break;

			case FACIL:
				break;

			case MITJA:
				break;

			case DIFICIL:
				break;

			default:
				// Aquest cas no s'hauria de donar
				break;
		}
		return usuari;
	}
}
