package com.nttdata.demoJUnit.bbdd;

import java.util.List;

import com.nttdata.demoJUnit.model.Articulo;

public interface BaseDatosI {

	public void iniciarBBDD();
	
	public List<Articulo> getArticulo();
	
	public Articulo buscarPorId(Integer id);
	
	public Integer addArticulo (Articulo articulo);
}
