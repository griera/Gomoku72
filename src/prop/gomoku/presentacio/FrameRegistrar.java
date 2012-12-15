package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameRegistrar extends JFrame {
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JButton jButton0;
	private JButton jButton1;
	private JLabel jLabel2;
	private JTextField jTextField0;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JPasswordField jPasswordField0;
	private JPasswordField jPasswordField1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public FrameRegistrar() {
		controlador_presentacio = new ControladorPresentacio();
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku - Registre de nou usuari");
		setLayout(new GroupLayout());
		add(getJButton0(), new Constraints(new Trailing(12, 12, 12),
				new Trailing(12, 12, 12)));
		add(getJButton1(), new Constraints(new Trailing(111, 12, 12),
				new Trailing(12, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(12, 12, 12), new Leading(
				79, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(12, 250, 12, 12),
				new Leading(101, 50, 50)));
		add(getJLabel3(), new Constraints(new Leading(12, 12, 12), new Leading(
				133, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(12, 12, 12), new Leading(
				181, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(12, 12, 12), new Leading(
				57, 88, 88)));
		add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(
				17, 10, 10)));
		add(getJPasswordField0(), new Constraints(new Leading(12, 250, 12, 12),
				new Leading(155, 12, 12)));
		add(getJPasswordField1(), new Constraints(new Leading(12, 250, 12, 12),
				new Leading(203, 12, 12)));
		setSize(396, 279);
	}

	private JPasswordField getJPasswordField1() {
		if (jPasswordField1 == null) {
			jPasswordField1 = new JPasswordField();
			jPasswordField1.setEchoChar('•');
		}
		return jPasswordField1;
	}

	private JPasswordField getJPasswordField0() {
		if (jPasswordField0 == null) {
			jPasswordField0 = new JPasswordField();
			jPasswordField0.setEchoChar('•');
		}
		return jPasswordField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("<html><font size=5>Registre de nou usuari</font></html>");
		}
		return jLabel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Si us plau ompli el seguent formulari i premi el botó \"Registrar\"");
		}
		return jLabel1;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Confirma contrasenya:");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Contrasenya:");
		}
		return jLabel3;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Àlies:");
		}
		return jLabel2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Cancel·lar");
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
			jButton0.setText("Registrar");
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
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public void main() {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FrameRegistrar frame = new FrameRegistrar();
				frame.setDefaultCloseOperation(FrameRegistrar.EXIT_ON_CLOSE);
				frame.setTitle("Gomoku - Registre de nou usuari");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioRegistrarBenvingut(this);
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		char[] pass1 = jPasswordField0.getPassword();
		String pass = "";
		char[] pass2 = jPasswordField1.getPassword();
		boolean iguals=true;
		if(pass1.length!=pass2.length){
			iguals=false;
		}
		else {
			for(int i=0; i<pass1.length;++i){
				if(pass1[i]!=pass2[i]){
					iguals=false;
				}
				else {
					pass+=pass1[i];
				}
			}
		}
		if (iguals) {
			if(pass1.length>=4){
			controlador_presentacio.RegistrarJugador(this,
					jTextField0.getText(),pass );
			}
			else {
				FrameError error = new FrameError();
				error.main();
				error.MissatgeActiva("Si us plau introdueixi una contrasenya de com a mínim 4 caracters");
				jPasswordField0.setText("");
				jPasswordField1.setText("");
			}
			} 
		else {
			FrameError error = new FrameError();
			error.main();
			error.MissatgeActiva("Les dues contrasenyes introduides no coincideixen");
			jPasswordField0.setText("");
			jPasswordField1.setText("");
		}
	}
	public void Netejapasswords (){
		jPasswordField0.setText("");
		jPasswordField1.setText("");
	}
	public void Netejatot(){
		jTextField0.setText("");
		jPasswordField0.setText("");
		jPasswordField1.setText("");
	}
}
