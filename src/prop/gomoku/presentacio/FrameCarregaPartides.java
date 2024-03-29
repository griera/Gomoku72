package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import prop.gomoku.domini.models.PartidaGomoku;

// VS4E -- DO NOT REMOVE THIS LINE!
public class FrameCarregaPartides extends JFrame
{
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JList jList0;
	private JScrollPane jScrollPane0;
	private JLabel jLabel0;
	private JButton jButton0;
	private JButton jButton1;
	private List<PartidaGomoku> partides;
	private JButton jButton2;
	private JButton jButton3;
	private DefaultListModel listModel;
	private JLabel jLabel1;
	private JTextField jTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameCarregaPartides()
	{
		initComponents();

	}

	private void initComponents() {
		setTitle("Gomoku72 - Gestió de Partides Guardades");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(94, 10, 10), new Leading(12, 12, 12)));
		add(getJButton2(), new Constraints(new Leading(315, 12, 12), new Leading(144, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(315, 146, 12, 12), new Leading(106, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(315, 146, 12, 12), new Leading(68, 12, 12)));
		add(getJButton3(), new Constraints(new Leading(315, 146, 12, 12), new Leading(184, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(13, 276, 10, 10), new Leading(67, 143, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(12, 12, 12), new Leading(228, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(90, 199, 12, 12), new Leading(228, 12, 12)));
		setSize(519, 304);
	}

	private JTextField getJTextField0()
	{
		if ( jTextField0 == null )
		{
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JLabel getJLabel1()
	{
		if ( jLabel1 == null )
		{
			jLabel1 = new JLabel();
			jLabel1.setText( "Nom Partida:" );
		}
		return jLabel1;
	}

	private JButton getJButton3()
	{
		if ( jButton3 == null )
		{
			jButton3 = new JButton();
			jButton3.setText( "Menú Principal" );
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
			jButton2.setText( "Reanomena Partida" );
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
			jButton1.setText( "Esborra Partida" );
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
			jButton0.setText( "Carrega Partida" );
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
			jLabel0.setText( "<html><font size = 5>Gestió de partides guardades</font></html>" );
		}
		return jLabel0;
	}

	private JScrollPane getJScrollPane0()
	{
		if ( jScrollPane0 == null )
		{
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView( getJList0() );
		}
		return jScrollPane0;
	}

	private JList getJList0()
	{
		if ( jList0 == null )
		{
			jList0 = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jList0.setModel( listModel );
			jList0.addListSelectionListener( new ListSelectionListener()
			{

				public void valueChanged( ListSelectionEvent event )
				{
					jList0ListSelectionValueChanged( event );
				}
			} );
		}
		return jList0;
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
				FrameCarregaPartides frame = new FrameCarregaPartides();
				frame.setDefaultCloseOperation( FrameCarregaPartides.EXIT_ON_CLOSE );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( false );
				frame.setResizable( false );
			}
		} );
	}

	private void jButton3MouseMouseClicked( MouseEvent event )
	{
		controlador_presentacio.sincronitzacioCarregaPartidesMenu( this );
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;

	}

	public void mostraLlistaPartides( List<PartidaGomoku> partides )
	{
		this.partides = partides;
		// System.out.println( "Ensenyo partides. Ho intento" );
		listModel = new DefaultListModel();
		for ( PartidaGomoku partida : partides )
		{
			// TODO
			listModel.addElement( partida.getNom() + " - " + partida.getDataCreacio().toString() );
			// System.out.println( partida );
		}
		jList0.setModel( listModel );
		this.setLocationRelativeTo( null );
		this.setVisible( true );
		this.setResizable( false );
		jTextField0.setText( "" );

	}

	private void jButton1MouseMouseClicked( MouseEvent event )
	{
		if(partides != null){
			if(jList0.getSelectedIndex() != -1){
				PartidaGomoku partida = partides.get( jList0.getSelectedIndex() );
				controlador_presentacio.esborraPartida( partida );
			}		
		}
	}

	private void jButton2MouseMouseClicked( MouseEvent event )
	{
		if ( partides != null )
		{
			if ( jList0.getSelectedIndex() != -1 )
			{
				PartidaGomoku partida = partides.get( jList0.getSelectedIndex() );
				if ( jTextField0.getText().length()==0 )
				{
					FrameError noname = new FrameError();
					noname.main();
					noname.MissatgeActiva( "El nou nom de la partida no pot estar buit, si us plau introdueixi un altre nom" );
				}
				else
				{
					controlador_presentacio.sincronitzacioCanviNom( partida, jTextField0.getText() );
					jTextField0.setText( "" );
				}
			}
		}
	}

	private void jList0ListSelectionValueChanged( ListSelectionEvent event )
	{
		// System.out.println(event.getFirstIndex());
		// System.out.println( this.jList0.getSelectedIndex() );
	}

	private void jButton0MouseMouseClicked( MouseEvent event )
	{
		if (jList0.getSelectedIndex() == -1)
		{
			return;
		}
		PartidaGomoku partida = partides.get( jList0.getSelectedIndex() );
		controlador_presentacio.carregaPartida( this, partida );
	}

}
