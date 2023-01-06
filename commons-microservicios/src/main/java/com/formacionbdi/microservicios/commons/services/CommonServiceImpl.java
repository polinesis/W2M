package com.formacionbdi.microservicios.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {

	
	@Autowired
	protected R respository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {;
		return respository.findAll();
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
		return respository.findById(id);
		
	}

	@Override
	@Transactional
	public E save(E entity) {
		return respository.save(entity);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		respository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return respository.findAll(pageable);
	}

}
