package com.marobri.tienda.dto;

import javax.validation.constraints.NotBlank;

public class AutorDTO {
	private Long id;

	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellidos;

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	

}
