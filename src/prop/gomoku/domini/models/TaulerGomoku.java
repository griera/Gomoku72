package prop.gomoku.domini.models;

import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.estats.EstatCasella;

public class TaulerGomoku extends Tauler
{

	public TaulerGomoku( int mida )
	{
		super( mida );
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esMovimentValid( EstatCasella fitxa, int fila, int columna ) throws IndexOutOfBoundsException,
			IllegalArgumentException
	{
		// TODO Auto-generated method stub
		return false;
	}

}
