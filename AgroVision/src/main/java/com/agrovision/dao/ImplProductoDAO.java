package com.agrovision.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agrovision.dominio.Producto;

public class ImplProductoDAO implements ProductoDAO{

	private static ImplProductoDAO unicaInstancia;
	
	public static ImplProductoDAO getUnicaInstancia(){
		if (unicaInstancia == null)
			try{
				unicaInstancia = new ImplProductoDAO();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		
		return unicaInstancia;
	}
	
	@Override
	public void putProducto(Producto producto) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        putProducto(session,producto);        
        tx.commit();
        session.close();
	}
	
	private void putProducto(Session session, Producto producto){
        Producto productoNuevo = new Producto();
        
        productoNuevo.setId(producto.getId());
        productoNuevo.setNombre(producto.getNombre());
        productoNuevo.setDescripcion(producto.getDescripcion());
        productoNuevo.setUrl(producto.getUrl());
        productoNuevo.setImagen(producto.getImagen());
        
        session.save(productoNuevo);
    }

	@Override
	public List<Producto> getAll() {
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Producto");
        List<Producto> producto =  query.list();
        session.close();
        return producto;
	}

	@Override
	public boolean updateProducto(Producto producto) {
		if(producto == null)  
            return false;  
      Session session = SessionUtil.getSession();
         Transaction tx = session.beginTransaction();
         String hql = "update Producto set nombre = :nombre, descripcion=:descripcion, imagen=:imagen, url=:url  where id = :id";
         Query query = session.createQuery(hql);
         query.setString("id",producto.getId());
         query.setString("nombre",producto.getNombre());
         query.setString("descripcion",producto.getDescripcion());
         query.setString("imagen", producto.getImagen());
         query.setString("url", producto.getUrl());
         int rowCount = query.executeUpdate();
         System.out.println("Rows affected: " + rowCount);
         tx.commit();
         session.close();
         
         return rowCount!=0;
	}

	@Override
	public boolean deleteProducto(Producto producto) {
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Producto where id = :id";
        Query query = session.createQuery(hql);
        query.setString("id",producto.getId());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        
        return rowCount!=0;
	}

	@Override
	public Producto getProducto(String nombre) {
		Session session = SessionUtil.getSession();
        String sql = "from Producto where nombre = :nombre";
        Query query = session.createQuery(sql);
        query.setString("nombre",nombre);        
        Producto producto =  (Producto) query.uniqueResult();
        session.close();
        return producto;
	}

	@Override
	public Producto getProductoById(String id) {
		Session session = SessionUtil.getSession();
        String sql = "from Producto where id = :id";
        Query query = session.createQuery(sql);
        query.setString("id",id);        
        Producto producto =  (Producto) query.uniqueResult();
        session.close();
        return producto;
	}

}
