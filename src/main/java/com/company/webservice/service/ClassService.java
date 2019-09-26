package com.company.webservice.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.webservice.model.Class;

import com.company.webservice.repository.ClassRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ClassService {

	private final ClassRepository repository;
	
	public Iterable<Class> search(Pageable pageable){
		log.info("Searching classes");
		return this.repository.findAll(pageable);
	}

	public Class create(Class classs) {
		log.info("Creating class");
		return this.repository.save(classs);
	}

	public Class update(Long id, Class classs) {
		Class classsSaved = findAlreadySave(id);
		BeanUtils.copyProperties(classs, classsSaved, "id");
		return repository.save(classsSaved);
	}
	
	public Class findAlreadySave(Long id) {
		Optional<Class> classstOp = repository.findById(id);
		if(!classstOp.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return classstOp.get();
	}
	
}
