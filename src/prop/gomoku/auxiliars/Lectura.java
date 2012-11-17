package prop.gomoku.auxiliars;

/**
 * Interfície que proporciona les eines adequades per llegir qualsevol tipus de dada primitiva en Java.
 * 
 */
public interface Lectura
{
	public char llegirChar();

	public byte llegirByte();

	public short llegirShort();

	public int llegirInt();

	public long llegirLong();

	public float llegirFloat();

	public double llegirDouble();

	public String llegirString();
}
