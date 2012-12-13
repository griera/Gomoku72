package prop.gomoku.tests;

import java.util.List;

import prop.gomoku.domini.controladors.records.ControladorRecordsGlobals;
import prop.gomoku.domini.controladors.records.CriteriRecords;

public class TestRecordsGlobals
{
	public static void main( String[] args )
	{
		ControladorRecordsGlobals ctrl_records = new ControladorRecordsGlobals();
		for ( CriteriRecords criteri : CriteriRecords.values() )
		{
			System.out.println( criteri.toString().toUpperCase() );

			int pos = 1;
			List<String[]> llista = ctrl_records.getLlistaRecords( criteri );
			for ( String[] registre : llista )
			{
				System.out.print( pos + ". " + registre[0] + ": " + registre[1] );
				if ( criteri.toString().contains( "PER" ) )
				{
					System.out.print( "%" );
				}
				System.out.println();
				pos++;
			}
		}
	}
}
