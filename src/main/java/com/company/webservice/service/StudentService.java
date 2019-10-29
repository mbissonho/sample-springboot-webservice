package com.company.webservice.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.webservice.model.Student;
import com.company.webservice.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StudentService {
	
	private final StudentRepository repository;
	
	public Iterable<Student> search(Pageable pageable){
		log.info("Searching students");
		return this.repository.findAll(pageable);
	}

	public Student create(Student student) {
		log.info("Creating student");
		return this.repository.save(student);
	}

	public Student update(Long id, Student student) {
		Student studentSaved = findAlreadySave(id);
		BeanUtils.copyProperties(student, studentSaved, "id");
		return repository.save(studentSaved);
	}
	
	public Student findAlreadySave(Long id) {
		Optional<Student> studentOp = repository.findById(id);
		if(!studentOp.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return studentOp.get();
	}

	public Student getStudentByName(String name) {
		return null;
	}
	
}
