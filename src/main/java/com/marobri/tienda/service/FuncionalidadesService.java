package com.marobri.tienda.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marobri.tienda.model.entities.Funcionalidad;
import com.marobri.tienda.repository.FuncionalidadesRepository;

@Service
public class FuncionalidadesService {

	@Autowired
	FuncionalidadesRepository funcionalidadesRepository;
	
	public Optional<Funcionalidad> buscarFuncionalidadPorNombre(String nombreFuncionalidad) {
		return funcionalidadesRepository.findByName(nombreFuncionalidad);
	}
	
}
