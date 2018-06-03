package com.agrovision;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.agrovision.controller.ControladorPlagas;
import com.agrovision.dominio.Cultivo;
import com.agrovision.dominio.Plaga;
import com.agrovision.dominio.Producto;
import com.agrovision.dominio.RegistroImagenes;
import com.agrovision.dominio.Tratamiento;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Clase que actúa como servicio REST de la aplicación web.
 * @author Jesus
 *
 */
@Path("/files")
public class ProcessingImage {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C:/Users/Jesus/Desktop/Upload_Files/";

	/**
	 * Función que a partir de una imagen recibida en una petición HTTP retorna una plaga en caso de que exista.
	 * @param fileInputStream FormData de la petición HTTP recibida
	 * @param contentDispositionHeader Cabecera de la petición HTTP recibida
	 * @return Una plaga en caso de que exista
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

		System.out.println("Estamos en ProcessingImage: "+contentDispositionHeader.getFileName());
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER	+ contentDispositionHeader.getFileName();

		// save the file to the server
		saveFile(fileInputStream, filePath);
		System.out.println("Almacenamos en servidor: "+filePath);

		VisionProcess vp = new VisionProcess(filePath);
		Map<Float,String> labels = new TreeMap<Float,String>();
		try {
			labels = vp.execute();
		} catch (Exception e) {
			return null; //Response.status(400).entity(e.getMessage()).build();
		}
		Plaga plaga = searchPlaga(labels);
	
		Date date = new Date(System.currentTimeMillis());
		RegistroImagenes registroImagenes = new RegistroImagenes();
		registroImagenes.setFecha_registro(date);
		if (plaga!=null)
			registroImagenes.setPlaga(plaga.getNombre());
		registroImagenes.setPlaga_encontrada(plaga!=null);
		registroImagenes.setUrl_imagen(filePath);
		
		ControladorPlagas controlador = new ControladorPlagas();
		controlador.almacenarRegistroImagenes(registroImagenes);
		
		return Response.status(200).entity(plaga).build();

	}

	/**
	 * Función privada que permite encontrar una plaga a partir del mapa de etiquetas devuelo por API Cloud Vision
	 * @param mapa Etiquetas de la imagen que se ha recibido como parámetro
	 * @return Retorna una plaga en caso de que exista; si no, retorna un valor nulo.
	 */
	// Búsqueda de las etiquetas en nuestra BBDD
		public Plaga searchPlaga(Map<Float,String> mapa){
			ControladorPlagas controlador = new ControladorPlagas();
			Plaga plaga = controlador.obtenerPlaga(mapa);
			return plaga;
		}
	
	/**
	 * Función privada que permite almacenar la imagen recibida en el path pasado como parámetro
	 * @param uploadedInputStream La imagen recibida
	 * @param serverLocation El path donde queremos almacenar la imagen
	 */
	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream,
			String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	/**
	 * Función que retorna la lista de cultivos existentes a partir de una petición HTTP
	 * @return Lista de cultivos existentes
	 */
	@POST
	@Path("/cultivos")
	@Produces("application/json")
	public List<String> searchCultivos(){
		return Cultivo.valuesStrings();
	}
	
	/**
	 * Función que devuelve la lista de tratamientos de una plaga a partir del nombre común de la plaga y un cultivo
	 * @param plaga Nombre común de la plaga 
	 * @param cultivo Nombre del cultivo
	 * @return Devuelve la lista de tratamientos para esa relación plaga/cultivo.
	 */
	@POST
	@Path("/listaTratamientos")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchListaTratamientos(@FormDataParam("plaga") String plaga,
            @FormDataParam("cultivo") String cultivo){
		
		ControladorPlagas controlador = new ControladorPlagas();
		Plaga plagaEnt = controlador.obtenerPlaga(plaga);
		Cultivo cultivoEnt = Cultivo.ofString(cultivo);
		
		List<Tratamiento> tratamientos = controlador.obtenerTratamientos(plagaEnt, cultivoEnt);
	
		return Response.status(200).entity(tratamientos).build();
	}
	
	/**
	 * Función que devuelve la lista de tratamientos de una plaga a partir del nombre científico de la plaga y un cultivo
	 * @param plaga Nombre científico de la plaga 
	 * @param cultivo Nombre del cultivo
	 * @return Devuelve la lista de tratamientos para esa relación plaga/cultivo.
	 */
	@POST
	@Path("/listaTratamientosByNombreCientifico")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchListaTratamientosByNombreCientifico(@FormDataParam("plaga") String plaga,
            @FormDataParam("cultivo") String cultivo){
		ControladorPlagas controlador = new ControladorPlagas();
		Plaga plagaEnt = controlador.obtenerPlagaByCientifico(plaga);
		
		Cultivo cultivoEnt = Cultivo.ofString(cultivo);

		List<Tratamiento> tratamientos = controlador.obtenerTratamientos(plagaEnt, cultivoEnt);
	
		return Response.status(200).entity(tratamientos).build();
	}
	
	/**
	 * Función que retorna el tratamiento a partir de su identificador
	 * @param tratamiento_id Identificador del tratamiento que queremos obtener
	 * @return Retorna el tratamiento en caso de que exista.
	 */
	@POST
	@Path("/tratamiento")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response searchTratamiento(@FormDataParam("tratamiento_id") int tratamiento_id){
		
		ControladorPlagas controlador = new ControladorPlagas();
		
		Tratamiento tratamiento = controlador.obtenerTratamiento(tratamiento_id);
		
		return Response.status(200).entity(tratamiento).build();
	}
	
	/**
	 * Función que retorna el producto a partir de su identificador
	 * @param producto_id Identificador del producto que queremos obtener
	 * @return Retorna el producto en caso de que exista.
	 */
	@POST
	@Path("/producto")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response searchProducto(@FormDataParam("producto_id") String producto_id){

		ControladorPlagas controlador = new ControladorPlagas();
		
		Producto producto = controlador.obtenerProducto(producto_id);
	
		return Response.status(200).entity(producto).build();

	}

}