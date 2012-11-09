package prop.gomoku.domini.models;

import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.estats.EstatCasella;

public class TaulerGomoku extends Tauler
{

	public TaulerGomoku()
	{
		super( 15 );
	}

	public boolean teFitxesSeguides( int fila, int columna, int limit, EstatCasella estat )
	{
		int fitxes_seguides = 1;
		int mida = this.mida;

		// Comprovaci� fila
		for ( int k = columna + 1; k < mida && estat == this.getEstatCasella( fila, k ); ++k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}
		
		for ( int k = columna - 1; k >= 0 && estat == this.getEstatCasella( fila, k ); --k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}

		// Comprovaci� col�lumna
		fitxes_seguides = 1;
		for ( int k = fila + 1; k < mida && estat == this.getEstatCasella( k, columna ); ++k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}
		
		for ( int k = fila - 1; k >= 0 && estat == this.getEstatCasella( k, columna ); --k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}

		// Comprovaci� diagonal nord-oest cap a sud-est
		fitxes_seguides = 1;
		for ( int k = fila + 1, l = columna + 1; k < mida && l < mida && estat == this.getEstatCasella( k, l ); ++k, ++l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}
		
		for ( int k = fila - 1, l = columna - 1; k >= 0 && l >= 0 && estat == this.getEstatCasella( k, l ); --k, --l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}

		// Comprovaci� diagonal nord-est cap a sud-oest
		fitxes_seguides = 1;
		for ( int k = fila - 1, l = columna + 1; k >= 0 && l < mida && estat == this.getEstatCasella( k, l ); --k, ++l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}
		
		for ( int k = fila + 1, l = columna - 1; k < mida && l >= 0 && estat == this.getEstatCasella( k, l ); ++k, --l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= limit )
			{
				return true;
			}
		}
		return false;
>>>>>>> remotes/genisrieraperez/genis-fork-gomoku/master
	}

	@Override
	public boolean esMovimentValid( EstatCasella fitxa, int fila, int columna ) throws IndexOutOfBoundsException,
			IllegalArgumentException
	{
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return false;
=======
		if ( fila < 0 || fila >= this.getMida() || columna < 0 || columna >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Posici� indicada fora del tauler" );
		}

		else if ( fitxa == EstatCasella.BUIDA )
		{
			if ( this.esBuit() )
			{
				return false;
				//throw new IllegalArgumentException( "No es pot treure cap fitxa pequ� el tauler est� buit" );
			}

			else if ( this.getEstatCasella( fila, columna ) == fitxa )
			{
				return false;
				//throw new IllegalArgumentException( "La casella ja est� buida" );
			}

			else
			{
				return true;
			}
		}

		else if ( this.getEstatCasella( fila, columna ) != EstatCasella.BUIDA )
		{
			return false;
			//throw new IllegalArgumentException( "La casella (" + fila + ", " + columna + ") ja est� ocupada" );
		}

		else if ( this.teFitxesSeguides( fila, columna, 6, fitxa ) )
		{
			return false;
			//throw new IllegalArgumentException( "No poden haver l�nies de m�s de cinc fitxes seguides" );
		}

		return true;
>>>>>>> remotes/genisrieraperez/genis-fork-gomoku/master
	}

}
