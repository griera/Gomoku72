package prop.gomoku.domini.models;

import prop.cluster.domini.models.Tauler;
import prop.cluster.domini.models.estats.EstatCasella;

/**
 * Representa un tauler del joc de taula Gomoku. Aquest té 15 files i 15 col·lumnes. Té un control de les fitxes que té
 * cada jugador al tauler i revisa que les fitxes s’afegeixen d’acord amb la normativa del Gomoku mitjançant un mètode 
 * redefinit de la seva classe pare <code>Tauler</code>.
 */
public class TaulerGomoku extends Tauler
{

	/**
	 * ID de serialització
	 */
	private static final long serialVersionUID = 2970709775673282357L;


	/**
	 * Mètode contructor de la classe <code>TaulerGomoku</code>. Crea un tauler de mida 15 files per 15 columnes,
	 * seguint la normativa estàndrad del joc de Taula Gomoku.
	 */
	public TaulerGomoku()
	{
		super( 15 );
	}

	/**
	 * Mètode que comprova si, donada la posició (<em>fila</em>, <em>columna</em>) d'una fitxa dins el tauler, existeix
	 * al voltant d'aquesta una línia de tantes fitxes del mateix color com indica <em>limit</em>. Aquesta línia pot
	 * estar formada en qualsevol de les direccions i sentits possibles a partir de la posició donada (vertical,
	 * horitzonatl, les dues diagonals, i de cadascuna d'aquestes direccions els dos sentit possibles, fent un total de
	 * 8 possibilitats de formar una línia d'exactament <em>limit</em> fitxes del mateix color).
	 * 
	 * @param fila Índex de la fila dins del tauler on es troba la fitxa sobre la qual volem fer la comprovació.
	 * @param columna Índex de la col·lumna dins del tauler on es troba la fitxa sobre la qual volem fer la comprovació.
	 * @param limit Nombre de fitxes seguides a comprovar si es troba formada dins del tauler.
	 * @param estat Representa l'estat de la casella a partir de la qual fer la comprovació. 
	 * @return Cert si, a partir de la fitxa en la posició indicada del tauler, existeix una línia de <em>límit</em>
	 *         fitxes seguides del mateix color que <em>estat</em>. Fals altrament.
	 */
	public boolean teFitxesSeguides( int fila, int columna, int limit, EstatCasella estat )
	{
		int fitxes_seguides = 1;
		int mida = this.mida;

		// Comprovació de la fila en els seus dos sentits
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

		// Comprovació de la col·lumna en els seus dos sentits
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

		// Comprovació de la diagonal nord-oest cap a sud-est i viceversa
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

		// Comprovació de la diagonal nord-est cap a sud-oest i viceversa
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
			throw new IndexOutOfBoundsException( "Posicio indicada fora del tauler" );
		}

		else if ( fitxa == EstatCasella.BUIDA )
		{
			if ( this.esBuit() )
			{
				throw new IllegalArgumentException( "No es pot treure cap fitxa perque el tauler esta buit" );
			}

			else if ( this.getEstatCasella( fila, columna ) == fitxa )
			{
				throw new IllegalArgumentException( "La casella (" + fila + ", " + columna + ") ja esta buida" );
			}

			else
			{
				return true;
			}
		}

		else if ( this.getEstatCasella( fila, columna ) != EstatCasella.BUIDA )
		{
			throw new IllegalArgumentException( "La casella (" + fila + ", " + columna + ") ja esta ocupada" );
		}

		else if ( this.teFitxesSeguides( fila, columna, 6, fitxa ) )
		{
			throw new IllegalArgumentException( "No poden haver linies de mes de cinc fitxes seguides" );
		}

		return true;
	}
	
	
	/**
	 * Mètode que escriu per pantalla la representació de l'actual tauler mitjançant caràcters. Tant les files com les
	 * col·lumnes estan indexades per proporcionar major facilitat en la lectura del tauler. El significat dels
	 * possibles caràcters és el següent:
	 * <ul>
	 * <li> El caràcter '.' significa que la casella està buida
	 * <li> El caràcter 'X' significa que la casella està ocupada per una fitxa de color negre
	 * <li> El caràcter 'O' significa que la casella està ocupada per una fitxa de color blanca
	 * </ul>
	 * Exemple del format d'escriptura per a un tauler de mida 15x15 (15 files per 15 col·lumnes):
	 * <pre>
	 *      0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
	 * 0:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 1:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 2:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 3:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 4:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 5:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 6:   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * 7:   .  .  .  O  .  .  .  .  .  .  .  .  .  .  .
	 * 8:   .  .  X  X  .  O  .  .  .  .  .  .  .  .  .
	 * 9:   .  .  X  X  O  O  .  .  .  .  .  .  .  .  .
	 * 10:  .  .  X  X  X  O  .  .  .  .  .  .  .  .  .
	 * 11:  .  .  .  O  X  O  O  .  .  .  .  .  .  .  .
	 * 12:  .  .  .  .  .  X  .  O  .  .  .  .  .  .  .
	 * 13:  .  .  .  .  .  .  .  .  X  .  .  .  .  .  .
	 * 14:  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .
	 * </pre>
	 */
	public void pinta()
	{
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
		for ( int fila = 0; fila < this.mida; ++fila )
		{
			System.out.print( fila + ":  " );
			if ( fila <= 9 )
			{
				System.out.print( " " );
			}

			for ( int columna = 0; columna < this.mida; ++columna )
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
