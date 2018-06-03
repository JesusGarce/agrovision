package com.agrovision.dominio;

import java.util.ArrayList;
import java.util.List;

public enum Cultivo {
	Apio, Brócoli, Calabaza, Lechuga, Maíz_dulce, Sandía, Cítricos, Melocotón, Pimiento, Otro;

	public static Cultivo ofString(String cultivo){
		switch(cultivo){
		case "Apio":
			return Apio;
		case "Brócoli":
			return Brócoli;
		case "Calabaza":
			return Calabaza;
		case "Lechuga":
			return Lechuga;
		case "Maíz dulce":
			return Maíz_dulce;
		case "Sandía":
			return Sandía;
		case "Cítricos":
			return Cítricos;
		case "Melocotón":
			return Melocotón;
		case "Pimiento":
			return Pimiento;
		default:
			return Otro;
		}
	}
	
	public static List<String> valuesStrings(){
		List<String> listaCultivos = new ArrayList<String>(10);
		for (Cultivo cultivo : Cultivo.values())
			listaCultivos.add(cultivo.toString());
		
		return listaCultivos;
			
	}

	public String toString(){
		switch (this){
			case Apio:
				return "Apio";
			case Brócoli:
				return "Brócoli";
			case Calabaza:
				return "Calabaza";
			case Lechuga:
				return "Lechuga";
			case Maíz_dulce:
				return "Maíz dulce";
			case Sandía:
				return "Sandía";
			case Cítricos:
				return "Cítricos";
			case Melocotón:
				return "Melocotón";
			case Pimiento:
				return "Pimiento";
			default:
				return "Otro";
		}
	}
}

