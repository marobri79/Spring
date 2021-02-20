package com.marobri.tienda.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedidos_libros")
public class PedidoLibro implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false, updatable = true, insertable = true)
	private Pedido pedido;
	
	@JsonIgnoreProperties(value = {"autor", "editorial"}, allowGetters = true)
	@Id
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	private int cantidad;
	
	private float precio;

	public PedidoLibro() {
		super();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
