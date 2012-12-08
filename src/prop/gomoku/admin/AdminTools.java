package prop.gomoku.admin;

import java.io.IOException;

import prop.gomoku.auxiliars.Lectura;
import prop.gomoku.auxiliars.LecturaScanners;
import prop.gomoku.domini.controladors.ControladorUsuari;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaInvalida;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.GestorPartidesGuardades;
import prop.gomoku.gestors.GestorUsuaris;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

public class AdminTools
{
	private static Lectura lectura = new LecturaScanners();
	private static GestorUsuaris gestor_usuaris = new GestorUsuaris();
	private static ControladorUsuari ctrl_usuari = new ControladorUsuari();
	private static GestorPartidesGuardades gestor_partides = new GestorPartidesGuardades();

	private static int menuPrincipal()
	{
		System.out.println( "Gomoku Admin Tools v.0.1\n" );
		System.out.println( "1. Afegir un Usuari al sistema" );
		System.out.println( "2. Esborrar un Usuari al sistema" );
		System.out.println( "3. Afegir una Partida al sistema" );
		System.out.println( "4. Esborrar una Partida al sistema" );
		System.out.println( "5. Sortir" );
		System.out.println();
		return lectura.llegirInt();
	}

	private static void afegeixUsuari()
	{
		System.out.println( "Alta o actualitzacio d'usuari al sistema" );
		System.out.print( "Nom: " );
		String nom = lectura.llegirString();
		System.out.print( "Contrasenya: " );
		String contrasenya = lectura.llegirString();

		UsuariGomoku usuari = null;
		try
		{
			usuari = ctrl_usuari.registraUsuari( nom, contrasenya );
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

		System.out.print( "Indica el nombre de VICTORIES contra IA: FACILs MITJAnes DIFICILs HUMA: " );
		int vict_huma = lectura.llegirInt();
		int vict_facil = lectura.llegirInt();
		int vict_mitja = lectura.llegirInt();
		int vict_dificil = lectura.llegirInt();
		usuari.incrementaVictories( 0, vict_facil );
		usuari.incrementaVictories( 1, vict_mitja );
		usuari.incrementaVictories( 2, vict_dificil );
		usuari.incrementaVictories( 3, vict_huma );

		System.out.print( "Indica el nombre de EMPAT contra IA: FACILs MITJAnes DIFICILs HUMA: " );
		int empats_huma = lectura.llegirInt();
		int empats_facil = lectura.llegirInt();
		int empats_mitja = lectura.llegirInt();
		int empats_dificil = lectura.llegirInt();
		usuari.incrementaEmpats( 0, empats_facil );
		usuari.incrementaEmpats( 1, empats_mitja );
		usuari.incrementaEmpats( 2, empats_dificil );
		usuari.incrementaEmpats( 3, empats_huma );

		System.out.print( "Indica el nombre de DERROTES contra IA: FACILs MITJAnes DIFICILs HUMA: " );
		int derrotes_huma = lectura.llegirInt();
		int derrotes_facil = lectura.llegirInt();
		int derrotes_mitja = lectura.llegirInt();
		int derrotes_dificil = lectura.llegirInt();
		usuari.incrementaDerrotes( 0, derrotes_facil );
		usuari.incrementaDerrotes( 1, derrotes_mitja );
		usuari.incrementaDerrotes( 2, derrotes_dificil );
		usuari.incrementaDerrotes( 3, derrotes_huma );

		if ( ctrl_usuari.actualitzaUsuari( usuari ) )
		{
			System.out.println( "Usuari donat d'alta/actualitzat correctament - " + usuari.toString() );
		}
		else
		{
			System.out.println( "No s'ha pogut realitzar l'alta/actualització per " + usuari.toString() );
		}
		System.out.println();
	}

	private static void esborraUsuari()
	{

	}

	private static void afegeixPartida()
	{
		System.out.println( "Alta de partida al sistema" );
		System.out.print( "Nom: " );
		String nom_partida = lectura.llegirString();

		String nom_jugador_principal = null;
		UsuariGomoku jugador_principal = null;
		{
			boolean correcte = false;
			while ( !correcte )
			{
				System.out.print( "Nom jugador principal: " );
				nom_jugador_principal = lectura.llegirString();
				try
				{
					jugador_principal = gestor_usuaris.carregaUsuari( nom_jugador_principal );
				} catch ( UsuariNoExisteix e )
				{
					System.err.println( "No existeix cap usuari amb nom: " + nom_jugador_principal );
				}
				correcte = true;
				System.out.println( "El jugador amb nom " + nom_jugador_principal + " s'ha carregat" );
			}
		}

		String nom_jugador_negres = null;
		UsuariGomoku jugador_negres = null;
		{
			boolean correcte = false;
			while ( !correcte )
			{
				System.out.print( "Nom jugador negres: " );
				nom_jugador_negres = lectura.llegirString();
				try
				{
					jugador_negres = gestor_usuaris.carregaUsuari( nom_jugador_negres );
				} catch ( UsuariNoExisteix e )
				{
					System.err.println( "No existeix cap usuari amb nom: " + nom_jugador_negres );
				}
				correcte = true;
				System.out.println( "El jugador amb nom " + nom_jugador_negres + " s'ha carregat" );
			}
		}

		String nom_jugador_blanques = null;
		UsuariGomoku jugador_blanques = null;
		{
			boolean correcte = false;
			while ( !correcte )
			{
				System.out.print( "Nom jugador blanques: " );
				nom_jugador_blanques = lectura.llegirString();
				try
				{
					jugador_blanques = gestor_usuaris.carregaUsuari( nom_jugador_blanques );
				} catch ( UsuariNoExisteix e )
				{
					System.err.println( "No existeix cap usuari amb nom: " + nom_jugador_blanques );
				}
				correcte = true;
				System.out.println( "El jugador amb nom " + nom_jugador_blanques + " s'ha carregat" );
			}
		}

		PartidaGomoku partida = new PartidaGomoku( jugador_principal, jugador_negres, jugador_blanques,
				new TaulerGomoku(), nom_partida );
		gestor_partides.guardaPartida( partida );
	}

	private static void esborraPartida()
	{

	}

	public static void main( String[] args )
	{

		boolean surt = false;
		while ( !surt )
		{
			switch ( menuPrincipal() )
			{
				case 1:
					afegeixUsuari();
					break;
				case 2:
					esborraUsuari();
					break;
				case 3:
					afegeixPartida();
					break;
				case 4:
					esborraPartida();
					break;
				default:
					// Inclou case 0
					break;
			}
		}
	}
}
