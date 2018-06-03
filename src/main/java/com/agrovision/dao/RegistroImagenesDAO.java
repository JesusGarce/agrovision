package com.agrovision.dao;

import java.util.Date;
import java.util.List;

import com.agrovision.dominio.RegistroImagenes;

public interface RegistroImagenesDAO {
	
	void añadirRegistroImagenes(RegistroImagenes registroImagenes);
	
	List<RegistroImagenes> obtenerAllRegistrosImagenes();
	
	List<RegistroImagenes> obtenerRegistrosDespuesDeFecha(Date date);

	int obtenerCantidadImagenesSubidas();
	 
	int obtenerCantidadImagenesSubidasSinPlaga();
	
	int obtenerCantidadImagenesSubidasConPlaga();
}
