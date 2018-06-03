package com.agrovision.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agrovision.dominio.RegistroImagenes;


public class ImplRegistroImagenesDAO implements RegistroImagenesDAO {

	private static ImplRegistroImagenesDAO unicaInstancia;
	
	public static ImplRegistroImagenesDAO getUnicaInstancia(){
		if (unicaInstancia == null)
			try{
				unicaInstancia = new ImplRegistroImagenesDAO();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		
		return unicaInstancia;
	}
	
	@Override
	public void añadirRegistroImagenes(RegistroImagenes registroImagenes) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        putRegistro(session,registroImagenes);        
        tx.commit();
        session.close();
	}
	
	private void putRegistro(Session session, RegistroImagenes registroImagenes){
		RegistroImagenes registroNuevo = new RegistroImagenes();
		
		registroNuevo.setUrl_imagen(registroImagenes.getUrl_imagen());
		registroNuevo.setPlaga_encontrada(registroImagenes.isPlaga_encontrada());
        registroNuevo.setPlaga(registroImagenes.getPlaga());
        registroNuevo.setFecha_registro(registroImagenes.getFecha_registro());	
        
        session.save(registroNuevo);
    }


	@Override
	public List<RegistroImagenes> obtenerAllRegistrosImagenes() {
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from RegistroImagenes");
        List<RegistroImagenes> registros =  query.list();
        session.close();
        return registros;
	}


	@Override
	public List<RegistroImagenes> obtenerRegistrosDespuesDeFecha(Date date) {		
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from RegistroImagenes where fecha_registro > :fecha_registro");
        query.setTimestamp("fecha_registro", date);
        List<RegistroImagenes> registros =  query.list();
        session.close();

        return registros;
	}
	
	@Override
	public int obtenerCantidadImagenesSubidas(){
		return obtenerAllRegistrosImagenes().size();
	}
	
	@Override
	public int obtenerCantidadImagenesSubidasSinPlaga(){		
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from RegistroImagenes where plaga_encontrada = :plaga_encontrada");
        query.setBoolean("plaga_encontrada", false);
        List<RegistroImagenes> registros =  query.list();
        session.close();
        System.out.println("Cantidad imagenes subidas sin plaga: "+registros.size());
        return registros.size();
	}
	
	@Override
	public int obtenerCantidadImagenesSubidasConPlaga(){
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from RegistroImagenes where plaga_encontrada = :plaga_encontrada");
        query.setBoolean("plaga_encontrada", true);
        List<RegistroImagenes> registros =  query.list();
        session.close();
        System.out.println("Cantidad imagenes subidas con plaga: "+registros.size());
        return registros.size();
	}

}
