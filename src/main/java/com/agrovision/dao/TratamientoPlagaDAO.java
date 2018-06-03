package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.TratamientoPlaga;

public interface TratamientoPlagaDAO {
	
	public void putTratamientoPlaga(TratamientoPlaga tratamientoPlaga);
	
	public List<TratamientoPlaga> getAll();
		
	public boolean deleteTratamientoPlaga(TratamientoPlaga tratamientoPlaga);
		
	public List<TratamientoPlaga> getTratamientoPlagaPorTratamiento(int id_tratamiento, String cultivo);
	
	public List<TratamientoPlaga> getTratamientoPlagaPorPlaga(int id_plaga, String cultivo);
	
	public List<TratamientoPlaga> getTratamientoPlagaPorCultivo(String cultivo);
	
}
