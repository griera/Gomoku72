package prop.gomoku.domini.controladors;

import prop.gomoku.domini.models.EstatCasella;
import prop.gomoku.domini.models.EstatMoviment;
import prop.gomoku.domini.models.Tauler;


public abstract class InteligenciaArtificial
{

    private int max( int a, int b )
    {
        if ( a < b )
            return b;
        return a;
    }

    private int min( int a, int b )
    {
        if ( a < b )
            return a;
        return b;
    }

    private EstatCasella intercanviaEstatCasella( EstatCasella estat )
    {
        if ( estat == EstatCasella.JUGADOR_A )
        {
            return EstatCasella.JUGADOR_B;
        }
        return EstatCasella.JUGADOR_A;
    }

    public abstract int func_aval( Tauler tauler, EstatMoviment estat_moviment );

    public int[] minimax( Tauler tauler, EstatCasella estat_casella,
            int profunditat_max )
    {
        int[] millorMoviment =
        { -1, -1 };
        int maxim, maxim_actual;
        maxim = -10;
        int m = tauler.getMida();
        int profunditat = 0;
        for ( int i = 0; i < m; ++i )
        {
            for ( int j = 0; j < m; ++j )
            {
                EstatMoviment em = tauler.setCasella( i, j, estat_casella );
                if ( em == EstatMoviment.GUANYADOR || em == EstatMoviment.EMPAT
                        || em == EstatMoviment.VALID )
                {

                    // System.out.println("TORN => " +
                    // (t.getNumIntersOcupades()));
                    // System.out.println("Maxim global en la iteracio [" + i +
                    // "][" + j + "] = " + maxim);
                    // pintaTauler(t, m);

                    // System.out.println("Color fitxa de totes les meves posibilitats dins del meu torn => "
                    // + estat_casella.toString());
                    estat_casella = this
                            .intercanviaEstatCasella( estat_casella );
                    // System.out.println("Color fitxa en la següent jugada => "
                    // + estat_casella.toString());
                    maxim_actual = this.valorMax( tauler, em, -10, 10,
                            estat_casella, profunditat + 1, profunditat_max );
                    // System.out.println("Maxim actual en la iteracio [" + i +
                    // "][" + j + "] = " + maxim_actual + "\n");
                    if ( maxim_actual > maxim )
                    {
                        maxim = maxim_actual;
                        millorMoviment[0] = i;
                        millorMoviment[1] = j;
                    }
                    tauler.setCasella( i, j, EstatCasella.BUIT );
                    estat_casella = intercanviaEstatCasella( estat_casella );
                }
            }
        }
        return millorMoviment;
    }

    private int valorMax( Tauler tauler, EstatMoviment estat_moviment,
            int alfa, int beta, EstatCasella estat_casella, int profunditat,
            int profunditat_max )
    {
        if ( profunditat == profunditat_max
                || estat_moviment == EstatMoviment.GUANYADOR
                || estat_moviment == EstatMoviment.EMPAT )
            return func_aval( tauler, estat_moviment );
        else
        {
            int m = tauler.getMida();
            for ( int i = 0; i < m; ++i )
            {
                for ( int j = 0; j < m; ++j )
                {
                    EstatMoviment em = tauler.setCasella( i, j, estat_casella );
                    if ( em == EstatMoviment.GUANYADOR
                            || em == EstatMoviment.EMPAT
                            || em == EstatMoviment.VALID )
                    {
                        // System.out.println("ESTIC A MAX => TORN => " +
                        // (t.getNumIntersOcupades()));
                        // pintaTauler(t, m);

                        // System.out.println("Color fitxa de totes les meves posibilitats dins del meu torn => "
                        // + estat_casella.toString());
                        estat_casella = intercanviaEstatCasella( estat_casella );
                        // System.out.println("Color fitxa en la següent jugada => "
                        // + estat_casella.toString());
                        alfa = max( alfa, this.valorMin( tauler, em, alfa,
                                beta, estat_casella, ( profunditat + 1 ),
                                profunditat_max ) );
                        tauler.setCasella( i, j, EstatCasella.BUIT ); // a la
                                                                      // tornada
                                                                      // enrere
                                                                      // cal
                                                                      // esborrar
                                                                      // la
                                                                      // fitxa
                                                                      // que
                                                                      // acabem
                                                                      // de
                                                                      // posar
                        if ( alfa >= beta )
                            return beta;
                        estat_casella = intercanviaEstatCasella( estat_casella );
                    }
                }
            }
            return alfa;
        }
    }

    private int valorMin( Tauler tauler, EstatMoviment estat_moviment,
            int alfa, int beta, EstatCasella estat_casella, int profunditat,
            int profunditat_max )
    {
        if ( profunditat == profunditat_max
                || estat_moviment == EstatMoviment.GUANYADOR
                || estat_moviment == EstatMoviment.EMPAT )
            return func_aval( tauler, estat_moviment );
        else
        {
            int m = tauler.getMida();
            for ( int i = 0; i < m; ++i )
            {
                for ( int j = 0; j < m; ++j )
                {
                    EstatMoviment em = tauler.setCasella( i, j, estat_casella );
                    if ( em == EstatMoviment.GUANYADOR
                            || em == EstatMoviment.EMPAT
                            || em == EstatMoviment.VALID )
                    {

                        // System.out.println("ESTIC A MIN => TORN => " +
                        // (t.getNumIntersOcupades()));
                        // pintaTauler(t, m);

                        // System.out.println("Color fitxa de totes les meves posibilitats dins del meu torn => "
                        // + estat_casella.toString());
                        estat_casella = this
                                .intercanviaEstatCasella( estat_casella );
                        // System.out.println("Color fitxa en la següent jugada => "
                        // + estat_casella.toString());
                        beta = min( beta, this.valorMax( tauler, em, alfa,
                                beta, estat_casella, ( profunditat + 1 ),
                                profunditat_max ) );
                        tauler.setCasella( i, j, EstatCasella.BUIT ); // a la
                                                                      // tornada
                                                                      // enrere
                                                                      // cal
                                                                      // esborrar
                                                                      // la
                                                                      // fitxa
                                                                      // que
                                                                      // acabem
                                                                      // de
                                                                      // posar
                        if ( alfa >= beta )
                            return alfa;
                        estat_casella = this
                                .intercanviaEstatCasella( estat_casella );
                    }
                }
            }
            return beta;
        }
    }
}
