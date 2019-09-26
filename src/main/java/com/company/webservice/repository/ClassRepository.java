package com.company.webservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.company.webservice.model.Class;

public interface ClassRepository extends PagingAndSortingRepository<Class, Long> {

}
