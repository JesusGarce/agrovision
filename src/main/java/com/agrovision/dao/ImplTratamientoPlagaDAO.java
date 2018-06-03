package com.agrovision.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agrovision.dominio.Tratamiento;
import com.agrovision.dominio.TratamientoPlaga;

public class ImplTratamientoPlagaDAO implements TratamientoPlagaDAO {

private static ImplTratamientoPlagaDAO unicaInstancia;
	
	public static ImplTratamientoPlagaDAO getUnicaInstancia(){
		if (unicaInstancia == null)
			try{
				unicaInstancia = new ImplTratamientoPlagaDAO();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		
		return unicaInstancia;
	}
	
	@Override
	public void putTratamientoPlaga(TratamientoPlaga tratamientoPlaga) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        putTratamientoPlaga(session,tratamientoPlaga);        
        tx.commit();
        session.close();
	}
	
	private void putTratamientoPlaga(Session session, TratamientoPlaga tratamientoPlaga){
        TratamientoPlaga tratamientoPlagaNuevo = new TratamientoPlaga();
        
        tratamientoPlagaNuevo.setId_plaga(tratamientoPlaga.getId_plaga());
        tratamientoPlagaNuevo.setId_tratamiento(tratamientoPlaga.getId_tratamiento());
        tratamientoPlagaNuevo.setCultivo(tratamientoPlaga.getCultivo());
        
        session.save(tratamientoPlagaNuevo);
    }
	
	@Override
	public List<TratamientoPlaga> getAll() {
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from TratamientoPlaga");
        List<TratamientoPlaga> tratamiento_plaga =  query.list();
        session.close();
        return tratamiento_plaga;
	}

	@Override
	public boolean deleteTratamientoPlaga(TratamientoPlaga tratamientoPlaga) {
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from TratamientoPlaga where id_plaga = :id_plaga and id_tratamiento = :id_tratamiento";
        Query query = session.createQuery(hql);
        query.setInteger("id_plaga",tratamientoPlaga.getId_plaga());
        query.setInteger("id_tratamiento",tratamientoPlaga.getId_tratamiento());
        int rowCount = query.executeUpdate();
        tx.commit();
        session.close();
        
        return rowCount!=0;
	}

	@Override
	public List<TratamientoPlaga> getTratamientoPlagaPorTratamiento(int id_tratamiento, String cultivo) {
		Session session = SessionUtil.getSession();
        String sql = "from TratamientoPlaga where id_tratamiento = :id_tratamiento and cultivo = :cultivo";
        Query query = session.createQuery(sql);
        query.setInteger("id_tratamiento",id_tratamiento);   
        query.setString("cultivo",cultivo);        
        List<TratamientoPlaga> tratamientoPlaga =  query.list();
        session.close();
        return tratamientoPlaga;
	}

	@Override
	public List<TratamientoPlaga> getTratamientoPlagaPorPlaga(int id_plaga, String cultivo) {
		System.out.println("getTratamientoPlagaPorPlaga: "+id_plaga+"|"+cultivo);
		Session session = SessionUtil.getSession();
        String sql = "from TratamientoPlaga where id_plaga = :id_plaga and cultivo = :cultivo";

        Query query = session.createQuery(sql);

        query.setInteger("id_plaga",id_plaga);   
        query.setString("cultivo",cultivo); 
        List<TratamientoPlaga> tratamientoPlaga = (List<TratamientoPlaga>) query.list();
        session.close();
        return tratamientoPlaga;
	}

	@Override
	public List<TratamientoPlaga> getTratamientoPlagaPorCultivo(String cultivo) {
		Session session = SessionUtil.getSession();
        String sql = "from TratamientoPlaga where cultivo = :cultivo";
        Query query = session.createQuery(sql);
        query.setString("cultivo",cultivo);        
        List<TratamientoPlaga> tratamientoPlaga =  query.list();
        session.close();
        return tratamientoPlaga;
	}

}
