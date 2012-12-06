package prop.gomoku.tests;

import java.util.PriorityQueue;

import prop.gomoku.domini.controladors.ControladorRecordsGlobals;
import prop.gomoku.domini.models.LlistaRecordsIndividuals;

public class TestRecordsGlobals
{
	public static void main(String[] args)
	{
		ControladorRecordsGlobals ctrl_records = new ControladorRecordsGlobals();
		int pos = 1;
		PriorityQueue<LlistaRecordsIndividuals> llista = ctrl_records.getRecordsIndividualsOrdenats();
		LlistaRecordsIndividuals record = llista.poll();
		while (record != null)
		{
			System.out.println(pos + ". " + record.getUsuari() );
			pos += 1;
			record = llista.poll();
		}
	}
}
