package com.agrovision.dao;

import java.util.Date;
import java.util.List;

import com.agrovision.dominio.RegistroImagenes;

/**
 * Clase DAO que permite la interacción con la tabla RegistroImagenes de la base de datos
 * @author Jesus
 *
 */
public interface RegistroImagenesDAO {
	
	/**
	 * Almacena una registro en la base de datos
	 * @param registroImagenes Registro a almacenar
	 */
	void añadirRegistroImagenes(RegistroImagenes registroImagenes);
	
	/**
	 * Devuelve todos los registros existentes
	 * @return retorna la lista de registros
	 */
	List<RegistroImagenes> obtenerAllRegistrosImagenes();
	
	/**
	 * Devuelve todos los registros existentes después de una fecha dada
	 * @param date Fecha a partir de la cual queremos saber los registros
	 * @return retorna la lista de registros
	 */
	List<RegistroImagenes> obtenerRegistrosDespuesDeFecha(Date date);

	/**
	 * Retorna la cantidad de imagenes subidas en total
	 * @return numero de imagenes almacenadas en la base de datos
	 */
	int obtenerCantidadImagenesSubidas();
	 
	/**
	 * Retorna la cantidad de imagenes subidas cuyo resultado fue que no tenian plaga
	 * @return numero de imagenes sin plaga almacenadas en la base de datos
	 */
	int obtenerCantidadImagenesSubidasSinPlaga();
	
	/**
	 * Retorna la cantidad de imagenes subidas cuyo resultado fue que si tenian plaga
	 * @return numero de imagenes con plaga almacenadas en la base de datos
	 */
	int obtenerCantidadImagenesSubidasConPlaga();
}
