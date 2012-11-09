package prop.gomoku.domini.controladors;

import prop.cluster.domini.controladors.InteligenciaArtificial;
import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.models.TaulerGomoku;

public class IAGomoku extends InteligenciaArtificial
{
	private void estructures2D( EstatCasella estat, int[][] opcions_linia )
	{
		int color = ( estat == EstatCasella.JUGADOR_A ) ? 0 : 1;

		if ( opcions_linia[color][6] > 1 )
		{
			opcions_linia[color][2] = opcions_linia[color][6];
			opcions_linia[color][6] = 0;
		}

		else if ( opcions_linia[color][6] >= 1 && opcions_linia[color][7] >= 1 )
		{
			++opcions_linia[color][3];
			opcions_linia[color][6] = 0;
			opcions_linia[color][7] = 0;
		}

		else if ( opcions_linia[color][7] > 1 )
		{
			++opcions_linia[color][4];
			opcions_linia[color][7] = 0;
		}

		else if ( opcions_linia[color][7] >= 1 && opcions_linia[color][9] >= 1 )
		{
			++opcions_linia[color][5];
			opcions_linia[color][7] = 0;
			opcions_linia[color][9] = 0;
		}

		else if ( opcions_linia[color][11] > 1 )
		{
			++opcions_linia[color][8];
			opcions_linia[color][11] = 0;
		}

		else if ( opcions_linia[color][12] > 1 )
		{
			++opcions_linia[color][10];
			opcions_linia[color][12] = 0;
		}
	}

	private void incrementaOpcions( EstatCasella estat, int[][] opcions_linia, boolean oberta, boolean semioberta,
			boolean linia_amb_forat, int fitxes_seguides )
	{
		int estructura = 0; // posicio de 5 en linia

		switch ( fitxes_seguides )
		{
			case 4:
				estructura = ( oberta ) ? 1 : 6;
				break;

			case 3:
				estructura = ( oberta ) ? 7 : 9;
				break;

			case 2:
				if ( linia_amb_forat && oberta )
				{
					estructura = 13;
				}

				else
				{
					estructura = ( oberta ) ? 11 : 12;
				}
				break;

			default:
				break;
		}

		int color = ( estat == EstatCasella.JUGADOR_A ) ? 0 : 1;
		++opcions_linia[color][estructura];
	}

