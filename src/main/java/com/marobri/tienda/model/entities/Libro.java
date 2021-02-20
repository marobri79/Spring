package com.marobri.tienda.model.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String identificadorImagen;
	
	private String titulo;
	
	private float precio;
	
	private boolean borrado;

	@JsonIgnoreProperties({"libros"})
	@ManyToOne
	@JoinColumn(name="autor_id", nullable=false, foreignKey=@ForeignKey(name = "FK_libro_autor_id"))
	private Autor autor;
	
	@JsonIgnoreProperties({"libros"})
	@ManyToOne
	@JoinColumn(name="editorial_id", nullable=false, foreignKey=@ForeignKey(name = "FK_libro_editorial_id"))
	private Editorial editorial;
	
	public Libro() {
		super();
	}

	public Libro(Long id, String titulo, Autor autor, Editorial editorial) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = 0;
		this.borrado = false;
	}
	
	public Libro(Long id, String titulo,float precio, String identificadorImagen, Autor autor, Editorial editorial) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio=precio;
		this.identificadorImagen = identificadorImagen;
		this.autor = autor;
		this.editorial = editorial;
		this.borrado = false;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

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

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public String getIdentificadorImagen() {
		return identificadorImagen;
	}

	public void setIdentificadorImagen(String identIficadorImagen) {
		this.identificadorImagen = identIficadorImagen;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	   
}
