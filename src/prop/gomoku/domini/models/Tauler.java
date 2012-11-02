package prop.gomoku.domini.models;

import prop.cluster.domini.models.estats.EstatCasella;

public abstract class Tauler
{
	protected int mida;
	protected EstatCasella[][] caselles;
	protected int num_fitxes_a;
	protected int num_fitxes_b;

	public Tauler( int mida )
	{
		this.mida = mida;
		this.caselles = new EstatCasella[mida][mida];
		this.num_fitxes_a = 0;
		this.num_fitxes_b = 0;
	}

	public Tauler( int mida, EstatCasella[][] caselles, int num_fitxes_a, int num_fitxes_b )
	{
		this.mida = mida;
		this.caselles = caselles;
		this.num_fitxes_a = num_fitxes_a;
		this.num_fitxes_b = num_fitxes_b;
	}

	public Tauler( Tauler original )
	{
		throw new IllegalArgumentException( "Classe no implementada" );
	}

	public int getMida()
	{
		return this.mida;
	}

	public boolean esCasellaValida( int fila, int columna )
	{
		if ( this.caselles[fila][columna] == EstatCasella.BUIT )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean esBuit()
	{
		if ( this.num_fitxes_a == 0 && this.num_fitxes_b == 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getNumFitxesA()
	{
		return this.num_fitxes_a;
	}

	public int getNumFitxesB()
	{
		return this.num_fitxes_b;
	}

	public int getTotalFitxes()
	{
		return this.num_fitxes_a + this.num_fitxes_b;
	}

	public EstatCasella getEstatCasella( int fila, int columna ) throws IndexOutOfBoundsException
	{
		if ( fila >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Fila indicada fora del tauler" );
		}
		if ( columna >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Col·lumna indicada fora del tauler" );
		}
		return this.caselles[fila][columna];

	}

	protected boolean setEstatCasella( EstatCasella nou_estat, int fila, int columna ) throws IndexOutOfBoundsException
	{
		if ( fila >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Fila indicada fora del tauler" );
		}
		if ( columna >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Col·lumna indicada fora del tauler" );
		}
		this.caselles[fila][columna] = nou_estat;
		return true;
	}

	public abstract boolean esMovimentValid( EstatCasella fitxa, int fila, int columna )
			throws IndexOutOfBoundsException, IllegalArgumentException;

	public boolean mouFitxa( EstatCasella fitxa, int fila, int columna ) throws IndexOutOfBoundsException,
			IllegalArgumentException
	{
		if ( fila >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Fila indicada fora del tauler" );
		}
		if ( columna >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Col·lumna indicada fora del tauler" );
		}
		if ( this.esMovimentValid( fitxa, fila, columna ) )
		{
			return this.setEstatCasella( fitxa, fila, columna );
		}
		else
		{
			return false;
		}
	}

	public boolean treuFitxa( int fila, int columna ) throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if ( fila >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Fila indicada fora del tauler" );
		}
		if ( columna >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Col·lumna indicada fora del tauler" );
		}
		if (this.caselles[fila][columna] == EstatCasella.BUIT) {
			throw new IllegalArgumentException("Casella ja buida");
		} else {
			this.caselles[fila][columna] = EstatCasella.BUIT;
			return true;
		}
	}

	public Object clone()
	{
		return caselles;
	}
}
