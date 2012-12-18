package prop.gomoku.presentacio;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Genís Riera Pérez
 *
 */
public class CasellaGUI extends JPanel implements MouseListener
{
	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 7545222972515472633L;

	private TaulerGUI tauler;
	private ImageIcon fons;
	private static int[] casellaMarcada = new int[2];

	public CasellaGUI()
	{
		// Aquest constructor no es farà servir. Es deixa delcarat per poder crear el bean.
	}

	public CasellaGUI( TaulerGUI tauler )
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
		if ( this.tauler.intentaFerMoviment( this.tauler.getCoordenades( (CasellaGUI) e.getComponent() ) ) )
		{
			// TODO fa falta aquesta crida?
			// CasellaGUI.setCasillaMarcada( this.tauler.getCoordenades( (CasellaGUI) e.getComponent() ) );
		}

	}

	public void mouseReleased( MouseEvent e )
	{
	}

	public static int[] getCasillaMarcada()
	{
		return CasellaGUI.casellaMarcada;
	}

	public static void setCasillaMarcada( int[] casellaMarcada )
	{
		CasellaGUI.casellaMarcada = casellaMarcada;
	}

}
