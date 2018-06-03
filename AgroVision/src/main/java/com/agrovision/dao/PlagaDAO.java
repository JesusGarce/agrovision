package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.Plaga;
/**
 * Clase DAO que permite la interacción con la tabla Plaga de la base de datos
 * @author Jesus
 *
 */
public interface PlagaDAO {

	/**
	 * Almacena una plaga en la base de datos
	 * @param plaga
	 */
	public void putPlaga(Plaga plaga);
	
	/**
	 * Devuelve todas las plagas existentes
	 * @return retorna la lista de plagas
	 */
	public List<Plaga> getAll();
	
	/**
	 * Actualiza una plaga almacenada en la base de datos
	 * @param plaga Nuevos valores de la plaga
	 * @return booleano indicando si se ha realizado o no la actualización
	 */
	public boolean updatePlaga(Plaga plaga);
	
	/**
	 * Borra una plaga almacenada en la base de datos
	 * @param plaga Plaga a borrar
	 * @return booleano indicando si se ha borrado o no.
	 */
	public boolean deletePlaga(Plaga plaga);
	
	/**
	 * Solicita una plaga a partir de su nombre cientifico
	 * @param nombre_cientifico Nombre cientifico de la plaga a buscar
	 * @return Retorna la plaga en caso de que exista
	 */
	public Plaga getPlaga(String nombre_cientifico);
	
	/**
	 * Solicita una plaga a partir de su nombre común
	 * @param nombre_comun Nombre común de la plaga a buscar
	 * @return Retorna la plaga en caso de que exista
	 */
	public Plaga getPlagaPorNombre(String nombre_comun);
	
	/**
	 * Solicita una plaga a partir de su identificador
	 * @param id identificador de la plaga a buscar
	 * @return Retorna la plaga en caso de que exista
	 */
	public Plaga getPlaga(int id);
	
}
