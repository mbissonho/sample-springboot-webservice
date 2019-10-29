package com.company.webservice;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.webservice.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
	
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getStudent_returnStudentNameAndLastName() {
		
		ResponseEntity<Student> response = restTemplate.getForEntity("/api/v1/student/{name}",  Student.class, "Lucas");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("Lucas");
		assertThat(response.getBody().getLastName()).isEqualTo("Ferreira");
	}
	
}
