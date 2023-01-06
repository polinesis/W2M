package com.formacionbdi.microservicios.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formacionbdi.microservicios.app.heroes.MicroserviciosHeroesApplication;
import com.formacionbdi.microservicios.app.heroes.models.services.SuperHeroeService;
import com.formacionbdi.microservicios.commons.superHeroes.models.entity.SuperHeroe;

@SpringBootTest(classes = MicroserviciosHeroesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SuperHeroeServiceTest  {
	@Autowired
	protected SuperHeroeService superHeroeService; 
	
	@Test
	public void filtrarPorNombre()  {
		
		String term = "spider";
		
		ArrayList<SuperHeroe> listTest = new ArrayList<SuperHeroe>();
		
		SuperHeroe superHeroe = new SuperHeroe();
		superHeroe.setNombre("spiderman");
		superHeroe.setCaracteristica("trepar adherirse y desplazarse a través de muros");
		listTest.add(superHeroe);
		
		ArrayList<SuperHeroe> list = (ArrayList<SuperHeroe>) superHeroeService.findByNombre(term);
		
		assertEquals(listTest.get(0).getNombre(), list.get(0).getNombre());
		
		
	}

}
