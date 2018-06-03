package com.agrovision.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import com.agrovision.dao.FactoriaDAO;
import com.agrovision.dao.TratamientoPlagaDAO;
import com.agrovision.dominio.TratamientoPlaga;

public class testDAOTratamientoPlaga {
	@Test
	public void when_creamosUnTratamientoPlaga_Then_TratamientoPlagaSeAlmacenaBD(){
		TratamientoPlagaDAO tratamientoPlagaDAO = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();

		TratamientoPlaga tratamientoPlaga = new TratamientoPlaga();
		tratamientoPlaga.setId_plaga(999);
		tratamientoPlaga.setId_tratamiento(999);
		tratamientoPlaga.setCultivo("Calabaza");
		
		tratamientoPlagaDAO.putTratamientoPlaga(tratamientoPlaga);
		
		List<TratamientoPlaga> tratamientoPlagaAct = tratamientoPlagaDAO.getTratamientoPlagaPorPlaga(999, "Calabaza");
		
		assertNotEquals(0, tratamientoPlagaAct.size());
	}
	
	@Test 
	public void when_solicitamosTodosTratamientoPlaga_Then_RecibimosTodosTratamientoPlagaBD(){
		TratamientoPlagaDAO tratamientoPlagaDAO = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();

		List<TratamientoPlaga> tratamientosPlaga = tratamientoPlagaDAO.getAll();
		
		assertNotEquals(0, tratamientosPlaga.size());
	}
	
	@Test
	public void when_borramosUnTratamientoPlaga_Then_TratamientoPlagaEsBorrado(){
		TratamientoPlagaDAO tratamientoPlagaDAO = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();
		TratamientoPlaga tratamientoPlaga = new TratamientoPlaga();
		tratamientoPlaga.setId_plaga(999);
		tratamientoPlaga.setId_tratamiento(999);
		tratamientoPlaga.setCultivo("Calabaza");
		
		tratamientoPlagaDAO.putTratamientoPlaga(tratamientoPlaga);

		List<TratamientoPlaga> tratamientoPlagaList = tratamientoPlagaDAO.getTratamientoPlagaPorPlaga(999, "Calabaza");
		
		tratamientoPlagaDAO.deleteTratamientoPlaga(tratamientoPlagaList.get(0));
		
		assertEquals(0,tratamientoPlagaDAO.getTratamientoPlagaPorPlaga(999, "Calabaza").size());
	}
	
	@Test
	public void when_solicitamosUnTratamientoPlagaByIdPlagaYCultivo_Then_TratamientoPlagaDAORetornaUnTratamiento(){
		TratamientoPlagaDAO tratamientoPlagaDAO = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();
		
		List<TratamientoPlaga> tratamientoPlaga = tratamientoPlagaDAO.getTratamientoPlagaPorPlaga(3, "Apio");
		
		assertNotEquals(0,tratamientoPlaga.size());
	}
	
	@Test
	public void when_solicitamosUnTratamientoPlagaByIdTratamientoYCultivo_Then_TratamientoPlagaDAORetornaUnTratamiento(){
		TratamientoPlagaDAO tratamientoPlagaDAO = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();
		
		List<TratamientoPlaga> tratamientoPlaga = tratamientoPlagaDAO.getTratamientoPlagaPorTratamiento(20, "Calabaza");
		
		assertNotEquals(0,tratamientoPlaga.size());
	}
	
	@Test
	public void when_solicitamosTratamientoPlagaByCultivo_Then_TratamientoPlagaDAORetornaListaTratamientos(){
		TratamientoPlagaDAO tratamientoPlagaDAO = FactoriaDAO.getInstancia().getTratamientoPlagaDAO();
		
		List<TratamientoPlaga> tratamientoPlaga = tratamientoPlagaDAO.getTratamientoPlagaPorCultivo("Calabaza");
		
		assertNotEquals(0,tratamientoPlaga.size());
	}
}
