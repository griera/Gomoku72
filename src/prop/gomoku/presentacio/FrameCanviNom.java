package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import prop.gomoku.domini.controladors.ControladorPartidesGuardades;
import prop.gomoku.domini.models.PartidaGomoku;

// VS4E -- DO NOT REMOVE THIS LINE!
public class FrameCanviNom extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JButton jButton0;
	private String nounom;
	private ControladorPresentacio controlador_presentacio;
	private PartidaGomoku partida;
	private JTextField jTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public FrameCanviNom()
	{
		initComponents();
	}

	private void initComponents()
	{
		controlador_presentacio = new ControladorPresentacio();
		setTitle( "Gomoku - Canviar Nom Partida" );
		setLayout( new GroupLayout() );
		add( getJButton0(), new Constraints( new Leading( 145, 10, 10 ), new Leading( 161, 10, 10 ) ) );
		add( getJTextField0(), new Constraints( new Leading( 84, 208, 12, 12 ), new Leading( 87, 24, 10, 10 ) ) );
		setSize( 383, 240 );
	}

	private JTextField getJTextField0()
	{
		if ( jTextField0 == null )
		{
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JButton getJButton0()
	{
		if ( jButton0 == null )
		{
			jButton0 = new JButton();
			jButton0.setText( "Acceptar" );
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
				FrameCanviNom frame = new FrameCanviNom();
				frame.setDefaultCloseOperation( FrameCanviNom.EXIT_ON_CLOSE );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( true );
			}
		} );
	}

	public void assignapartida( PartidaGomoku partida )
	{
		this.partida = partida;
		System.out.println( this.partida );
	}

	private void jButton0MouseMouseClicked( MouseEvent event )
	{
		nounom = jTextField0.getText();
		//controlador_presentacio.canvinomiguardat( this );
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;
		
	}

}
