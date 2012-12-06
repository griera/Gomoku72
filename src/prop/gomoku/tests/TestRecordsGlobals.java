package prop.gomoku.tests;

import java.util.PriorityQueue;

import prop.gomoku.domini.controladors.records.AccesRecords;
import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals;
import prop.gomoku.domini.controladors.records.CriteriRecords;
import prop.gomoku.domini.models.LlistaRecordsIndividuals;

public class TestRecordsGlobals
{
	public static void main( String[] args )
	{
		for ( CriteriRecords criteri : CriteriRecords.values() )
		{
			System.out.println( criteri.toString().toUpperCase() );
			ControladorRecordsGlobals ctrl_records = new ControladorRecordsGlobals( criteri );
			int pos = 1;
			PriorityQueue<LlistaRecordsIndividuals> llista = ctrl_records.getRecordsIndividualsOrdenats();
			LlistaRecordsIndividuals record = llista.poll();
			while ( record != null )
			{
				int dada_interes = AccesRecords.get( record, criteri );
				System.out.print( pos + ". " + record.getUsuari().getNom() + ": "+ dada_interes );
				if (criteri.toString().contains( "PER" ))
				{
					System.out.print("%");
				}
				System.out.print( " - " + record.getUsuari() );
				System.out.println();
				pos += 1;
				record = llista.poll();
			}
		}
	}
}
