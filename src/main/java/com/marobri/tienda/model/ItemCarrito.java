package com.marobri.tienda.model;

public class ItemCarrito {

	private Long idLibro;
	private Integer cantidad;
	private Float precio;
	
	public ItemCarrito() {
		
	}
	
	public ItemCarrito(Long idLibro, Integer cantidad, Float precio) {
		super();
		this.idLibro = idLibro;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Long getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
}