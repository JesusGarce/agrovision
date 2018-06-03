var tratamientosSalida;
var tratamientoSalida;
var plagaSalida;
var listaCultivos;
var cultivoElegido;
var productoElegido;
var tratamientoElegido;

var divTratamiento;
var divTratamientos;
var divInicio;
var divCultivo;
var divProducto;

var url_server = 'http://192.168.1.21:8080/AgroVision/rest/files/';

function cultivos(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url_server + 'cultivos');
	//xhr.open('POST', 'http://172.22.77.130:8080/AgroVision/rest/files/cultivos');

	xhr.onload = function(){
		listaCultivos = JSON.parse(xhr.responseText);

		console.log("Lista cultivos: "+listaCultivos);
		for (var cultivo in listaCultivos){
			console.log("Cultivo: " + listaCultivos[cultivo]);
			
			divInicio = "<h3> En primer lugar ... </h3>";
			divInicio += "<p><h6> Necesitamos saber tu cultivo para ofrecerte los tratamientos de manera más precisa, ya que para cada cultivo hay unos tratamientos más adecuados que otros.  </h6><p>"
			divInicio += "<img src='img/logo2.svg' height='150' width='150'>";
			divInicio += "<div class='dropdown-show'>";
			divInicio += "<b>¿Cual es tu cultivo?</b><br>";
			divInicio += "  <a class='btn btn-default dropdown-toggle' href='#download' role='button' id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>";
			divInicio += "Elige</a>";
			divInicio += "<div class='dropdown-menu' aria-labelledby='dropdownMenuLink'>";
			for (var cultivo in listaCultivos){
				divInicio += "<button class='dropdown-item' type='button' onClick='cultivoElegido(`"+listaCultivos[cultivo]+"`);'>"+listaCultivos[cultivo]+"</button>"
			}	
			
			document.getElementById("plaga").innerHTML = divInicio;

		}
	}
	
	xhr.send();
	
}

function cultivoElegido(cultivo){
	cultivoElegido = cultivo;
	console.log("Cultivo Elegido: "+cultivoElegido);
	
	var imgCultivo;
	
	switch(cultivoElegido) {
	case "Apio":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/923/mvWo1W.png";
		break;
	case "Brócoli":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/923/ZMR7pz.png";
		break;
	case "Calabaza":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/923/ZdXK4J.png";
		break;
	case "Lechuga":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/922/9XlEdF.png";
		break;
	case "Maíz dulce":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/923/fKw5QL.png";
		break;
	case "Sandía":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/924/zMX678.jpg";
		break;
	case "Cítricos":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/923/4QZj25.jpg";
		break;
	case "Melocotón":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/922/vnHA3J.jpg";
		break;
	case "Pimiento":
		imgCultivo = "https://imageshack.com/a/img924/3338/e9IhYI.gif";
		break;
	case "Otro":
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/922/3XnPbx.jpg";
		break;
	default:
		imgCultivo = "https://imagizer.imageshack.com/v2/320x240q90/922/3XnPbx.jpg";
		break;
	
	}
	
	divCultivo = "<h3> ¡Bien! Tu cultivo es <b>"+cultivoElegido+"</b> </h3>";
	divCultivo += "<img src='"+imgCultivo+"' height='100' width='100'>";
	divCultivo += "<p><h6> Ahora, el siguiente paso es enviarnos la imagen que quieres que sea procesada por <b>AgroVision</b>. </h6></p>";
	divCultivo += "<p><h6> Para ello, pulsa sobre el botón <b>Siguiente</b>, y aparecerá en tu pantalla la opción de seleccionar o arrastrar una imagen. </h6></p>";
	divCultivo += "<p><h6> En caso de que el cultivo elegido no sea el esperado, pulsa sobre el botón <b>Volver atrás</b> y podrás elegir otro cultivo. </h6></p>";
	divCultivo += "<p><div class='row'> <div class='col-xs-6 col-md-4'>";
	divCultivo += "<a href='javascript:window.location.reload(true)' class='btn btn-default' role='button' id='boton'>< Volver atrás</a></div>";
	divCultivo += "<div class='col-xs-6 col-md-4'></div>";
	divCultivo += "<div class='col-xs-6 col-md-4'>";
	divCultivo += "<a href='#download' onClick='passToInput(`"+cultivoElegido+"`);' class='btn btn-default' role='button' id='producto'>Siguiente ></a> </div></p>";

	document.getElementById("plaga").innerHTML = divCultivo;

}

function passToInput(cultivo){
	var input = '<form><input type="file" accept="image/*"></form>';
	document.getElementById("plaga").innerHTML = input;
	$('input[type="file"]').imageuploadify();
}

