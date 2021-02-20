package com.marobri.tienda.model;

import java.util.ArrayList;
import java.util.List;


public class Carrito {
	
	private List<ItemCarrito> lineasCarrito;

	public Carrito() {
		super();
		this.lineasCarrito = new ArrayList<ItemCarrito>();
	}

	public List<ItemCarrito> getLineasCarrito() {
		return lineasCarrito;
	}

	public void setLineasCarrito(List<ItemCarrito> lineasCarrito) {
		this.lineasCarrito = lineasCarrito;
	}
	
	
	
}