package com.agrovision.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.agrovision.dao.FactoriaDAO;
import com.agrovision.dao.RegistroImagenesDAO;
import com.agrovision.dominio.RegistroImagenes;

public class testDAORegistroImagenes {


	@Test
	public void when_solicitamosLosRegistros_Then_RecibimosTodosLosRegistros(){
		RegistroImagenesDAO registroImagenesDAO = FactoriaDAO.getInstancia().getRegistroImagenesDAO();

		List<RegistroImagenes> registroImagenes = registroImagenesDAO.obtenerAllRegistrosImagenes();
		
		Assert.assertFalse(registroImagenes.isEmpty());
	}
	
	@Test
	public void when_solicitamosLosRegistrosPosterioresAUnaFecha_Then_RecibimosLosRegistrosPosteriores() throws ParseException{
		RegistroImagenesDAO registroImagenesDAO = FactoriaDAO.getInstancia().getRegistroImagenesDAO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String fechaString = "2018/05/25 15:59:29";
		Date fecha = sdf.parse(fechaString);

		List<RegistroImagenes> registroImagenes = registroImagenesDAO.obtenerRegistrosDespuesDeFecha(fecha);
		
		Assert.assertFalse(registroImagenes.isEmpty());
	}
	
	@Test
	public void when_solicitamosLaCantidadDeRegistros_Then_RecibimosLaCantidad(){
		RegistroImagenesDAO registroImagenesDAO = FactoriaDAO.getInstancia().getRegistroImagenesDAO();

		Assert.assertTrue(registroImagenesDAO.obtenerCantidadImagenesSubidas()>0);
	}
	
	@Test
	public void when_solicitamosLaCantidadDeRegistrosSinPlaga_Then_RecibimosLaCantidad(){
		RegistroImagenesDAO registroImagenesDAO = FactoriaDAO.getInstancia().getRegistroImagenesDAO();

		Assert.assertTrue(registroImagenesDAO.obtenerCantidadImagenesSubidasSinPlaga()>0);
	}
	
	@Test
	public void when_solicitamosLaCantidadDeRegistrosConPlaga_Then_RecibimosLaCantidad(){
		RegistroImagenesDAO registroImagenesDAO = FactoriaDAO.getInstancia().getRegistroImagenesDAO();

		Assert.assertTrue(registroImagenesDAO.obtenerCantidadImagenesSubidasConPlaga()>0);
	}
	
	@Test
	public void when_creamosUnRegistro_Then_RegistroSeAlmacenaBD(){
		RegistroImagenesDAO registroImagenesDAO = FactoriaDAO.getInstancia().getRegistroImagenesDAO();
		
		int cant_anterior = registroImagenesDAO.obtenerCantidadImagenesSubidas();
		
		RegistroImagenes registroImagenes = new RegistroImagenes();
		registroImagenes.setPlaga("Plaga");
		registroImagenes.setPlaga_encontrada(true);
		registroImagenes.setUrl_imagen("C:/Users/Jesus/Desktop/Upload_Files/plaga.jpg");
		registroImagenes.setFecha_registro(new Date(System.currentTimeMillis()));
		
		registroImagenesDAO.añadirRegistroImagenes(registroImagenes);
		
		int cant_posterior = registroImagenesDAO.obtenerCantidadImagenesSubidas();
		
		Assert.assertTrue(cant_posterior > cant_anterior);
	}
	
}
