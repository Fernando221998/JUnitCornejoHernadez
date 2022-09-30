package com.nttdata.demoJUnit.bbdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.demoJUnit.model.Articulo;
import com.nttdata.demoJUnit.service.CarritoCompraServiceI;

@Service
public class BaseDatosImp implements BaseDatosI {

	private Map<Integer, Articulo> baseDatos= new HashMap<>();
	

	
	@Override
	public void iniciarBBDD() {
		baseDatos.put(1, new Articulo("Pantalon", 200.00));
		baseDatos.put(2, new Articulo("Camisa", 300.00));
		baseDatos.put(3, new Articulo("Jersey", 400.00));
		baseDatos.put(4, new Articulo("Vestido", 500.00));
	}

	@Override
	public List<Articulo> getArticulo() {
		List<Articulo> listaArticulos = new ArrayList<>();
		for (Map.Entry<Integer, Articulo> entry : baseDatos.entrySet()) {
			listaArticulos.add(entry.getValue());
		}
		return listaArticulos;
	}

	@Override
	public Articulo buscarPorId(Integer id) {	
		return baseDatos.get(id);
	}

	@Override
	public Integer addArticulo(Articulo articulo) {
		List<Articulo> lista = new ArrayList<>();
		lista=getArticulo();
		lista.add(articulo);
		return articulo.getId();
		
	}
	
	

}
