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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import prop.gomoku.domini.controladors.records.CriteriRecords;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameEstadistiquesGlobals extends JFrame
{
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;
	private JList jList0;
	private int index;
	private JScrollPane jScrollPane0;
	private JLabel jLabel0;
	private DefaultListModel listmodel;
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameEstadistiquesGlobals()
	{
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku72 - Rànquings Globals");
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(47, 473, 10, 10), new Leading(76, 372, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(372, 148, 12, 12), new Leading(460, 35, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(189, 12, 12), new Leading(26, 10, 10)));
		setSize(562, 552);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Tancar");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("<html><font size=5>Rànquings Globals</font></html>");
		}
		return jLabel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJList0());
		}
		return jScrollPane0;
	}

	private JList getJList0() {
		if (jList0 == null) {
			jList0 = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jList0.setModel(listModel);
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
			@Override
			public void run()
			{
				FrameEstadistiquesGlobals frame = new FrameEstadistiquesGlobals();
				frame.setDefaultCloseOperation( FrameEstadistiquesGlobals.EXIT_ON_CLOSE );
				frame.setTitle( "FrameEstadistiquesGlobals" );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( false );
				frame.setResizable( false );
			}
		} );
	}

	public void setControladorPresentacio( ControladorPresentacio controlador_presentacio )
	{
		this.controlador_presentacio = controlador_presentacio;
	}
	public void carregaestadistiques(List<List<String[]>> records){
		int i = 0;
		int j = 0;
		listmodel = new DefaultListModel();
		for (CriteriRecords criteri : CriteriRecords.values())
		{
			// TODO   tractament dels titols adequats
			listmodel.add( j, criteri.getTextDescriptiu() + ":" );
			j++;
			List<String[]> record_criteri = records.get( i );
			for (String[] record : record_criteri)
			{
				String dades = "";
				dades+=record[0];
				int espais = 20-dades.length();
				// System.out.println(espais);
				for(int k=0; k<espais;k++){
					dades+="&nbsp;";
					// System.out.print(" ,"+ k);
				}

				// System.out.println();
				dades+=record[1];
				if(criteri.toString().contains( "PER" ) ){
					dades+="%";
				}
				String html1 = "<html><font face=\"Monospace\">";
				String html2 = "</font></html>";
				dades=html1+dades+html2;
				listmodel.add( j, dades );
				j++;
			}
			listmodel.add( j, " " );
			j++;
			i++;
		}
		jList0.setModel( listmodel );
		this.setLocationRelativeTo( null );
		this.setVisible( true );
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		this.dispose();
	}

}
