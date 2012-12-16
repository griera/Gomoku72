package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameAccedirEstadistiques extends JFrame {
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameAccedirEstadistiques() {
		initComponents();
	}

	private void initComponents() {
		controlador_presentacio = new ControladorPresentacio();
		setTitle("Gomoku - Rànquings globals i rècords individuals");
		setLayout(new GroupLayout());
		add(getJButton1(), new Constraints(new Leading(119, 177, 10, 10), new Leading(25, 10, 10)));
		add(getJButton2(), new Constraints(new Leading(119, 176, 12, 12), new Leading(69, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(119, 176, 12, 12), new Leading(113, 12, 12)));
		setSize(414, 188);
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Estadístiques globals");
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
			jButton1.setText("Estadístiques individuals");
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
			jButton0.setText("Menú Principal");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
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
				FrameAccedirEstadistiques frame = new FrameAccedirEstadistiques();
				frame.setDefaultCloseOperation(FrameAccedirEstadistiques.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setResizable( false );
			}
		});
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioEstadistiquesMenu(this);
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioAccedirEstadistiquesIndividuals(this);
		System.out.println(controlador_presentacio.getUsuariActual());
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioAccedirEstadistiquesGlobals(this);
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;
	}

}
