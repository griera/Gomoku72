package prop.gomoku.domini.controladors;

import prop.cluster.domini.controladors.InteligenciaArtificial;
import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;

public class IAGomoku extends InteligenciaArtificial
{
	/*
	 * Donada una opci� v�lida de fer una l�nia de 5 fitxes per la fitxa estat, incrementar les seves opcions en funci� 
	 * del seu color.
	 */
	private void incrementaOpcions( EstatCasella estat, int[] opcions_linia )
	{
		switch ( estat )
		{
			case JUGADOR_A:
				++opcions_linia[0];
				break;

			case JUGADOR_B:
				++opcions_linia[1];
				break;

			default:
				break;
		}
	}

	/*
	 * Aquest m�tode calcula el nombre d'opcions que t� la fitxa estat d'aconseguir formar una l�nia de fitxes seguint
	 * la direcci� de la fila on es troba.
	 */
	private void computaFila( Tauler tauler, EstatCasella estat, int m, int x, int y, int[] opcions_linia )
	{
		int fitxes_seguides = 1;
		for ( int k = y + 1; k < m
				&& ( estat == tauler.getEstatCasella( x, k ) || 
				tauler.getEstatCasella( x, k ) == EstatCasella.BUIT ); ++k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				fitxes_seguides = 1;
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}

		for ( int k = y - 1; k >= 0
				&& ( estat == tauler.getEstatCasella( x, k ) || 
				tauler.getEstatCasella( x, k ) == EstatCasella.BUIT ); ++k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}
	}

	/*
	 * Aquest m�tode calcula el nombre d'opcions que t� la fitxa estat d'aconseguir formar una l�nia de fitxes seguint
	 * la direcci� de la columna on es troba.
	 */
	private void computaColumna( Tauler tauler, EstatCasella estat, int m, int x, int y, int[] opcions_linia )
	{
		int fitxes_seguides = 1;
		for ( int k = x + 1; k < m
				&& ( estat == tauler.getEstatCasella( k, y ) || 
				tauler.getEstatCasella( k, y ) == EstatCasella.BUIT ); ++k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				fitxes_seguides = 1;
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}

		for ( int k = x - 1; k >= 0
				&& ( estat == tauler.getEstatCasella( k, y ) || 
				tauler.getEstatCasella( k, y ) == EstatCasella.BUIT ); ++k )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}
	}

	/*
	 * Aquest m�tode calcula el nombre d'opcions que t� la fitxa estat d'aconseguir formar una l�nia de fitxes seguint
	 * la direcci� de la diagonal que es despla�a de nord-oest cap a sud-est.
	 */	
	private void computaDiagonalNoSe( Tauler tauler, EstatCasella estat, int m, int x, int y, int[] opcions_linia )
	{
		int fitxes_seguides = 1;
		for ( int k = x + 1, l = y + 1; k < m && l < m
				&& ( estat == tauler.getEstatCasella( k, l ) || 
				tauler.getEstatCasella( k, l ) == EstatCasella.BUIT ); ++k, ++l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				fitxes_seguides = 1;
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}

		for ( int k = x - 1, l = y - 1; k >= 0 && l >= 0
				&& ( estat == tauler.getEstatCasella( k, l ) || 
				tauler.getEstatCasella( k, l ) == EstatCasella.BUIT ); --k, --l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}
	}

	/*
	 * Aquest m�tode calcula el nombre d'opcions que t� la fitxa estat d'aconseguir formar una l�nia de fitxes seguint
	 * la direcci� de la diagonal que es despla�a de nord-est cap a sud-oest.
	 */	
	private void computaDiagonalNeSo( Tauler tauler, EstatCasella estat, int m, int x, int y, int[] opcions_linia )
	{
		int fitxes_seguides = 1;
		for ( int k = x - 1, l = y + 1; k < m && l < m
				&& ( estat == tauler.getEstatCasella( k, l ) || 
				tauler.getEstatCasella( k, l ) == EstatCasella.BUIT ); --k, ++l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				fitxes_seguides = 1;
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}

		for ( int k = x + 1, l = y - 1; k >= 0 && l >= 0
				&& ( estat == tauler.getEstatCasella( k, l ) || 
				tauler.getEstatCasella( k, l ) == EstatCasella.BUIT ); ++k, --l )
		{
			++fitxes_seguides;
			if ( fitxes_seguides >= 5 )
			{
				this.incrementaOpcions( estat, opcions_linia );
				break; // sortir del for
			}
		}
	}

	@Override
	public int funcioAvaluacio( Tauler tauler, EstatPartida estat_partida, int profunditat, EstatCasella fitxa_jugador )
	{
		if ( estat_partida == EstatPartida.EMPAT )
		{
			return 0;
		}
		else if ( estat_partida != EstatPartida.NO_FINALITZADA )
		{ // suposant que mai cridarem al m�tode per a un estat invalid!
			if ( profunditat % 2 == 1 )
			{
				return Integer.MAX_VALUE;
			}
			return Integer.MIN_VALUE;
		}
		else
		{
			int m = tauler.getMida();
			
			/*
			 * La posici� 0 d'aquest array representa les opcions de fer una l�nia per al jugador amb fitxes negre.
			 * La posici� 1 d'aquest array representa les opcions de fer una l�nia per al jugador amb fitxes blanques.
			 */
			int[] opcions_linia = new int[2];
			
			for ( int x = 0; x < m; ++x )
			{
				for ( int y = 0; y < m; ++y )
				{
					// Si ens trobem una fitxa
					if ( tauler.getEstatCasella( x, y ) != EstatCasella.BUIT )
					{
						EstatCasella estat = tauler.getEstatCasella( x, y );

						// Comprovaci� fila
						this.computaFila( tauler, estat, m, x, y, opcions_linia );

						// Comprovaci� columna
						this.computaColumna( tauler, estat, m, x, y, opcions_linia );

						// Comprovaci� diagonal nord-oest cap a sud-est
						this.computaDiagonalNoSe( tauler, estat, m, x, y, opcions_linia );

						// Comprovaci� diagonal nord-est cap a sud-oest
						this.computaDiagonalNeSo( tauler, estat, m, x, y, opcions_linia );

						// Final del recompte d'opcions per a una fitxa trobada en el tauler.
						// Continuem recorrent el tauler en busca de m�s fitxes per computar les seves opcions..
					}
				}
			}

			// Ja hem recorregut tot el tauler, per tant, ja hem fet totes les avaluacions
			// Ara nom�s cal computar els resultats obtinguts

			int avaluacio = opcions_linia[0] - opcions_linia[1];
			if ( fitxa_jugador == EstatCasella.JUGADOR_A )
			{
				return avaluacio;
			}
			return -avaluacio;
		}
	}
}
