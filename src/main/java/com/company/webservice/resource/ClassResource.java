package com.company.webservice.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.webservice.model.Class;
import com.company.webservice.service.ClassService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/class")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClassResource {

	private final ClassService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Class>> search(Pageable pageable){
		return new ResponseEntity<Iterable<Class>>(service.search(pageable), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Class> create(@RequestBody @Valid Class classs){
		Class classsCreated = service.create(classs);
		return ResponseEntity.status(HttpStatus.CREATED).body(classsCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Class> update(@PathVariable Long id, @RequestBody @Valid Class classs){
		return ResponseEntity.ok(service.update(id, classs));
	}
	
}
