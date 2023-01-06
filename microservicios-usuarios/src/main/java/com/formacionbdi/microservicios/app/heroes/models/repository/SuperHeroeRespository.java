package com.formacionbdi.microservicios.app.heroes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.formacionbdi.microservicios.commons.superHeroes.models.entity.SuperHeroe;

public interface SuperHeroeRespository extends PagingAndSortingRepository<SuperHeroe, Long> {

	@Query("select a from SuperHeroe a where upper(a.nombre) like upper(concat('%',?1,'%')) ")
	public List<SuperHeroe> findByNombre(String term);
	
}
