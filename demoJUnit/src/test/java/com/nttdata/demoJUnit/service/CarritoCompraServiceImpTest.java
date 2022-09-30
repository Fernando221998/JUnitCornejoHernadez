package com.nttdata.demoJUnit.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.CustomMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nttdata.demoJUnit.bbdd.BaseDatosI;
import com.nttdata.demoJUnit.model.Articulo;

@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImpTest {

	@InjectMocks
	CarritoCompraServiceImp carrito = new CarritoCompraServiceImp();
	
	@Mock
	private BaseDatosI baseDatos;
	
	@Test
	public void testLimpiarCesta() {
		/*Articulo articulo = new Articulo("Pantalon", 200.00);
		carro.addAticulo(articulo);
		List<Articulo> list=carro.getArticulos();*/
		carrito.limpiarCesta();
		assertTrue(carrito.getArticulos().isEmpty());
	}

	@Test
	public void testAddAticulo() {
		assertTrue(carrito.getArticulos().isEmpty());
		carrito.addAticulo(new Articulo("Pantalon", 200.00));
		assertFalse(carrito.getArticulos().isEmpty());
	}

	@Test
	public void testGetNumArticulo() {
		carrito.addAticulo(new Articulo("Pantalon", 200.00));
		carrito.addAticulo(new Articulo("Camisa", 300.00));
		Integer resultado = carrito.getNumArticulo();
		assertEquals(2, resultado);
	}

	@Test
	public void testGetArticulos() {
		carrito.addAticulo(new Articulo("Pantalon", 200.00));
		carrito.addAticulo(new Articulo("Camisa", 300.00));
		List<Articulo> list=carrito.getArticulos();
		
		
		/*org.hamcrest.Matcher<List<Articulo>> listMacher = new org.hamcrest.CustomMatcher<List<Articulo>>("listMacher") {
			
			@Override
			public boolean matches(Object argumento) {
				if(!(argumento == null || List.class.isInstance(argumento))) {
					return false;
				}
				boolean equals = true;
				
				List<Articulo> lista = (List<Articulo>) argumento;
				
				equals &= lista.get(0).getNombre().equals("Pantalon");
				equals &= lista.get(0).getPrecio().equals(200.00);
				equals &= lista.get(0).getNombre().equals("Camisa");				
				equals &= lista.get(0).getPrecio().equals(300.00);
				
				
				return equals;
			}
		};
		
		assertThat(list,listMacher);*/
		assertEquals(2, list.size());
		assertEquals("Camisa", list.get(1).getNombre());
	}
	
	@Test
	public void testGetArticulosBBDD() {
		List<Articulo> lista = new ArrayList<>();
		lista.add(new Articulo("Pantalon", 200.00));
		lista.add(new Articulo("Camisa", 300.00));
		lista.add(new Articulo("Jersey", 300.00));
		lista.add(new Articulo("Vestido", 300.00));
		when(baseDatos.getArticulo()).thenReturn(lista);
		List<Articulo> list=carrito.getArticulosBBDD();
		assertEquals(4, list.size());
	}

	@Test
	public void testTotalPrice() {
		carrito.addAticulo(new Articulo("Pantalon", 200.00));
		carrito.addAticulo(new Articulo("Camisa", 300.00));
		Double resultado = carrito.totalPrice();
		assertEquals(500.00, resultado);
	}

	@Test
	public void testCalculadorDescuento() {
		assertEquals(300.00, carrito.calculadorDescuento(300, 100));
	}
	
	@Test
	public void testAplicarDescuentoBBDD() {
		
		Articulo articulo = new Articulo("Pantalon", 200.00);
		when(baseDatos.buscarPorId(1)).thenReturn(articulo);
		Double resultado = carrito.aplicarDescuentoBBDD(1, 100);
		assertEquals(200, resultado);
		verify(baseDatos, times(1)).buscarPorId(any(Integer.class));
	}
	
	@Test
	public void testInsertar() {
		
		Articulo articulo = new Articulo(5, "Chandal", 100.00);
		when(baseDatos.addArticulo(articulo)).thenReturn(articulo.getId());
		carrito.insertar(articulo);
		
		// El ID del nuevo artículo coincide con un valor en concreto
		assertEquals(articulo.getId(), 5);
		
		// Comprobar que la cesta tiene el objeto nuevo insertado
		List<Articulo> lista=carrito.getArticulos();
		assertTrue(lista.contains(articulo));
		
		// Comprobar que se ha llamado al método insertar artículo de BaseDatos al menos una vez
		verify(baseDatos, atLeast(1)).addArticulo(any(Articulo.class));
	}

}
