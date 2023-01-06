package com.formacionbdi.microservicios.commons.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.formacionbdi.microservicios.commons.model.ResponseApiDTO;
import com.formacionbdi.microservicios.commons.services.CommonService;
import com.formacionbdi.microservicios.commons.utils.Utils;

import io.swagger.annotations.ApiOperation;


public class CommonController<E,S extends CommonService<E>> {
	
	@Autowired
	protected S service; 

	@ApiOperation(value = "  consulta de todos los heroes ", response = ResponseEntity.class)
	@GetMapping("/listar")
	public ResponseEntity<?> listar(@RequestHeader(name = "x-api-key") String apiKey){
		long startTime = System.nanoTime();

		if (validaToken(apiKey)) {
			
			long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
			ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
			responseApiDTO.setData((List<?>) service.findAll());
		
			
			 return ResponseEntity.ok().body(responseApiDTO);
		 } else {
			  ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			   long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
				responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
				responseApiDTO.setMensaje("Credenciales no válidas, tiempo de respuesta");
				
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseApiDTO);
					
		  
	    }
	}

	
	@ApiOperation(value = "  crear un heroe, pasando nombre y caracteristica ", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<?> crear(@RequestHeader(name = "x-api-key") String apiKey, @Valid @RequestBody E entity, BindingResult result) {
		long startTime = System.nanoTime();

		 if (validaToken(apiKey)) {
				
			if(result.hasErrors()) {
				return this.validar(result);
			}
					
			E entitydb = service.save(entity);
			
			long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
			ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			ArrayList<E>  listaHeroe = new ArrayList<>();
			listaHeroe.add(entitydb);
		
			responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
		
			return ResponseEntity.status(HttpStatus.CREATED).body(responseApiDTO);
			
		 } else {
			  ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			   long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
				responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
				responseApiDTO.setMensaje("Credenciales no válidas, tiempo de respuesta");
				
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseApiDTO);
				
		  
	    }
	}
	
	@ApiOperation(value = "  elimina un heroe por id ", response = ResponseEntity.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@RequestHeader(name = "x-api-key") String apiKey, @PathVariable Long id) {
		long startTime = System.nanoTime();

		 if (validaToken(apiKey)) {
					
		service.deleteById(id);
		long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
		ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
		responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseApiDTO);
		
		 
		 } else {
			  ResponseApiDTO  responseApiDTO = new ResponseApiDTO(); 
			   long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
				responseApiDTO.setTiempoRespuesta("Tiempo Respuesta: " + endTime/ 1000000);
				responseApiDTO.setMensaje("Credenciales no válidas, tiempo de respuesta");
				
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseApiDTO);
					
		  
	    }
	
	}


	
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		
				result.getFieldErrors().forEach(err ->{
					
					errores.put(err.getField(), " El campo : " +  err.getField() + " " + err.getDefaultMessage());
				}
						
		);
		
				return ResponseEntity.badRequest().body(errores);
	
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
