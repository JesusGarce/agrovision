package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.Producto;

/**
 * Clase DAO que permite la interacción con la tabla Producto de la base de datos
 * @author Jesus
 *
 */
public interface ProductoDAO {

	/**
	 * Almacena un producto en la base de datos
	 * @param producto Producto a almacenar
	 */
	public void putProducto(Producto producto);
	
	/**
	 * Devuelve todos los productos existentes
	 * @return retorna la lista de productos
	 */
	public List<Producto> getAll();
	
	/**
	 * Actualiza un producto almacenado en la base de datos
	 * @param producto Nuevos valores del producto
	 * @return booleano indicando si se ha realizado o no la actualización
	 */
	public boolean updateProducto(Producto producto);
	
	/**
	 * Borra un producto almacenado en la base de datos
	 * @param producto Producto a borrar
	 * @return booleano indicando si se ha borrado o no.
	 */
	public boolean deleteProducto(Producto producto);
	
	/**
	 * Solicita un producto a partir de su nombre
	 * @param nombre Nombre del producto a buscar
	 * @return Retorna el producto en caso de que exista
	 */
	public Producto getProducto(String nombre);
	
	/**
	 * Solicita un producto a partir de su identificador
	 * @param id identificador del producto a buscar
	 * @return Retorna el producto en caso de que exista
	 */
	public Producto getProductoById(String id);
}
