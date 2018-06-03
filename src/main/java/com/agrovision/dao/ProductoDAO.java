package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.Producto;

public interface ProductoDAO {

	public void putProducto(Producto producto);
	
	public List<Producto> getAll();
	
	public boolean updateProducto(Producto producto);
	
	public boolean deleteProducto(Producto producto);
	
	public Producto getProducto(String nombre);
	
	public Producto getProductoById(String id);
}
