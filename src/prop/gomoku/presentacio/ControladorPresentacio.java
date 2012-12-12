package prop.gomoku.presentacio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import prop.gomoku.domini.controladors.ControladorPartidesGuardades;
import prop.gomoku.domini.controladors.ControladorUsuari;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaIncorrecta;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaInvalida;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;

public class ControladorPresentacio
{
	private FrameEstadistiques frame_estadistiques;
	private FrameConfiguracioPartida2CPU frame_configuracio2cpu;
	private FrameCarregaPartides frame_carrega_partides;
	private static UsuariGomoku usuari_actiu;
	private UsuariGomoku usuari_oponent;
	private FrameBenvingut frame_benvingut;
	private FrameIdentificacio frame_identificacio;
	private FrameRegistrar frame_registrar;
	private ControladorUsuari controlador_usuari;
	private FrameMenuPrincipal frame_menu_principal;
	private FrameNovaPartida frame_nova_partida;
	private FrameConfiguracioPartida1 frame_configuracio1;
	private FrameConfiguracioPartida2 frame_configuracio2;
	private FrameConfiguracioPartida3 frame_configuracio3;
	private FrameConfiguracioPartida2Persones frame_configuracio2persones;

	public void sincronizacionBenvingutIdentificacio( FrameBenvingut frame_benvingut )
	{
		if ( frame_benvingut == null )
		{
			frame_benvingut = new FrameBenvingut();
		}
		if ( frame_identificacio == null )
		{
			frame_identificacio = new FrameIdentificacio();
		}
		frame_benvingut.setVisible( false );
		frame_identificacio.main();
	}

	public void sincronitzacioIdentificacioBenvingut( FrameIdentificacio frame_identificacio )
	{
		if ( frame_benvingut == null )
		{
			frame_benvingut = new FrameBenvingut();
		}
		if ( frame_identificacio == null )
		{
			frame_identificacio = new FrameIdentificacio();
		}
		frame_identificacio.dispose();
		frame_benvingut.main();
	}

	public void sincronizacionBenvingutRegistrar( FrameBenvingut frame_benvingut )
	{
		if ( frame_benvingut == null )
		{
			frame_benvingut = new FrameBenvingut();
		}
		if ( frame_registrar == null )
		{
			frame_registrar = new FrameRegistrar();
		}
		frame_benvingut.dispose();
		frame_registrar.main();
	}

	public void sincronitzacioNovaPartidaMenu( FrameNovaPartida frame_nova_partida )
	{
		if ( frame_nova_partida == null )
		{
			frame_nova_partida = new FrameNovaPartida();
		}
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frame_nova_partida.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
	}

	public void sincronitzacioNovaPartidaConfiguracio( FrameNovaPartida frame_nova_partida )
	{
		if ( frame_nova_partida == null )
		{
			frame_nova_partida = new FrameNovaPartida();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_nova_partida.dispose();
		frame_configuracio1.main();
		frame_configuracio1.setControladorPresentacio( this );
	}

	public void sincronitzacioConfiguracioNovaPartida( FrameConfiguracioPartida1 frame_configuracio1 )
	{
		if ( frame_nova_partida == null )
		{
			frame_nova_partida = new FrameNovaPartida();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_nova_partida.main();
	}

	public void sincronitzacioConfiguracioMenu( FrameConfiguracioPartida1 frame_configuracio1 )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_menu_principal.main();
	}

	public void sincronitzacioConfiguracio3Menu( FrameConfiguracioPartida3 frame_configuracio3 )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		frame_configuracio3.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
	}

