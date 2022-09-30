package com.nttdata.demoJUnit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.demoJUnit.bbdd.BaseDatosI;
import com.nttdata.demoJUnit.model.Articulo;

@Service
public class CarritoCompraServiceImp implements CarritoCompraServiceI {
	
	
	private List<Articulo> cesta = new ArrayList<Articulo>();
	
	@Autowired
	private BaseDatosI baseDatos;

	@Override
	public void limpiarCesta() {
		cesta.clear();
		
	}

	@Override
	public void addAticulo(Articulo a) {
		cesta.add(a);
		
	}

	@Override
	public Integer getNumArticulo() {
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return cesta;
	}
	
	@Override
	public List<Articulo> getArticulosBBDD() {
		baseDatos.iniciarBBDD();
		return baseDatos.getArticulo();
	}

	@Override
	public Double totalPrice() {
		Double precioTotal= 0.0;
		for(Articulo articulo : cesta)
		{
			precioTotal=precioTotal+articulo.getPrecio();
		}
		return precioTotal;
	}

	@Override
	public Double calculadorDescuento(double precio, double porcentajeDescuento) {
		return precio=(precio*porcentajeDescuento/100);
	}

	@Override
	public Double aplicarDescuentoBBDD(Integer id, double porcentajeDescuento) {
		Articulo articulo = baseDatos.buscarPorId(id);
		if (articulo != null) {
			return calculadorDescuento(articulo.getPrecio(), porcentajeDescuento);
		}else {
			System.out.println("El articulo no existe");
		}
		return null;
	}

	@Override
	public void insertar(Articulo a) {
		Integer id =baseDatos.addArticulo(a);
		cesta.add(a);
	}

}
