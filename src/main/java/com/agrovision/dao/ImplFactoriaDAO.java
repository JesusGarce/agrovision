package com.agrovision.dao;


public class ImplFactoriaDAO extends FactoriaDAO {

	
	@Override
	public ImplPlagaDAO getPlagaDAO() {
		return new ImplPlagaDAO();
	}

	@Override
	public ImplProductoDAO getProductoDAO() {
		return new ImplProductoDAO();

	}

	@Override
	public ImplTratamientoDAO getTratamientoDAO() {
		return new ImplTratamientoDAO();
	}

	@Override
	public ImplTratamientoPlagaDAO getTratamientoPlagaDAO() {
		return new ImplTratamientoPlagaDAO();
	}

	@Override
	public RegistroImagenesDAO getRegistroImagenesDAO() {
		return new ImplRegistroImagenesDAO();
	}

}
