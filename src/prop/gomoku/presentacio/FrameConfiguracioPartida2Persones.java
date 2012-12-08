package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameConfiguracioPartida2Persones extends JFrame {
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JRadioButton jRadioButton0;
	private JRadioButton jRadioButton1;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JTextField jTextField0;
	private JTextField jTextField1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JPanel jPanel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameConfiguracioPartida2Persones() {
		controlador_presentacio = new ControladorPresentacio();
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku - Configuració de Partida Ràpida");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(59, 10, 10), new Leading(20, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(25, 10, 10), new Leading(62, 12, 12)));
		add(getJRadioButton0(), new Constraints(new Leading(25, 12, 12), new Leading(98, 10, 10)));
		add(getJRadioButton1(), new Constraints(new Leading(25, 12, 12), new Leading(140, 12, 12)));
		add(getJButton0(), new Constraints(new Trailing(12, 12, 12), new Trailing(12, 12, 12)));
		add(getJButton1(), new Constraints(new Trailing(105, 12, 12), new Trailing(12, 90, 90)));
		add(getJButton2(), new Constraints(new Trailing(198, 12, 12), new Trailing(12, 90, 90)));
		add(getJPanel0(), new Constraints(new Bilateral(40, 12, 0), new Leading(176, 140, 10, 10)));
		setSize(454, 376);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder(null, "Usuari Registrat", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			jPanel0.setFocusable(false);
			jPanel0.setEnabled(false);
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTextField0(), new Constraints(new Trailing(12, 282, 12, 12), new Leading(20, 10, 10)));
			jPanel0.add(getJTextField1(), new Constraints(new Trailing(12, 282, 12, 12), new Leading(70, 10, 10)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(12, 28, 306), new Leading(70, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Trailing(312, 12, 12), new Leading(20, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Àlies:");
			jLabel2.setEnabled(false);
			jLabel2.setFocusable(false);
		}
		return jLabel2;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Contrasenya:");
			jLabel3.setEnabled(false);
			jLabel3.setFocusable(false);
		}
		return jLabel3;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setFocusable(false);
			jTextField1.setEnabled(false);
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setFocusable(false);
			jTextField0.setEnabled(false);
		}
		return jTextField0;
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

	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setText("Usuari convidat (no registrat)");
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
			jRadioButton0.setText("Usuari registrat");
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
			jLabel1.setText("Seleccioni el tipus d'usuari contra el que vol jugar:");
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
				FrameConfiguracioPartida2Persones frame = new FrameConfiguracioPartida2Persones();
				frame.setDefaultCloseOperation(FrameConfiguracioPartida2Persones.EXIT_ON_CLOSE);
				frame.setTitle("FrameConfiguracioPartida1");
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
			jPanel0.setEnabled(true);
			jPanel0.setFocusable(true);
			jTextField0.setEnabled(true);
			jTextField0.setFocusable(true);
			jTextField1.setFocusable(true);
			jTextField1.setEnabled(true);
			jLabel3.setFocusable(true);
			jLabel3.setEnabled(true);
			jLabel2.setEnabled(true);
			jLabel2.setFocusable(true);
			
		}
		if(jRadioButton0.isSelected()==false){
			jPanel0.setEnabled(false);
			jPanel0.setFocusable(false);
			jTextField0.setEnabled(false);
			jTextField0.setFocusable(false);
			jTextField1.setEnabled(false);
			jTextField1.setFocusable(false);
			jLabel3.setFocusable(false);
			jLabel3.setEnabled(false);
			jLabel2.setEnabled(false);
			jLabel2.setEnabled(false);
			jLabel2.setFocusable(false);
		}
	}

	private void jRadioButton1MouseMouseClicked(MouseEvent event) {
		if(jRadioButton1.isSelected()){
			if(jPanel0.isEnabled()){
				jPanel0.setEnabled(false);
				jPanel0.setFocusable(false);
				jTextField0.setEnabled(false);
				jTextField0.setFocusable(false);
				jTextField1.setFocusable(false);
				jTextField1.setEnabled(false);
				jLabel2.setEnabled(false);
				jLabel2.setFocusable(false);
				jLabel3.setEnabled(false);
				jLabel3.setFocusable(false);
			}
			jRadioButton0.setSelected(false);
			
		}
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio2PersonesMenu(this);
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio2persones1(this);
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		
		if(jRadioButton0.isSelected()){
			controlador_presentacio.IdentificarOponent( this, jTextField0.getText(), jTextField1.getText() );
		}
		if(jRadioButton1.isSelected()){
			controlador_presentacio.IdentificarOponent( this, "Convidat", "Convidat");
		}
	}
	public void setControladorPresentacio(ControladorPresentacio controlador_presentacio){
		this.controlador_presentacio=controlador_presentacio;
	}

	public void NetejaAliesContrasenya()
	{
		jTextField0.setText( "" );
		jTextField1.setText( "" );
		
	}


}