	private void computaFila( TaulerGomoku tauler, EstatCasella estat, int m, int x, int y, int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		boolean linia_oberta_dreta = false;
		boolean linia_oberta_esquerra = false;
		boolean linia_amb_forat = false;
		int k = y + 1;
		while ( k < m && estat == tauler.getEstatCasella( x, k ) )
		{
			++fitxes_seguides;
			++k;
		}

		if ( k < m && tauler.getEstatCasella( x, k ) == EstatCasella.BUIDA )
		{
			linia_oberta_dreta = true;
			if ( k + 1 < m && tauler.getEstatCasella( x, k + 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = y - 1;
		while ( k >= 0 && estat == tauler.getEstatCasella( x, k ) )
		{
			++fitxes_seguides;
			--k;
		}
		if ( k >= 0 && tauler.getEstatCasella( x, k ) == EstatCasella.BUIDA )
		{
			linia_oberta_esquerra = true;
			if ( k - 1 >= 0 && tauler.getEstatCasella( x, k - 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_dreta && linia_oberta_esquerra; // linia completament oberta?
		boolean semioberta = linia_oberta_dreta || linia_oberta_esquerra; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			incrementaOpcions( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	private void computaColumna( TaulerGomoku tauler, EstatCasella estat, int m, int x, int y, int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		boolean linia_oberta_inferior = false;
		boolean linia_oberta_superior = false;
		boolean linia_amb_forat = false;
		int k = x + 1;
		while ( k < m && estat == tauler.getEstatCasella( k, y ) )
		{
			++fitxes_seguides;
			++k;
		}

		if ( k < m && tauler.getEstatCasella( k, y ) == EstatCasella.BUIDA )
		{
			linia_oberta_inferior = true;
			if ( k + 1 < m && tauler.getEstatCasella( k + 1, y ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = x - 1;
		while ( k >= 0 && estat == tauler.getEstatCasella( k, y ) )
		{
			++fitxes_seguides;
			--k;
		}

		if ( k >= 0 && tauler.getEstatCasella( k, y ) == EstatCasella.BUIDA )
		{
			linia_oberta_superior = true;
			if ( k - 1 >= 0 && tauler.getEstatCasella( k - 1, y ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_superior && linia_oberta_inferior; // linia completament oberta?
		boolean semioberta = linia_oberta_superior || linia_oberta_inferior; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			incrementaOpcions( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	private void computaDiagonalNoSe( TaulerGomoku tauler, EstatCasella estat, int m, int x, int y,
			int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		boolean linia_oberta_sudest = false;
		boolean linia_oberta_nordoest = false;
		boolean linia_amb_forat = false;
		int k = x + 1;
		int l = y + 1;
		while ( k < m && l < m && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			++k;
			++l;
		}

		if ( k < m && l < m && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_sudest = true;
			if ( k + 1 < m && l + 1 < m && tauler.getEstatCasella( k + 1, l + 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = x - 1;
		l = y - 1;
		while ( k >= 0 && l >= 0 && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			--k;
			--l;
		}

		if ( k >= 0 && l >= 0 && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_nordoest = true;
			if ( k - 1 >= 0 && l - 1 >= 0 && tauler.getEstatCasella( k - 1, l - 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_sudest && linia_oberta_nordoest; // linia completament oberta?
		boolean semioberta = linia_oberta_sudest || linia_oberta_nordoest; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			incrementaOpcions( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	private void computaDiagonalNeSo( TaulerGomoku tauler, EstatCasella estat, int m, int x, int y,
			int[][] opcions_linia )
	{
		int fitxes_seguides = 1;
		boolean linia_oberta_nordest = false;
		boolean linia_oberta_sudoest = false;
		boolean linia_amb_forat = false;
		int k = x - 1;
		int l = y + 1;
		while ( k >= 0 && l < m && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			--k;
			++l;
		}

		if ( k >= 0 && l < m && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_nordest = true;
			if ( k - 1 >= 0 && l + 1 < m && tauler.getEstatCasella( k - 1, l + 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		k = x + 1;
		l = y - 1;
		while ( k < m && l >= 0 && estat == tauler.getEstatCasella( k, l ) )
		{
			++fitxes_seguides;
			++k;
			--l;
		}

		if ( k < m && l >= 0 && tauler.getEstatCasella( k, l ) == EstatCasella.BUIDA )
		{
			linia_oberta_sudoest = true;
			if ( k + 1 < m && l - 1 >= 0 && tauler.getEstatCasella( k + 1, l - 1 ) == estat )
			{
				linia_amb_forat = true;
			}
		}

		boolean oberta = linia_oberta_nordest && linia_oberta_sudoest; // linia completament oberta?
		boolean semioberta = linia_oberta_nordest || linia_oberta_sudoest; // línia semioberta?
		if ( fitxes_seguides > 1 && ( oberta || semioberta ) )
		{
			incrementaOpcions( estat, opcions_linia, oberta, semioberta, linia_amb_forat, fitxes_seguides );
		}
	}

	public int funcioAvaluacio( Tauler tauler, EstatPartida estat_moviment, int profunditat, EstatCasella fitxa_jugador )
	{
		if ( estat_moviment == EstatPartida.EMPAT )
		{
			return 0;
		}

		else if ( estat_moviment != EstatPartida.NO_FINALITZADA && profunditat % 2 == 1 )
		{
			return Integer.MAX_VALUE;
		}

		else if ( estat_moviment != EstatPartida.NO_FINALITZADA && profunditat % 2 == 0 )
		{
			return Integer.MIN_VALUE;
		}

		else
		{
			int mida = tauler.getMida();
			int[] puntuacions = { Integer.MAX_VALUE, 10000, 10000, 10000, 5000, 1000, 500, 200, 100, 50, 10, 5, 3, 5000 };
			int[][] opcions_linia;
			int opcions_linia_total[][] = new int[2][14];
			int avaluacio_negre = 0;
			int avaluacio_blanc = 0;
			for ( int x = 0; x < mida; ++x )
			{
				for ( int y = 0; y < mida; ++y )
				{
					// Si ens trobem una fitxa
					if ( tauler.getEstatCasella( x, y ) != EstatCasella.BUIDA )
					{
						EstatCasella estat = tauler.getEstatCasella( x, y );
						opcions_linia = new int[2][14];
						// Comprovació fila
						computaFila( (TaulerGomoku) tauler, estat, mida, x, y, opcions_linia );

						// Comprovació columna
						computaColumna( (TaulerGomoku) tauler, estat, mida, x, y, opcions_linia );

						// Comprovació diagonal nord-oest cap a sud-est
						computaDiagonalNoSe( (TaulerGomoku) tauler, estat, mida, x, y, opcions_linia );

						// Comprovació diagonal nord-est cap a sud-oest
						computaDiagonalNeSo( (TaulerGomoku) tauler, estat, mida, x, y, opcions_linia );

						estructures2D( estat, opcions_linia );

						for ( int i = 0; i < opcions_linia_total.length; ++i )
						{
							for ( int j = 0; j < opcions_linia_total[0].length; ++j )
							{
								opcions_linia_total[i][j] += opcions_linia[i][j];
							}
						}

						if ( opcions_linia[0][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_A )
						{
							return Integer.MAX_VALUE;
						}

						if ( opcions_linia[0][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_B )
						{
							return Integer.MIN_VALUE;
						}

						if ( opcions_linia[1][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_A )
						{
							return Integer.MIN_VALUE;
						}

						if ( opcions_linia[1][0] > 0 && fitxa_jugador == EstatCasella.JUGADOR_B )
						{
							return Integer.MAX_VALUE;
						}

						for ( int i = 0; i < puntuacions.length; ++i )
						{
							avaluacio_negre += ( opcions_linia[0][i] * puntuacions[i] );
							avaluacio_blanc += ( opcions_linia[1][i] * puntuacions[i] );
						}

						// Final del recompte d'opcions per a una fitxa trobada en el tauler.
						// Continuem recorrent el tauler en busca de més fitxes per computar les seves opcions...
					}
				}
			}

			if ( fitxa_jugador == EstatCasella.JUGADOR_A && tauler.getTotalFitxes() % 2 == 1 )
			{
				if ( opcions_linia_total[1][0] > 0 || opcions_linia_total[1][1] > 0 || opcions_linia_total[1][2] > 0
						|| opcions_linia_total[1][3] > 0 || opcions_linia_total[1][6] > 0 )
				{
					return Integer.MIN_VALUE;
				}

				if ( opcions_linia_total[0][0] > 0 || opcions_linia_total[0][1] > 0 || opcions_linia_total[0][2] > 0
						|| opcions_linia_total[0][3] > 0 )
				{
					return Integer.MAX_VALUE;
				}

				if ( opcions_linia_total[1][4] > 0 || opcions_linia_total[1][5] > 0 || opcions_linia_total[1][6] > 0
						|| opcions_linia_total[1][7] > 0 || opcions_linia_total[1][13] > 0 )
				{
					return Integer.MIN_VALUE;
				}
			}

			if ( fitxa_jugador == EstatCasella.JUGADOR_A && tauler.getTotalFitxes() % 2 == 0 )
			{
				if ( opcions_linia_total[0][0] > 0 || opcions_linia_total[0][1] > 0 || opcions_linia_total[0][2] > 0
						|| opcions_linia_total[0][3] > 0 )
				{
					return Integer.MAX_VALUE;
				}

				if ( opcions_linia_total[1][0] > 0 || opcions_linia_total[1][1] > 0 || opcions_linia_total[1][2] > 0
						|| opcions_linia_total[1][3] > 0 )
				{
					return Integer.MIN_VALUE;
				}

				if ( opcions_linia_total[0][4] > 0 || opcions_linia_total[0][5] > 0 || opcions_linia_total[0][6] > 0
						|| opcions_linia_total[0][7] > 0 || opcions_linia_total[0][13] > 0 )
				{
					return Integer.MAX_VALUE;
				}

				if ( opcions_linia_total[1][4] > 0 || opcions_linia_total[1][5] > 0 || opcions_linia_total[1][6] > 0
						|| opcions_linia_total[1][7] > 0 || opcions_linia_total[1][13] > 0 )
				{
					return Integer.MIN_VALUE;
				}

			}

			if ( fitxa_jugador == EstatCasella.JUGADOR_B && tauler.getTotalFitxes() % 2 == 0 )
			{
				if ( opcions_linia_total[0][0] > 0 || opcions_linia_total[0][1] > 0 || opcions_linia_total[0][2] > 0
						|| opcions_linia_total[0][3] > 0 || opcions_linia_total[0][6] > 0 )
				{
					return Integer.MIN_VALUE;
				}

				if ( opcions_linia_total[1][0] > 0 || opcions_linia_total[1][1] > 0 || opcions_linia_total[1][2] > 0
						|| opcions_linia_total[1][3] > 0 )
				{
					return Integer.MAX_VALUE;
				}

				if ( opcions_linia_total[0][4] > 0 || opcions_linia_total[0][5] > 0 || opcions_linia_total[0][6] > 0
						|| opcions_linia_total[0][7] > 0 || opcions_linia_total[0][13] > 0 )
				{
					return Integer.MIN_VALUE;
				}
			}

			if ( fitxa_jugador == EstatCasella.JUGADOR_B && tauler.getTotalFitxes() % 2 == 1 )
			{
				if ( opcions_linia_total[1][0] > 0 || opcions_linia_total[1][1] > 0 || opcions_linia_total[1][2] > 0
						|| opcions_linia_total[1][3] > 0 )
				{
					return Integer.MAX_VALUE;
				}

				if ( opcions_linia_total[0][0] > 0 || opcions_linia_total[0][1] > 0 || opcions_linia_total[0][2] > 0
						|| opcions_linia_total[0][3] > 0 )
				{
					return Integer.MIN_VALUE;
				}

				if ( opcions_linia_total[1][4] > 0 || opcions_linia_total[1][5] > 0 || opcions_linia_total[1][6] > 0
						|| opcions_linia_total[1][7] > 0 || opcions_linia_total[1][13] > 0 )
				{
					return Integer.MAX_VALUE;
				}

				if ( opcions_linia_total[0][4] > 0 || opcions_linia_total[0][5] > 0 || opcions_linia_total[0][6] > 0
						|| opcions_linia_total[0][7] > 0 || opcions_linia_total[0][13] > 0 )
				{
					return Integer.MIN_VALUE;
				}
			}

			// Ja hem recorregut tot el tauler, per tant, ja hem fet totes les avaluacions
			// Ara només cal computar els resultats obtinguts

			if ( fitxa_jugador == EstatCasella.JUGADOR_A )
			{
				return avaluacio_negre - avaluacio_blanc;
			}
			return avaluacio_blanc - avaluacio_negre;
		}
	}
}
