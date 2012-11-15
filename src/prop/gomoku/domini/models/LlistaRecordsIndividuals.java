package prop.gomoku.domini.models;

public class LlistaRecordsIndividuals
{
	private EstadistiquesPartidaRapida estadistiques;
	private UsuariGomoku usuari;

	public LlistaRecordsIndividuals( UsuariGomoku usuari )
	{
		this.usuari = usuari;
		estadistiques = new EstadistiquesPartidaRapida( usuari.getNumVictories(), usuari.getNumDerrotes(), usuari
				.getNumEmpats() );
	}

	public EstadistiquesPartidaRapida getStatsPartidaRapida()
	{
		return estadistiques;
	}

	public void setStatsPartidaRapida( EstadistiquesPartidaRapida estadistiques )
	{
		this.estadistiques = estadistiques;
	}

}
