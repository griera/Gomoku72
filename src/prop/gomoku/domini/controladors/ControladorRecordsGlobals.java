package prop.gomoku.domini.controladors;

import java.util.Comparator;
import java.util.PriorityQueue;

import prop.gomoku.domini.models.LlistaRecordsIndividuals;

public class ControladorRecordsGlobals
{
	private PriorityQueue<LlistaRecordsIndividuals> llista_victories = new PriorityQueue<LlistaRecordsIndividuals>();

	
	
	public ControladorRecordsGlobals()
	{
		this.llista_victories = new PriorityQueue<LlistaRecordsIndividuals>( 5, new ComparadorRecords(0) );
	}

	private class ComparadorRecords implements Comparator<LlistaRecordsIndividuals>
	{

		public ComparadorRecords(int criteri)
		{
			switch ( criteri )
			{
				case 0:
					
					break;

				default:
					break;
			}
		}
		
		@Override
		public int compare( LlistaRecordsIndividuals record_0, LlistaRecordsIndividuals record_1 )
		{
			// TODO Auto-generated method stub
//			if (record_0.getStatsPartidaRapida().getDerrotes().getNumFacils())
//			{
//				
//			}
			return 0;
		}

	}
	
}
