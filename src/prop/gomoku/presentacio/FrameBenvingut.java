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

// VS4E -- DO NOT REMOVE THIS LINE!
public class FrameBenvingut extends JFrame
{
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton0;
	private JLabel jLabel0;
	private JButton jButton3;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameBenvingut()
	{
		controlador_presentacio = new ControladorPresentacio();
		initComponents();
	}
	private void initComponents() {
		setTitle("Gomoku - Benvinguts");
		setLayout(new GroupLayout());
		add(getJButton0(), new Constraints(new Leading(109, 12, 12), new Leading(69, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(106, 139, 10, 10), new Leading(6, 38, 10, 10)));
		add(getJButton3(), new Constraints(new Leading(109, 12, 12), new Leading(113, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(109, 12, 12), new Leading(157, 12, 12)));
		add(getJButton2(), new Constraints(new Leading(109, 12, 12), new Leading(201, 12, 12)));
		setSize(320, 248);
	}
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Entrar com a convidat");
			jButton3.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton3MouseMouseClicked(event);
				}
			});
		}
		return jButton3;
	}
	private JLabel getJLabel0()
	{
		if ( jLabel0 == null )
		{
			jLabel0 = new JLabel();
			jLabel0.setText( "<html><FONT SIZE = 5>GOMOKU</FONT></html>" );
		}
		return jLabel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Identificar");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}
	private JButton getJButton2()
	{
		if ( jButton2 == null )
		{
			jButton2 = new JButton();
			jButton2.setText( "Manual D'usuari" );
			jButton2.addMouseListener( new MouseAdapter()
			{

				public void mouseClicked( MouseEvent event )
				{
					jButton2MouseMouseClicked( event );
				}
			} );
		}
		return jButton2;
	}

	private JButton getJButton1()
	{
		if ( jButton1 == null )
		{
			jButton1 = new JButton();
			jButton1.setText( "Registrar" );
			jButton1.addMouseListener( new MouseAdapter()
			{

				public void mouseClicked( MouseEvent event )
				{
					jButton1MouseMouseClicked( event );
				}
			} );
		}
		return jButton1;
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
	 * Main entry of the class. Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer. You can modify it as you like.
	 */
	public void main()
	{
		installLnF();
		SwingUtilities.invokeLater( new Runnable()
		{
			public void run()
			{
				FrameBenvingut frame = new FrameBenvingut();
				frame.setDefaultCloseOperation( FrameBenvingut.EXIT_ON_CLOSE );
				frame.setTitle( "Benvingut" );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( true );
			}
		} );
	}
	
	private void jButton0MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincronizacionBenvingutIdentificacio(this);
	}

	private void jButton2MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.MostraPDF();
	}

	private void jButton1MouseMouseClicked( MouseEvent event )
	{
			controlador_presentacio.sincronizacionBenvingutRegistrar( this );		
	}
	private void jButton3MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioBenvingutMenu(this);
	}

}
