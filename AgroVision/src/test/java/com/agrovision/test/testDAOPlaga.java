package com.agrovision.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.agrovision.dao.FactoriaDAO;
import com.agrovision.dao.PlagaDAO;
import com.agrovision.dominio.Plaga;

public class testDAOPlaga {

	@Test
	public void when_solicitamosUnaPlagaById_Then_PlagaDAORetornaUnaPlaga(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		
		Plaga plaga = plagaDAO.getPlaga(23);
		
		assertNotNull(plaga);
	}
	
	@Test
	public void when_solicitamosUnaPlagaByNombreCientifico_Then_PlagaDAORetornaUnaPlaga(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		
		Plaga plaga = plagaDAO.getPlaga("Caterpillar");
		
		assertNotNull(plaga);
	}
	
	@Test
	public void when_solicitamosUnaPlagaByNombreComun_Then_PlagaDAORetornaUnaPlaga(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		
		Plaga plaga = plagaDAO.getPlagaPorNombre("Oruga");
		
		assertNotNull(plaga);
	}
	
	@Test
	public void when_creamosUnaPlaga_Then_PlagaSeAlmacenaBD(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		Plaga plaga = new Plaga();
		plaga.setNombre("Sesia");
		plaga.setNombre_cientifico("Sesia apiformis");
		plaga.setCausa("Aparecen al comienzo de la primavera y se realiza la puesta sobre la corteza del árbol.");
		plaga.setDescripcion("Es un insecto parecido a una avispa que debilitan el árbol provocando incluso su muerte.");
		plaga.setImagen("http://aprendeanimal.com/wp-content/uploads/2014/04/sesia-del-alamo-foto_principal.png");
		
		plagaDAO.putPlaga(plaga);
		
		Plaga plagaAct = plagaDAO.getPlaga("Sesia apiformis");
		
		assertEquals(plaga, plagaAct);
	}
	
	@Test
	public void when_solicitamosTodasPlagas_Then_RecibimosTodasPlagas(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		
		List<Plaga> listaPlagas = plagaDAO.getAll();
		
		assertNotEquals(0,listaPlagas.size());
	}
	
	@Test
	public void when_actualizamosPlaga_Then_PlagaEsActualizada(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		Plaga plaga = plagaDAO.getPlagaPorNombre("Sesia");
		plaga.setCausa("Aparecen al comienzo de la primavera y se realiza la puesta sobre la corteza del árbol.");

		plagaDAO.updatePlaga(plaga);
		
		Plaga plagaAct = plagaDAO.getPlagaPorNombre("Sesia");
	
		assertEquals(plaga.getDescripcion(),plagaAct.getDescripcion());
	}
	
	@Test
	public void when_borramosUnaPlaga_Then_PlagaEsBorradaBD(){
		PlagaDAO plagaDAO = FactoriaDAO.getInstancia().getPlagaDAO();
		Plaga plaga = plagaDAO.getPlagaPorNombre("Sesia");
		
		plagaDAO.deletePlaga(plaga);
		
		Plaga plagaAct = plagaDAO.getPlagaPorNombre("Sesia");
		
		assertNull(plagaAct);
	}
}
