package com.marobri.tienda.exception;

public class ExcepcionPrivilegios extends Exception {
	
	public ExcepcionPrivilegios(String privilegioRequerido) {
		super("No tienes el privilegio requerido: " + privilegioRequerido);
	}

}
