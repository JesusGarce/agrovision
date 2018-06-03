package com.agrovision.dao;

import java.util.Date;
import java.util.List;

import com.agrovision.dominio.RegistroImagenes;

public interface RegistroImagenesDAO {
	
	void a�adirRegistroImagenes(RegistroImagenes registroImagenes);
	
	List<RegistroImagenes> obtenerAllRegistrosImagenes();
	
	List<RegistroImagenes> obtenerRegistrosDespuesDeFecha(Date date);

	int obtenerCantidadImagenesSubidas();
	 
	int obtenerCantidadImagenesSubidasSinPlaga();
	
	int obtenerCantidadImagenesSubidasConPlaga();
}