function tratamientos(plaga){

	        const formData = new FormData();

			formData.append("plaga",plaga);
			formData.append("cultivo",cultivoElegido);

			var xhr = new XMLHttpRequest();
            xhr.open('POST', url_server + 'listaTratamientos');
            //xhr.open('POST', 'http://localhost:8080/AgroVision/rest/files/listaTratamientos');
			//xhr.open('POST', 'http://172.22.77.130:8080/AgroVision/rest/files/listaTratamientos');
            
            xhr.onload = function () {
            	tratamientosSalida = JSON.parse(xhr.responseText);
        		console.log("Tratamientos: "+tratamientosSalida);
            	
        		var tratamientosTradicionales = new Array();
        		var tratamientosEcologicos = new Array();
        		
        		for (var tratamiento in tratamientosSalida){
        			console.log("Tratamiento: tratamientosSalida[tratamiento] + ");
        			if (tratamientosSalida[tratamiento].tipo == "tradicional"){
        				tratamientosTradicionales.push(tratamientosSalida[tratamiento]);
        			}
        			else if (tratamientosSalida[tratamiento].tipo == "ecológico"){
        				tratamientosEcologicos.push(tratamientosSalida[tratamiento])
        			}
        		}
        		
        		divTratamientos ="<h3>"+plaga+" | "+cultivoElegido+"</h3>";
            	divTratamientos += "<p><ul class='list-group'> <h6><b>Tratamientos tradicionales</b></h6>";
        		
            	if (tratamientosTradicionales.length == 0)
                	divTratamientos += "No hay tratamientos tradicionales para este cultivo.";
            	else if (tratamientosTradicionales.length > 5) {
            		for (i = 0; i < 5; i++){
            			console.log("Tratamiento tradicional: "+tratamientosTradicionales[i].nombre);
            			divTratamientos += "<li class='list-group-item d-flex justify-content-between align-items-center'>";
            			divTratamientos += tratamientosTradicionales[i].nombre+" | "+tratamientosTradicionales[i].clasificacion;
            			divTratamientos += "<a href='#download' onClick='tratamiento(`"+tratamientosTradicionales[i].id+"`);' class='badge badge-success'><b>></b></a></li>";
                    }
            	} else {
            		for (var tratTrad in tratamientosTradicionales){
            			console.log("Tratamiento tradicional: "+tratamientosTradicionales[tratTrad].nombre);
            			divTratamientos += "<li class='list-group-item d-flex justify-content-between align-items-center'>";
            			divTratamientos += tratamientosTradicionales[tratTrad].nombre+" | "+tratamientosTradicionales[tratTrad].clasificacion;
            			divTratamientos += "<a href='#download' onClick='tratamiento(`"+tratamientosTradicionales[tratTrad].id+"`);' class='badge badge-success'><b>></b></a></li>";
                    }
            	}
            	
            	divTratamientos+="</p><p><ul class='list-group'> <h6><b>Tratamientos ecológicos</b></h6>";
            	
            	if (tratamientosEcologicos.length == 0)
                	divTratamientos += "No hay tratamientos ecológicos para este cultivo.";
            	else {
            		for (var tratEcol in tratamientosEcologicos){
            			console.log("Tratamiento ecológico: "+tratamientosEcologicos[tratEcol]);
            			divTratamientos += "<li class='list-group-item d-flex justify-content-between align-items-center'>";
            			divTratamientos += tratamientosEcologicos[tratEcol].nombre;
            			divTratamientos += "<a href='#download' onClick='tratamiento(`"+tratamientosEcologicos[tratEcol].id+"`);' class='badge badge-success'><b>></b></a></li>";
                    }
            	}
            	
            	divTratamientos +="</ul></ul></p>";
            	divTratamientos += "<a href='#download' onClick='passToInput(`"+cultivoElegido+"`);' class='btn btn-default' role='button' id='boton'>< Volver atrás</a>";

            	document.getElementById("plaga").innerHTML = divTratamientos;
            };

            xhr.send(formData);  
            
	}

