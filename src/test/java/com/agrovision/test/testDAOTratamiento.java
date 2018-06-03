package com.agrovision.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.agrovision.dao.FactoriaDAO;
import com.agrovision.dao.TratamientoDAO;
import com.agrovision.dominio.Tratamiento;

public class testDAOTratamiento {

	@Test
	public void when_creamosUnTratamiento_Then_TratamientoSeAlmacenaBD(){
		TratamientoDAO tratamientoDAO = FactoriaDAO.getInstancia().getTratamientoDAO();
		
		Tratamiento tratamiento = new Tratamiento();
		tratamiento.setNombre("Tratamiento curativo nuevo");
		tratamiento.setPrincipio_activo("Principio_activo");
		tratamiento.setClasificacion("Acaricida");
		tratamiento.setDescripcion("3 aplicaciones por ciclo de cultivo. 0,233gr/l");
		tratamiento.setProducto("23432");
		tratamiento.setTipo("tradicional");
		
		tratamientoDAO.putTratamiento(tratamiento);
		
		Tratamiento tratamientoAct = tratamientoDAO.getTratamiento("Tratamiento curativo nuevo");

		assertEquals(tratamiento,tratamientoAct);
	}

	
	@Test
	public void when_actualizamosTratamiento_Then_SeActualizaTratamientoBD(){
		TratamientoDAO tratamientoDAO = FactoriaDAO.getInstancia().getTratamientoDAO();
		
		Tratamiento tratamiento = new Tratamiento();
		tratamiento.setNombre("Tratamiento curativo nuevo");
		tratamiento.setPrincipio_activo("Principio_activo");
		tratamiento.setClasificacion("Acaricida");
		tratamiento.setDescripcion("3 aplicaciones por ciclo de cultivo. 0,233gr/l");
		tratamiento.setProducto("23432");
		tratamiento.setTipo("tradicional");
		
		tratamientoDAO.putTratamiento(tratamiento);
		
		tratamiento = tratamientoDAO.getTratamiento("Tratamiento curativo nuevo");
		tratamiento.setClasificacion("Insecticida");
		
		tratamientoDAO.updateTratamiento(tratamiento);
		
		Tratamiento tratamientoAct = tratamientoDAO.getTratamiento("Tratamiento curativo nuevo");
		tratamientoDAO.deleteTratamiento(tratamiento);
		
		assertEquals(tratamiento.getClasificacion(),tratamientoAct.getClasificacion());
	}
	
	
	@Test 
	public void when_solicitamosTodosTratamientos_Then_RecibimosTodosTratamientosBD(){
		TratamientoDAO tratamientoDAO = FactoriaDAO.getInstancia().getTratamientoDAO();

		List<Tratamiento> tratamientos = tratamientoDAO.getAll();
		
		assertNotEquals(0, tratamientos.size());
	}

	
	@Test
	public void when_solicitamosUnTratamientoById_Then_TratamientoDAORetornaUnTratamiento(){
		TratamientoDAO tratamientoDAO = FactoriaDAO.getInstancia().getTratamientoDAO();
		
		Tratamiento tratamiento = tratamientoDAO.getTratamiento(69);
		
		assertNotNull(tratamiento);
	}
	
	@Test
	public void when_solicitamosTratamientoByProducto_Then_TratamientoDAORetornaListaTratamientos(){
		TratamientoDAO tratamientoDAO = FactoriaDAO.getInstancia().getTratamientoDAO();
		
		List<Tratamiento> tratamientos = tratamientoDAO.getTratamientoPorProducto("17919");
	
		assertNotEquals(0, tratamientos.size());
	}
	
	
	@Test
	public void when_borramosUnTratamiento_Then_TratamientoEsBorradoEnBD(){
		TratamientoDAO tratamientoDAO = FactoriaDAO.getInstancia().getTratamientoDAO();

		Tratamiento tratamiento = tratamientoDAO.getTratamiento("Tratamiento curativo nuevo");
				
		tratamientoDAO.deleteTratamiento(tratamiento);
		
		assertNull(tratamientoDAO.getTratamiento("Tratamiento curativo nuevo"));
	}
}
