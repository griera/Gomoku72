package prop.gomoku.tests;

import prop.gomoku.auxiliars.LecturaScanners;

public class Vigenere
{
	private static final int MIDA_DICCIONARI = 94;
	
	private char[] missatge;
	private char[] clau;
	private char[] missatge_xifrat; // resultat xifrat
	private char matriu[][];

	public Vigenere( String missatge, String clau )
	{
		this.missatge = missatge.toCharArray();
		char[] clau_temporal = clau.toCharArray();
		this.clau = new char[missatge.length()];
		int cont = 0;
		for ( int i = 0; i < missatge.length(); i++ )// For mete la clave multiples veces en 1 arreglo
		{
			this.clau[i] = clau_temporal[cont];
			cont++;
			if ( cont == clau_temporal.length )
				cont = 0;
		}
		
		// la clau ja s'ha guardat en un array de la mateixa dimensi� que la del missatge a xifrar
		this.matriu = generaMatriuConversio();// Generem la matriu de conversio de l'abecedari
		this.xifrar(); // xifrem el missatge
	}

	public void xifrar()
	{
		char[] resultat_xifrat = new char[missatge.length];
		for ( int cont = 0; cont < this.missatge.length; cont++ )
		{
			int i = (int) this.missatge[cont] - 33;
			int j = (int) this.clau[cont] - 33;
			resultat_xifrat[cont] = this.matriu[i][j];
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
		for ( int cont = 0; cont < resultat_desxifrat.length; cont++ )
		{
			int j = (int) this.clau[cont] - 33;
			int k = 0;
			while ( this.matriu[k][j] != this.missatge_xifrat[cont] && k < this.matriu.length )
			{
				++k;
			}
			resultat_desxifrat[cont] = (char) ( k + 33 );
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
		for ( int c = 0; c < MIDA_DICCIONARI; c++ )
		{
			abc[c] = diccionari_temporal[c];
			abc[c + MIDA_DICCIONARI] = diccionari_temporal[c];
		}
		char[][] matriu = new char[MIDA_DICCIONARI][MIDA_DICCIONARI];
		for ( int i = 0; i < MIDA_DICCIONARI; i++ )
		{
			contador = 0;
			for ( int j = 0; j < MIDA_DICCIONARI; j++ )
			{
				matriu[i][j] = abc[contador + i];
				contador++;
			}
		}
		return matriu;
	}

	private char[] generaDiccionari()
	{
		char[] diccionari = new char[MIDA_DICCIONARI];
		for ( int i = 33; i <= 126; i++ )
		{
			diccionari[i - 33] = (char) i;
		}
		return diccionari;
	}
	
	public static void main( String[] args )
	{
		System.out.println( "Benvinguts a la prova del programa de xifrat que utilitza l'algorisme Vigenere" );
		boolean surt = false;
		while ( !surt )
		{
			System.out.print( "Si us plau, introdueixi el missatge que desitjar xifrar: " );
			LecturaScanners dada = new LecturaScanners();
			String missatge = dada.llegirString();
			System.out.print( "\nSi us plau, introdueixi una clau qualsevol per a poder xifrar el missatge" +
					"(aquesta ha de tenir una mida igual o inferior a la delmissatge a xifrar): " );
			String clau = dada.llegirString();
			Vigenere prova = new Vigenere( missatge, clau );
			String missatge_xifrat = prova.getMissatgeXifrat();
			System.out.println( "\nAquest es el seu missatge xifrat: " + missatge_xifrat );
			String missatge_desxifrat = prova.getMissatgeDesxifrat();
			System.out.println( "\nAquest es el seu missatge desxifrat " +
					"(proces de desxifrat a partir del missatge xifrat): " + missatge_desxifrat );
			
			if ( missatge_desxifrat.equals( missatge ) )
			{
				System.out.println( "\nEl missatge original i el missatge desxifrat coincideixen" );
			}
			
			else
			{
				System.out.println( "\nEl missatge original i el missatge desxifrat NO coincideixen " +
						"==> Revisar la implementacio de la classe Vigenere" );
			}
			
			System.out.println();
			System.out.println( "Vol tornar a xifrar un altre missatge? [s/n]" );
			char resposta = dada.llegirChar();
			surt = (resposta == 's') ? false : true;
		}
	}
}
