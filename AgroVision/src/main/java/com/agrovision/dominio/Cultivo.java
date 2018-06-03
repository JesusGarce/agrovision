package com.agrovision.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumerado que muestra la lista de posibles cultivos
 * @author Jesus
 *
 */
public enum Cultivo {
	Apio, Br�coli, Calabaza, Lechuga, Ma�z_dulce, Sand�a, C�tricos, Melocot�n, Pimiento, Otro;

	/**
	 * Convierte a partir de un string en un Cultivo
	 * @param cultivo
	 * @return Retorna el cultivo
	 */
	public static Cultivo ofString(String cultivo){
		switch(cultivo){
		case "Apio":
			return Apio;
		case "Br�coli":
			return Br�coli;
		case "Calabaza":
			return Calabaza;
		case "Lechuga":
			return Lechuga;
		case "Ma�z dulce":
			return Ma�z_dulce;
		case "Sand�a":
			return Sand�a;
		case "C�tricos":
			return C�tricos;
		case "Melocot�n":
			return Melocot�n;
		case "Pimiento":
			return Pimiento;
		default:
			return Otro;
		}
	}
	
	/**
	 * Obtenemos la lista de cadenas que indican los cultivos existentes
	 * @return Retorna la lista de nombres de cultivos
	 */
	public static List<String> valuesStrings(){
		List<String> listaCultivos = new ArrayList<String>(10);
		for (Cultivo cultivo : Cultivo.values())
			listaCultivos.add(cultivo.toString());
		
		return listaCultivos;
			
	}

	/**
	 * Convierte un cultivo en un String
	 * @return Retorna la cadena
	 */
	public String toString(){
		switch (this){
			case Apio:
				return "Apio";
			case Br�coli:
				return "Br�coli";
			case Calabaza:
				return "Calabaza";
			case Lechuga:
				return "Lechuga";
			case Ma�z_dulce:
				return "Ma�z dulce";
			case Sand�a:
				return "Sand�a";
			case C�tricos:
				return "C�tricos";
			case Melocot�n:
				return "Melocot�n";
			case Pimiento:
				return "Pimiento";
			default:
				return "Otro";
		}
	}
}

