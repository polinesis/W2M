package com.formacionbdi.microservicios.app.heroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@EntityScan({"com.formacionbdi.microservicios.commons.superHeroes.models.entity"})
public class MicroserviciosHeroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosHeroesApplication.class, args);
	}
	
	


}
