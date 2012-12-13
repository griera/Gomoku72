package prop.gomoku.domini.controladors.records;

import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.UsuariGomoku;

public class ControladorRecordsIndividuals
{
	// TODO
	private LlistaRecordsIndividuals records;

	public ControladorRecordsIndividuals( UsuariGomoku usuari )
	{
		records = new LlistaRecordsIndividuals( usuari );
	}

	public int getLlistaRecords( CriteriRecords criteri )
	{
		return AccesRecords.get( records, criteri );
	}
}
