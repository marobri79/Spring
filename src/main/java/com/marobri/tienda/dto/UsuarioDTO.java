package com.marobri.tienda.dto;

import java.util.Set;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

	private Long id;

	@NotBlank
	private String nombre;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String clave;
	
	@NotBlank
	private String direccion;

	@NotBlank
	private String telefono;

    private Set<PedidoDTO> pedidos;
	private Set<FuncionalidadDTO> funcionalidades;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
	public String getClave() {
		return clave;
	}
	
	@JsonProperty
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Set<PedidoDTO> getPedidos() {
		return pedidos;
	}
	
	public void setPedidos(Set<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Set<FuncionalidadDTO> getFuncionalidades() {
		return funcionalidades;
	}
	
	public void setFuncionalidades(Set<FuncionalidadDTO> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	
}
