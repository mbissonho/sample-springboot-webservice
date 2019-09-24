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

import com.company.webservice.model.Student;
import com.company.webservice.service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentResource {
	
	private final StudentService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Student>> search(Pageable pageable){
		return new ResponseEntity<Iterable<Student>>(service.search(pageable), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Student> create(@RequestBody @Valid Student student){
		Student studentCreated = service.create(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(studentCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody @Valid Student student){
		return ResponseEntity.ok(service.update(id, student));
	}

}
