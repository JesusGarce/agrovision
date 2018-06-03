package com.agrovision.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.agrovision.dao.FactoriaDAO;
import com.agrovision.dao.PlagaDAO;
import com.agrovision.dao.ProductoDAO;
import com.agrovision.dao.RegistroImagenesDAO;
import com.agrovision.dao.TratamientoDAO;
import com.agrovision.dao.TratamientoPlagaDAO;
import com.agrovision.dominio.Cultivo;
import com.agrovision.dominio.Plaga;
import com.agrovision.dominio.Producto;
import com.agrovision.dominio.RegistroImagenes;
import com.agrovision.dominio.Tratamiento;
import com.agrovision.dominio.TratamientoPlaga;

public class ControladorPlagas {

	public Plaga obtenerPlaga(Map<Float,String> resultadosVision){
		Plaga plagaResult = null;
		PlagaDAO dao = FactoriaDAO.getInstancia().getPlagaDAO();
		for (Float label : resultadosVision.keySet() )
			System.out.println(" | "+resultadosVision.get(label)+" | Label: "+label);
			
		for (Float label : resultadosVision.keySet() ){
			Plaga plagaAux = dao.getPlaga(resultadosVision.get(label));
			if (plagaAux!=null){
				System.out.println(" | "+plagaAux.getNombre_cientifico()+" | Label: "+label);
				plagaResult = plagaAux;
				
				int porcentaje = (int)(label*100);
				if (porcentaje > 100)
					porcentaje = 100;
				
				plagaResult.añadirCoincidencia(porcentaje);
				return plagaResult;
			}
				
		}
		return plagaResult;
	}
	
	public Plaga obtenerPlaga(String nombreComun){
		PlagaDAO dao = FactoriaDAO.getInstancia().getPlagaDAO();
				
		return dao.getPlagaPorNombre(nombreComun);
	}
	
	public Plaga obtenerPlagaByCientifico(String nombreCientifico){
		PlagaDAO dao = FactoriaDAO.getInstancia().getPlagaDAO();
		return dao.getPlaga(nombreCientifico);
	}
	
	public List<Tratamiento> obtenerTratamientos(Plaga plaga, Cultivo cultivo){
		List<Tratamiento> listaTratamientos = new LinkedList<Tratamiento>();
		
		TratamientoPlagaDAO daoTratamientoPlaga = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();
		TratamientoDAO daoTratamiento = FactoriaDAO.getInstancia().getTratamientoDAO();

		for (TratamientoPlaga t : daoTratamientoPlaga.getTratamientoPlagaPorPlaga(plaga.getId(), cultivo.toString())){
			listaTratamientos.add(daoTratamiento.getTratamiento(t.getId_tratamiento()));
			
		}
		return listaTratamientos;
	}
	
	public Tratamiento obtenerTratamiento(int id_tratamiento){
		TratamientoDAO daoTratamiento = FactoriaDAO.getInstancia().getTratamientoDAO();
		
		return daoTratamiento.getTratamiento(id_tratamiento);
	}
	
	public Producto obtenerProducto(String id_producto){
		ProductoDAO daoProducto = FactoriaDAO.getInstancia().getProductoDAO();
		
		return daoProducto.getProductoById(id_producto);
	}

	public Producto obtenerProductodeTratamiento(Tratamiento tratamiento){
		
		ProductoDAO daoProducto = FactoriaDAO.getInstancia().getProductoDAO();
		
		return daoProducto.getProductoById(tratamiento.getProducto());
	}
	
	public void almacenarRegistroImagenes(RegistroImagenes registroImagenes){
		RegistroImagenesDAO daoRegistro = FactoriaDAO.getInstancia().getRegistroImagenesDAO();
		
		daoRegistro.añadirRegistroImagenes(registroImagenes);
		
		System.out.println("Hemos creado el registroImagenes" +registroImagenes);
	}
	
	
}
