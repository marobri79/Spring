package com.marobri.tienda.dto;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.marobri.tienda.model.entities.Usuario;

public class FuncionalidadDTO {


	private Long id;
	
	@NotBlank
	private String nombre;

	
}
