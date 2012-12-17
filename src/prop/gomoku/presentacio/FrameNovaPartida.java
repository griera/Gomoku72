package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameNovaPartida extends JFrame {
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameNovaPartida() {
		controlador_presentacio= new ControladorPresentacio();
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku72 - Nova Partida");
		setLayout(new GroupLayout());
		add(getJButton2(), new Constraints(new Leading(236, 85, 10, 10), new Leading(244, 27, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(85, 185, 10, 10), new Leading(95, 37, 10, 10)));
		add(getJButton1(), new Constraints(new Leading(84, 186, 12, 12), new Leading(167, 38, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(36, 10, 10), new Leading(54, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(68, 10, 10), new Leading(12, 12, 12)));
		setSize(339, 289);
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Enrere");
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
			jButton1.setText("Entrenament");
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
			jButton0.setText("Partida Ràpida");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Si us plau, seleccioni la modalitat de la partida:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("<html><font size=5>Selecció de la modalitat</font></html>");
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
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public void main() {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FrameNovaPartida frame = new FrameNovaPartida();
				frame.setDefaultCloseOperation(FrameNovaPartida.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setResizable( false );
			}
		});
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioNovaPartidaMenu(this);
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioNovaPartidaConfiguracio(this);
	}
	public void setControladorPresentacio(ControladorPresentacio controlador_presentacio){
		this.controlador_presentacio=controlador_presentacio;
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.setEntrenament( true );
		controlador_presentacio.sincronitzacioNovaPartidaConfiguracio( this );
	}
}
