package prop.gomoku.domini.controladors;

/**
 * La classe <code>Vigenere</code> permet el xifrat i desxifrat de missatges de text utilitzant l'algorisme criptogràfic
 * Vigenère, descobert pel criptògraf francès Blaise de Vigenère.
 * 
 * @author Genís Riera Pérez
 */
public class Vigenere
{
	/**
	 * Indica el nombre de caràcters que formen el diccionari.
	 */
	private static final int mida_diccionari = 94;

	/**
	 * Índex de la taula de caràcters ASCII que correspon al primer caràcter vàlid del diccionari.
	 */
	private static final int index_inicial_ascii = 33;

	/**
	 * Índex de la taula de caràcters ASCII que correspon a l'�ltim caràcter vàlid del diccionari.
	 */
	private static final int index_final_ascii = 126;

	/**
	 * Cadena de caràcters a xifrar.
	 */
	private char[] missatge;

	/**
	 * Cadena de caràcters que s'utilitza com a clau per a xifrar i desxifrar.
	 */
	private char[] clau;

	/**
	 * Cadena de caràcters que representa el missatge xifrat.
	 */
	private char[] missatge_xifrat;

	/**
	 * Matriu de caràcters emprada per al procés de xifrat i desxifrat (caràcter a caràcter) del missatge a tractar.
	 */
	private char matriu_conversio[][];

	/**
	 * Mètode contructor de la classe <code>Vigenere</code>. Xifra un missatge de text fent ús de l'algorisme Vigenère
	 * 
	 * @param missatge Missatge de text a xifrar
	 * @param clau Cadena de caràcters que servir� de clau per al procés de xifrat i desxifrat del missatge
	 */
	public Vigenere( String missatge, String clau )
	{
		this.missatge = missatge.toCharArray();
		char[] clau_temporal = clau.toCharArray();
		this.clau = new char[missatge.length()];

		// Representa l'índex de la mida de l'atribut clau
		int mida_clau = 0;

		// Ara anem replicant la seqüència de caràcters de l'atribut clau fins a obtenir una cadena de caràcters de la
		// mateixa mida que el missatge a xifrar
		for ( int index = 0; index < missatge.length(); ++index )
		{
			this.clau[index] = clau_temporal[mida_clau];
			++mida_clau;

			// Si hem arribat a l'últim caràcter de la clau, resetegem el seu índex per continuar emmagatzemant la seva
			// seqüència a clau_temporal[]
			if ( mida_clau == clau_temporal.length )
			{
				mida_clau = 0;
			}
		}
		// Ara la clau ja s'ha guardat en un array de la mateixa mida que la del missatge a xifrar

		// Generem la matriu de conversió del diccionari
		this.matriu_conversio = generaMatriuConversio();

		this.xifrar();
	}

	public void xifrar()
	{
		char[] resultat_xifrat = new char[this.missatge.length];

		// Recorrem el missatge a xifrar caràcter a caràcter, i cadascun l'encriptem utilitzant la taula de conversió
		for ( int index = 0; index < this.missatge.length; ++index )
		{
			int fila = (int) this.missatge[index] - Vigenere.index_inicial_ascii;
			int columna = (int) this.clau[index] - Vigenere.index_inicial_ascii;
			resultat_xifrat[index] = this.matriu_conversio[fila][columna];
		}
		this.missatge_xifrat = resultat_xifrat;
	}

	/**
	 * Mètode consultor del missatge xifrat.
	 * 
	 * @return Una cadena de caràcters que representa el missatge xifrat.
	 */
	public String getMissatgeXifrat()
	{
		return new String( this.missatge_xifrat );
	}

	/**
	 * Mètode consultor del missatge desxifrat, a partir del missatge xifrat. És a dir, partint del missatge xifrat,
	 * aplicant el procés de desxifrat, obté el missatge original.
	 * 
	 * @return Una cadena de caràcters que representa el missatge desxifrat (l'original).
	 */
	public String getMissatgeDesxifrat()
	{
		char[] resultat_desxifrat = new char[missatge.length];

		// Recorrem el missatge a desxifrar caràcter a caràcter, i cadascun el desencriptem utilitzant la taula de
		// conversió
		for ( int contador = 0; contador < resultat_desxifrat.length; ++contador )
		{
			// Identifiquem la columna corresponent de la taula de conversió per al desxifrat del caràcter actual
			int columna = (int) this.clau[contador] - Vigenere.index_inicial_ascii;

			// A partir de la columna corresponent, cerquem la fila corresponent de la taula de conversió per obtenir el
			// caràcter actual desxifrat correctament
			int fila = 0;
			while ( this.matriu_conversio[fila][columna] != this.missatge_xifrat[contador]
					&& fila < this.matriu_conversio.length )
			{
				++fila;
			}
			resultat_desxifrat[contador] = (char) ( fila + Vigenere.index_inicial_ascii );
		}

		return new String( resultat_desxifrat );

	}

	/**
	 * Mètode que crea, a partir del diccionari de caràcters pres com a vàlid pel procés de xifrat i desxifrat, la taula
	 * de conversió de caràcters (també coneguda com a taula de Vigenere), sense la qual no es podria dur a terme els
	 * processos d'encriptació de Vigenère.
	 * 
	 * @return Una matriu de caràcters que representa la taula de conversió (o taula de Vigenère).
	 */
	private char[][] generaMatriuConversio()
	{
		int contador;
		char diccionari_temporal[] = this.generaDiccionari();
		char auxiliar[] = new char[Vigenere.mida_diccionari * 2];
		for ( int index = 0; index < Vigenere.mida_diccionari; ++index )
		{
			auxiliar[index] = diccionari_temporal[index];
			auxiliar[index + Vigenere.mida_diccionari] = diccionari_temporal[index];
		}
		char[][] matriu_conversio = new char[Vigenere.mida_diccionari][Vigenere.mida_diccionari];
		for ( int fila = 0; fila < Vigenere.mida_diccionari; ++fila )
		{
			contador = 0;
			for ( int columna = 0; columna < Vigenere.mida_diccionari; ++columna )
			{
				matriu_conversio[fila][columna] = auxiliar[contador + fila];
				++contador;
			}
		}
		return matriu_conversio;
	}

	/**
	 * Mètode que genera el diccionari vàlid per a l'encriptació i desencriptació de missatges de text.
	 * <p>
	 * Està basat en la taula de caràcters ASCII, on s'agafa un subconjunt d'aquesta com a conjunt vàlid de caràcters a
	 * tenir en compte, tant pel xifrat i desxifrat de missatges com per a la construcció de la taula de conversió.
	 * <p>
	 * Tot missatge de text que contingui algun caràcter que no pertanyi al diccionari serà interpretat com a missatge
	 * incorrecte i, per tant, no se li podran aplicar els mecanismes d'encriptació.
	 * 
	 * @return Un array de caràcters que representa el diccionari de referència per acceptar missatges de text que
	 *         vulguin ser encriptats.
	 */
	private char[] generaDiccionari()
	{
		char[] diccionari = new char[Vigenere.mida_diccionari];
		for ( int index = Vigenere.index_inicial_ascii; index <= Vigenere.index_final_ascii; ++index )
		{
			diccionari[index - Vigenere.index_inicial_ascii] = (char) index;
		}
		return diccionari;
	}
}
