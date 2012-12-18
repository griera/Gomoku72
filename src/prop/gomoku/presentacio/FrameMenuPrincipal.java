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

import prop.gomoku.domini.models.UsuariGomoku;

// VS4E -- DO NOT REMOVE THIS LINE!
public class FrameMenuPrincipal extends JFrame
{
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameMenuPrincipal()
	{
		initComponents();
	}

	private void initComponents() {
		controlador_presentacio = new ControladorPresentacio();
		setTitle("Gomoku72 - Menú Principal");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(135, 10, 10), new Leading(44, 10, 10)));
		add(getJButton2(), new Constraints(new Leading(127, 10, 10), new Leading(180, 10, 10)));
		add(getJButton4(), new Constraints(new Leading(127, 150, 10, 10), new Leading(268, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(127, 150, 10, 10), new Leading(92, 10, 10)));
		add(getJButton1(), new Constraints(new Leading(127, 150, 10, 10), new Leading(136, 10, 10)));
		add(getJButton3(), new Constraints(new Leading(127, 150, 10, 10), new Leading(224, 10, 10)));
		setSize(400, 350);
	}

	private JButton getJButton4()
	{
		if ( jButton4 == null )
		{
			jButton4 = new JButton();
			jButton4.setText( "Tancar Sessió" );
			jButton4.addMouseListener( new MouseAdapter()
			{

				public void mouseClicked( MouseEvent event )
				{
					jButton4MouseMouseClicked( event );
				}
			} );
		}
		return jButton4;
	}

	private JButton getJButton3()
	{
		if ( jButton3 == null )
		{
			jButton3 = new JButton();
			jButton3.setText( "Manual d'Usuari" );
			jButton3.addMouseListener( new MouseAdapter()
			{

				public void mouseClicked( MouseEvent event )
				{
					jButton3MouseMouseClicked( event );
				}
			} );
		}
		return jButton3;
	}

	private JButton getJButton2()
	{
		if ( jButton2 == null )
		{
			jButton2 = new JButton();
			jButton2.setText( "Rànquings i Rècords" );
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
			jButton1.setText( "Partides Guardades" );
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

	private JButton getJButton0()
	{
		if ( jButton0 == null )
		{
			jButton0 = new JButton();
			jButton0.setText( "Nova Partida" );
			jButton0.setToolTipText( "Jugar una nova partida" );
			jButton0.addMouseListener( new MouseAdapter()
			{

				public void mouseClicked( MouseEvent event )
				{
					jButton0MouseMouseClicked( event );
				}
			} );
		}
		return jButton0;
	}

	private JLabel getJLabel0()
	{
		if ( jLabel0 == null )
		{
			jLabel0 = new JLabel();
			jLabel0.setText( "<html><font size=5>Menú Principal</font></html>" );
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
	 * Main entry of the class. Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer. You can modify it as you like.
	 */
	public void main()
	{
		installLnF();
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				FrameMenuPrincipal frame = new FrameMenuPrincipal();
				frame.setDefaultCloseOperation( FrameMenuPrincipal.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible(true);
				frame.setResizable( false );
			}
		} );
	}

	private void jButton4MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincronitzacioMenuBenvingut( this );
	}

	private void jButton0MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincronitzacioMenuNovaPartida( this );
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;

	}

	private void jButton1MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincronitzacioMenuCarregaPartides( this );
	}

	private void jButton3MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.MostraPDF();
	}

	public UsuariGomoku getUsuariActiu()
	{
		return controlador_presentacio.getUsuariActual();
	}

	private void jButton2MouseMouseClicked( MouseEvent event )
	{
		// TODO
		// System.out.println("Abans d'anar-nos del menu principal: " + this.controlador_presentacio.getUsuariActual());
		controlador_presentacio.sincronitzacioMenuEstadistiques( this );
	}
	public void menuConvidat(){
		/*jButton1.setEnabled(false);
		jButton1.setFocusable(false);
		jButton2.setEnabled(false);
		jButton2.setFocusable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);*/
	}
}
