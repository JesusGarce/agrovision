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

/**
 * Controlador que interactua entre el servicio REST y el servicio DAO
 * @author Jesus
 *
 */
public class ControladorPlagas {

	/**
	 * A partir de un mapa de etiquetas se busca si existe alguna plaga en ese mapa
	 * @param resultadosVision mapa de etiquetas junto con su valor de coincidencia
	 * @return Plaga en caso de que exista
	 */
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
	
	/**
	 * A partir del nombre común de una plaga, se retorna el objeto plaga
	 * @param nombreComun Nombre común de una plaga
	 * @return devuelve el objeto Plaga
	 */
	public Plaga obtenerPlaga(String nombreComun){
		PlagaDAO dao = FactoriaDAO.getInstancia().getPlagaDAO();
				
		return dao.getPlagaPorNombre(nombreComun);
	}
	
	/**
	 * A partir del nombre científico de una plaga, se retorna el objeto plaga
	 * @param nombreCientifico Nombre científico de una plaga
	 * @return devuelve el objeto Plaga
	 */
	public Plaga obtenerPlagaByCientifico(String nombreCientifico){
		PlagaDAO dao = FactoriaDAO.getInstancia().getPlagaDAO();
		return dao.getPlaga(nombreCientifico);
	}
	
	/**
	 * Función que retorna la lista de tratamientos existentes de la relación plaga/cultivo
	 * @param plaga Objeto plaga de la cual queremos saber que tratamientos existen
	 * @param cultivo Cultivo del cual queremos saber que tratamientos existen
	 * @return Retorna la lista de tratamientos de esta relación
	 */
	public List<Tratamiento> obtenerTratamientos(Plaga plaga, Cultivo cultivo){
		List<Tratamiento> listaTratamientos = new LinkedList<Tratamiento>();
		
		TratamientoPlagaDAO daoTratamientoPlaga = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();
		TratamientoDAO daoTratamiento = FactoriaDAO.getInstancia().getTratamientoDAO();

		for (TratamientoPlaga t : daoTratamientoPlaga.getTratamientoPlagaPorPlaga(plaga.getId(), cultivo.toString())){
			listaTratamientos.add(daoTratamiento.getTratamiento(t.getId_tratamiento()));
			
		}
		return listaTratamientos;
	}
	
	/**
	 * Obtiene un tratamiento a partir de su identificador
	 * @param id_tratamiento Identificador del tratamiento que queremos obtener
	 * @return Retornamos el objeto Tratamiento deseado
	 */
	public Tratamiento obtenerTratamiento(int id_tratamiento){
		TratamientoDAO daoTratamiento = FactoriaDAO.getInstancia().getTratamientoDAO();
		
		return daoTratamiento.getTratamiento(id_tratamiento);
	}
	
	/**
	 * Obtiene un producto a partir de su identificador
	 * @param id_producto Identificador del producto que queremos obtener
	 * @return Retornamos el objeto Producto deseado
	 */
	public Producto obtenerProducto(String id_producto){
		ProductoDAO daoProducto = FactoriaDAO.getInstancia().getProductoDAO();
		
		return daoProducto.getProductoById(id_producto);
	}

	/**
	 * Obtiene un producto a partir de su tratamiento
	 * @param tratamiento Tratamiento del cual queremos saber su producto
	 * @return Retornamos el objeto Producto deseado
	 */
	public Producto obtenerProductodeTratamiento(Tratamiento tratamiento){
		
		ProductoDAO daoProducto = FactoriaDAO.getInstancia().getProductoDAO();
		
		return daoProducto.getProductoById(tratamiento.getProducto());
	}
	
	/**
	 * Almacenamos un registro de la imagen que se ha subido a la aplicación
	 * @param registroImagenes Registro que se ha creado
	 */
	public void almacenarRegistroImagenes(RegistroImagenes registroImagenes){
		RegistroImagenesDAO daoRegistro = FactoriaDAO.getInstancia().getRegistroImagenesDAO();
		
		daoRegistro.añadirRegistroImagenes(registroImagenes);
		
		System.out.println("Hemos creado el registroImagenes" +registroImagenes);
	}
	
	
}
