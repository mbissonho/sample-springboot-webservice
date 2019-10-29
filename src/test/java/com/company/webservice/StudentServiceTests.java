package com.company.webservice;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.webservice.repository.StudentRepository;
import com.company.webservice.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTests {

	@Mock
	private StudentRepository repository;
	
	private StudentService service;
	
	@Before
	public void setUp() {
		service = new StudentService(repository);
	}
	
	
	
}
