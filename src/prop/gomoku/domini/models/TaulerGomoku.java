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

		// Comprovació fila
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

		// Comprovació col·lumna
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

		// Comprovació diagonal nord-oest cap a sud-est
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

		// Comprovació diagonal nord-est cap a sud-oest
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
	}

	@Override
	public boolean esMovimentValid( EstatCasella fitxa, int fila, int columna ) throws IndexOutOfBoundsException,
			IllegalArgumentException
	{
		if ( fila < 0 || fila >= this.getMida() || columna < 0 || columna >= this.getMida() )
		{
			throw new IndexOutOfBoundsException( "Posició indicada fora del tauler" );
		}

		else if ( fitxa == EstatCasella.BUIDA )
		{
			if ( this.esBuit() )
			{
				throw new IllegalArgumentException( "No es pot treure cap fitxa pequè el tauler està buit" );
			}

			else if ( this.getEstatCasella( fila, columna ) == fitxa )
			{
				throw new IllegalArgumentException( "La casella (" + fila + ", " + columna + ") ja està buida" );
			}

			else
			{
				return true;
			}
		}

		else if ( this.getEstatCasella( fila, columna ) != EstatCasella.BUIDA )
		{
			throw new IllegalArgumentException( "La casella (" + fila + ", " + columna + ") ja està ocupada" );
		}

		else if ( this.teFitxesSeguides( fila, columna, 6, fitxa ) )
		{
			throw new IllegalArgumentException( "No poden haver línies de més de cinc fitxes seguides" );
		}

		return true;
	}
	
	public void pinta()
	{
		int mida = this.getMida();
		System.out.print( "   " );
		for ( int k = 0; k < mida; ++k )
		{
			System.out.print( " " );
			if ( k <= 9 )
			{
				System.out.print( " " + k );
			}

			else
			{
				System.out.print( k );
			}
		}

		System.out.println();
		for ( int fila = 0; fila < mida; ++fila )
		{
			System.out.print( fila + ":  " );
			if ( fila <= 9 )
			{
				System.out.print( " " );
			}

			for ( int columna = 0; columna < mida; ++columna )
			{
				EstatCasella estat = this.getEstatCasella( fila, columna );
				switch ( estat )
				{
					case BUIDA:
						System.out.print( ".  " );
						break;

					case JUGADOR_A:
						System.out.print( "X  " );
						break;

					case JUGADOR_B:
						System.out.print( "O  " );
						break;
				}

			}
			System.out.println();
		}
		System.out.println();
	}
}
