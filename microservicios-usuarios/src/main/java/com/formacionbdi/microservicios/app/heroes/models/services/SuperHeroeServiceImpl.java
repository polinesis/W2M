package com.formacionbdi.microservicios.app.heroes.models.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.heroes.models.repository.SuperHeroeRespository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;
import com.formacionbdi.microservicios.commons.superHeroes.models.entity.SuperHeroe;

@Service
public class SuperHeroeServiceImpl extends CommonServiceImpl<SuperHeroe, SuperHeroeRespository> implements SuperHeroeService {

	
	
	@Override
	@Transactional(readOnly = true) 
	public Iterable<SuperHeroe> findAllById(Iterable<Long> ids) {
		return respository.findAllById(ids);
	}

	@Override
	@Transactional(readOnly = true) 
	public List<SuperHeroe> findByNombre(String term) {
		return respository.findByNombre(term);
	}


	
	
	

}
