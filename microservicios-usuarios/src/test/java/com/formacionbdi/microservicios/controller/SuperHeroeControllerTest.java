package com.formacionbdi.microservicios.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.formacionbdi.microservicios.app.heroes.MicroserviciosHeroesApplication;
import com.formacionbdi.microservicios.app.heroes.controllers.SuperHeroeController;
import com.formacionbdi.microservicios.app.heroes.models.services.SuperHeroeService;
import com.formacionbdi.microservicios.commons.superHeroes.models.entity.SuperHeroe;




@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {MicroserviciosHeroesApplication.class, SuperHeroeController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperHeroeControllerTest  {
	
	@MockBean
	protected SuperHeroeService superHeroeService; 
	
	@Autowired
	protected MockMvc mvc; 
	
	protected HttpHeaders httpHeaders; 
	
	@Test
	public void filtrarPorNombre() throws Exception  {
		
		String term = "spider";
		
		ArrayList<SuperHeroe> listTest = new ArrayList<SuperHeroe>();
		
		SuperHeroe superHeroe = new SuperHeroe();
		superHeroe.setNombre("spiderman");
		superHeroe.setCaracteristica("trepar adherirse y desplazarse a través de muros");
		listTest.add(superHeroe);
		
		when(superHeroeService.findByNombre(term)).thenReturn(listTest);
		//x-api-key
		
		mvc.perform(
				get("/filtrar/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed());
		
			
	}

}
