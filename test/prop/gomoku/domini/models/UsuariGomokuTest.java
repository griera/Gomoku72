/**
 * 
 */
package prop.gomoku.domini.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 */
public class UsuariGomokuTest
{
	private static String nom_per_defecte;
	private static String contrasenya_per_defecte;
	private static List<String> noms_valids;
	private static List<String> contrasenyes_valides;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception
	{
		nom_per_defecte = "Nom Usuari";
		contrasenya_per_defecte = "¿aB3`^'´?";

		noms_valids = new ArrayList<String>();
		contrasenyes_valides = new ArrayList<String>();

		noms_valids.add( "nomValid" );
		noms_valids.add( "nom_valid" );
		noms_valids.add( "nom vàlid" );
		noms_valids.add( "Nom Vàlid" );
		noms_valids.add( "Nom_Vàlid!" );

		contrasenyes_valides.add( "1234" );
		contrasenyes_valides.add( "contràsënyáAmbAccents" );
		contrasenyes_valides.add( "contrasenya`'´ç?¿!¡//" );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGeneral()
	{
		for ( String nom : noms_valids )
		{
			for ( String contrasenya : contrasenyes_valides )
			{
				UsuariGomoku usuari = new UsuariGomoku( nom, contrasenya );
				assertNotNull( usuari );
				assertNotNull( usuari.getNom() );
				assertNotNull( usuari.getContrasenya() );
				assertNotNull( usuari.getNumVictories() );
				assertNotNull( usuari.getNumEmpats() );
				assertNotNull( usuari.getNumDerrotes() );
			}
		}
	}

	@Test
	public void testSettersIGetters()
	{
		UsuariGomoku usuari = new UsuariGomoku( nom_per_defecte, contrasenya_per_defecte );
		usuari.setNom( "abcd" );
		assertEquals( "abcd", usuari.getNom() );
		usuari.setContrasenya( "ABCD" );
		assertEquals( "ABCD", usuari.getContrasenya() );
	}

	@Test
	public void testContrasenyaInvalidaEnConstructor()
	{
		exception.expect( IllegalArgumentException.class );
		new UsuariGomoku( nom_per_defecte, "contrasenya_inclou espai" );
	}

	@Test
	public void testContrasenyaInvalidaEnSetter()
	{
		UsuariGomoku usuari = new UsuariGomoku( nom_per_defecte, contrasenya_per_defecte );
		String contrasenya_original = usuari.getContrasenya();
		assertFalse( usuari.setContrasenya( "inclou espai" ));
		assertEquals(contrasenya_original, usuari.getContrasenya());
	}
}
