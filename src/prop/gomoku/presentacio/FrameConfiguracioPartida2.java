package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import prop.gomoku.domini.models.TipusUsuari;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameConfiguracioPartida2 extends JFrame {
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JRadioButton jRadioButton0;
	private JRadioButton jRadioButton1;
	private JRadioButton jRadioButton2;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameConfiguracioPartida2() {
		controlador_presentacio = new ControladorPresentacio();
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku - Configuració de Partida Ràpida");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(59, 10, 10), new Leading(
				20, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(25, 10, 10), new Leading(
				62, 12, 12)));
		add(getJRadioButton0(), new Constraints(new Leading(25, 12, 12),
				new Leading(98, 10, 10)));
		add(getJRadioButton1(), new Constraints(new Leading(25, 12, 12),
				new Leading(140, 12, 12)));
		add(getJRadioButton2(), new Constraints(new Leading(25, 12, 12),
				new Leading(182, 12, 12)));
		add(getJButton0(), new Constraints(new Trailing(12, 12, 12),
				new Trailing(12, 12, 12)));
		add(getJButton1(), new Constraints(new Trailing(105, 12, 12),
				new Trailing(12, 90, 90)));
		add(getJButton2(), new Constraints(new Trailing(198, 12, 12),
				new Trailing(12, 90, 90)));
		setSize(454, 267);
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Menú Principal");
			jButton2.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton2MouseMouseClicked(event);
				}
			});
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Enrere");
			jButton1.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton1MouseMouseClicked(event);
				}
			});
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Següent");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setText("Fàcil");
			jRadioButton2.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton2MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton2;
	}

	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setText(" Mitjà");
			jRadioButton1.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton1MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton1;
	}

	private JRadioButton getJRadioButton0() {
		if (jRadioButton0 == null) {
			jRadioButton0 = new JRadioButton();
			jRadioButton0.setText("Difícil");
			jRadioButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton0MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Seleccioni el Nivell de dificultat de la màquina:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("<html><font size=5>Configuració de la partida ràpida</font></html>");
		}
		return jLabel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public void main() {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FrameConfiguracioPartida2 frame = new FrameConfiguracioPartida2();
				frame.setDefaultCloseOperation(FrameConfiguracioPartida2.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void jRadioButton0MouseMouseClicked(MouseEvent event) {
		if(jRadioButton0.isSelected()){
			jRadioButton1.setSelected(false);
			jRadioButton2.setSelected(false);
		}
	}

	private void jRadioButton1MouseMouseClicked(MouseEvent event) {
		if(jRadioButton1.isSelected()){
			jRadioButton0.setSelected(false);
			jRadioButton2.setSelected(false);
		}
	}

	private void jRadioButton2MouseMouseClicked(MouseEvent event) {
		if(jRadioButton2.isSelected()){
			jRadioButton0.setSelected(false);
			jRadioButton1.setSelected(false);
		}
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio2Menu(this);
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio21(this);
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio23(this);
	}

	public TipusUsuari getTipusOponent() {
		TipusUsuari tipus_maquina = TipusUsuari.FACIL;
		if(jRadioButton0.isSelected()) {
			tipus_maquina=TipusUsuari.DIFICIL;
		}
		else if(jRadioButton1.isSelected()){
			tipus_maquina=TipusUsuari.MITJA;
		}
		else if(jRadioButton2.isSelected()){
			tipus_maquina=TipusUsuari.FACIL;
		}
		return tipus_maquina;
	}


}
