package prop.gomoku.auxiliars;

public class Vigenere
{
	/**
	 * Indica el nombre de caràcters que formen el diccionari
	 */
	private static final int MIDA_DICCIONARI = 94;

	/**
	 * Índex de la taula de caràcters ASCII que correspon al primer caràcter vàlid del diccionari
	 */
	private static final int INDEX_INICIAL_ASCII = 33;

	/**
	 * Índex de la taula de caràcters ASCII que correspmason a l'últim caràcter vàlid del diccionari
	 */
	private static final int INDEX_FINAL_ASCII = 126;

	private char[] missatge;
	private char[] clau;
	private char[] missatge_xifrat; // resultat xifrat
	private char matriu_conversio[][];

	public Vigenere( String missatge, String clau )
	{
		this.missatge = missatge.toCharArray();
		char[] clau_temporal = clau.toCharArray();
		this.clau = new char[missatge.length()];
		int cont = 0;
		for ( int i = 0; i < missatge.length(); ++i )
		{
			this.clau[i] = clau_temporal[cont];
			cont++;
			if ( cont == clau_temporal.length )
				cont = 0;
		}

		// la clau ja s'ha guardat en un array de la mateixa dimensió que la del missatge a xifrar

		this.matriu_conversio = generaMatriuConversio();// Generem la matriu de conversio del diccionari
		this.xifrar();
	}

	public void xifrar()
	{
		char[] resultat_xifrat = new char[missatge.length];
		for ( int k = 0; k < this.missatge.length; ++k )
		{
			int fila = (int) this.missatge[k] - INDEX_INICIAL_ASCII;
			int columna = (int) this.clau[k] - INDEX_INICIAL_ASCII;
			resultat_xifrat[k] = this.matriu_conversio[fila][columna];
		}
		this.missatge_xifrat = resultat_xifrat;
	}

	public String getMissatgeXifrat()
	{
		String missatge_xifrat = "";
		for ( int i = 0; i < this.missatge_xifrat.length; ++i )
		{
			missatge_xifrat += this.missatge_xifrat[i];
		}
		return missatge_xifrat;
	}

	public String getMissatgeDesxifrat()
	{
		char[] resultat_desxifrat = new char[missatge.length];
		for ( int cont = 0; cont < resultat_desxifrat.length; ++cont )
		{
			int j = (int) this.clau[cont] - INDEX_INICIAL_ASCII;
			int k = 0;
			while ( this.matriu_conversio[k][j] != this.missatge_xifrat[cont] && k < this.matriu_conversio.length )
			{
				++k;
			}
			resultat_desxifrat[cont] = (char) ( k + INDEX_INICIAL_ASCII );
		}

		String missatge_desxifrat = "";
		for ( int i = 0; i < resultat_desxifrat.length; ++i )
		{
			missatge_desxifrat += resultat_desxifrat[i];
		}
		return missatge_desxifrat;

	}

	private char[][] generaMatriuConversio()
	{
		int contador;
		char diccionari_temporal[] = this.generaDiccionari();
		char abc[] = new char[diccionari_temporal.length * 2];
		for ( int i = 0; i < MIDA_DICCIONARI; ++i )
		{
			abc[i] = diccionari_temporal[i];
			abc[i + MIDA_DICCIONARI] = diccionari_temporal[i];
		}
		char[][] matriu_conversio = new char[MIDA_DICCIONARI][MIDA_DICCIONARI];
		for ( int i = 0; i < MIDA_DICCIONARI; ++i )
		{
			contador = 0;
			for ( int j = 0; j < MIDA_DICCIONARI; ++j )
			{
				matriu_conversio[i][j] = abc[contador + i];
				++contador;
			}
		}
		return matriu_conversio;
	}

	private char[] generaDiccionari()
	{
		char[] diccionari = new char[MIDA_DICCIONARI];
		for ( int i = INDEX_INICIAL_ASCII; i <= INDEX_FINAL_ASCII; ++i )
		{
			diccionari[i - INDEX_INICIAL_ASCII] = (char) i;
		}
		return diccionari;
	}

}
