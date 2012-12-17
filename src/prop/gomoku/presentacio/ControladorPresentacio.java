package prop.gomoku.presentacio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.controladors.ControladorDocumentacio;
import prop.gomoku.domini.controladors.ControladorPartidaEnJoc;
import prop.gomoku.domini.controladors.ControladorPartidesGuardades;
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
/**
 * Classe que representa el controlador de presentacio, que gestiona totes les vistes i les interaccions dins la capa de presentacio 
 * i les seves relacions amb la capa de domini
 * @author Alexander Gimenez Ortega
 *
 */
public class ControladorPresentacio {
	/**
	 * Repersenta la vista que ens dona a escollir quin tipus d'estadistiques volem consultar
	 */
	private FrameAccedirEstadistiques frame_estadistiques;
	/**
	 * Representa la vista de la configuracio de partida on s'enfrenten dos jugador cpu
	 */
	private FrameConfiguracioPartida2CPU frame_configuracio2cpu;
	/**
	 * Representa la vista que ens permet carregar i gestionar les partides guardades
	 */
	private FrameCarregaPartides frame_carrega_partides;
	/**
	 * Representa el usuari actualment identificat en el sistema, o convidat si s'ha entreat com a tal
	 */
	private static UsuariGomoku usuari_actiu;
	/**
	 * Representa el jugador numero 1 de la partida
	 */
	private UsuariGomoku jugador_actiu;
	/**
	 * Representa el jugador numero 2 de la partida
	 */
	private UsuariGomoku jugador_oponent;
	/**
	 * Representa la vista de benvinguda al joc de gomoku on l'usuari pot escollir entre registrarse, identificarse, entrar com a convidat o llegir el manual d'usuari
	 */
	private FrameBenvingut frame_benvingut;
	/**
	 * Representa la vista on l'usuari es pot identificar per accedir al sistema
	 */
	private FrameIdentificacio frame_identificacio;
	/**
	 * Representa la vista on l'usuari pot registrarse per a mes endevant poder accedir al sistema amb el seu usuari
	 */
	private FrameRegistrar frame_registrar;
	/**
	 * Representa el controlador usuari de la capa de domini amb el que intercanviarem dades informacio
	 */
	private ControladorUsuari controlador_usuari;
	/**
	 * Representa la vista del menu principal
	 */
	private FrameMenuPrincipal frame_menu_principal;
	/**
	 * Representa la vista on ens permet escollir entre partida rapida o partida d'entrenament
	 */
	private FrameNovaPartida frame_nova_partida;
	/**
	 * Representa la vista on configurem la partida que farem a continuacio, es la primera que ens trobarem de tres
	 */
	private FrameConfiguracioPartida1 frame_configuracio1;
	/**
	 * Representa la vista on configurem la partida que farem a continuacio, es la segona que ens trobarem de tres
	 */
	private FrameConfiguracioPartida2 frame_configuracio2;
	/**
	 * Representa la vista on configurem la partida que farem a continuacio, es la ultima que ens trobarem de tres
	 */
	private FrameConfiguracioPartida3 frame_configuracio3;
	/**
	 * Representa la vista on configurem la partida que farem a continuacio entre dos humans, es la segona que ens trobarem de tres
	 */
	private FrameConfiguracioPartida2Persones frame_configuracio2persones;
	/**
	 * Representa la vista de les estadistiques globals en forma de llista
	 */
	private FrameEstadistiquesGlobals frame_estadistiques_globals;
	/**
	 * Representa  la vista de les estadistiques individuals de l'usuari actual en forma de llista, basant-se en diferents criteris
	 */
	private FrameEstadistiquesIndividuals frame_estadistiques_indivuals;
	/**
	 * Representa la partida que s'esta jugant actualment en el sistema
	 */
	private PartidaGomoku partida_en_curs;
	/**
	 * Representa la vista del tauler on es juga la partida actual
	 */
	private FrameTaulerGUI tauler_partida;
	/**
	 * Indica l'estat de la partida en curs
	 */
	private EstatPartida estat;
	/**
	 * Representa la vista del tauler on s'executen els entrenaments
	 */
	private FrameTaulerGUIEntrenament tauler_entrenament;
	/**
	 * Representa el controlador de la partida en joc i serveix per a intercanviar informacio amb la capa de domini
	 */
	private ControladorPartidaEnJoc ctrl_en_joc;
	/**
	 * Indica si la partida que es jugaara a continuacio es o no d'entrenament
	 */
	private static boolean entrenament;
	/**
	 * Fa l'intercanvi del frame de benvinguda cap al frame d'identificacio
	 * @param frame_benvingut Representa el frame de benvinguda que volem tancar
	 */
	public void sincronizacionBenvingutIdentificacio(
			FrameBenvingut frame_benvingut) {
		if (frame_benvingut == null) {
			frame_benvingut = new FrameBenvingut();
		}
		if (frame_identificacio == null) {
			frame_identificacio = new FrameIdentificacio();
		}
		frame_benvingut.setVisible(false);
		frame_identificacio.main();
	}
/**
 * Fa l'intercanvi del frame d'identificacio cap al frame de benvinguda
 * @param frame_identificacio Representa el frame d'identificacio que volem tancar
 */
	public void sincronitzacioIdentificacioBenvingut(
			FrameIdentificacio frame_identificacio) {
		if (frame_benvingut == null) {
			frame_benvingut = new FrameBenvingut();
		}
		if (frame_identificacio == null) {
			frame_identificacio = new FrameIdentificacio();
		}
		frame_identificacio.dispose();
		frame_benvingut.main();
	}
	/**
	 * Fa l'intercanvi del frame de benvinguda cap al frame de registre en el sistema
	 * @param frame_benvingut Representa el frame de benvinguda que volem tancar
	 */
	public void sincronizacionBenvingutRegistrar(FrameBenvingut frame_benvingut) {
		if (frame_benvingut == null) {
			frame_benvingut = new FrameBenvingut();
		}
		if (frame_registrar == null) {
			frame_registrar = new FrameRegistrar();
		}
		frame_benvingut.dispose();
		frame_registrar.main();
	}
	/**
	 *  Fa l'intercanvi del frame de nova partida cap al menu principal
	 * @param frame_nova_partida Representa el frame de nova partida que volem tancar
	 */
	public void sincronitzacioNovaPartidaMenu(
			FrameNovaPartida frame_nova_partida) {
		if (frame_nova_partida == null) {
			frame_nova_partida = new FrameNovaPartida();
		}
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frame_nova_partida.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi del frame de nova partida cap a la configuracio de partida
	 * @param frame_nova_partida Representa el frame de nova partida que volem tancar
	 */
	public void sincronitzacioNovaPartidaConfiguracio(
			FrameNovaPartida frame_nova_partida) {
		if (frame_nova_partida == null) {
			frame_nova_partida = new FrameNovaPartida();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_nova_partida.dispose();
		frame_configuracio1.main();
		frame_configuracio1.setControladorPresentacio(this);
		System.out.println("Entrenament :"
				+ frame_configuracio1.getcontroladorPresentacio()
						.getEntrenament());
		System.out.println("Es el mateix controlador pres. = "
				+ (this == frame_configuracio1.getcontroladorPresentacio()));
	}
	/**
	 * Fa l'intercanvi del frame de configuracio cap al de nova partida
	 * @param frame_configuracio1 Representa el frame de configuracio que volem tancar
	 */
	public void sincronitzacioConfiguracioNovaPartida(
			FrameConfiguracioPartida1 frame_configuracio1) {
		if (frame_nova_partida == null) {
			frame_nova_partida = new FrameNovaPartida();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		entrenament = false;
		frame_configuracio1.dispose();
		frame_nova_partida.main();
		frame_nova_partida.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio i el menu principal	
	 * @param frame_configuracio1 Representa el frame de configuracio que volem tancar
	 */
	public void sincronitzacioConfiguracioMenu(
			FrameConfiguracioPartida1 frame_configuracio1) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		entrenament= false;
		frame_configuracio1.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el menu de configuracio3  i el menu principal
	 * @param frame_configuracio3 Representa el menu de configuracio 3 que volem tancar
	 */
	public void sincronitzacioConfiguracio3Menu(
			FrameConfiguracioPartida3 frame_configuracio3) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		frame_configuracio3.dispose();
		entrenament = false;
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 i el menu principal
	 * @param frame_configuracio2 Representa el frame de configuracio de partida 2 que volem tancar
	 */
	public void sincronitzacioConfiguracio2Menu(
			FrameConfiguracioPartida2 frame_configuracio2) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_configuracio2 == null) {
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		frame_configuracio2.dispose();
		entrenament = false;
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 on juguen 2 cpu cap al menu principal
	 * @param frame_configuracio2cpu Representa el frame de configuracio partida 2 on juguen 2 cpu que volem tancar
	 */
	public void sincronitzacioConfiguracio2CPUMenu(
			FrameConfiguracioPartida2CPU frame_configuracio2cpu) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_configuracio2cpu == null) {
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		frame_configuracio2cpu.dispose();
		entrenament = false;
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 entre 2 persones cap al menu principal
	 * @param frame_configuracio2persones Representa el frame configuracio de partida 2 entre 2 persones que volem tancar
	 */
	public void sincronitzacioConfiguracio2PersonesMenu(
			FrameConfiguracioPartida2Persones frame_configuracio2persones) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_configuracio2persones == null) {
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		frame_configuracio2persones.dispose();
		entrenament = false;
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de  carregar partides cap al menu principal
	 * @param frame_carrega_partides Representa el frame de gestio de partides que volem tancar
	 */
	public void sincronitzacioCarregaPartidesMenu(
			FrameCarregaPartides frame_carrega_partides) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_carrega_partides == null) {
			frame_carrega_partides = new FrameCarregaPartides();
		}
		frame_carrega_partides.dispose();
		frame_menu_principal.setControladorPresentacio(this);
		frame_menu_principal.main();
	}
	/**
	 *  Fa l'intercanvi entre el menu principal cap al menu de gestio de partides guardades
	 * @param frame_menu_principal Representa el frame de menu principal que volem tancar
	 */
	public void sincronitzacioMenuCarregaPartides(
			FrameMenuPrincipal frame_menu_principal) {

		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_carrega_partides == null) {
			frame_carrega_partides = new FrameCarregaPartides();
		}
		frame_menu_principal.dispose();
		frame_carrega_partides.main();

		System.out.println("Usuari que vol carregar les partides: "
				+ usuari_actiu);

		ControladorPartidesGuardades ctrl_partides_guardades = new ControladorPartidesGuardades();
		List<PartidaGomoku> llista_partides = ctrl_partides_guardades
				.carregaPartides(usuari_actiu);
		System.out.println("A punt de mostrar la llista de partides");
		for (PartidaGomoku partida : llista_partides) {
			System.out.println(partida);
		}
		frame_carrega_partides.setControladorPresentacio(this);
		frame_carrega_partides.mostraLlistaPartides(llista_partides);

	}
	/**
	 * Fa l'intercanvi de el frame de configuracio de partida 2 cap al frame de configuracio de partida 1
	 * @param frame_configuracio2 Representa el frame de configuracio de partida 2 que volem tancar 
	 */
	public void sincronitzacioConfiguracio21(
			FrameConfiguracioPartida2 frame_configuracio2) {
		if (frame_configuracio2 == null) {
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio2.dispose();
		frame_configuracio1.main();
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 on juguen 2 cpu cap al frame de configuracio de partida 3
	 * @param frame_configuracio2cpu Representa el frame ed configuracio de partida 2 on juguen 2 cpu que volem tancar
	 */
	public void sincronitzacioConfiguracio2cpu3(
			FrameConfiguracioPartida2CPU frame_configuracio2cpu) {
		if (frame_configuracio2cpu == null) {
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		frame_configuracio2cpu.dispose();
		frame_configuracio3.main();
		frame_configuracio3.setControladorPresentacio(this);
		TipusUsuari[] tipus_usuari = frame_configuracio2cpu.getnivellmaquina();
		try {
			jugador_actiu = controlador_usuari
					.carregaUsuariSistema(tipus_usuari[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			jugador_oponent = controlador_usuari
					.carregaUsuariSistema(tipus_usuari[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Maquina 1:" + usuari_actiu + "Maquina 2:"
				+ jugador_oponent);
		frame_configuracio3.setTipusText();
		frame_configuracio3.setNomsusuaris();
	}
	/** 
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 on juguen 2 cpu cap al frame de configuracio de partida 1
	 * @param frame_configuracio2cpu Representa el frame de configuracio de partida 2 on juguen 2 cpu que volem tancar
	 */
	public void sincronitzacioConfiguracio2cpu1(
			FrameConfiguracioPartida2CPU frame_configuracio2cpu) {
		if (frame_configuracio2cpu == null) {
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio2cpu.dispose();
		frame_configuracio1.main();

	}
	/**
	 * Tanca la finestra de preparacio de partida i salta cap a la vista d'entrenament
	 * @param frame_configuracio3 Representa el frame de configuracio 3 que volem tancar
	 * @param color_jugador1 Indica el color del jugador 1
	 */
	public void iniciaEntrenament(
			FrameConfiguracioPartida3 frame_configuracio3, int color_jugador1) {
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if (tauler_entrenament == null) {
			tauler_entrenament = new FrameTaulerGUIEntrenament();
		}
		frame_configuracio3.dispose();
		int color = color_jugador1;
		if (color == 2) {
			Random aleatori = new Random();
			double aleatoridouble = aleatori.nextDouble();
			if (aleatoridouble >= 0.5)
				color = 0;
			else
				color = 1;
		}
		if (color == 0) {
			partida_en_curs = new PartidaGomoku(usuari_actiu, jugador_actiu,
					jugador_oponent, new TaulerGomoku(), "nova_partida");
		} else {
			partida_en_curs = new PartidaGomoku(usuari_actiu, jugador_oponent,
					jugador_actiu, new TaulerGomoku(), "nova_partida");
		}
		System.out.println(partida_en_curs);
		tauler_entrenament.main();
		tauler_entrenament.setPartida(partida_en_curs);
		tauler_entrenament.activa();
		tauler_entrenament.setControladorPresentacioTauler(this);
		tauler_entrenament.setControladorPresentacio(this);
		estat = EstatPartida.NO_FINALITZADA;
		this.juga_partida(tauler_partida);
	}
	/**
	 * Tanca la finestra de preparacio de partida 3 i inicia la vista del tauler principal de la partida
	 * @param frame_configuracio3 Representa el frame de configuracio de partida 3 que volem tancar
	 * @param color_jugador1 Indica del jugador 1
	 */
	public void iniciaPartida(FrameConfiguracioPartida3 frame_configuracio3,
			int color_jugador1) {
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if (tauler_partida == null) {
			tauler_partida = new FrameTaulerGUI();
		}
		frame_configuracio3.dispose();
		int color = color_jugador1;
		if (color == 2) {
			Random aleatori = new Random();
			double aleatoridouble = aleatori.nextDouble();
			if (aleatoridouble >= 0.5)
				color = 0;
			else
				color = 1;
		}
		if (color == 0) {
			partida_en_curs = new PartidaGomoku(usuari_actiu, jugador_actiu,
					jugador_oponent, new TaulerGomoku(), "nova_partida");
		} else {
			partida_en_curs = new PartidaGomoku(usuari_actiu, jugador_oponent,
					jugador_actiu, new TaulerGomoku(), "nova_partida");
		}
		System.out.println(partida_en_curs);
		tauler_partida.main();
		tauler_partida.setPartida(partida_en_curs);
		tauler_partida.activa();
		tauler_partida.setControladorPresentacioTauler(this);
		tauler_partida.setControladorPresentacio(this);
		estat = EstatPartida.NO_FINALITZADA;
		if ((partida_en_curs.getJugadorA().getTipus() != TipusUsuari.HUMA && partida_en_curs
				.getJugadorA().getTipus() != TipusUsuari.CONVIDAT)
				&& (partida_en_curs.getJugadorB().getTipus() != TipusUsuari.HUMA && partida_en_curs
						.getJugadorB().getTipus() != TipusUsuari.CONVIDAT)) {
			tauler_partida.bloquejaTauler();
		}

		tauler_partida.actualitzaDades();

		juga_partida(tauler_partida);
	}
	/**
	 * Prepara el controlador de partida en joc per a començar la partida i si el primer moviment correspon a un jugador cpu, fa que faci aquest moviment
	 * @param tauler_partida Representa la vista del tauler on es juga la partida
	 */
	private void juga_partida(FrameTaulerGUI tauler_partida) {
		System.out
				.println("La partida acaba de començar, si la maquina es negres moura");
		ctrl_en_joc = new ControladorPartidaEnJoc(partida_en_curs);
		if ((partida_en_curs.getJugadorA().getTipus() != TipusUsuari.HUMA
				&& partida_en_curs.getJugadorA().getTipus() != TipusUsuari.CONVIDAT && partida_en_curs
				.getTornsJugats() % 2 == 0) && entrenament == false) { // negres
																		// maquina
			int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
			ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_A, mov_ia[0], mov_ia[1]);
			tauler_partida.getTaulerActual().pinta(mov_ia[0], mov_ia[1],
					EstatCasella.JUGADOR_A);
			estat = partida_en_curs.comprovaEstatPartida(mov_ia[0], mov_ia[1]);
		} else if ((partida_en_curs.getJugadorB().getTipus() != TipusUsuari.HUMA
				&& partida_en_curs.getJugadorB().getTipus() != TipusUsuari.CONVIDAT && partida_en_curs
				.getTornsJugats() % 2 == 1) && entrenament == false) { // blanques
																		// maquina
			int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
			ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_B, mov_ia[0], mov_ia[1]);
			tauler_partida.getTaulerActual().pinta(mov_ia[0], mov_ia[1],
					EstatCasella.JUGADOR_B);
			estat = partida_en_curs.comprovaEstatPartida(mov_ia[0], mov_ia[1]);
		}

	}
	/**
	 * Gestiona totes les accions necesaries quan una partida ha arribat al seu fi
	 */
	public void fi_partida() {
		System.out.println(estat);
		System.out.println("Entro a fi partida");
		System.out.println(partida_en_curs);
		FrameError endgame = new FrameError();
		endgame.main();
		if (estat == EstatPartida.EMPAT) {
			endgame.MissatgeActiva("La partida ha acabat en un empat");
		} else if (estat == EstatPartida.GUANYA_JUGADOR_A) {
			if (partida_en_curs.getJugadorA().getTipus() == TipusUsuari.HUMA
					|| partida_en_curs.getJugadorA().getTipus() == TipusUsuari.CONVIDAT) {
				endgame.MissatgeActiva("El jugador "
						+ partida_en_curs.getJugadorA().getNom()
						+ " guanya la partida, enhorabona!");
			} else {
				endgame.MissatgeActiva(partida_en_curs.getJugadorA().getNom()
						+ " es el guanyador de la partida");
			}
		} else {
			if (partida_en_curs.getJugadorB().getTipus() == TipusUsuari.HUMA
					|| partida_en_curs.getJugadorB().getTipus() == TipusUsuari.CONVIDAT) {
				endgame.MissatgeActiva("El jugador "
						+ partida_en_curs.getJugadorB().getNom()
						+ " guanya la partida, enhorabona!");

			} else {
				endgame.MissatgeActiva(partida_en_curs.getJugadorB().getNom()
						+ " es el guanyador de la partida");

			}
		}
		ctrl_en_joc.actualitzaDadesFinalPartida();
		entrenament = false;
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 amb 2 jugadors humans cap al frame de configuracio de partida 1
	 * @param frame_configuracio2persones Representa el frame de configuracio de partida 2 amb 2 humans com a jugadors que volem tancar
	 */
	public void sincronitzacioConfiguracio2persones1(
			FrameConfiguracioPartida2Persones frame_configuracio2persones) {
		if (frame_configuracio2persones == null) {
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio2persones.dispose();
		frame_configuracio1.main();
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 cap al frame de configuracio 3
	 * @param frame_configuracio2 Representa el frame de configuracio de partida 2 que volem tancar
	 */
	public void sincronitzacioConfiguracio23(
			FrameConfiguracioPartida2 frame_configuracio2) {
		jugador_actiu = usuari_actiu;
		if (frame_configuracio2 == null) {
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		TipusUsuari tipus_oponent = frame_configuracio2.getTipusOponent();
		try {
			jugador_oponent = controlador_usuari
					.carregaUsuariSistema(tipus_oponent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame_configuracio2.dispose();
		System.out.println(tipus_oponent);
		System.out.println("user1: " + jugador_actiu + "oponent: "
				+ jugador_oponent);
		System.out.println("Entrenament: " + entrenament);
		frame_configuracio3.main();
		frame_configuracio3.setControladorPresentacio(this);
		frame_configuracio3.setTipusText();
		frame_configuracio3.setNomsusuaris();
	}
	/**
	 * Fa l'intercanvi de la vista de escollir el tipus d'estadistiques que volem veure cap a la vista de les estadistiques individuals
	 * @param frame_estadistiques Representa el frame on escollim el tipus d'estadistiques que volem veure que volem tancar
	 */
	public void sincronitzacioAccedirEstadistiquesIndividuals(
			FrameAccedirEstadistiques frame_estadistiques) {
		if (frame_estadistiques == null) {
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		if (frame_estadistiques_indivuals == null) {
			frame_estadistiques_indivuals = new FrameEstadistiquesIndividuals();
		}
		frame_estadistiques_indivuals.main();
		System.out.println("Usuari actiu abans de crear els records: "
				+ usuari_actiu);
		int[] records = new int[35];
		int i = 0;
		ControladorRecordsIndividuals records_individuals = new ControladorRecordsIndividuals(
				usuari_actiu);
		for (CriteriRecords criteri : CriteriRecords.values()) {
			records[i] = records_individuals.getLlistaRecords(criteri);
			++i;
		}
		frame_estadistiques_indivuals.carregaestadistiques(records);
	}
	/**
	 * Fa l'intercanvi entre el frame de escollir el tipus d'estadistiques que volem veure amb el el frame que mostra les estadistiques globals
	 * @param frame_estadistiques Representa el frame que mostra els tipus d'estadistiques que podem veure que volem tancar
	 */
	public void sincronitzacioAccedirEstadistiquesGlobals(
			FrameAccedirEstadistiques frame_estadistiques) {
		if (frame_estadistiques == null) {
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		if (frame_estadistiques_globals == null) {
			frame_estadistiques_globals = new FrameEstadistiquesGlobals();
		}
		frame_estadistiques_globals.main();
		List<List<String[]>> records = new ArrayList<List<String[]>>();
		List<String[]> recordcriteri;
		ControladorRecordsGlobals controlador_records_globals = new ControladorRecordsGlobals();
		for (CriteriRecords criteri : CriteriRecords.values()) {
			recordcriteri = (controlador_records_globals
					.getLlistaRecords(criteri));
			records.add(recordcriteri);
		}
		frame_estadistiques_globals.carregaestadistiques(records);

	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 3 cap al frame de configuracio de partida 1
	 * @param frame_configuracio3
	 */
	public void sincronitzacioConfiguracio31(
			FrameConfiguracioPartida3 frame_configuracio3) {
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio3.dispose();
		frame_configuracio1.main();
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 amb 2 humans cap al frame de configuracio de partida 3
	 * @param frame_configuracio2persones Representa el frame de configuracio de partida 2 amb 2 humans
	 */
	public void sincronitzacioConfiguracio2Persones3(
			FrameConfiguracioPartida2Persones frame_configuracio2persones) {
		jugador_actiu = usuari_actiu;
		if (frame_configuracio3 == null) {
			frame_configuracio3 = new FrameConfiguracioPartida3();
		}
		if (frame_configuracio2persones == null) {
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		frame_configuracio2persones.dispose();
		frame_configuracio3.main();
		frame_configuracio3.setNomsusuaris();
		frame_configuracio3.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 1 cap al frame de configuracio de partida 2
	 * @param frame_configuracio1 Representa el frame de configuracio de partida 1 que volem tancar
	 */
	public void sincronitzacioConfiguracio12(
			FrameConfiguracioPartida1 frame_configuracio1) {
		if (frame_configuracio2 == null) {
			frame_configuracio2 = new FrameConfiguracioPartida2();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		jugador_actiu = usuari_actiu;
		frame_configuracio1.dispose();
		frame_configuracio2.main();
		frame_configuracio2.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 1 cap al frame de configuracio de partida 2 entre 2 cpu
	 * @param frame_configuracio1 Representa el frame de configuracio partida 1 que volem tancar
	 */
	public void sincronitzacioConfiguracio12CPU(
			FrameConfiguracioPartida1 frame_configuracio1) {
		if (frame_configuracio2cpu == null) {
			frame_configuracio2cpu = new FrameConfiguracioPartida2CPU();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_configuracio2cpu.main();
		frame_configuracio2cpu.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio 1 cap al frame de configuracio 2 entre 2 humans
	 * @param frame_configuracio1 Representa el frame de configuracio de partida 1 que volem tancar
	 */
	public void sincronitzacioConfiguracio12Persones(
			FrameConfiguracioPartida1 frame_configuracio1) {
		if (frame_configuracio2persones == null) {
			frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
		}
		if (frame_configuracio1 == null) {
			frame_configuracio1 = new FrameConfiguracioPartida1();
		}
		frame_configuracio1.dispose();
		frame_configuracio2persones.main();
		jugador_actiu = usuari_actiu;
	}
	/**
	 * Fa l'intercanvi entre el frame de menu principal cap al frame de nova partida
	 * @param frame_menu_principal Representa el frame de menu principal que volem tancar
	 */
	public void sincronitzacioMenuNovaPartida(
			FrameMenuPrincipal frame_menu_principal) {
		if (frame_nova_partida == null) {
			frame_nova_partida = new FrameNovaPartida();
		}
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frame_menu_principal.dispose();
		frame_nova_partida.main();
		frame_nova_partida.setControladorPresentacio(this);

	}
	/**
	 * Fa l'intercanvi entre el frame de menu principal cap al frame  de benvinguda
	 * @param frame_menu_principal Representa el frame de menu principal que volem tancar
	 */
	public void sincronitzacioMenuBenvingut(
			FrameMenuPrincipal frame_menu_principal) {
		usuari_actiu = null;
		if (frame_benvingut == null) {
			frame_benvingut = new FrameBenvingut();
		}
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frame_menu_principal.dispose();
		frame_benvingut.main();
	}
	/**
	 * Fa l'intercanvi entre el frame de registre cap al frame de benvinguda
	 * @param frame_registrar Representa el frame de registre que volem tancar
	 */
	public void sincronitzacioRegistrarBenvingut(FrameRegistrar frame_registrar) {
		if (frame_benvingut == null) {
			frame_benvingut = new FrameBenvingut();
		}
		if (frame_registrar == null) {
			frame_registrar = new FrameRegistrar();
		}
		frame_registrar.dispose();
		frame_benvingut.main();
	}
	/**
	 * Fa l'intercanvi entre el frame de identificacio cap al menu principal realitzant un intent de identificar-se en el sistema,
	 * si l'usuari existeix en el sistema i la contrasenya es correcta, podra accedir-hi, altrement li apareixera un missatge d'error
	 * @param frame_identificacio Representa el frame de identificacio que volem tancar
	 * @param alies Indica l'alies amb el que el usuari vol accedir al sistema
	 * @param contrasenya Indica la contrasenya amb el que el usuari vol accedir al sistema
	 */
	public void Identificarse(FrameIdentificacio frame_identificacio,
			String alies, String contrasenya) {
		System.out.println("contrasenya : " + contrasenya);
		boolean excepcio = false;
		try {
			System.out.println("dades: " + alies + " - " + contrasenya);
			usuari_actiu = controlador_usuari.identificaUsuari(alies,
					contrasenya);
			System.out.println("Usuari actiu: " + usuari_actiu);
		} catch (UsuariNoExisteix e) {
			System.out.println(e.getMessage());
			frame_identificacio.NetejaAliesContrasenya();
			excepcio = true;
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error
					.MissatgeActiva("L'usuari introduit no esta registrat en el sistema.");

		} catch (ContrasenyaIncorrecta e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			frame_identificacio.netejaContrasenya();
			excepcio = true;
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error
					.MissatgeActiva("La contrasenya introduida no es la correcte per a aquest usuari");

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			excepcio = true;
			System.out.println("Illegar argument");
		} catch (ContrasenyaInvalida e) {
			frame_identificacio.netejaContrasenya();
			System.out.println(e.getMessage());
			excepcio = true;
			FrameError error = new FrameError();
			error.main();
			error.MissatgeActiva("La contrasenya introduida conte caràcters invalids");
		}
		if (excepcio == false) {
			if (frame_menu_principal == null) {
				frame_menu_principal = new FrameMenuPrincipal();
			}
			if (frame_identificacio == null) {
				frame_identificacio = new FrameIdentificacio();
			}
			// System.out.println(usuari_actiu);
			// System.out.println(frame_identificacio.getUsuariActual());
			frame_identificacio.dispose();
			frame_menu_principal.main();
			frame_menu_principal.setControladorPresentacio(this);
			System.out.println("Usuari actiu 2: " + usuari_actiu);
		}
	}
	/**
	 * Fa l'intercanvi entre el frame de configuracio de partida 2 amb 2 jugadors humans i intenta identificar a un segon jugador, que si ha introduit les seves dades correctament podra accedir com a jugador secundari al sistema
	 * @param frame_configuracio2persones Representa el frame de configuracio 2 de 2 humans que volem tancar
	 * @param alies  Indica l'alies amb el que el segon usuari vol accedir al sistema
	 * @param contrasenya Indica la contrasenya amb la que el segon usuari vol accedir al sistema
	 */
	public void IdentificarOponent(
			FrameConfiguracioPartida2Persones frame_configuracio2persones,
			String alies, String contrasenya) {
		jugador_actiu = usuari_actiu;
		boolean excepcio = false;
		try {
			if (alies == "Convidat") {
				try {
					jugador_oponent = controlador_usuari
							.carregaUsuariSistema(TipusUsuari.CONVIDAT);
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				jugador_oponent = controlador_usuari.identificaUsuari(alies,
						contrasenya);
			}
		} catch (UsuariNoExisteix e) {
			frame_configuracio2persones.NetejaAliesContrasenya();
			excepcio = true;
			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error
					.MissatgeActiva("L'usuari introduit no esta registrat en el sistema.");

		} catch (ContrasenyaIncorrecta e) {
			frame_configuracio2persones.NetejaContrasenya();
			excepcio = true;

			FrameError frame_error = new FrameError();
			frame_error.main();
			frame_error
					.MissatgeActiva("La contrasenya introduida no es la correcte per a aquest usuari");
		} catch (IllegalArgumentException e) {

		} catch (ContrasenyaInvalida e) {
			frame_configuracio2persones.NetejaContrasenya();
			FrameError frame_error = new FrameError();
			frame_error.main();
			excepcio = true;
			frame_error
					.MissatgeActiva("La contrasenya introduida conte caràcters invalids");
			frame_configuracio2persones.NetejaContrasenya();
		}
		if (excepcio == false) {
			if (frame_configuracio2persones == null) {
				frame_configuracio2persones = new FrameConfiguracioPartida2Persones();
			}
			if (frame_configuracio3 == null) {
				frame_configuracio3 = new FrameConfiguracioPartida3();
			}
			frame_configuracio2persones.dispose();
			frame_configuracio3.main();
			frame_configuracio3.setControladorPresentacio(this);
			System.out.println("usuari_acutal:" + jugador_actiu
					+ "usuari oponent: " + jugador_oponent);
			frame_configuracio3.setTipusText();
			frame_configuracio3.setNomsusuaris();
		}
	}
	/**
	 * Fa l'intercanvi del frame de registre cap al menu principal si el registre es dur a terme correctament. Altrement apareixera un missatge d'error.
	 * @param frame_registrar Representa el frame de registre que volem tancar
	 * @param nom Indica el nom d'usuari amb el que l'usuari vol registrar-se en el sistema
	 * @param contrasenya Indica la contrasenya amb la que l'usuari vol registrar-se en el sistema
	 */
	public void RegistrarJugador(FrameRegistrar frame_registrar, String nom,
			String contrasenya) {
		boolean excepcio = false;
		try {
			usuari_actiu = controlador_usuari.registraUsuari(nom, contrasenya);
		} catch (ContrasenyaInvalida e) {
			FrameError contrasenyainvalida = new FrameError();
			contrasenyainvalida.main();
			contrasenyainvalida
					.MissatgeActiva("La contrasenya introduida conte caràcters invalids");
			frame_registrar.Netejapasswords();
			excepcio = true;
		} catch (IOException e) {
			excepcio = true;
			System.out.println("Fallo de IO");

		} catch (UsuariJaExisteix e) {
			FrameError usuarijaexisteix = new FrameError();
			usuarijaexisteix.main();
			usuarijaexisteix
					.MissatgeActiva("El nom d'usuari introduit ja esta actualment registrat al sistema");
			frame_registrar.Netejatot();
			excepcio = true;
		}

		if (excepcio == false) {
			if (frame_menu_principal == null) {
				frame_menu_principal = new FrameMenuPrincipal();
			}
			if (frame_registrar == null) {
				frame_registrar = new FrameRegistrar();
			}
			frame_registrar.dispose();
			frame_menu_principal.main();
			System.out.println(usuari_actiu);
		}
	}
	/**
	 * Activa la vista del frame de benvinguda
	 */
	public void InicialitzarPresentacio() {
		frame_benvingut = new FrameBenvingut();
		frame_benvingut.main();
	}
	/**
	 * Fa l'intercanvi entre els frame d'estadistiques cap al menu principal
	 * @param frame_estadistiques Representa el frame de escollir el tipus d'estadistiques que volem veure que volem tancar
	 */
	public void sincronitzacioEstadistiquesMenu(
			FrameAccedirEstadistiques frame_estadistiques) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_estadistiques == null) {
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		frame_estadistiques.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame de benvinguda cap al frame del menu principal
	 * @param frame_benvingut Representa el frame de benvinguda que volem tancar
	 */
	public void sincronitzacioBenvingutMenu(FrameBenvingut frame_benvingut) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_benvingut == null) {
			frame_benvingut = new FrameBenvingut();
		}
		try {
			usuari_actiu = controlador_usuari
					.carregaUsuariSistema(TipusUsuari.CONVIDAT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		frame_benvingut.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
		frame_menu_principal.menuConvidat();
	}
	/**
	 * Fa l'intercanvi entre el frame del menu principal cap al frame d'estadistiques
	 * @param frame_menu_principal Representa el frame de menu principal que volem tancar
	 */
	public void sincronitzacioMenuEstadistiques(
			FrameMenuPrincipal frame_menu_principal) {
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		if (frame_estadistiques == null) {
			frame_estadistiques = new FrameAccedirEstadistiques();
		}
		frame_menu_principal.dispose();
		frame_estadistiques.main();
		frame_estadistiques.setControladorPresentacio(this);
	}
	/**
	 * Creadora per defecte de un controlador de presentacio
	 */
	public ControladorPresentacio() {
		controlador_usuari = new ControladorUsuari();	
	}
	/**
	 * Metode per modificar el jugador secundari
	 * @param usuari_oponent Indica el nou jugador secundari
	 */
	public void setUsuari_oponent(UsuariGomoku usuari_oponent) {
		this.jugador_oponent = usuari_oponent;
	}
	/**
	 * Metode que crida el controlador de documentacio per a mostrar el manual d'usuari
	 */
	public void MostraPDF() {
		ControladorDocumentacio.obreManualPDF();
	}
	/**
	 * Metode que permet obetnir el usuari actualment identificat en el sistema
	 * @return Retorna l'usuari actualment loguejat en el sistema
	 */
	public UsuariGomoku getUsuariActual() {
		return usuari_actiu;
	}
	/**
	 * Metode per obtenir la llista de partides guardades al sistema
	 * @return La llista de partides guardades al sistema
	 */
	public List<PartidaGomoku> getLlistaPartides() {
		return new ControladorPartidesGuardades().carregaPartides(usuari_actiu);
	}
	/**
	 * Metode per obtenir el jugador secundari actualment
	 * @return Retorna el jugador secundari per a la seguent partida
	 */
	public UsuariGomoku getOponentActual() {
		return jugador_oponent;
	}
	
	/**
	 * 
	 * @param nom
	 * @return
	 */
	
	public boolean setCanviNom(String nom) {
		return true;
	}
	/**
	 * Metode que gestiona la visibilitat dels moviments de les maquines aixi com la paritat dels seus torns durant una partida  entre dues maquines
	 */
	public void juga_maquines() {
		if(partida_en_curs.estaFinalitzada()) return ;
		if (partida_en_curs.getJugadorA().getTipus() == TipusUsuari.HUMA
				|| partida_en_curs.getJugadorA().getTipus() == TipusUsuari.CONVIDAT
				|| partida_en_curs.getJugadorB().getTipus() == TipusUsuari.HUMA
				|| partida_en_curs.getJugadorB().getTipus() == TipusUsuari.CONVIDAT)
			return;

		int[] ultim_moviment;
		int[] mov_ia;
		boolean finalitzada;
		if (partida_en_curs.getTornsJugats() % 2 == 0) {
			if (estat == EstatPartida.NO_FINALITZADA) {
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_A, mov_ia[0],
						mov_ia[1]);
				tauler_partida.pinta(mov_ia[0], mov_ia[1],
						EstatCasella.JUGADOR_A);
				tauler_partida.actualitzaDades();
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida(ultim_moviment[0],
						ultim_moviment[1]);
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if (finalitzada) {
					fi_partida();
					return;
				}
			}
			if (estat == EstatPartida.NO_FINALITZADA) {
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_B, mov_ia[0],
						mov_ia[1]);
				tauler_partida.pinta(mov_ia[0], mov_ia[1],
						EstatCasella.JUGADOR_B);
				tauler_partida.actualitzaDades();
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida(ultim_moviment[0],
						ultim_moviment[1]);
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if (finalitzada) {
					fi_partida();
					return;
				}
			}
		} else {
			if (estat == EstatPartida.NO_FINALITZADA) {
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_B, mov_ia[0],
						mov_ia[1]);
				tauler_partida.pinta(mov_ia[0], mov_ia[1],
						EstatCasella.JUGADOR_B);
				tauler_partida.actualitzaDades();
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida(ultim_moviment[0],
						ultim_moviment[1]);
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if (finalitzada) {
					fi_partida();
					return;
				}
			}
			if (estat == EstatPartida.NO_FINALITZADA) {
				mov_ia = ctrl_en_joc.getMovimentMaquina();
				ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_A, mov_ia[0],
						mov_ia[1]);
				tauler_partida.pinta(mov_ia[0], mov_ia[1],
						EstatCasella.JUGADOR_A);
				tauler_partida.actualitzaDades();
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida(ultim_moviment[0],
						ultim_moviment[1]);
				System.out.println(estat);
				finalitzada = partida_en_curs.estaFinalitzada();
				if (finalitzada) {
					fi_partida();
					return;
				}
			}

		}
	}
	/**
	 * Metode per comprovar l'usuari que actualment es el jugador 1
	 * @return Retorna el usuari que actualment es el jugador 1
	 */
	public UsuariGomoku getJugadorActual() {
		return jugador_actiu;
	}
	/**
	 * Metode que gestiona quan l'usuari pot o no clickar al tauler per fer un moviment i gestiona tambe els moviments amb els que la maquina contesta, permetent la seva visibilitat al tauler. Es que gestiona principalment l'accio a les partides, es invocat quan es prem alguna interseccio del tauler visual
	 * @param coord Coordenades on s'ha pitjat la interseccio
	 * @return Retorna un boolea que indica si el moviment s'ha pogut dur a terme existosament
	 */
	public boolean ferMoviment(int[] coord) {
		if (partida_en_curs.estaFinalitzada())
			return false;
		if (entrenament
				|| (partida_en_curs.getJugadorA().getTipus() == TipusUsuari.HUMA || partida_en_curs
						.getJugadorA().getTipus() == TipusUsuari.CONVIDAT)
				|| (partida_en_curs.getJugadorB().getTipus() == TipusUsuari.HUMA || partida_en_curs
						.getJugadorB().getTipus() == TipusUsuari.CONVIDAT)) {
			int[] ultim_moviment;
			if (estat == EstatPartida.NO_FINALITZADA) {
				ultim_moviment = ctrl_en_joc.getUltimMoviment();
				estat = partida_en_curs.comprovaEstatPartida(ultim_moviment[0],
						ultim_moviment[1]);
				if (estat != EstatPartida.NO_FINALITZADA) {
					System.out.println(estat);
					return false;
				}
			}
			int fila = coord[0];
			int columna = coord[1];
			System.out.println("Clicat: " + fila + " " + columna);
			if (partida_en_curs.getTornsJugats() % 2 == 0) {
				try {
					ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_A, fila, columna); // es
																					// mouen
																					// negres
				} catch (Exception e) {
					System.out.println(e.getMessage());
					if (estat != EstatPartida.NO_FINALITZADA) {
						if (entrenament == false) {
							fi_partida();
						}
					}
					return false;
				}
				estat = partida_en_curs.comprovaEstatPartida(fila, columna);
				if (entrenament) {
					tauler_entrenament.pinta(fila, columna,
							EstatCasella.JUGADOR_A);
				} else {
					tauler_partida.pinta(fila, columna, EstatCasella.JUGADOR_A);
					tauler_partida.actualitzaDades();
				}
				if (estat == EstatPartida.NO_FINALITZADA
						&& partida_en_curs.getJugadorB().getTipus() != TipusUsuari.CONVIDAT
						&& partida_en_curs.getJugadorB().getTipus() != TipusUsuari.HUMA
						&& entrenament == false) {
					int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
					ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_B, mov_ia[0],
							mov_ia[1]);
					tauler_partida.pinta(mov_ia[0], mov_ia[1],
							EstatCasella.JUGADOR_B);
					tauler_partida.actualitzaDades();
				} else if (estat != EstatPartida.NO_FINALITZADA) {
					 fi_partida();
					 return false;
				}
			} else {
				try {
					ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_B, fila, columna);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					if (estat != EstatPartida.NO_FINALITZADA) {
						if (entrenament == false) {
							fi_partida();
						}
					}
					return false;
				}
				if (entrenament) {
					tauler_entrenament.pinta(fila, columna,
							EstatCasella.JUGADOR_B);
				} else {
					tauler_partida.pinta(fila, columna, EstatCasella.JUGADOR_B);
					tauler_partida.actualitzaDades();
				}
				if (estat == EstatPartida.NO_FINALITZADA
						&& partida_en_curs.getJugadorA().getTipus() != TipusUsuari.CONVIDAT
						&& partida_en_curs.getJugadorA().getTipus() != TipusUsuari.HUMA
						&& entrenament == false) {
					int[] mov_ia = ctrl_en_joc.getMovimentMaquina();
					ctrl_en_joc.mouFitxa(EstatCasella.JUGADOR_A, mov_ia[0],
							mov_ia[1]);
					tauler_partida.pinta(mov_ia[0], mov_ia[1],
							EstatCasella.JUGADOR_A);
					tauler_partida.actualitzaDades();
				} else if (estat != EstatPartida.NO_FINALITZADA) {
					// fi_partida();
				}
			}
			ultim_moviment = ctrl_en_joc.getUltimMoviment();
			estat = partida_en_curs.comprovaEstatPartida(ultim_moviment[0],
					ultim_moviment[1]);
		}
		if (estat != EstatPartida.NO_FINALITZADA) {
			if (entrenament == false) {
				fi_partida();
			}
		}
		return true;
	}
	/**
	 * Metode invocat al seleccionar el boto de partida guarda i que s'ocupa de gestionar amb la capa de domini el guardat de la partida
	 * @param partida Indica la partida que volem guardar
	 */
	public void guardaPartida(PartidaGomoku partida) {
		if (partida.getJugadorPrincipal().getTipus() == TipusUsuari.CONVIDAT) {
			FrameError noesguarda = new FrameError();
			noesguarda.main();
			noesguarda
					.MissatgeActiva("Els usuaris Convidats no poden guardar partides, registris al sistema per a accedir a aquesta funcionalitat");
		} else {
			ControladorPartidesGuardades controlador_partides = new ControladorPartidesGuardades();
			controlador_partides.guardaPartida(partida);
		}
	}
	/**
	 * Carrega una partida i la mostra llesta per a ser jugada
	 * @param frame_carrega_partides Representa el frame de carrega partides que volem tancar
	 * @param partida Indica la partida que volem carregar per continuar jugant
	 */
	public void carregaPartida(FrameCarregaPartides frame_carrega_partides,
			PartidaGomoku partida) {
		if (tauler_partida == null) {
			tauler_partida = new FrameTaulerGUI();
		}
		frame_carrega_partides.dispose();
		if (partida.estaFinalitzada())
			estat = EstatPartida.GUANYA_JUGADOR_A;
		else
			estat = EstatPartida.NO_FINALITZADA;
		partida_en_curs = partida;
		tauler_partida.main();
		tauler_partida.setPartida(partida_en_curs);
		tauler_partida.activa();
		tauler_partida.setControladorPresentacioTauler(this);
		tauler_partida.setControladorPresentacio(this);
		tauler_partida.pinta();
		tauler_partida.actualitzaDades();
		System.out.println("Partida : " + partida);
		juga_partida(tauler_partida);

	}
	/**
	 * Metode per obtenir la partida en curs
	 * @return Retorna la partida que s'esta jugant actualment o que s'esta preparant per a ser jugada
	 */
	public PartidaGomoku getPartidaactual() {
		return partida_en_curs;
	}
	/**
	 * Fa l'intercanvi entre el frame de jugar una partida cap al menu principal
	 * @param provaTaulerGUI Representa el frame del tauler on juguem una partide que volem tancar
	 */
	public void sincronitzacioPartidaMenu(FrameTaulerGUI provaTaulerGUI) {
		if (provaTaulerGUI == null) {
			provaTaulerGUI = new FrameTaulerGUI();
		}
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		provaTaulerGUI.dispose();
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}
	/**
	 * Fa l'intercanvi entre el frame d'entrenament cap al de la partida
	 * @param tauler Indica el tauler en el seu estat actual, un cop finalitzar l'entrenament
	 */
	public void sincroEntrenamentPartida(FrameTaulerGUIEntrenament tauler) {
		if (tauler == null) {
			tauler = new FrameTaulerGUIEntrenament();
		}
		if (tauler_partida == null) {
			tauler_partida = new FrameTaulerGUI();
		}
		entrenament = false;
		tauler.dispose();
		tauler_partida.main();
		tauler_partida.setPartida(partida_en_curs);
		tauler_partida.activa();
		tauler_partida.setControladorPresentacioTauler(this);
		tauler_partida.setControladorPresentacio(this);
		tauler_partida.pinta();
		tauler_partida.actualitzaDades();
		juga_partida(tauler_partida);
	}
	/**
	 * Metode per indicar si estem o no en mode entrenament
	 * @param entrenament Indica si anem a jugar o no una partida d'entrenament
	 */
	public void setEntrenament(boolean entrenament) {
		this.entrenament = entrenament;
	}
	/**
	 * Metode que retorna si la partida que ens disposem a jugar es o no d'entrenament
	 * @return Retorna el boolea que indica si la partida es o no d'entrenament
	 */
	public boolean getEntrenament() {
		return entrenament;
	}
	/**
	 * Canvia el nom de una partida i actualitza les dades mostrades per pantalla
	 * @param partida Indica la partida a la qual volem canviar el nom
	 * @param nou_nom Indica el nou nom que volem que tingui la partida
	 */
	public void sincronitzacioCanviNom(PartidaGomoku partida, String nou_nom) {
		ControladorPartidesGuardades partides = new ControladorPartidesGuardades();
		partida.setNom(nou_nom);
		partides.guardaPartida(partida);
		List<PartidaGomoku> partides_nou_nom = partides
				.carregaPartides(usuari_actiu);
		frame_carrega_partides.mostraLlistaPartides(partides_nou_nom);
	}
	/**
	 * Metode per esborrar una partida de la llista de partides guardades
	 * @param partida Indica la partida que volem esborrar
	 */
	public void esborraPartida(PartidaGomoku partida) {
		ControladorPartidesGuardades partides = new ControladorPartidesGuardades();
		partides.esborraPartida(partida);
		List<PartidaGomoku> partides_actualitzades = partides
				.carregaPartides(usuari_actiu);
		frame_carrega_partides.mostraLlistaPartides(partides_actualitzades);
	}
	/**
	 * Fa l'intercanvi entre el frame d'entrenament cap al frame  menu principal
	 * @param frameTaulerGUIEntrenament Representa el frame de entrenament que volem tancar
	 */
	public void sincronitzacioEntrenamentMenu(
			FrameTaulerGUIEntrenament frameTaulerGUIEntrenament) {
		if (frameTaulerGUIEntrenament == null) {
			frameTaulerGUIEntrenament = new FrameTaulerGUIEntrenament();
		}
		if (frame_menu_principal == null) {
			frame_menu_principal = new FrameMenuPrincipal();
		}
		frameTaulerGUIEntrenament.dispose();
		entrenament = false;
		frame_menu_principal.main();
		frame_menu_principal.setControladorPresentacio(this);
	}

}
