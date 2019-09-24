package com.company.webservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.webservice.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

}
