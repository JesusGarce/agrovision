package com.agrovision.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agrovision.dominio.Plaga;

public class ImplPlagaDAO implements PlagaDAO {

	private static ImplPlagaDAO unicaInstancia;
	
	public static ImplPlagaDAO getUnicaInstancia(){
		if (unicaInstancia == null)
			try{
				unicaInstancia = new ImplPlagaDAO();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		
		return unicaInstancia;
	}
	
	@Override
	public void putPlaga(Plaga plaga) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        putPlaga(session,plaga);        
        tx.commit();
        session.close();
	}
	
	private void putPlaga(Session session, Plaga plaga){
        Plaga plagaNueva = new Plaga();
        
        plagaNueva.setNombre(plaga.getNombre());
        plagaNueva.setNombre_cientifico(plaga.getNombre_cientifico());
        plagaNueva.setDescripcion(plaga.getDescripcion());
        plagaNueva.setCausa(plaga.getCausa());
        plagaNueva.setImagen(plaga.getImagen());
        
        session.save(plagaNueva);
    }

	@Override
	public List<Plaga> getAll() {
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Plaga");
        List<Plaga> plagas =  query.list();
        session.close();
        return plagas;
	}

	@Override
	public boolean updatePlaga(Plaga plaga) {
		if(plaga == null)  
            return false;  
		 Session session = SessionUtil.getSession();
         Transaction tx = session.beginTransaction();
         String hql = "update Plaga set nombre = :nombre, nombre_cientifico=:nombre_cientifico, descripcion=:descripcion, imagen=:imagen, causa=:causa  where id = :id";
         Query query = session.createQuery(hql);
         query.setInteger("id",plaga.getId());
         query.setString("nombre",plaga.getNombre());
         query.setString("nombre_cientifico",plaga.getNombre_cientifico());
         query.setString("descripcion",plaga.getDescripcion());
         query.setString("imagen", plaga.getImagen());
         query.setString("causa", plaga.getCausa());
         int rowCount = query.executeUpdate();
         tx.commit();
         session.close();
         
         return rowCount!=0;
	}

	@Override
	public boolean deletePlaga(Plaga plaga) {
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Plaga where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",plaga.getId());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        
        return rowCount!=0;
	}

	@Override
	public Plaga getPlaga(String nombre_cientifico) {
		Session session = SessionUtil.getSession();
        //String sql = "from Plaga where nombre_cientifico like :nombre_cientifico";
        String sql = "from Plaga where nombre_cientifico = :nombre_cientifico";
        Query query = session.createQuery(sql);
        query.setString("nombre_cientifico",nombre_cientifico);  
        //query.setString("nombre_cientifico","%"+nombre_cientifico+"%");  
        Plaga plaga =  (Plaga) query.uniqueResult();
        session.close();
        return plaga;
	}

	@Override
	public Plaga getPlaga(int id) {
		Session session = SessionUtil.getSession();
        String sql = "from Plaga where id = :id";
        Query query = session.createQuery(sql);
        query.setInteger("id",id);        
        Plaga plaga =  (Plaga) query.uniqueResult();
        session.close();
        return plaga;
	}

	@Override
	public Plaga getPlagaPorNombre(String nombre_comun) {
		Session session = SessionUtil.getSession();
        String sql = "from Plaga where nombre = :nombre";
        Query query = session.createQuery(sql);
        query.setString("nombre",nombre_comun);  
        Plaga plaga =  (Plaga) query.uniqueResult();
        session.close();
        return plaga;
	}

}
