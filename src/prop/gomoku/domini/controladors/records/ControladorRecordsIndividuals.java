package prop.gomoku.domini.controladors.records;

import prop.gomoku.domini.models.LlistaRecordsIndividuals;
import prop.gomoku.domini.models.UsuariGomoku;

/**
 * Controlador de records individuals que permet obtenir les dades rellevants als diferents criteris que s'utilitzen
 * també als records globals
 * 
 * @author Mauricio Ignacio Contreras Pinilla
 * 
 */
public class ControladorRecordsIndividuals
{
	/**
	 * Llista de records individuals de la qual es volen obtenir els valors segons el criteri
	 */
	private LlistaRecordsIndividuals records;

	/**
	 * Constructora bàsica a la que s'ha d'especificar el usuari del qual volem analitzar les estadístiques
	 * 
	 * @param usuari Usuari Gomoku del qual es volen analitzar les estadístiques
	 */
	public ControladorRecordsIndividuals( UsuariGomoku usuari )
	{
		records = new LlistaRecordsIndividuals( usuari );
	}

	/**
	 * Retorna a partir de la llista de records especificada en la creadora el valor rellevant segons el criteri indicat
	 * 
	 * Nota: El nom d'aquest mètode es déu principalment a voler mantenir certa paral·lelisme amb el controlador de
	 * records globals
	 * 
	 * @param criteri Criteri pel qual es vol obtenir la dada rellevant
	 * @return Valor de la dada rellevant corresponent al criteri especificat
	 */
	public int getLlistaRecords( CriteriRecords criteri )
	{
		return AccesRecords.get( records, criteri );
	}
}
