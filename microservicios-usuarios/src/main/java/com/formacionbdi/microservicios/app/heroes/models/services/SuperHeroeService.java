package com.formacionbdi.microservicios.app.heroes.models.services;

import java.util.List;

import com.formacionbdi.microservicios.commons.services.CommonService;
import com.formacionbdi.microservicios.commons.superHeroes.models.entity.SuperHeroe;


public interface SuperHeroeService extends CommonService<SuperHeroe> {

	public List<SuperHeroe> findByNombre(String term);
	
	public Iterable<SuperHeroe> findAllById(Iterable<Long> ids);
	
	
}
