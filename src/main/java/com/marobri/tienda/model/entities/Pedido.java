package com.marobri.tienda.model.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime fecha;
	private boolean enviado;
	private float precioTotal;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false, foreignKey=@ForeignKey(name = "FK_usuario_pedido_id"))
	private Usuario usuario;
	
	@JsonIgnoreProperties("pedido")
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PedidoLibro> lineas;
	 
	 
	public Pedido() {
	}


	public Pedido(Long id, LocalDateTime fecha, boolean enviado, float precioTotal, Usuario usuario, Set<PedidoLibro> lineas) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.enviado = enviado;
		this.precioTotal = precioTotal;
		this.usuario = usuario;
		this.lineas = lineas;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public boolean isEnviado() {
		return enviado;
	}


	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}


	public float getPrecioTotal() {
		return precioTotal;
	}


	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<PedidoLibro> getLineas() {
		return lineas;
	}


	public void setLineas(Set<PedidoLibro> lineas) {
		this.lineas = lineas;
	}
	   
}
