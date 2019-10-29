package com.company.webservice;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.webservice.model.Student;
import com.company.webservice.resource.StudentResource;
import com.company.webservice.resource.exception.StudentNotFoundException;
import com.company.webservice.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentResource.class)
public class StudentResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@Test
	public void getStudent_ShouldReturnStudent() throws Exception {
		when(this.studentService.getStudentByName("Lucas")).thenReturn(new Student("Lucas", "Ferreira"));
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student/{name}", "Lucas"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("name").value("Lucas"))
		.andExpect(jsonPath("lastName").value("Ferreira"));
		
	}
	
	@Test
	public void getStudent_NotFound() throws Exception {
		
		when(this.studentService.getStudentByName(any())).thenThrow(new StudentNotFoundException());
		this.mockMvc.perform(get("/api/v1/student/{name}", "Lucas"))
		.andExpect(status().isNotFound());
		
	}
	
}
