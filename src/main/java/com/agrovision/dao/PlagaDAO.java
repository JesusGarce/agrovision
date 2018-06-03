package com.agrovision.dao;

import java.util.List;

import com.agrovision.dominio.Plaga;

public interface PlagaDAO {

	public void putPlaga(Plaga plaga);
	
	public List<Plaga> getAll();
	
	public boolean updatePlaga(Plaga plaga);
	
	public boolean deletePlaga(Plaga plaga);
	
	public Plaga getPlaga(String nombre_cientifico);
	
	public Plaga getPlagaPorNombre(String nombre_comun);
	
	public Plaga getPlaga(int id);
	
}
