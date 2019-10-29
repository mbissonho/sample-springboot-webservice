package com.company.webservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.webservice.model.Student;
import com.company.webservice.repository.StudentRepository;
import com.company.webservice.resource.exception.StudentNotFoundException;
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
	
	@Test
	public void getStudentByName_ShouldReturnStudentByName() {
		given(repository.findByName("Lucas")).willReturn(new Student("Lucas", "Ferreira"));
		Student student = service.getStudentByName("Lucas");
		assertThat(student.getName()).isEqualTo("Lucas");
		assertThat(student.getLastName()).isEqualTo("Ferreira");
	}
	
	@Test(expected = StudentNotFoundException.class)
	public void getStudentByName_ShouldReturnStudentNotFound() {
		given(repository.findByName("Lucas")).willReturn(null);
		service.getStudentByName("Lucas");
	}
	
	
}
