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
public class FrameConfiguracioPartida2CPU extends JFrame
{
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JRadioButton jRadioButton0;
	private JRadioButton jRadioButton1;
	private JRadioButton jRadioButton2;
	private JRadioButton jRadioButton3;
	private JRadioButton jRadioButton4;
	private JRadioButton jRadioButton5;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameConfiguracioPartida2CPU()
	{
		initComponents();
	}

	private void initComponents() {
		controlador_presentacio = new ControladorPresentacio();
		setTitle("Gomoku72- Configuració de Partida");
		setLayout(new GroupLayout());
		add(getJLabel1(), new Constraints(new Leading(26, 10, 10), new Leading(54, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(26, 12, 12), new Leading(132, 10, 10)));
		add(getJRadioButton0(), new Constraints(new Leading(26, 12, 12), new Leading(88, 12, 12)));
		add(getJRadioButton1(), new Constraints(new Leading(95, 12, 12), new Leading(88, 12, 12)));
		add(getJRadioButton2(), new Constraints(new Leading(165, 12, 12), new Leading(88, 12, 12)));
		add(getJRadioButton3(), new Constraints(new Leading(26, 12, 12), new Leading(166, 12, 12)));
		add(getJRadioButton4(), new Constraints(new Leading(95, 12, 12), new Leading(166, 12, 12)));
		add(getJRadioButton5(), new Constraints(new Leading(165, 12, 12), new Leading(166, 12, 12)));
		add(getJButton0(), new Constraints(new Trailing(12, 12, 12), new Trailing(12, 48, 48)));
		add(getJButton2(), new Constraints(new Leading(26, 105, 105), new Trailing(12, 198, 198)));
		add(getJButton1(), new Constraints(new Trailing(99, 156, 156), new Trailing(12, 160, 160)));
		add(getJLabel0(), new Constraints(new Trailing(99, 12, 12), new Leading(12, 50, 50)));
		setSize(402, 255);
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

	private JRadioButton getJRadioButton5() {
		if (jRadioButton5 == null) {
			jRadioButton5 = new JRadioButton();
			jRadioButton5.setText("Difícil");
			jRadioButton5.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton5MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton5;
	}

	private JRadioButton getJRadioButton4() {
		if (jRadioButton4 == null) {
			jRadioButton4 = new JRadioButton();
			jRadioButton4.setText("Mitjà");
			jRadioButton4.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton4MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton4;
	}

	private JRadioButton getJRadioButton3() {
		if (jRadioButton3 == null) {
			jRadioButton3 = new JRadioButton();
			jRadioButton3.setText("Fàcil");
			jRadioButton3.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton3MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton3;
	}

	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setText("Difícil");
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
			jRadioButton1.setText("Mitjà");
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
			jRadioButton0.setText("Fàcil");
			jRadioButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jRadioButton0MouseMouseClicked(event);
				}
			});
		}
		return jRadioButton0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Seleccioni el nivell de dificultat de la Màquina 2:");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Seleccioni el nivell de dificultat de la Màquina 1:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("<html><font size=5>Configuracio de la Partida</font></html>");
		}
		return jLabel0;
	}

	private static void installLnF()
	{
		try
		{
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if ( lnfClassname == null )
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel( lnfClassname );
		} catch ( Exception e )
		{
			System.err.println( "Cannot install " + PREFERRED_LOOK_AND_FEEL + " on this platform:" + e.getMessage() );
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public void main( )
	{
		installLnF();
		SwingUtilities.invokeLater( new Runnable()
		{
			public void run()
			{
				FrameConfiguracioPartida2CPU frame = new FrameConfiguracioPartida2CPU();
				frame.setDefaultCloseOperation( FrameConfiguracioPartida2CPU.EXIT_ON_CLOSE );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( true );
				frame.setResizable( false );
			}
		} );
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio2cpu1( this );
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioConfiguracio2CPUMenu( this );
	}

	private void jRadioButton3MouseMouseClicked(MouseEvent event) {
		if(jRadioButton3.isSelected()){
			jRadioButton4.setSelected( false );
			jRadioButton5.setSelected( false );
			jRadioButton0.setEnabled(false);
			jRadioButton0.setFocusable(false);
			jRadioButton1.setEnabled(true);
			jRadioButton1.setFocusable(true);
			jRadioButton2.setEnabled(true);
			jRadioButton2.setFocusable(true);
		}
	}

	private void jRadioButton4MouseMouseClicked(MouseEvent event) {
		if(jRadioButton4.isSelected()){
			jRadioButton5.setSelected( false );
			jRadioButton3.setSelected( false );
			jRadioButton1.setEnabled(false);
			jRadioButton1.setFocusable(false);
			jRadioButton0.setEnabled(true);
			jRadioButton0.setFocusable(true);
			jRadioButton2.setEnabled(true);
			jRadioButton2.setFocusable(true);
		}
	}

	private void jRadioButton5MouseMouseClicked(MouseEvent event) {
		if(jRadioButton5.isSelected()){
			jRadioButton4.setSelected( false );
			jRadioButton3.setSelected( false );
			jRadioButton2.setEnabled(false);
			jRadioButton2.setFocusable(false);
			jRadioButton1.setEnabled(true);
			jRadioButton1.setFocusable(true);
			jRadioButton0.setFocusable(true);
			jRadioButton0.setEnabled(true);
		}
	}

	private void jRadioButton2MouseMouseClicked(MouseEvent event) {
		if(jRadioButton2.isSelected()){
			jRadioButton0.setSelected( false );
			jRadioButton1.setSelected( false );
			jRadioButton5.setEnabled(false);
			jRadioButton5.setFocusable(false);
			jRadioButton4.setEnabled(true);
			jRadioButton4.setFocusable(true);
			jRadioButton3.setEnabled(true);
			jRadioButton3.setFocusable(true);
		}
	}

	private void jRadioButton1MouseMouseClicked(MouseEvent event) {
		if(jRadioButton1.isSelected()){
			jRadioButton2.setSelected( false );
			jRadioButton0.setSelected( false );
			jRadioButton4.setEnabled(false);
			jRadioButton4.setFocusable(false);
			jRadioButton3.setEnabled(true);
			jRadioButton3.setFocusable(true);
			jRadioButton5.setEnabled(true);
			jRadioButton5.setFocusable(true);
		}
	}

	private void jRadioButton0MouseMouseClicked(MouseEvent event) {
		if(jRadioButton0.isSelected()){
			jRadioButton1.setSelected( false );
			jRadioButton2.setSelected(false);
			jRadioButton3.setEnabled(false);
			jRadioButton3.setEnabled(false);
			jRadioButton4.setEnabled(true);
			jRadioButton4.setFocusable(true);
			jRadioButton5.setEnabled(true);
			jRadioButton5.setFocusable(true);
			
		}
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;
		
	}

	public TipusUsuari [] getnivellmaquina() {
		TipusUsuari [] tipus_usuari= new TipusUsuari[2];
		if(jRadioButton0.isSelected()) tipus_usuari[0]=TipusUsuari.FACIL;
		else if(jRadioButton1.isSelected()) tipus_usuari[0]=TipusUsuari.MITJA;
		else if(jRadioButton2.isSelected()) tipus_usuari[0]=TipusUsuari.DIFICIL;
		if(jRadioButton3.isSelected()) tipus_usuari[1] = TipusUsuari.FACIL;
		else if(jRadioButton4.isSelected()) tipus_usuari[1] = TipusUsuari.MITJA;
		else if(jRadioButton5.isSelected()) tipus_usuari[1] = TipusUsuari.DIFICIL;
		return tipus_usuari;
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		if((jRadioButton0.isSelected()|jRadioButton1.isSelected()|jRadioButton2.isSelected())&(jRadioButton3.isSelected()|jRadioButton4.isSelected()|jRadioButton5.isSelected())){
			controlador_presentacio.sincronitzacioConfiguracio2cpu3(this);
		}
		else {
			FrameError error = new FrameError();
			error.main();
			error.MissatgeActiva("Si us plau, seleccioni un nivell de dificultat per a cada jugador màquina");
		}
	}

}
