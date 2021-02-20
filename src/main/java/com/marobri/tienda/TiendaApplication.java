package com.marobri.tienda;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.marobri.tienda.util.CargadorMaestrosBD;

@SpringBootApplication
@EnableJpaRepositories
public class TiendaApplication {
	
	@Autowired
	private CargadorMaestrosBD cargadorMaestrosBD;
	
	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}
	
	@Bean
	InitializingBean precargarBaseDeDatos() {
		return () -> {
			cargadorMaestrosBD.precargar();
		};
	}
	
   @Bean
   public ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();
      return modelMapper;
   }
	
}