function tratamiento(tratamiento_id){
	
	for (var tratamiento in tratamientosSalida){
		if (tratamientosSalida[tratamiento].id == tratamiento_id){
			console.log("Tratamiento: "+tratamientosSalida[tratamiento]);
			tratamientoSalida = tratamientosSalida[tratamiento];
		}
	}
	
	divTratamiento = "<h3 id='nombreTratamiento'> "+tratamientoSalida.nombre + "</h3>";
	divTratamiento += "<p><h6> "+tratamientoSalida.descripcion +"</h6><p>";
	divTratamiento += "<p><ul>";
	if (tratamientoSalida.principio_activo!=null)
		divTratamiento += "<li><b>Principio activo: </b>"+tratamientoSalida.principio_activo+"</li>";
	if (tratamientoSalida.clasificacion!=null)
		divTratamiento += "<li><b>Clasificación: </b>"+tratamientoSalida.clasificacion+"</li></ul></p>";
	divTratamiento += "<div class='row'> <div class='col-xs-6 col-md-4'>";
	divTratamiento += "<a href='#download' onClick='volverListaTratamientos();' class='btn btn-default' role='button' id='boton'>< Volver atrás</a></div>";
	if (tratamientoSalida.producto!=null){
    	divTratamiento += "<div class='col-xs-6 col-md-4'></div>";
    	divTratamiento += "<div class='col-xs-6 col-md-4'>";
    	divTratamiento += "<a href='#download' onClick='producto(`"+tratamientoSalida.producto+"`);' class='btn btn-default' role='button' id='producto'>Ver producto ></a> ";
    	divTratamiento += "</div></div>";
	} else {
		divTratamiento += "</div>";
	}
	
	document.getElementById("plaga").innerHTML = divTratamiento;
	
}

/*
function tratamiento(tratamiento_id){	
	
	
	const formData = new FormData();
	formData.append("tratamiento_id",tratamiento_id);
	
	var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/AgroVision/rest/files/tratamiento');

    xhr.onload = function () {
    	tratamientoSalida = JSON.parse(xhr.responseText);
    	
    	divTratamiento = "<h3 id='nombreTratamiento'> "+tratamientoSalida.nombre + "</h3>";
    	divTratamiento += "<p><h6> "+tratamientoSalida.descripcion +"</h6><p>";
    	divTratamiento += "<p><ul>";
    	if (tratamientoSalida.principio_activo!=null)
    		divTratamiento += "<li><b>Principio activo: </b>"+tratamientoSalida.principio_activo+"</li>";
    	if (tratamientoSalida.clasificacion!=null)
    		divTratamiento += "<li><b>Clasificación: </b>"+tratamientoSalida.clasificacion+"</li></ul></p>";
    	divTratamiento += "<div class='row'> <div class='col-xs-6 col-md-4'>";
    	divTratamiento += "<a href='#download' onClick='volverListaTratamientos();' class='btn btn-default' role='button' id='boton'>< Volver atrás</a></div>";
    	if (tratamientoSalida.producto!=null){
        	divTratamiento += "<div class='col-xs-6 col-md-4'></div>";
        	divTratamiento += "<div class='col-xs-6 col-md-4'>";
        	divTratamiento += "<a href='#download' onClick='producto(`"+tratamientoSalida.producto+"`);' class='btn btn-default' role='button' id='producto'>Ver producto ></a> ";
        	divTratamiento += "</div></div>";
    	} else {
    		divTratamiento += "</div>";
    	}
    	
    	document.getElementById("plaga").innerHTML = divTratamiento;
    };

    xhr.send(formData); 
	
}
*/

function producto(producto_id){
	
	const formData = new FormData();
	formData.append("producto_id",producto_id);
	
	var xhr = new XMLHttpRequest();
    xhr.open('POST', url_server + 'producto');
    //xhr.open('POST', 'http://172.22.77.130:8080/AgroVision/rest/files/producto');
    
    xhr.onload = function () {
    	productoElegido = JSON.parse(xhr.responseText);
    	
    	console.log("Producto: "+productoElegido);
    	
    	divProducto = "<img src='"+productoElegido.imagen+"' height='150' width='150'><br>";
    	divProducto += "<br><h3 id='nombreProducto'> "+productoElegido.nombre + "</h3>";
    	divProducto += "<p><h6> "+productoElegido.descripcion +"</h6><p>";
    	divProducto += "<div class='row'> <div class='col-xs-6 col-md-4'>";
    	divProducto += "<a href='#download' onClick='volverTratamiento();' class='btn btn-default' role='button' id='boton'>< Volver atrás</a> </div>";
    	divProducto += "<div class='col-xs-6 col-md-4'></div>";
    	divProducto += "<div class='col-xs-6 col-md-4'>";
    	divProducto += "<a href='"+productoElegido.url+"' target='_blank' class='btn btn-default' role='button' id='producto'>Saber más ></a> ";
    	divProducto += "</div></div>";
    	
    	document.getElementById("plaga").innerHTML = divProducto;
    	
    };

    xhr.send(formData);
	
}

function volverInicio(){
	document.getElementById("plaga").innerHTML = divInicio;
}

function volverTratamiento(){
	document.getElementById("plaga").innerHTML = divTratamiento;
}

function volverListaTratamientos(){
	document.getElementById("plaga").innerHTML = divTratamientos;
}