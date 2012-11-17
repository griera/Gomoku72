package prop.gomoku.domini.models;

/**
 * Classe que representa tot el conjunt d'estadistiques individuals d'un jugador
 * 
 */
public class LlistaRecordsIndividuals
{
	/**
	 * Estadistiques individuals del jugador en mode partida rapida
	 */
	private EstadistiquesPartides estadistiques;
	/**
	 * Usuari al qual pertanyen les estadistiques
	 */
	private UsuariGomoku usuari; // TODO: s'utilitzarà de cara a la tercera entrega

	/**
	 * Creadora per defecte que a partir d'un usuari, crea unes estadistiques basantse en les dades primitives que te
	 * guardades aquest usuari a partir de les partides jugades.
	 * 
	 * @param usuari Indica l'usuari al qual pertanyen les estadistiques creades
	 */
	public LlistaRecordsIndividuals( UsuariGomoku usuari )
	{
		this.usuari = usuari;
		estadistiques = new EstadistiquesPartides( usuari.getNumVictories(), usuari.getNumDerrotes(), usuari
				.getNumEmpats() );
	}

	/**
	 * Metode per obtenir les estadistiques de la LlistaRecordsIndividuals
	 * 
	 * @return Retorna el conjunt d'estadistiques(conjunt de victories,derrotes,empats i percentatges)
	 */
	public EstadistiquesPartides getStatsPartidaRapida()
	{
		return estadistiques;
	}

	/**
	 * Metode per modificar les estadistiques que te guardades LlistaRecordsIndividuals
	 * 
	 * @param estadistiques Estadistiques a introduir a la llista de records individuals
	 */
	public void setStatsPartidaRapida( EstadistiquesPartides estadistiques )
	{
		this.estadistiques = estadistiques;
	}
	
	/**
	 * Mètode per obtenir el Usuari al que correspon aquestes estadístiques
	 */
	public UsuariGomoku getUsuari()
	{
		return this.usuari;
	}

}

