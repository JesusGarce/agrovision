package com.agrovision.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.agrovision.VisionProcess;
import com.agrovision.controller.ControladorPlagas;
import com.agrovision.dominio.Cultivo;
import com.agrovision.dominio.Plaga;
import com.agrovision.dominio.Producto;
import com.agrovision.dominio.Tratamiento;

public class testControlador {

	@Test
	public void when_RecibeMapaDeEtiquetas_Then_RetornaUnaPlagaSiExiste() throws IOException, Exception {
		String filePath = "C:/Users/Jesus/Desktop/Upload_Files/ceratitis-capitata.jpg";
		VisionProcess vp = new VisionProcess(filePath);
		Map<Float,String> labels = vp.execute();
		
		ControladorPlagas controladorPlagas = new ControladorPlagas();
		Plaga plaga = controladorPlagas.obtenerPlaga(labels);
		
		assertNotNull(plaga);
	}

	@Test
	public void when_RecibeNombreComunPlaga_Then_RetornaPlagaSiExiste() {
		ControladorPlagas controladorPlagas = new ControladorPlagas();
		
		Plaga plaga = controladorPlagas.obtenerPlaga("Oruga");
		
		assertNotNull(plaga);
	}
	
	@Test
	public void when_RecibePlagaYCultivo_Then_RetornaTratamientos() {
		ControladorPlagas controladorPlagas = new ControladorPlagas();
		Plaga plaga = controladorPlagas.obtenerPlaga("Oruga");
		
		List<Tratamiento> tratamientos = controladorPlagas.obtenerTratamientos(plaga, Cultivo.Calabaza);
		
		assertNotEquals(0,tratamientos.size());
	}
	
	@Test
	public void when_RecibeIdTratamiento_Then_RetornaTratamiento() {
		ControladorPlagas controladorPlagas = new ControladorPlagas();

		Tratamiento tratamiento = controladorPlagas.obtenerTratamiento(23);
		
		assertNotNull(tratamiento);
	}
	
	@Test
	public void when_RecibeIdProducto_Then_RetornaProducto() {
		ControladorPlagas controladorPlagas = new ControladorPlagas();

		Producto producto = controladorPlagas.obtenerProducto("17919");
		
		assertNotNull(producto);
	}
	
	@Test
	public void when_RecibeTratamiento_Then_RetornaProducto() {
		ControladorPlagas controladorPlagas = new ControladorPlagas();
		Tratamiento tratamiento = controladorPlagas.obtenerTratamiento(23);
		
		Producto producto = controladorPlagas.obtenerProductodeTratamiento(tratamiento);
		
		assertNotNull(producto);
	}
	
}
