package com.marobri.tienda.util;

import java.util.ArrayList;
import java.util.List;

public class Constantes {
	
	public static final String AUTOR_LECTURA = "autor_lectura";
	public static final String AUTOR_CREAR = "autor_crear";
	public static final String AUTOR_EDITAR = "autor_editar";
	public static final String AUTOR_BORRAR = "autor_borrar";
	
	public static final String EDITORIAL_LECTURA = "editorial_lectura";
	public static final String EDITORIAL_CREAR = "editorial_crear";
	public static final String EDITORIAL_EDITAR = "editorial_editar";
	public static final String EDITORIAL_BORRAR = "editorial_borrar";
	
	public static final String LIBRO_LECTURA = "libro_lectura";
	public static final String LIBRO_CREAR = "libro_crear";
	public static final String LIBRO_EDITAR = "libro_editar";
	public static final String LIBRO_BORRAR = "libro_borrar";
	
	public static final String PEDIDO_LECTURA = "pedido_lectura";
	public static final String PEDIDO_CREAR = "pedido_crear";
	public static final String PEDIDO_EDITAR = "pedido_editar";
	public static final String PEDIDO_BORRAR = "pedido_borrar";
	
	public static final String USUARIO_LECTURA = "usuario_lectura";
	public static final String USUARIO_CREAR = "usuario_crear";
	public static final String USUARIO_EDITAR = "usuario_editar";
	public static final String USUARIO_BORRAR = "usuario_borrar";
	
	public static final List <String> FUNCIONALIDADES_PARA_ADMIN = new ArrayList<String>() {
		{
			add(AUTOR_LECTURA);
			add(AUTOR_CREAR);
			add(AUTOR_EDITAR);
			add(AUTOR_BORRAR);
			
			add(EDITORIAL_LECTURA);
			add(EDITORIAL_CREAR);
			add(EDITORIAL_EDITAR);
			add(EDITORIAL_BORRAR);
			
			add(LIBRO_LECTURA);
			add(LIBRO_CREAR);
			add(LIBRO_EDITAR);
			add(LIBRO_BORRAR);
			
			add(PEDIDO_LECTURA);
			add(PEDIDO_CREAR);
			add(PEDIDO_EDITAR);
			add(PEDIDO_BORRAR);
			
			add(USUARIO_LECTURA);
			add(USUARIO_CREAR);
			add(USUARIO_EDITAR);
			add(USUARIO_BORRAR);
		}
	};
	
	public static final List <String> FUNCIONALIDADES_PARA_CLIENTE = new ArrayList<String>() {
		{
			add(AUTOR_LECTURA);
			add(EDITORIAL_LECTURA);
			add(LIBRO_LECTURA);
			add(PEDIDO_CREAR);
		}
	};

}
