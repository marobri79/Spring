package com.marobri.tienda.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class LibroDTO {

	private Long id;

	@NotBlank
	private String titulo;
	private float precio=0;
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	private String identificadorImagen;
	
	private Set<AutorDTO> autor_id;
	private Set<EditorialDTO> editorial_id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Set<AutorDTO> getAutor_id() {
		return autor_id;
	}
	public void setAutor_id(Set<AutorDTO> autor_id) {
		this.autor_id = autor_id;
	}
	public Set<EditorialDTO> getEditorial_id() {
		return editorial_id;
	}
	public void setEditorial_id(Set<EditorialDTO> editorial_id) {
		this.editorial_id = editorial_id;
	}

}
