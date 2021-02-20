package com.marobri.tienda.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/imagenes")
public class ImagenesController {
	
	private static final String CARPETA_IMAGENES = "C:\\IMAGENES\\";
	
	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {

		File file = new File(CARPETA_IMAGENES + id);
	    InputStream targetStream = new FileInputStream(file);

        byte[] bytes = StreamUtils.copyToByteArray(targetStream);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
	
    @PostMapping("/subirFichero")
    public Map singleFileUpload(@RequestParam("ficheroImagen") MultipartFile file) {

    	Map<String, String> m = new HashMap<>();
    	
        if (file.isEmpty()) {
            return m;
        }

        try {

            byte[] bytes = file.getBytes();

            String identificador = generarIdentificadorImagen();
            Path path = Paths.get(CARPETA_IMAGENES + identificador);
            Files.write(path, bytes);

            
            m.put("identificador", identificador);
            return m;

        } catch (IOException e) {
            return m;
        }

    }
    
    private String generarIdentificadorImagen() {
    	UUID uuid = UUID.randomUUID();
    	return uuid.toString();	
    }

}
