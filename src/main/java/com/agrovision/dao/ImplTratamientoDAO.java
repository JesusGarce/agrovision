package com.agrovision.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agrovision.dominio.Tratamiento;

public class ImplTratamientoDAO implements TratamientoDAO {

	private static ImplTratamientoDAO unicaInstancia;
	
	public static ImplTratamientoDAO getUnicaInstancia(){
		if (unicaInstancia == null)
			try{
				unicaInstancia = new ImplTratamientoDAO();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		
		return unicaInstancia;
	}
	
	@Override
	public void putTratamiento(Tratamiento tratamiento) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        putTratamiento(session,tratamiento);        
        tx.commit();
        session.close();
	}
	
	private void putTratamiento(Session session, Tratamiento tratamiento){
        Tratamiento tratamientoNueva = new Tratamiento();
        
        tratamientoNueva.setNombre(tratamiento.getNombre());
        tratamientoNueva.setDescripcion(tratamiento.getDescripcion());
        tratamientoNueva.setPrincipio_activo(tratamiento.getPrincipio_activo());
        tratamientoNueva.setClasificacion(tratamiento.getClasificacion());
        tratamientoNueva.setTipo(tratamiento.getTipo());
        tratamientoNueva.setProducto(tratamiento.getProducto());
        
        session.save(tratamientoNueva);
    }

	@Override
	public List<Tratamiento> getAll() {
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Tratamiento");
        List<Tratamiento> tratamiento =  query.list();
        session.close();
        return tratamiento;
	}

	@Override
	public boolean updateTratamiento(Tratamiento tratamiento) {
		if(tratamiento == null)  
            return false;  
      Session session = SessionUtil.getSession();
         Transaction tx = session.beginTransaction();
         String hql = "update Tratamiento set nombre = :nombre, descripcion= :descripcion, principio_activo= :principio_activo, clasificacion= :clasificacion, tipo= :tipo, producto= :producto  where id = :id";
         Query query = session.createQuery(hql);
         query.setInteger("id",tratamiento.getId());
         query.setString("nombre",tratamiento.getNombre());
         query.setString("descripcion",tratamiento.getDescripcion());
         query.setString("principio_activo", tratamiento.getPrincipio_activo());
         query.setString("clasificacion", tratamiento.getClasificacion().toString());
         query.setString("tipo", tratamiento.getTipo().toString());
         query.setString("producto", tratamiento.getProducto());
         int rowCount = query.executeUpdate();
         System.out.println("Rows affected: " + rowCount);
         tx.commit();
         session.close();
         
         return rowCount!=0;
	}

	@Override
	public boolean deleteTratamiento(Tratamiento tratamiento) {
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Tratamiento where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",tratamiento.getId());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        
        return rowCount!=0;
	}

	@Override
	public List<Tratamiento> getTratamientoPorProducto(String id_producto) {
		Session session = SessionUtil.getSession();
        String sql = "from Tratamiento where producto = :producto";
        Query query = session.createQuery(sql);
        query.setString("producto",id_producto);        
        List<Tratamiento> tratamientos =  query.list();
        session.close();
        return tratamientos;
	}

	@Override
	public Tratamiento getTratamiento(int id) {
		Session session = SessionUtil.getSession();
        String sql = "from Tratamiento where id = :id";
        Query query = session.createQuery(sql);
        query.setInteger("id",id);        
        Tratamiento tratamiento =  (Tratamiento) query.uniqueResult();
        session.close();
        return tratamiento;
	}
	
	@Override
	public Tratamiento getTratamiento(String nombre) {
		Session session = SessionUtil.getSession();
        String sql = "from Tratamiento where nombre = :nombre";
        Query query = session.createQuery(sql);
        query.setString("nombre",nombre);        
        Tratamiento tratamiento =  (Tratamiento) query.uniqueResult();
        session.close();
        return tratamiento;
	}

}
