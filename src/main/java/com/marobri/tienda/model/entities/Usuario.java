package com.marobri.tienda.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nombre;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false, unique = false)
	private String clave;
	
	@Column(nullable = false, unique = false)
	private String direccion;
	
	@Column(nullable = false, unique = false)
	private String telefono;
	
	@JsonIgnoreProperties("usuario")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Pedido> pedidos;
	
	@JsonIgnoreProperties("usuarios")
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(
		name = "usuarios_funcionalidades",
		joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_usuarios_funcionalidades_usuario_id")),
		inverseJoinColumns = @JoinColumn(name = "funcionalidad_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_usuarios_funcionalidades_funcionalidad_id"))
	)
	private Set<Funcionalidad> funcionalidades;

	public Usuario() {
		super();
	}

	public Usuario(Long idUsuario, String nombreUsuario, String direccionUsuario, String claveUsuario,
			String emailUsuario, String telefonoUsuario, Set<Pedido> pedidos, Set<Funcionalidad> funcionalidades) {
		super();
		this.id = idUsuario;
		this.nombre = nombreUsuario;
		this.direccion = direccionUsuario;
		this.clave = claveUsuario;
		this.email = emailUsuario;
		this.telefono = telefonoUsuario;
		this.pedidos = pedidos;
		this.funcionalidades = funcionalidades;
	}

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// TO:DO Ver si tenemos que excluir algo por bucle ciclico infinito
	public Set<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(Set<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	@JsonIgnore
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
