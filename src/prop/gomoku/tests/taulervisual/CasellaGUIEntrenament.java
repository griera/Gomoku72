package prop.gomoku.tests.taulervisual;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CasellaGUIEntrenament extends JPanel implements MouseListener
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 7545222972515472633L;

	private TaulerGUIEntrenament tauler;
	private ImageIcon fons;
	private static int[] casellaMarcada = new int[2];

	public CasellaGUIEntrenament()
	{
		// Aquest constructor no es farà servir. Es deixa delcarat per poder crear el bean.
	}

	public CasellaGUIEntrenament( TaulerGUIEntrenament tauler )
	{
		initComponents();
		this.tauler = tauler;
		if ( this.tauler.getTipusTauler() == true )
		{
			// tauler respon a clics?
			this.addMouseListener( this );
		}
	}

	public void setFons( ImageIcon fons )
	{
		this.fons = fons;
	}

	public ImageIcon getFons()
	{
		return this.fons;
	}

	private void initComponents()
	{

		GroupLayout layout = new GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0, 161,
				Short.MAX_VALUE ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0, 193,
				Short.MAX_VALUE ) );
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage( this.fons.getImage(), 0, 0, this.getWidth(), this.getHeight(), this );
	}

	public void mouseClicked( MouseEvent e )
	{
	}

	public void mouseEntered( MouseEvent e )
	{
	}

	public void mouseExited( MouseEvent e )
	{
	}

	public void mousePressed( MouseEvent e )
	{
		if ( this.tauler.intentaFerMoviment( this.tauler.getCoordenades( (CasellaGUIEntrenament) e.getComponent() ) ) )
		{
			// TODO hace falta esta llamada?
			CasellaGUIEntrenament.setCasillaMarcada( this.tauler.getCoordenades( (CasellaGUIEntrenament) e.getComponent() ) );
		}
		// TODO

	}

	public void mouseReleased( MouseEvent e )
	{
	}

	public static int[] getCasillaMarcada()
	{
		return CasellaGUIEntrenament.casellaMarcada;
	}

	public static void setCasillaMarcada( int[] casellaMarcada )
	{
		CasellaGUIEntrenament.casellaMarcada = casellaMarcada;
	}

}
