package com.agrovision.dao;

/**
 * Singleton que actúa de Factoria del resto de clases DAO
 * @author Jesus
 *
 */
public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia = null;

	public static FactoriaDAO getInstancia() {
		if (unicaInstancia == null)
			try{
			unicaInstancia= new ImplFactoriaDAO();
			} catch(Exception e){
				e.getMessage();
			}
		return unicaInstancia;
	}
	
	public FactoriaDAO() {	};
	
	public abstract PlagaDAO getPlagaDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract TratamientoDAO getTratamientoDAO();
	public abstract TratamientoPlagaDAO getTratamientoPlagaDAO();
	public abstract RegistroImagenesDAO getRegistroImagenesDAO();

}
