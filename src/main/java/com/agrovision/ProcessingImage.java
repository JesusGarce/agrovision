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

@Path("/files")
public class ProcessingImage {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C:/Users/Jesus/Desktop/Upload_Files/";

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

	// Búsqueda de las etiquetas en nuestra BBDD
		public Plaga searchPlaga(Map<Float,String> mapa){
			ControladorPlagas controlador = new ControladorPlagas();
			Plaga plaga = controlador.obtenerPlaga(mapa);
			//System.out.println("Plaga resultado:"+ plaga.getNombre() +" "+plaga.getDescripcion() );		
			//searchTratamientos(plaga,Cultivo.Calabaza);
			return plaga;
		}
	
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
	
	@POST
	@Path("/cultivos")
	@Produces("application/json")
	public List<String> searchCultivos(){
		return Cultivo.valuesStrings();
	}
	
	@POST
	@Path("/listaTratamientos")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchListaTratamientos(@FormDataParam("plaga") String plaga,
            @FormDataParam("cultivo") String cultivo){
		
		ControladorPlagas controlador = new ControladorPlagas();
		System.out.println("Plaga: "+plaga );
		Plaga plagaEnt = controlador.obtenerPlaga(plaga);
		Cultivo cultivoEnt = Cultivo.ofString(cultivo);
		
		System.out.println(cultivoEnt);
		System.out.println(plagaEnt.toString());
		List<Tratamiento> tratamientos = controlador.obtenerTratamientos(plagaEnt, cultivoEnt);
	
		return Response.status(200).entity(tratamientos).build();
	}
	
	@POST
	@Path("/listaTratamientosByNombreCientifico")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchListaTratamientosByNombreCientifico(@FormDataParam("plaga") String plaga,
            @FormDataParam("cultivo") String cultivo){
		System.out.println("Plaga: "+plaga + " | Cultivo "+cultivo);
		ControladorPlagas controlador = new ControladorPlagas();
		Plaga plagaEnt = controlador.obtenerPlagaByCientifico(plaga);
		
		Cultivo cultivoEnt = Cultivo.ofString(cultivo);
		
		System.out.println(cultivoEnt);
		System.out.println(plagaEnt.toString());
		List<Tratamiento> tratamientos = controlador.obtenerTratamientos(plagaEnt, cultivoEnt);
	
		return Response.status(200).entity(tratamientos).build();
	}
	
	@POST
	@Path("/tratamiento")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response searchTratamiento(@FormDataParam("tratamiento_id") int tratamiento_id){
		
		ControladorPlagas controlador = new ControladorPlagas();
		
		Tratamiento tratamiento = controlador.obtenerTratamiento(tratamiento_id);
		
		return Response.status(200).entity(tratamiento).build();
	}
	
	
	@POST
	@Path("/producto")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response searchProducto(@FormDataParam("producto_id") String producto_id){

		ControladorPlagas controlador = new ControladorPlagas();
		
		Producto producto = controlador.obtenerProducto(producto_id);
	
		return Response.status(200).entity(producto).build();

	}

}