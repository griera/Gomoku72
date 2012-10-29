package prop.gomoku.domini.controladors;

import prop.gomoku.domini.models.EstatCasella;
import prop.gomoku.domini.models.EstatPartida;
import prop.gomoku.domini.models.Tauler;

public class IAGomoku extends InteligenciaArtificial
{

	@Override
	public int funcioAvaluacio( Tauler tauler, EstatPartida estat_partida, int profunditat, EstatCasella fitxa_jugador )
	{
		if ( estat_partida == EstatPartida.EMPAT )
			return 0;
		else if ( estat_partida != EstatPartida.NO_FINALITZADA )
		{ // suposant que mai cridarem al mètode per a un estat invalid!
			if ( profunditat % 2 == 1 )
				return Integer.MAX_VALUE;
			return Integer.MIN_VALUE;
		}
		else
		{
			int m = tauler.getMida();
			int opcio_linia_negra = 0, opcio_linia_blanca = 0;
			for ( int x = 0; x < m; ++x )
			{
				for ( int y = 0; y < m; ++y )
				{
					// Si ens trobem una fitxa
					if ( tauler.getEstatCasella( x, y ) != EstatCasella.BUIT )
					{
						int fitxes_seguides = 1;
						EstatCasella estat = tauler.getEstatCasella( x, y );

						// Comprovació fila
						for ( int k = y + 1; k < m
								&& ( estat == tauler.getEstatCasella( x, k ) || tauler.getEstatCasella( x, k ) == EstatCasella.BUIT ); ++k )
						{
							++fitxes_seguides;
							if ( fitxes_seguides >= 5 )
							{
								fitxes_seguides = 1;
								switch ( estat )
								{
									case JUGADOR_A:
										++opcio_linia_negra;
										break;

									case JUGADOR_B:
										++opcio_linia_blanca;
										break;
									default:
										break;
								}
								break; // sortir del for
							}
						}

						for ( int k = y - 1; k >= 0
								&& ( estat == tauler.getEstatCasella( x, k ) || tauler.getEstatCasella( x, k ) == EstatCasella.BUIT ); ++k )
						{
							++fitxes_seguides;
							if ( fitxes_seguides >= 5 )
							{
								switch ( estat )
								{
									case JUGADOR_A:
										++opcio_linia_negra;
										break;

									case JUGADOR_B:
										++opcio_linia_blanca;
										break;
									default:
										break;

								}
								break; // sortir del for
							}
						}

						// Canviarem de direcció
						fitxes_seguides = 1;

						// Comprovació columna
						// Comprovació diagonal esquerre-dreta
						// Comprovació diagonal dreta-esquerre
					}
				}
			}
			int avaluacio = opcio_linia_negra - opcio_linia_blanca;
			if ( fitxa_jugador == EstatCasella.JUGADOR_A )
				return avaluacio;
			return -avaluacio;
		}
	}

}
