package prop.gomoku.auxiliars;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Classe que implementa els mètodes de la interfície Lectura que permeten llegir dades entrades per teclat.
 * <p>
 * Llegeix tota la línia en la qual s'han introduït dades per teclat fins que es troba un salt de línia.
 * <p>
 * Tota la línia llegida s'interpreta coma una sola dada.
 * @see java.io.BufferedReader
 */
public class LecturaBuffers implements Lectura
{

	private BufferedReader in;

	/**
	 * 
	 */
	public LecturaBuffers()
	{
		this.in = new BufferedReader( new InputStreamReader( System.in ) );
	}

	/**
	 * 
	 */
	public char llegirChar()
	{
		char c = ' ';
		try
		{
			BufferedInputStream in = new BufferedInputStream( System.in );
			c = (char) in.read();
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );

			/* Ajuda a debugar errors del programa, imprimint una traça de la pila on es van empilant els mètodes
			 * cridats (la pila la gestiona la JVM) */
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * 
	 */
	public byte llegirByte()
	{
		String s = null;
		byte dada = 0;
		try
		{
			s = this.in.readLine();
			dada = Byte.parseByte( s );
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return dada;
	}

	/**
	 * 
	 */
	public short llegirShort()
	{
		String s = null;
		Short dada = null;
		try
		{
			s = this.in.readLine();
			dada = Short.parseShort( s );

		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return dada;
	}

	/**
	 * 
	 */
	public int llegirInt()
	{
		String s = null;
		int dada = 0;
		try
		{
			s = this.in.readLine();
			dada = Integer.parseInt( s );
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return dada;
	}

	/**
	 * 
	 */
	public long llegirLong()
	{
		String s = null;
		Long dada = (long) 0;
		try
		{
			s = this.in.readLine();
			dada = Long.parseLong( s );
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return dada;
	}

	/**
	 * 
	 */
	public float llegirFloat()
	{
		String s = null;
		Float dada = (float) 0;
		try
		{
			s = this.in.readLine();
			dada = Float.parseFloat( s );
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return dada;
	}

	/**
	 * 
	 */
	public double llegirDouble()
	{
		String s = null;
		Double dada = (double) 0;
		try
		{
			s = this.in.readLine();
			dada = Double.parseDouble( s );
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return dada;
	}

	/**
	 * 
	 */
	public String llegirString()
	{
		String s = null;
		try
		{
			s = this.in.readLine();
		} catch ( Exception e )
		{
			System.out.println( "Error durant la lectura per teclat" );
			e.printStackTrace();
		}
		return s;
	}
}
