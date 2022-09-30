package com.nttdata.demoJUnit.model;

public class Articulo {
	
	private Integer id;
	private String nombre;
	private Double precio;
	


	

	public Articulo(Integer id, String nombre, Double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}



	public Articulo(String nombre, Double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	
}


