package com.nttdata.demoJUnit.service;

import java.util.List;

import com.nttdata.demoJUnit.model.Articulo;

public interface CarritoCompraServiceI {

	public void limpiarCesta();
	
	public void addAticulo(Articulo a);
	
	public Integer getNumArticulo();
	
	List<Articulo> getArticulos();
	
	List<Articulo> getArticulosBBDD(); 
	
	Double totalPrice(); 
	
	Double calculadorDescuento(double precio, double porcentajeDescuento); 
	
	Double aplicarDescuentoBBDD(Integer id, double porcentajeDescuento); 
	
	public void insertar(Articulo a);
}
