package com.marobri.tienda.dto;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class PedidoDTO {
	private Long id;

	@NotBlank
	private String enviado;
	
	@NotBlank
	private Date fecha;
	
	@NotBlank
	private float precio_total;
	
	private Set<UsuarioDTO> usuario_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnviado() {
		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(float precio_total) {
		this.precio_total = precio_total;
	}

	public Set<UsuarioDTO> getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Set<UsuarioDTO> usuario_id) {
		this.usuario_id = usuario_id;
	}
	
}
