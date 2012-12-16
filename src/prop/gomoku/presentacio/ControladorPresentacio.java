package prop.gomoku.presentacio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;
import prop.gomoku.domini.controladors.ControladorPartidesGuardades;
import prop.gomoku.domini.controladors.ControladorPreparacioPartida;
import prop.gomoku.domini.controladors.ControladorUsuari;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaIncorrecta;
import prop.gomoku.domini.controladors.excepcions.ContrasenyaInvalida;
import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals;
import prop.gomoku.domini.controladors.records.ControladorRecordsIndividuals;
import prop.gomoku.domini.controladors.records.CriteriRecords;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;
import prop.gomoku.domini.models.TipusUsuari;
import prop.gomoku.domini.models.UsuariGomoku;
import prop.gomoku.gestors.excepcions.UsuariJaExisteix;
import prop.gomoku.gestors.excepcions.UsuariNoExisteix;
import prop.gomoku.tests.taulervisual.ProvaTaulerGUI;
import prop.gomoku.tests.taulervisual.ProvaTaulerGUIEntrenament;

public class ControladorPresentacio
{
	private FrameAccedirEstadistiques frame_estadistiques;
	private FrameConfiguracioPartida2CPU frame_configuracio2cpu;
	private FrameCarregaPartides frame_carrega_partides;
	private static UsuariGomoku usuari_actiu;
	private UsuariGomoku jugador_actiu;
	private UsuariGomoku jugador_oponent;
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
	private FrameEstadistiquesGlobals frame_estadistiques_globals;
	private FrameEstadistiquesIndividuals frame_estadistiques_indivuals;
	private PartidaGomoku partida_en_curs;
	private ProvaTaulerGUI tauler_partida;
	private EstatPartida estat;
	private ProvaTaulerGUIEntrenament tauler_entrenament;
	private ControladorPartidaEnJoc ctrl_en_joc;
	private static boolean entrenament;

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
		System.out.println( "Entrenament :" + frame_configuracio1.getcontroladorPresentacio().getEntrenament() );
		System.out.println( "Es el mateix controlador pres. = "
				+ ( this == frame_configuracio1.getcontroladorPresentacio() ) );
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

