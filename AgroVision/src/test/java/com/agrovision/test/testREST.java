package com.agrovision.test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.agrovision.ProcessingImage;
import com.agrovision.VisionProcess;
import com.agrovision.controller.ControladorPlagas;
import com.agrovision.dominio.Cultivo;
import com.agrovision.dominio.Plaga;
import com.agrovision.dominio.Tratamiento;

public class testREST {

	//private static String url = "";	
	
	@Test
	public void when_SeIniciaAgrovision_then_SeCargaListaCultivos() {
		ProcessingImage processingImage = new ProcessingImage();
		List<String> listaCultivosActual = processingImage.searchCultivos();
		
		assertEquals(Cultivo.valuesStrings(),listaCultivosActual);
	}
	
	@Test
	public void when_SeSubeUnaImagen_then_RetornaLaPlagaSiLaEncuentra() throws IOException, Exception {
		ProcessingImage processingImage = new ProcessingImage();
		String filePath = "C:/Users/Jesus/Desktop/Upload_Files/ceratitis-capitata.jpg";
		VisionProcess vp = new VisionProcess(filePath);
		Map<Float,String> labels = vp.execute();
		Plaga plaga = processingImage.searchPlaga(labels);

		assertNotNull(plaga);
	}
	
	@Test
	public void when_SeSubeUnaImagen_then_RetornaNuloSiNoEncuentraPlaga() throws IOException, Exception {
		ProcessingImage processingImage = new ProcessingImage();
		String filePath = "C:/Users/Jesus/Desktop/Upload_Files/not_found.png";
		VisionProcess vp = new VisionProcess(filePath);
		Map<Float,String> labels = vp.execute();
		Plaga plaga = processingImage.searchPlaga(labels);
		
		assertNull(plaga);
	}
	
	@Test
	public void when_SeSolicitaListaTratamientos_then_SeRetornaListaTratamientos() throws IOException, Exception {
		ProcessingImage processingImage = new ProcessingImage();
		String filePath = "C:/Users/Jesus/Desktop/Upload_Files/ceratitis-capitata.jpg";
		VisionProcess vp = new VisionProcess(filePath);
		Map<Float,String> labels = vp.execute();
		Plaga plaga = processingImage.searchPlaga(labels);
		ControladorPlagas controladorPlagas = new ControladorPlagas();
		List<Tratamiento> tratamientos = controladorPlagas.obtenerTratamientos(plaga, Cultivo.Calabaza);
		
		assertNotNull(tratamientos);
	}
	
	@Test
	public void when_SeSolicitaTratamiento_then_SeRetornaTratamiento() {
		ProcessingImage processingImage = new ProcessingImage();

		Response respuesta = processingImage.searchTratamiento(69);

		assertNotNull(respuesta.getEntity());
	}
	
	@Test
	public void when_SeSolicitaUnProducto_then_SeRetornaProducto() {
		ProcessingImage processingImage = new ProcessingImage();
		
		Response respuesta = processingImage.searchProducto("17919");

		assertNotNull(respuesta.getEntity());
	}
	

}
