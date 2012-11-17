package prop.gomoku.domini.controladors;

import prop.cluster.domini.models.Usuari;
import prop.cluster.domini.models.estats.EstatCasella;
import prop.cluster.domini.models.estats.EstatPartida;
import prop.gomoku.domini.models.PartidaGomoku;
import prop.gomoku.domini.models.TaulerGomoku;

public class ControladorPartidaEnJoc
{
	private int profunditat_maxima = 3;
	private PartidaGomoku partida;
	private IAGomoku ia;
	int fila_ult_moviment;
	int columna_ult_moviment;

	public ControladorPartidaEnJoc( PartidaGomoku partida )
	{
		this.partida = partida;
		this.ia = new IAGomoku();
		this.fila_ult_moviment = 0;
		this.columna_ult_moviment = 0;
	}

	public ControladorPartidaEnJoc( Usuari jugador_a, Usuari jugador_b, String nom_partida )
	{
		this.partida = new PartidaGomoku(jugador_a, jugador_b, new TaulerGomoku(), nom_partida);
		this.ia = new IAGomoku();
		this.fila_ult_moviment = 0;
		this.columna_ult_moviment = 0;
	}
	
	public PartidaGomoku getPartida()
	{
		return this.partida;
	}

	public Usuari getJugadorActual()
	{
		int torn_actual = this.getTornActual();
		if ( torn_actual % 2 == 1 )
		{
			return this.getPartida().getJugadorA();
		}
		else
		{
			return this.getPartida().getJugadorB();
		}
	}

	public int getTornActual()
	{
		return this.partida.getTornsJugats() + 1;
	}

	public int[] getMovimentMaquina()
	{
		int[] moviment_ia = new int[2];
		int mida = this.getPartida().getTauler().getMida();

		if ( this.getTornActual() == 1 )
		{
			moviment_ia[0] = ( mida / 2 );
			moviment_ia[1] = ( mida / 2 );
		}

		else if ( this.getTornActual() == 2 )
		{
			moviment_ia = ia.movimentAdjacentAleatori( this.fila_ult_moviment, this.columna_ult_moviment, mida );
		}

		else
		{
			moviment_ia = this.ia.minimax( this.partida, this.getColorActual(), this.profunditat_maxima );
		}

		if ( moviment_ia[0] == -1 && moviment_ia[1] == -1 )
		{
			System.out.print( this.getJugadorActual().getNom() + "s'ha rendit perquè ja ha perdut matemàticament." );
			System.out.println( " Farà un moviment aleatori:" );
			TaulerGomoku tauler = (TaulerGomoku) this.getPartida().getTauler();
			moviment_ia = ia.movimentAleatori( tauler );
		}

		this.fila_ult_moviment = moviment_ia[0];
		this.columna_ult_moviment = moviment_ia[1];

		return moviment_ia;
	}

	public EstatCasella getColorActual()
	{
		if ( this.getTornActual() % 2 == 1 )
		{
			return EstatCasella.JUGADOR_A;
		}
		else
		{
			return EstatCasella.JUGADOR_B;
		}
	}

	public EstatPartida mouFitxa( EstatCasella fitxa, int fila, int columna )
	{
		// TODO
		this.getPartida().getTauler().mouFitxa( fitxa, fila, columna );
		this.fila_ult_moviment = fila;
		this.columna_ult_moviment = columna;
		
		EstatPartida estat_partida = this.partida.comprovaEstatPartida( fila, columna );
		if (estat_partida == EstatPartida.NO_FINALITZADA)
		{
			this.partida.incrementaTornsJugats( 1 );
		}
		return estat_partida;
	}
}