	public void sincronitzacioCarregaPartidesMenu( FrameCarregaPartides frame_carrega_partides )
	{
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

		System.out.println( "Usuari que vol carregar les partides: " + usuari_actiu );

		ControladorPartidesGuardades ctrl_partides_guardades = new ControladorPartidesGuardades();
		List<PartidaGomoku> llista_partides = ctrl_partides_guardades.carregaPartides( usuari_actiu );
		System.out.println( "A punt de mostrar la llista de partides" );
		for ( PartidaGomoku partida : llista_partides )
		{
			System.out.println( partida );
		}
		frame_carrega_partides.setControladorPresentacio( this );
		frame_carrega_partides.mostraLlistaPartides( llista_partides );

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
		frame_configuracio3.setControladorPresentacio( this );
		TipusUsuari[] tipus_usuari = frame_configuracio2cpu.getnivellmaquina();
		try
		{
			jugador_actiu = controlador_usuari.carregaUsuariSistema( tipus_usuari[0] );
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			jugador_oponent = controlador_usuari.carregaUsuariSistema( tipus_usuari[1] );
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( "Maquina 1:" + usuari_actiu + "Maquina 2:" + jugador_oponent );
		frame_configuracio3.setTipusText();
		frame_configuracio3.setNomsusuaris();
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

	public void iniciaEntrenament( FrameConfiguracioPartida3 frame_configuracio3, int color_jugador1 )
	{
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if ( tauler_entrenament == null )
		{
			tauler_entrenament = new ProvaTaulerGUIEntrenament();
		}
		frame_configuracio3.dispose();
		int color = color_jugador1;
		if ( color == 2 )
		{
			Random aleatori = new Random();
			double aleatoridouble = aleatori.nextDouble();
			if ( aleatoridouble >= 0.5 )
				color = 0;
			else
				color = 1;
		}
		if ( color == 0 )
		{
			partida_en_curs = new PartidaGomoku( usuari_actiu, jugador_actiu, jugador_oponent, new TaulerGomoku(),
					"nova_partida" );
		}
		else
		{
			partida_en_curs = new PartidaGomoku( usuari_actiu, jugador_oponent, jugador_actiu, new TaulerGomoku(),
					"nova_partida" );
		}
		System.out.println( partida_en_curs );
		tauler_entrenament.main();
		tauler_entrenament.setPartida( partida_en_curs );
		tauler_entrenament.activa();
		tauler_entrenament.setControladorPresentacioTauler( this );
		tauler_entrenament.setControladorPresentacio( this );
		estat = EstatPartida.NO_FINALITZADA;
		this.juga_partida( tauler_partida );
	}

	public void iniciaPartida( FrameConfiguracioPartida3 frame_configuracio3, int color_jugador1 )
	{
		if ( frame_configuracio3 == null )
		{
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if ( tauler_partida == null )
		{
			tauler_partida = new ProvaTaulerGUI();
		}
		frame_configuracio3.dispose();
		int color = color_jugador1;
		if ( color == 2 )
		{
			Random aleatori = new Random();
			double aleatoridouble = aleatori.nextDouble();
			if ( aleatoridouble >= 0.5 )
				color = 0;
			else
				color = 1;
		}
		if ( color == 0 )
		{
			partida_en_curs = new PartidaGomoku( usuari_actiu, jugador_actiu, jugador_oponent, new TaulerGomoku(),
					"nova_partida" );
		}
		else
		{
			partida_en_curs = new PartidaGomoku( usuari_actiu, jugador_oponent, jugador_actiu, new TaulerGomoku(),
					"nova_partida" );
		}
		System.out.println( partida_en_curs );
		tauler_partida.main();
		tauler_partida.setPartida( partida_en_curs );
		tauler_partida.activa();
		tauler_partida.setControladorPresentacioTauler( this );
		tauler_partida.setControladorPresentacio( this );
		estat = EstatPartida.NO_FINALITZADA;
		if ( ( partida_en_curs.getJugadorA().getTipus() != TipusUsuari.HUMA && partida_en_curs.getJugadorA().getTipus() != TipusUsuari.CONVIDAT )
				&& ( partida_en_curs.getJugadorB().getTipus() != TipusUsuari.HUMA && partida_en_curs.getJugadorB()
						.getTipus() != TipusUsuari.CONVIDAT ) )
		{
			tauler_partida.bloquejaTauler();
		}
		juga_partida( tauler_partida );
	}

	private void juga_partida( ProvaTaulerGUI tauler_partida )
	{
		System.out.println( "La partida acaba de començar, si la maquina es negres moura" );
		ctrl_en_joc = new ControladorPartidaEnJoc( partida_en_curs );
		if ( ( partida_en_curs.getJugadorA().getTipus() != TipusUsuari.HUMA
				&& partida_en_curs.getJugadorA().getTipus() != TipusUsuari.CONVIDAT && partida_en_curs.getTornsJugats() % 2 == 0 )
				&& entrenament == false )
		{ // negres maquina
			int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
			ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_A, mov_ia[0], mov_ia[1] );
			tauler_partida.getTaulerActual().pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_A );
			estat = partida_en_curs.comprovaEstatPartida( mov_ia[0], mov_ia[1] );
		}
		else if ( ( partida_en_curs.getJugadorB().getTipus() != TipusUsuari.HUMA
				&& partida_en_curs.getJugadorB().getTipus() != TipusUsuari.CONVIDAT && partida_en_curs.getTornsJugats() % 2 == 1 )
				&& entrenament == false )
		{ // blanques maquina
			int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
			ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_B, mov_ia[0], mov_ia[1] );
			tauler_partida.getTaulerActual().pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_B );
			estat = partida_en_curs.comprovaEstatPartida( mov_ia[0], mov_ia[1] );
		}
		
	}

	public void fi_partida()
	{
		System.out.println(estat);
		System.out.println( "Entro a fi partida" );
		System.out.println(partida_en_curs);
		FrameError endgame = new FrameError();
		endgame.main();
		if ( estat == EstatPartida.EMPAT )
		{
			endgame.MissatgeActiva( "La partida ha acabat en un empat" );
		}
		else if ( estat == EstatPartida.GUANYA_JUGADOR_A )
		{
			if ( partida_en_curs.getJugadorA().getTipus() == TipusUsuari.HUMA
					|| partida_en_curs.getJugadorA().getTipus() == TipusUsuari.CONVIDAT )
			{
				endgame.MissatgeActiva( "El jugador " + partida_en_curs.getJugadorA().getNom()
						+ " guanya la partida, enhorabona!" );
			}
			else
			{
				endgame.MissatgeActiva( partida_en_curs.getJugadorA().getNom() + " es el guanyador de la partida" );
			}
		}
		else
		{
			if ( partida_en_curs.getJugadorB().getTipus() == TipusUsuari.HUMA
					|| partida_en_curs.getJugadorB().getTipus() == TipusUsuari.CONVIDAT )
			{
				endgame.MissatgeActiva( "El jugador " + partida_en_curs.getJugadorB().getNom()
						+ " guanya la partida, enhorabona!" );

			}
			else
			{
				endgame.MissatgeActiva( partida_en_curs.getJugadorB().getNom() + " es el guanyador de la partida" );

			}
		}
		ctrl_en_joc.actualitzaDadesFinalPartida();
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

	public void sincronitzacioConfiguracio23( FrameConfiguracioPartida2 frame_configuracio2 )
	{
		jugador_actiu = usuari_actiu;
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
			jugador_oponent = controlador_usuari.carregaUsuariSistema( tipus_oponent );
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame_configuracio2.dispose();
		// TODO
		System.out.println( tipus_oponent );
		System.out.println( "user1: " + jugador_actiu + "oponent: " + jugador_oponent );
		System.out.println( "Entrenament: " + entrenament );
		frame_configuracio3.main();
		frame_configuracio3.setControladorPresentacio( this );
		frame_configuracio3.setTipusText();
		frame_configuracio3.setNomsusuaris();
	}

	public void sincronitzacioAccedirEstadistiquesIndividuals( FrameAccedirEstadistiques frame_estadistiques )
	{
		if ( frame_estadistiques == null )
		{
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		if ( frame_estadistiques_indivuals == null )
		{
			frame_estadistiques_indivuals = new FrameEstadistiquesIndividuals();
		}
		frame_estadistiques_indivuals.main();
		System.out.println( "Usuari actiu abans de crear els records: " + usuari_actiu );
		int[] records = new int[35];
		int i = 0;
		ControladorRecordsIndividuals records_individuals = new ControladorRecordsIndividuals( usuari_actiu );
		for ( CriteriRecords criteri : CriteriRecords.values() )
		{
			records[i] = records_individuals.getLlistaRecords( criteri );
			++i;
		}
		frame_estadistiques_indivuals.carregaestadistiques( records );
	}

	// public void sincronitzacioCanviNom(PartidaGomoku partida){
	// if(frame_canvi_nom==null){
	// frame_canvi_nom = new FrameCanviNom();
	// }
	// partida_canvi_nom = partida;
	// frame_canvi_nom.main();
	// frame_canvi_nom.setControladorPresentacio(this);
	//		
	//		
	//		
	// }
	//	
	// public void canvinomiguardat(FrameCanviNom canvi_nom){
	// partida_canvi_nom.setNom( nomcanvi );
	// canvi_nom.dispose();
	// ControladorPartidesGuardades controlador_partides = new ControladorPartidesGuardades();
	// controlador_partides.guardaPartida( partida_canvi_nom );
	// frame_carrega_partides.mostraLlistaPartides( controlador_partides.carregaPartides( usuari_actiu ) );
	// }
	public void sincronitzacioAccedirEstadistiquesGlobals( FrameAccedirEstadistiques frame_estadistiques )
	{
		if ( frame_estadistiques == null )
		{
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		if ( frame_estadistiques_globals == null )
		{
			frame_estadistiques_globals = new FrameEstadistiquesGlobals();
		}
		frame_estadistiques_globals.main();
		List<List<String[]>> records = new ArrayList<List<String[]>>();
		List<String[]> recordcriteri;
		ControladorRecordsGlobals controlador_records_globals = new ControladorRecordsGlobals();
		for ( CriteriRecords criteri : CriteriRecords.values() )
		{
			recordcriteri = ( controlador_records_globals.getLlistaRecords( criteri ) );
			records.add( recordcriteri );
		}
		frame_estadistiques_globals.carregaestadistiques( records );

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
		jugador_actiu = usuari_actiu;
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
		frame_configuracio3.setControladorPresentacio( this );
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
		jugador_actiu = usuari_actiu;
		frame_configuracio1.dispose();
		frame_configuracio2.main();
		frame_configuracio2.setControladorPresentacio( this );
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
		jugador_actiu = usuari_actiu;
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
		System.out.println( "contrasenya : " + contrasenya );
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
			frame_identificacio.netejaContrasenya();
			System.out.println( e.getMessage() );
			excepcio = true;
			FrameError error = new FrameError();
			error.main();
			error.MissatgeActiva( "La contrasenya introduida conte caràcters invalids" );
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
		jugador_actiu = usuari_actiu;
		boolean excepcio = false;
		try
		{
			if ( alies == "Convidat" )
			{
				try
				{
					jugador_oponent = controlador_usuari.carregaUsuariSistema( TipusUsuari.CONVIDAT );
				} catch ( IOException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				jugador_oponent = controlador_usuari.identificaUsuari( alies, contrasenya );
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
			frame_configuracio2persones.NetejaContrasenya();
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
			frame_configuracio2persones.NetejaContrasenya();
			FrameError frame_error = new FrameError();
			frame_error.main();
			excepcio = true;
			frame_error.MissatgeActiva( "La contrasenya introduida conte caràcters invalids" );
			frame_configuracio2persones.NetejaContrasenya();
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
			System.out.println( "usuari_acutal:" + jugador_actiu + "usuari oponent: " + jugador_oponent );
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
			FrameError contrasenyainvalida = new FrameError();
			contrasenyainvalida.main();
			contrasenyainvalida.MissatgeActiva( "La contrasenya introduida conte caràcters invalids" );
			frame_registrar.Netejapasswords();
			excepcio = true;
		} catch ( IOException e )
		{
			excepcio = true;
			System.out.println( "Fallo de IO" );
			// TODO missatge error usuari no registrat

		} catch ( UsuariJaExisteix e )
		{
			FrameError usuarijaexisteix = new FrameError();
			usuarijaexisteix.main();
			usuarijaexisteix.MissatgeActiva( "El nom d'usuari introduit ja esta actualment registrat al sistema" );
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

	public void sincronitzacioEstadistiquesMenu( FrameAccedirEstadistiques frame_estadistiques )
	{
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if ( frame_estadistiques == null )
		{
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		frame_estadistiques.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
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
			usuari_actiu = controlador_usuari.carregaUsuariSistema( TipusUsuari.CONVIDAT );
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame_benvingut.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
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
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		frame_menu_principal.dispose();
		frame_estadistiques.main();
		frame_estadistiques.setControladorPresentacio( this );
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
		this.jugador_oponent = usuari_oponent;
	}

	public void MostraPDF()
	{
		if ( Desktop.isDesktopSupported() )
		{
			URL url = ControladorPresentacio.class.getResource( "/prop/gomoku/recursos/Manual_usuari.pdf" );
			try
			{
				File myFile = new File( url.toString().substring( 6 ) );
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

	public UsuariGomoku getOponentActual()
	{
		return jugador_oponent;
	}

	public boolean setCanviNom( String nom )
	{
		return true;
	}

	public void juga_maquines()
	{
		if ( partida_en_curs.getJugadorA().getTipus() == TipusUsuari.HUMA
				|| partida_en_curs.getJugadorA().getTipus() == TipusUsuari.CONVIDAT
				|| partida_en_curs.getJugadorB().getTipus() == TipusUsuari.HUMA
				|| partida_en_curs.getJugadorB().getTipus() == TipusUsuari.CONVIDAT )
			return;
		
		int[] ultim_moviment;
		int[] mov_ia;
		boolean finalitzada;
		if ( partida_en_curs.getTornsJugats() % 2 == 0 )
		{
			if ( estat == EstatPartida.NO_FINALITZADA )
			{
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_A, mov_ia[0], mov_ia[1] );
				tauler_partida.pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_A );
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if ( finalitzada )
				{
					fi_partida();
				}
			}
			if ( estat == EstatPartida.NO_FINALITZADA )
			{			
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_B, mov_ia[0], mov_ia[1] );
				tauler_partida.pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_B );
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if ( finalitzada )
				{
					fi_partida();
				}
			}
		}
		else
		{
			if ( estat == EstatPartida.NO_FINALITZADA )
			{				
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_B, mov_ia[0], mov_ia[1] );
				tauler_partida.pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_B );
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if ( finalitzada )
				{
					fi_partida();
				}
			}
			if ( estat == EstatPartida.NO_FINALITZADA )
			{
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_A, mov_ia[0], mov_ia[1] );
				tauler_partida.pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_A );
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if ( finalitzada )
				{
					fi_partida();
				}
			}

		}
	}

	public UsuariGomoku getJugadorActual()
	{
		return jugador_actiu;
	}

	public boolean ferMoviment( int[] coord )
	{
		if ( partida_en_curs.estaFinalitzada() )
			return false;
		if ( entrenament
				|| ( partida_en_curs.getJugadorA().getTipus() == TipusUsuari.HUMA || partida_en_curs.getJugadorA()
						.getTipus() == TipusUsuari.CONVIDAT )
				|| ( partida_en_curs.getJugadorB().getTipus() == TipusUsuari.HUMA || partida_en_curs.getJugadorB()
						.getTipus() == TipusUsuari.CONVIDAT ) )
		{
			int[] ultim_moviment;
			if ( estat == EstatPartida.NO_FINALITZADA )
			{
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
				if ( estat != EstatPartida.NO_FINALITZADA )
				{
					System.out.println( estat );
					return false;
				}
			}
			int fila = coord[0];
			int columna = coord[1];
			System.out.println( "Clicat: " + fila + " " + columna );
			if ( partida_en_curs.getTornsJugats() % 2 == 0 )
			{
				try
				{
					ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_A, fila, columna ); // es mouen negres
				} catch ( Exception e )
				{
					System.out.println( e.getMessage() );
					if ( estat != EstatPartida.NO_FINALITZADA )
					{
						if ( entrenament == false )
						{
							fi_partida();
						}
					}
					return false;
				}
				if ( entrenament )
				{
					tauler_entrenament.pinta( fila, columna, EstatCasella.JUGADOR_A );
				}
				else
				{
					tauler_partida.pinta( fila, columna, EstatCasella.JUGADOR_A );
				}
				if ( estat == EstatPartida.NO_FINALITZADA
						&& partida_en_curs.getJugadorB().getTipus() != TipusUsuari.CONVIDAT
						&& partida_en_curs.getJugadorB().getTipus() != TipusUsuari.HUMA && entrenament == false )
				{
					int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
					ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_B, mov_ia[0], mov_ia[1] );
					tauler_partida.pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_B );
				}
				else if ( estat != EstatPartida.NO_FINALITZADA )
				{
					// fi_partida();
				}
			}
			else
			{
				try
				{
					ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_B, fila, columna );
				} catch ( Exception e )
				{
					System.out.println( e.getMessage() );
					if ( estat != EstatPartida.NO_FINALITZADA )
					{
						if ( entrenament == false )
						{
							fi_partida();
						}
					}
					return false;
				}
				if ( entrenament )
				{
					tauler_entrenament.pinta( fila, columna, EstatCasella.JUGADOR_B );
				}
				else
				{
					tauler_partida.pinta( fila, columna, EstatCasella.JUGADOR_B );
				}
				if ( estat == EstatPartida.NO_FINALITZADA
						&& partida_en_curs.getJugadorA().getTipus() != TipusUsuari.CONVIDAT
						&& partida_en_curs.getJugadorA().getTipus() != TipusUsuari.HUMA && entrenament == false )
				{
					int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
					ctrl_en_joc.mouFitxa( EstatCasella.JUGADOR_A, mov_ia[0], mov_ia[1] );
					tauler_partida.pinta( mov_ia[0], mov_ia[1], EstatCasella.JUGADOR_A );
				}
				else if ( estat != EstatPartida.NO_FINALITZADA )
				{
					// fi_partida();
				}
			}
			ultim_moviment = ctrl_en_joc.getUltimMoviment();
			estat = partida_en_curs.comprovaEstatPartida( ultim_moviment[0], ultim_moviment[1] );
		}
		if ( estat != EstatPartida.NO_FINALITZADA )
		{
			if ( entrenament == false )
			{
				fi_partida();
			}
		}
		return true;
	}

	public void guardaPartida( PartidaGomoku partida )
	{
		if ( partida.getJugadorPrincipal().getTipus() == TipusUsuari.CONVIDAT )
		{
			FrameError noesguarda = new FrameError();
			noesguarda.main();
			noesguarda
					.MissatgeActiva( "Els usuaris Convidats no poden guardar partides, registris al sistema per a accedir a aquesta funcionalitat" );
		}
		else
		{
			ControladorPartidesGuardades controlador_partides = new ControladorPartidesGuardades();
			controlador_partides.guardaPartida( partida );
		}
	}

	public void carrega_partida( FrameCarregaPartides frame_carrega_partides, PartidaGomoku partida )
	{
		if ( tauler_partida == null )
		{
			tauler_partida = new ProvaTaulerGUI();
		}
		frame_carrega_partides.dispose();
		if ( partida.estaFinalitzada() )
			estat = EstatPartida.GUANYA_JUGADOR_A;
		else
			estat = EstatPartida.NO_FINALITZADA;
		partida_en_curs = partida;
		tauler_partida.main();
		tauler_partida.setPartida( partida_en_curs );
		tauler_partida.activa();
		tauler_partida.setControladorPresentacioTauler( this );
		tauler_partida.setControladorPresentacio( this );
		tauler_partida.pinta();
		System.out.println( "Partida : " + partida );
		juga_partida( tauler_partida );

	}

	public PartidaGomoku getPartidaactual()
	{
		return partida_en_curs;
	}

	public void sincronitzacioPartidaMenu( ProvaTaulerGUI provaTaulerGUI )
	{
		if ( provaTaulerGUI == null )
		{
			provaTaulerGUI = new ProvaTaulerGUI();
		}
		if ( frame_menu_principal == null )
		{
			frame_menu_principal = new FrameMenuPrincipal();
		}
		provaTaulerGUI.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio( this );
	}

	public void sincroEntrenamentPartida( ProvaTaulerGUIEntrenament tauler )
	{
		if ( tauler == null )
		{
			tauler = new ProvaTaulerGUIEntrenament();
		}
		if ( tauler_partida == null )
		{
			tauler_partida = new ProvaTaulerGUI();
		}
		entrenament = false;
		tauler.dispose();
		tauler_partida.main();
		tauler_partida.setPartida( partida_en_curs );
		tauler_partida.activa();
		tauler_partida.setControladorPresentacioTauler( this );
		tauler_partida.setControladorPresentacio( this );
		tauler_partida.pinta();
		juga_partida( tauler_partida );
	}

	public void setEntrenament( boolean entrenament )
	{
		this.entrenament = entrenament;
	}

	public boolean getEntrenament()
	{
		return entrenament;
	}

	public void sincronitzacioCanviNom( PartidaGomoku partida, String nou_nom )
	{
		ControladorPartidesGuardades partides = new ControladorPartidesGuardades();
		partida.setNom( nou_nom );
		partides.guardaPartida( partida );
		List<PartidaGomoku> partides_nou_nom = partides.carregaPartides( usuari_actiu );
		frame_carrega_partides.mostraLlistaPartides( partides_nou_nom );
	}

	public void esborraPartida( PartidaGomoku partida )
	{
		ControladorPartidesGuardades partides = new ControladorPartidesGuardades();
		partides.esborraPartida( partida );
		List<PartidaGomoku> partides_actualitzades = partides.carregaPartides( usuari_actiu );
		frame_carrega_partides.mostraLlistaPartides( partides_actualitzades );
	}
}
