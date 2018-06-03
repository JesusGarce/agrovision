package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.TratamientoPlaga;

/**
 *  * Clase DAO que permite la interacción con la tabla TratamientoPlaga que indica la relación entre tratamientos y plagas de la base de datos
 * @author Jesus
 *
 */
public interface TratamientoPlagaDAO {
	
	/**
	 * Almacena una tratamiento/plaga en la base de datos
	 * @param tratamientoPlaga Relación a almacenar
	 */
	public void putTratamientoPlaga(TratamientoPlaga tratamientoPlaga);
	
	/**
	 * Devuelve todas las relaciones existentes
	 * @return retorna la lista de relaciones
	 */
	public List<TratamientoPlaga> getAll();
		
	/**
	 * Borra una relación almacenada en la base de datos
	 * @param tratamientoPlaga Relación a borrar
	 * @return booleano indicando si se ha borrado o no.
	 */
	public boolean deleteTratamientoPlaga(TratamientoPlaga tratamientoPlaga);
		
	/**
	 * Solicita la lista de relaciones a partir de su tratamiento y cultivo
	 * @param id_tratamiento Identificador del tratamiento a buscar
	 * @param cultivo Cultivo a consultar
	 * @return Retorna la lista de relaciones en caso de que existan
	 */
	public List<TratamientoPlaga> getTratamientoPlagaPorTratamiento(int id_tratamiento, String cultivo);
	
	/**
	 * Solicita la lista de relaciones a partir de su plaga y cultivo
	 * @param id_plaga Identificador de la plaga a buscar
	 * @param cultivo Cultivo a consultar
	 * @return Retorna la lista de relaciones en caso de que existan
	 */
	public List<TratamientoPlaga> getTratamientoPlagaPorPlaga(int id_plaga, String cultivo);
	
	/**
	 * Solicita la lista de relaciones a partir de su cultivo
	 * @param cultivo Cultivo a consultar
	 * @return Retorna la lista de relaciones en caso de que existan
	 */
	public List<TratamientoPlaga> getTratamientoPlagaPorCultivo(String cultivo);
	
}
