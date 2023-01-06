package com.formacionbdi.microservicios.app.heroes.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.heroes.model.LoginDTO;
import com.formacionbdi.microservicios.app.heroes.model.ResponseDTO;
import com.formacionbdi.microservicios.app.heroes.utils.Utils;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
public class TokenizerController {
	 
	  @PostMapping(path = "/autenticar")
	  @ApiOperation(value = "Crea token a partir de crenciales", response = ResponseEntity.class)
	  public ResponseEntity<ResponseDTO> getToken(@RequestBody LoginDTO req) {
	   
		  ResponseDTO res = new ResponseDTO();
	    ResponseEntity<ResponseDTO> response = null;

	    if (req.getUsername().equals("W2M") && req.getPassword().equals("W2M124")) {
	      String token = new Date().getTime() + ".W2M!";
	         

	      res.setToken("Barer : " + Utils.encrypt(token));
	      response = new ResponseEntity<>(res, HttpStatus.OK);
	    } else {
	    	 res.setToken("Credenciales no válidas");
	       response = new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	    }
	    return response;
	  }

}
