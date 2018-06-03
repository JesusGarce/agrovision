package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.Tratamiento;

/**
 * Clase DAO que permite la interacción con la tabla Tratamiento de la base de datos
 * @author Jesus
 *
 */
public interface TratamientoDAO {

	/**
	 * Almacena un tratamiento en la base de datos
	 * @param tratamiento Tratamiento a almacenar
	 */
	public void putTratamiento(Tratamiento tratamiento);
	
	/**
	 * Devuelve todos los tratamientos existentes
	 * @return retorna la lista de tratamientos
	 */
	public List<Tratamiento> getAll();
	
	/**
	 * Actualiza un tratamiento almacenado en la base de datos
	 * @param tratamiento Nuevos valores del tratamiento
	 * @return booleano indicando si se ha realizado o no la actualización
	 */
	public boolean updateTratamiento(Tratamiento tratamiento);
	
	/**
	 * Borra un tratamiento almacenado en la base de datos
	 * @param tratamiento Tratamiento a borrar
	 * @return booleano indicando si se ha borrado o no.
	 */
	public boolean deleteTratamiento(Tratamiento tratamiento);
		
	/**
	 * Solicita un tratamiento a partir de su producto
	 * @param id_producto Identificador del producto  del tratamiento a buscar
	 * @return Retorna el tratamiento en caso de que exista
	 */
	public List<Tratamiento> getTratamientoPorProducto(String id_producto);
	
	/**
	 * Solicita un tratamiento a partir de su nombre
	 * @param nombre Nombre del tratamiento a buscar
	 * @return Retorna el tratamiento en caso de que exista
	 */
	public Tratamiento getTratamiento(String nombre);
		
	/**
	 * Solicita un tratamiento a partir de su identificador
	 * @param nombre Nombre del tratamiento a buscar
	 * @return Retorna el tratamiento en caso de que exista
	 */
	public Tratamiento getTratamiento(int id);
	
}
