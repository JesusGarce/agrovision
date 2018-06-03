package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.Tratamiento;

public interface TratamientoDAO {

public void putTratamiento(Tratamiento tratamiento);
	
	public List<Tratamiento> getAll();
	
	public boolean updateTratamiento(Tratamiento tratamiento);
	
	public boolean deleteTratamiento(Tratamiento tratamiento);
		
	public List<Tratamiento> getTratamientoPorProducto(String id_producto);
	
	public Tratamiento getTratamiento(String nombre);
		
	public Tratamiento getTratamiento(int id);
	
}
