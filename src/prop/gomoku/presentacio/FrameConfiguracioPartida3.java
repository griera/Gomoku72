package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import prop.gomoku.domini.models.TipusUsuari;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameConfiguracioPartida3 extends JFrame {
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
	private JPanel jPanel0;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameConfiguracioPartida3() {
		controlador_presentacio = new ControladorPresentacio();
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku72 - Configuració de Partida");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(59, 10, 10), new Leading(20, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(25, 10, 10), new Leading(62, 12, 12)));
		add(getJRadioButton0(), new Constraints(new Leading(25, 12, 12), new Leading(98, 10, 10)));
		add(getJRadioButton1(), new Constraints(new Leading(25, 12, 12), new Leading(140, 12, 12)));
		add(getJRadioButton2(), new Constraints(new Leading(25, 12, 12), new Leading(182, 12, 12)));
		add(getJPanel0(), new Constraints(new Trailing(12, 205, 102, 102), new Leading(98, 100, 50, 50)));
		add(getJButton0(), new Constraints(new Trailing(12, 12, 12), new Leading(232, 10, 10)));
		add(getJButton1(), new Constraints(new Trailing(99, 12, 12), new Leading(232, 10, 10)));
		add(getJButton2(), new Constraints(new Leading(25, 183, 183), new Leading(232, 10, 10)));
		setSize(454, 311);
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Jugador 2 :");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Jugador 1 :");
		}
		return jLabel2;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder(null, "Jugadors", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel2(), new Constraints(new Leading(7, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(7, 12, 12), new Leading(40, 12, 12)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(76, 12, 12), new Leading(12, 12, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(76, 12, 12), new Leading(40, 12, 12)));
		}
		return jPanel0;
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
			jRadioButton2.setText("Aleatori");
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
			jRadioButton1.setText("Blanc");
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
			jRadioButton0.setText("Negre");
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
			jLabel1.setText("Seleccioni el color de les seves fitxes:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("<html><font size=5>Configuració de la Partida</font></html>");
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
				FrameConfiguracioPartida3 frame = new FrameConfiguracioPartida3();
				frame.setDefaultCloseOperation(FrameConfiguracioPartida3.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setResizable( false );
			}
		});
	}

	private void jRadioButton0MouseMouseClicked(MouseEvent event) {
		if (jRadioButton0.isSelected()) {
			jRadioButton1.setSelected(false);
			jRadioButton2.setSelected(false);
		}
	}

	private void jRadioButton1MouseMouseClicked(MouseEvent event) {
		if (jRadioButton1.isSelected()) {
			jRadioButton0.setSelected(false);
			jRadioButton2.setSelected(false);
		}
	}

	private void jRadioButton2MouseMouseClicked(MouseEvent event) {
		if (jRadioButton2.isSelected()) {
			jRadioButton0.setSelected(false);
			jRadioButton1.setSelected(false);
		}
		jButton0.setSelected(true);
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio3Menu(this);
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio31(this);
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		int color = -1;
		if(jRadioButton0.isSelected()) color=0;
		else if(jRadioButton1.isSelected()) color=1;
		else if(jRadioButton2.isSelected()) color = 2;
		System.out.println(controlador_presentacio.getEntrenament());
		// TODO
		//controlador_presentacio.setentrenament( true );
		if(color!=-1 && controlador_presentacio.getEntrenament()==false)controlador_presentacio.iniciaPartida(this,color);
		else if(color!=-1){
			controlador_presentacio.iniciaEntrenament( this, color );
		}
	}

	public void setControladorPresentacio(ControladorPresentacio controlador_presentacio) {
		this.controlador_presentacio = controlador_presentacio;
	}
	public void setTipusText(){
		if(controlador_presentacio.getUsuariActual().getTipus()==controlador_presentacio.getOponentActual().getTipus() || controlador_presentacio.getUsuariActual().getTipus() == TipusUsuari.CONVIDAT){
			if(controlador_presentacio.getUsuariActual().getTipus()==TipusUsuari.HUMA){
				jLabel1.setText("Seleccioni el color de les fitxes del Jugador 1:");
			}
		}
		else if(controlador_presentacio.getUsuariActual().getTipus()!=TipusUsuari.HUMA) {
			
			jLabel1.setText("Seleccioni el color de les fitxes de la Màquina1:");
			}
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable( false );
		
	}
	
	public void setNomsusuaris(){
		jLabel4.setText(controlador_presentacio.getJugadorActual().getNom());
		jLabel5.setText(controlador_presentacio.getOponentActual().getNom());
		System.out.println("label4: " + jLabel4.getText()+ "label5 "+ jLabel5.getText());
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.setResizable( false );
	}

}
