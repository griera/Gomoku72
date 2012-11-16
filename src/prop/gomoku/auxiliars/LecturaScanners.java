package prop.gomoku.auxiliars;

import java.util.Scanner;

/**
 * Classe qur implementa els mètodes de la interfície Lectura que permeten llegir dades entrades per teclat.
 * <p>
 * Llegeix la dada introduïda per teclat fins que arriba a un delimitador
 * @see java.util.Scanner
 */
public class LecturaScanners implements Lectura
{
	private Scanner scanner;
	
	/**
	 * Mètode contructor per defecte.
	 */
	public LecturaScanners()
	{
		this.scanner = new Scanner( System.in );
	}

	/**
	 * Mètode que ens permet llegir un caràcter.
	 * 
	 * @return caràcter llegit.
	 */
	public char llegirChar()
	{
		return this.scanner.next().charAt( 0 );
	}

	/**
	 * Mètode que ens permet llegir un byte.
	 * 
	 * @return byte llegit.
	 */
	public byte llegirByte()
	{
		return this.scanner.nextByte();
	}

	/**
	 * Mètode que ens permet llegir un short.
	 * 
	 * @return short llegit.
	 */
	public short llegirShort()
	{
		return this.scanner.nextShort();
	}

	/**
	 * Mètode que ens permet llegir un enter.
	 * 
	 * @return enter llegit.
	 */
	public int llegirInt()
	{
		return this.scanner.nextInt();
	}

	/**
	 * Mètode que ens permet llegir un long.
	 * 
	 * @return long llegit.
	 */
	public long llegirLong()
	{
		return this.scanner.nextLong();
	}

	/**
	 * Mètode que ens permet llegir un float.
	 * 
	 * @return float llegit.
	 */
	public float llegirFloat()
	{
		return this.scanner.nextFloat();
	}

	/**
	 * Mètode que ens permet llegir un double.
	 * 
	 * @return double llegit.
	 */
	public double llegirDouble()
	{
		return this.scanner.nextDouble();
	}

	/**
	 * Mètode que ens permet llegir un string.
	 * 
	 * @return string llegit.
	 */	
	public String llegirString()
	{
		return this.scanner.next();
	}
}
