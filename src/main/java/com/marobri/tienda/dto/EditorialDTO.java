package com.marobri.tienda.dto;

import javax.validation.constraints.NotBlank;

public class EditorialDTO {
	private Long id;

	@NotBlank
	private String nombre;
	
	@NotBlank
	private String pais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
