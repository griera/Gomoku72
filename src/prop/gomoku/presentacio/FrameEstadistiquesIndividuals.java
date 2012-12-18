package prop.gomoku.presentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class FrameEstadistiquesIndividuals extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JList jList0;
	private JScrollPane jScrollPane0;
	private JLabel jLabel0;
	private DefaultListModel listmodel;
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameEstadistiquesIndividuals()
	{
		initComponents();
	}

	private void initComponents() {
		setTitle("Gomoku72 - Rècords Individuals");
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(47, 465, 10, 10), new Leading(76, 372, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(411, 101, 12, 12), new Leading(456, 35, 10, 10)));
		add(getJLabel0(), new Constraints(new Leading(188, 10, 10), new Leading(27, 10, 10)));
		setSize(556, 544);
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
			jLabel0.setText("<html><font size=5>Rècords Individuals</font></html>");
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
				FrameEstadistiquesIndividuals frame = new FrameEstadistiquesIndividuals();
				frame.setDefaultCloseOperation( FrameEstadistiquesIndividuals.EXIT_ON_CLOSE );
				frame.setTitle( "FrameEstadistiquesGlobals" );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( false );
			}
		} );
	}

	public void carregaestadistiques(int[] records){
		int i = 0;
		listmodel = new DefaultListModel();
		int j = 0;
		for (CriteriRecords criteri : CriteriRecords.values())
		{
			// TODO   tractament dels titols adequats
			int record_criteri = records[i];
				String dades = "";
				
				if(i%5==0 && i!=0){
					listmodel.add(j," ");
					j++;
				}
				
				dades+=criteri.getTextDescriptiu() + ": ";
				int espais = 55-dades.length();
				// System.out.println(espais);
				for(int k=0; k<espais;k++){
					dades+="&nbsp;";
				}
				dades+=record_criteri;
				if(criteri.toString().contains( "PER" )){
					dades+="%";
				}
				String html1 = "<html><font face=\"Monospace\">";
				String html2 = "</font></html>";
				dades=html1+dades+html2;
				listmodel.add( j, dades );
				i++;
				j++;
				
		}
		jList0.setModel( listmodel );
		this.setLocationRelativeTo( null );
		this.setVisible( true );
		this.setResizable( false );
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		this.dispose();
	}

}
