package com.formacionbdi.microservicios.app.heroes.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthzController {

  /**
   * Metodo GET Healthz
   * 
   * @return String mensaje ok, status 200
   */
  @GetMapping(path = "/healthz")
  public ResponseEntity<String> healthzCheck() {
    return ResponseEntity.ok("mensaje de respuesta para el servicio microservicio heroes, OK");
  }

  
}
