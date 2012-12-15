package prop.gomoku.presentacio;
import prop.gomoku.domini.models.UsuariGomoku;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameIdentificacio extends JFrame
{
	private ControladorPresentacio controlador_presentacio;
	private static final long serialVersionUID = 1L;

	private JButton jButton1;
	private JButton jButton0;
	private JLabel jLabel2;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JMenuBar jMenuBar0;
	private JTextField jTextField0;
	private JPasswordField jPasswordField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FrameIdentificacio()
	{
		controlador_presentacio = new ControladorPresentacio();
		initComponents();
	}
	private void initComponents() {
		setTitle("Gomoku - Identificació");
		setLayout(new GroupLayout());
		add(getJLabel2(), new Constraints(new Leading(8, 125, 10, 10), new Leading(7, 26, 12, 12)));
		add(getJButton1(), new Constraints(new Trailing(12, 82, 12, 12), new Leading(238, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(195, 93, 106), new Leading(238, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(12, 87, 12, 12), new Leading(166, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(12, 12, 12), new Leading(92, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(11, 12, 12), new Leading(36, 10, 10)));
		add(getJLabel4(), new Constraints(new Leading(12, 12, 12), new Leading(64, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(11, 192, 12, 12), new Leading(126, 12, 12)));
		add(getJPasswordField0(), new Constraints(new Leading(11, 192, 12, 12), new Leading(200, 12, 12)));
		setJMenuBar(getJMenuBar0());
		setSize(389, 279);
	}
	private JPasswordField getJPasswordField0() {
		if (jPasswordField0 == null) {
			jPasswordField0 = new JPasswordField();
			jPasswordField0.setEchoChar('•');
		}
		return jPasswordField0;
	}
	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}
	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
		}
		return jMenuBar0;
	}
	private JLabel getJLabel4(){
	if(jLabel4==null){
	jLabel4 = new JLabel();
	jLabel4.setText("\"Entrar\" per a accedir al sistema:");
	}
	return jLabel4;
	}
	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Si us plau introdueixi les seves dades i premi el botó");
		}
		return jLabel3;
	}
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Àlies:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Contrasenya:");
		}
		return jLabel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("<html><font size= 5>IDENTIFIQUI'S</font></html>");
		}
		return jLabel2;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Cancel·lar");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Entrar");
			jButton1.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton1MouseMouseClicked(event);
				}
			});
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
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public void main()
	{
		installLnF();
		SwingUtilities.invokeLater( new Runnable()
		{
			public void run()
			{
				FrameIdentificacio frame = new FrameIdentificacio();
				frame.setDefaultCloseOperation( FrameIdentificacio.EXIT_ON_CLOSE );
				frame.setTitle( "Identificacio" );
				frame.getContentPane().setPreferredSize( frame.getSize() );
				frame.pack();
				frame.setLocationRelativeTo( null );
				frame.setVisible( true );
			}
		} );
	}
	public void NetejaAliesContrasenya(){
		jTextField0.setText( "" );
		jPasswordField0.setText( "" );
	}
	public void NetejaAlies(){
		jTextField0.setText("");
	}
	private void jButton0MouseMouseClicked(MouseEvent event) {
		controlador_presentacio.sincronitzacioIdentificacioBenvingut(this);
	}
	private void jButton1MouseMouseClicked(MouseEvent event) {
		char [] contrasenyaarray=jPasswordField0.getPassword();
		String pass = "";
		for(int i=0; i< contrasenyaarray.length; ++i){
			pass+=contrasenyaarray[i];
		}
		controlador_presentacio.Identificarse(this,jTextField0.getText(),pass);
	}
	public UsuariGomoku getUsuariActual() {
		
		return controlador_presentacio.getUsuariActual();
	}
	public void netejaContrasenya() {
		jPasswordField0.setText("");	
	}

}