	public void sincronitzacioConfiguracio2Menu( FrameConfiguracioPartida2 frame_configuracio2 )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_configuracio2 == null )
		{
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		frame_configuracio2.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
	}

	public void sincronitzacioConfiguracio2CPUMenu( FrameConfiguracioPartida2CPU frame_configuracio2cpu )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_configuracio2cpu == null )
		{
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		frame_configuracio2cpu.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
	}

	public void sincronitzacioConfiguracio2PersonesMenu( FrameConfiguracioPartida2Persones frame_configuracio2persones )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_configuracio2persones == null )
		{
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		frame_configuracio2persones.dispose();
		frame_menu_principal.main();
	}

	public void sincronitzacioCarregaPartidesMenu(FrameCarregaPartides frame_carrega_partides){
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_carrega_partides == null )
		{
			frame_carrega_partides = new FrameCarregaPartides();
		}
		frame_carrega_partides.dispose();
		frame_menu_principal.setControladorPresentacio( this );
		frame_menu_principal.main();
	}

	public void sincronitzacioMenuCarregaPartides( FrameMenuPrincipal frame_menu_principal )
	{

		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_carrega_partides == null )
		{
			frame_carrega_partides = new FrameCarregaPartides();
		}

		frame_menu_principal.dispose();
		frame_carrega_partides.main();
		frame_carrega_partides.setControladorPresentacio( this );
		
		// TODO
		
		System.out.println("Usuari que vol carregar les partides: " + usuari_actiu);
		
		ControladorPartidesGuardades ctrl_partides_guardades = new ControladorPartidesGuardades();
		List<PartidaGomoku> llista_partides = ctrl_partides_guardades.carregaPartides( usuari_actiu );
		// TODO
		System.out.println("A punt de mostrar la llista de partides");
		for (PartidaGomoku partida : llista_partides)
		{
			System.out.println(partida);
		}
		frame_carrega_partides.mostraLlistaPartides(llista_partides);

	}

	public void sincronitzacioConfiguracio21( FrameConfiguracioPartida2 frame_configuracio2 )
	{
		if ( frame_configuracio2 == null )
		{
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio2.dispose();
		frame_configuracio1.main();
	}

	public void sincronitzacioConfiguracio2cpu3( FrameConfiguracioPartida2CPU frame_configuracio2cpu )
	{
		if ( frame_configuracio2cpu == null )
		{
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		frame_configuracio2cpu.dispose();
		frame_configuracio3.main();
		TipusUsuari[] tipus_usuari = frame_configuracio2cpu.getnivellmaquina();
		try
		{
			usuari_actiu=controlador_usuari.carregaUsuariSistema(tipus_usuari[0]);
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			usuari_oponent=controlador_usuari.carregaUsuariSistema(tipus_usuari[1]);
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame_configuracio3.setTipusText();
	}
	public void sincronitzacioConfiguracio2cpu1( FrameConfiguracioPartida2CPU frame_configuracio2cpu )
	{
		if ( frame_configuracio2cpu == null )
		{
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio2cpu.dispose();
		frame_configuracio1.main();
		
	}

	public void sincronitzacioConfiguracio2persones1( FrameConfiguracioPartida2Persones frame_configuracio2persones )
	{
		if ( frame_configuracio2persones == null )
		{
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio2persones.dispose();
		frame_configuracio1.main();
	}
	
	public void sincronitzacioConfiguracio23(FrameConfiguracioPartida2 frame_configuracio2){
		if ( frame_configuracio2 == null )
		{
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		TipusUsuari tipus_oponent = frame_configuracio2.getTipusOponent();
		try
		{
			usuari_oponent = controlador_usuari.carregaUsuariSistema(tipus_oponent);
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame_configuracio2.dispose();
		// TODO
		System.out.println(tipus_oponent);
		System.out.println("user1: "+ usuari_actiu + "oponent: " + usuari_oponent);
		frame_configuracio3.main();		
		frame_configuracio3.setTipusText();
		frame_configuracio3.setNomsusuaris();
	}

	public void sincronitzacioConfiguracio31( FrameConfiguracioPartida3 frame_configuracio3 )
	{
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio3.dispose();
		frame_configuracio1.main();
	}

	public void sincronitzacioConfiguracio2Persones3( FrameConfiguracioPartida2Persones frame_configuracio2persones )
	{
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if ( frame_configuracio2persones == null )
		{
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		frame_configuracio2persones.dispose();
		frame_configuracio3.main();
		frame_configuracio3.setNomsusuaris();
	}

	public void sincronitzacioConfiguracio12( FrameConfiguracioPartida1 frame_configuracio1 )
	{
		if ( frame_configuracio2 == null )
		{
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_configuracio2.main();
	}

	public void sincronitzacioConfiguracio12CPU( FrameConfiguracioPartida1 frame_configuracio1 )
	{
		if ( frame_configuracio2cpu == null )
		{
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_configuracio2cpu.main();
		frame_configuracio2cpu.setControladorPresentacio( this );
	}

	public void sincronitzacioConfiguracio12Persones( FrameConfiguracioPartida1 frame_configuracio1 )
	{
		if ( frame_configuracio2persones == null )
		{
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		if ( frame_configuracio1 == null )
		{
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_configuracio2persones.main();
	}

	public void sincronitzacioMenuNovaPartida( FrameMenuPrincipal frame_menu_principal )
	{
		if ( frame_nova_partida == null )
		{
			frame_nova_partida = new FrameNovaPartida();
		}
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frame_menu_principal.dispose();
		frame_nova_partida.main();
		frame_nova_partida.setControladorPresentacio( this );

	}

	public void sincronitzacioMenuBenvingut( FrameMenuPrincipal frame_menu_principal )
	{
		usuari_actiu = null;
		if ( frame_benvingut == null )
		{
			frame_benvingut = new FrameBenvingut();
		}
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frame_menu_principal.dispose();
		frame_benvingut.main();
	}

	public void sincronitzacioRegistrarBenvingut( FrameRegistrar frame_registrar )
	{
		if ( frame_benvingut == null )
		{
			frame_benvingut = new FrameBenvingut();
		}
		if ( frame_registrar == null )
		{
			frame_registrar = new FrameRegistrar();
		}
		frame_registrar.dispose();
		frame_benvingut.main();
	}

	public void sincronitzacioerroridentificacio( FrameError frame_error )
	{

	}

	public void Identificarse( FrameIdentificacio frame_identificacio, String alies, String contrasenya )
	{
		boolean excepcio = false;
		try
		{
			System.out.println( "dades: " + alies + " - " + contrasenya );
			usuari_actiu = controlador_usuari.identificaUsuari( alies, contrasenya );
			System.out.println( "Usuari actiu: " + usuari_actiu );
		} catch ( UsuariNoExisteix e )
		{
			System.out.println( e.getMessage() );
			frame_identificacio.NetejaAliesContrasenya();
			excepcio = true;
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error.MissatgeActiva( "L'usuari introduit no esta registrat en el sistema." );

		} catch ( ContrasenyaIncorrecta e )
		{
			e.printStackTrace();
			System.out.println( e.getMessage() );
			frame_identificacio.netejaContrasenya();
			excepcio = true;
			// TODO missatge error contrasenya incorrecte
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error.MissatgeActiva( "La contrasenya introduida no es la correcte per a aquest usuari" );

		} catch ( IllegalArgumentException e )
		{
			System.out.println( e.getMessage() );
			excepcio = true;// TODO Auto-generated catch block
			System.out.println( "Illegar argument" );
		} catch ( ContrasenyaInvalida e )
		{
			System.out.println( e.getMessage() );
			excepcio = true;
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if ( excepcio == false )
		{
			if ( frame_menu_principal == null )
			{
				frame_menu_principal = new FrameMenuPrincipal();
			}
			if ( frame_identificacio == null )
			{
				frame_identificacio = new FrameIdentificacio();
			}
			// System.out.println(usuari_actiu);
			// System.out.println(frame_identificacio.getUsuariActual());
			frame_identificacio.dispose();
			frame_menu_principal.main();
			frame_menu_principal.setControladorPresentacio( this );
			System.out.println( "Usuari actiu 2: " + usuari_actiu );
		}
	}

	public void IdentificarOponent( FrameConfiguracioPartida2Persones frame_configuracio2persones, String alies,
			String contrasenya )
	{
		boolean excepcio = false;
		try
		{
			if(alies=="Convidat"){
				try
				{
					usuari_oponent=controlador_usuari.carregaUsuariSistema(TipusUsuari.CONVIDAT);
				} catch ( IOException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				usuari_oponent = controlador_usuari.identificaUsuari( alies, contrasenya );
			}
		} catch ( UsuariNoExisteix e )
		{
			frame_configuracio2persones.NetejaAliesContrasenya();
			excepcio = true;
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error.MissatgeActiva( "L'usuari introduit no esta registrat en el sistema." );

		} catch ( ContrasenyaIncorrecta e )
		{
			frame_identificacio.NetejaAlies();
			excepcio = true;
			// TODO missatge error contrasenya incorrecte
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error.MissatgeActiva( "La contrasenya introduida no es la correcte per a aquest usuari" );

		} catch ( IllegalArgumentException e )
		{
			// TODO Auto-generated catch block

		} catch ( ContrasenyaInvalida e )
		{
			// TODO Auto-generated catch block

		}
		if ( excepcio == false )
		{
			if ( frame_configuracio2persones == null )
			{
				frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
			}
			if ( frame_configuracio3 == null )
			{
				frame_configuracio3 = new FrameConfiguracioPartida3();
			}
			frame_configuracio2persones.dispose();
			frame_configuracio3.main();
			frame_configuracio3.setControladorPresentacio( this );
			System.out.println("usuari_acutal:" + usuari_actiu + "usuari oponent: " + usuari_oponent);
			frame_configuracio3.setTipusText();
			frame_configuracio3.setNomsusuaris();
		}
	}

	public void RegistrarJugador( FrameRegistrar frame_registrar, String nom, String contrasenya )
	{
		boolean excepcio = false;
		
			try
			{
				usuari_actiu = controlador_usuari.registraUsuari( nom, contrasenya );
			} catch ( ContrasenyaInvalida e )
			{
				FrameError contrasenyainvalida= new FrameError();
				contrasenyainvalida.main();
				contrasenyainvalida.MissatgeActiva("La contrasenya introduida conte caràcters invalids");
				frame_registrar.Netejapasswords();
				excepcio = true;
			}
			catch ( IOException e )
		{
			excepcio = true;
			System.out.println( "Fallo de IO" );
			// TODO missatge error usuari no registrat

		} catch ( UsuariJaExisteix e )
		{
			FrameError usuarijaexisteix = new FrameError();
			usuarijaexisteix.main();
			usuarijaexisteix.MissatgeActiva("El nom d'usuari introduit ja esta actualment registrat al sistema");
			frame_registrar.Netejatot();
			excepcio = true;	
		}
	
		if ( excepcio == false )
		{
			if ( frame_menu_principal == null )
			{
				frame_menu_principal = new FrameMenuPrincipal();
			}
			if ( frame_registrar == null )
			{
				frame_registrar = new FrameRegistrar();
			}
			frame_registrar.dispose();
			frame_menu_principal.main();
			System.out.println( usuari_actiu );
		}
	}

	public void InicialitzarPresentacio()
	{
		frame_benvingut = new FrameBenvingut();
		frame_benvingut.main();

	}

	public void sincronitzacioEstadistiquesMenu( FrameEstadistiques frame_estadistiques )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_estadistiques == null )
		{
			frame_estadistiques = new FrameEstadistiques();
		}
		frame_estadistiques.dispose();
		frame_menu_principal.main();
	}
	public void sincronitzacioBenvingutMenu( FrameBenvingut frame_benvingut )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_benvingut == null )
		{
			frame_benvingut = new FrameBenvingut();
		}
		try
		{
			usuari_actiu=controlador_usuari.carregaUsuariSistema(TipusUsuari.CONVIDAT);
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame_benvingut.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
		frame_menu_principal.menuConvidat();
	}

	public void sincronitzacioMenuEstadistiques( FrameMenuPrincipal frame_menu_principal )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_estadistiques == null )
		{
			frame_estadistiques = new FrameEstadistiques();
		}
		frame_menu_principal.dispose();
		frame_estadistiques.main();
	}

	public ControladorPresentacio()
	{
		frame_benvingut = null;
		frame_registrar = null;
		frame_identificacio = null;
		controlador_usuari = new ControladorUsuari();
		frame_menu_principal = null;
		frame_nova_partida = null;
	}

	public void setUsuari_oponent( UsuariGomoku usuari_oponent )
	{
		this.usuari_oponent = usuari_oponent;
	}

	public void MostraPDF()
	{
		if ( Desktop.isDesktopSupported() )
		{
			try
			{
				File myFile = new File( "D:\\AD.pdf" );
				Desktop.getDesktop().open( myFile );
			} catch ( IOException ex )
			{
				// no application registered for PDFs
			}
		}
	}

	public UsuariGomoku getUsuariActual()
	{
		return usuari_actiu;
	}

	public List<PartidaGomoku> getLlistaPartides()
	{
		return new ControladorPartidesGuardades().carregaPartides( usuari_actiu );
	}
	public UsuariGomoku getOponentActual(){
		return usuari_oponent;
	}
}
