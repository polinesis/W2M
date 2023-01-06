package com.formacionbdi.microservicios.app.heroes.controllers;

import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.heroes.model.ResponseApiDTO;
import com.formacionbdi.microservicios.app.heroes.models.services.SuperHeroeService;
import com.formacionbdi.microservicios.app.heroes.utils.Utils;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.superHeroes.models.entity.SuperHeroe;

import io.swagger.annotations.ApiOperation;

@RestController
public class SuperHeroeController extends CommonController<SuperHeroe, SuperHeroeService>{

	@ApiOperation(value = " edita un heroe por id, se puede modificar el nombre y la caracteristica", response = ResponseEntity.class)
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editar(@RequestHeader(name = "x-api-key") String apiKey, @Valid @RequestBody SuperHeroe superHeroe, BindingResult result, @PathVariable Long id) {
	
		
		long startTime = System.nanoTime();

		 if (validaToken(apiKey)) {
			   
		
				if(result.hasErrors()) {
					return this.validar(result);
				}
				
				Optional<SuperHeroe> o = service.findById(id);
				if (!o.isPresent()) {
					return ResponseEntity.notFound().build();
				}	
				
				var superHeroedb = o.get();
				
				superHeroedb.setNombre(superHeroe.getNombre());
				superHeroedb.setCaracteristica(superHeroe.getCaracteristica());
				
				long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
				
				ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
				SuperHeroe nuevoHeroe = service.save(superHeroedb);
				ArrayList<SuperHeroe>  listaHeroe = new ArrayList<>();
				listaHeroe.add(nuevoHeroe);
				responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
				responseApiDTO.setData(listaHeroe);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(responseApiDTO);
		  } else {
				
			  ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			   long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
				responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
				responseApiDTO.setMensaje("Credenciales no válidas, tiempo de respuesta");
				
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseApiDTO);
					
			  
		    }

	}
	
	@ApiOperation(value = " consulta de heroe por nombre", response = ResponseEntity.class)
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@RequestHeader(name = "x-api-key") String apiKey, @PathVariable String term) {
		
		long startTime = System.nanoTime();

		if (validaToken(apiKey)) {
				
			ArrayList<SuperHeroe> list = (ArrayList<SuperHeroe>) service.findByNombre(term);
			ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
			
			   
			responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
			responseApiDTO.setData(list);
		
		return ResponseEntity.ok(responseApiDTO);
		 } else {
			  ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
				
			    long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
				responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
				responseApiDTO.setMensaje("Credenciales no válidas, tiempo de respuesta");
				
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseApiDTO);
					
			
		  
	    }
	}

	
	private boolean validaToken(String token) {
	    
		String dec = Utils.decrypt(token);
		
		if(dec == null) {return false;}
				
	    String[] parts = dec.split("\\.");
	    if (parts.length >= 2) {
	      return (parts[1].equals("W2M!") && (parts[0].matches("\\d+")));
	    } else {
	      return false;
	    }
	  }


}
