package com.agrovision.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.agrovision.dao.FactoriaDAO;
import com.agrovision.dao.ProductoDAO;
import com.agrovision.dominio.Producto;

public class testDAOProducto {

	@Test
	public void when_solicitamosUnProductoById_Then_ProductoDAORetornaUnProducto(){
		ProductoDAO productoDAO = FactoriaDAO.getInstancia().getProductoDAO();
		
		Producto producto = productoDAO.getProductoById("17919");
		
		assertNotNull(producto);
	}
	
	@Test
	public void when_solicitamosUnProductoByName_Then_ProductoDAORetornaUnProducto(){
		ProductoDAO productoDAO = FactoriaDAO.getInstancia().getProductoDAO();
		
		Producto producto = productoDAO.getProducto("COSTAR");
		
		assertNotNull(producto);
	}
	
	@Test
	public void when_creamosUnProducto_Then_ProductoSeAlmacenaEnBD(){
		Producto productoExpected = new Producto();
		productoExpected.setId("88756");
		productoExpected.setNombre("PRODUCTO NUEVO");
		productoExpected.setDescripcion("Esta es la descripcion del nuevo producto");
		productoExpected.setImagen("https://www.hogarmania.com/archivos/201303/plaga-jardin-668x400x80xX.jpg");
		productoExpected.setUrl("http://www.mapama.gob.es/agricultura/pags/fitos/registro/productos/pdf/88756.pdf");
		
		ProductoDAO productoDAO = FactoriaDAO.getInstancia().getProductoDAO();
		productoDAO.putProducto(productoExpected);
		
		Producto productoAct = productoDAO.getProductoById("88756");
		
		assertEquals(productoExpected, productoAct);
	}
	
	@Test
	public void when_solicitamosListaProductos_Then_ReceiveAllProductos(){
		ProductoDAO productoDAO = FactoriaDAO.getInstancia().getProductoDAO();
		
		List<Producto> listProductos = productoDAO.getAll();
		
		assertNotEquals(0, listProductos.size());
	}
	
	@Test
	public void when_updateAProduct_Then_TheProductoChange(){
		Producto productoExpected = new Producto();
		productoExpected.setId("88756");
		productoExpected.setNombre("PRODUCTO ACTUALIZADO");
		productoExpected.setDescripcion("Esta es la nueva descripción del nuevo producto");
		productoExpected.setImagen("https://www.hogarmania.com/archivos/201303/plaga-jardin-668x400x80xX.jpg");
		productoExpected.setUrl("http://www.mapama.gob.es/agricultura/pags/fitos/registro/productos/pdf/88756.pdf");
		
		ProductoDAO productoDAO = FactoriaDAO.getInstancia().getProductoDAO();
		productoDAO.updateProducto(productoExpected);
		
		Producto producto = productoDAO.getProductoById("88756");
				
		assertEquals("PRODUCTO ACTUALIZADO",producto.getNombre());
	}
	
	@Test
	public void when_deleteAProducto_Then_TheProductoIsDeleted(){
		ProductoDAO productoDAO = FactoriaDAO.getInstancia().getProductoDAO();
		Producto producto = productoDAO.getProductoById("88756");
		
		productoDAO.deleteProducto(producto);
		
		Producto productoAct = productoDAO.getProductoById("88756");
		
		assertNull(productoAct);
	}
}
