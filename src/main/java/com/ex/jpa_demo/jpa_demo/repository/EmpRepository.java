package com.ex.jpa_demo.jpa_demo.repository;

import com.ex.jpa_demo.jpa_demo.entity.Employee;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.salary > :salary")
    @QueryHints({@QueryHint(name = "org.hibernate.readOnly",value = "true"),
            @QueryHint(name = "org.hibernate.fetchSize",value = "5"),  //Hibernate will fetch them from the DB in batches of 5 rows at a time (as a performance optimization, not filtering).
            @QueryHint(name = "org.hibernate.cacheable",value = "true"), //works only if 2nd-level cache configured
            @QueryHint(name = "jakarta.persistence.cache.retrieveMode",value = "USE"),
            @QueryHint(name = "jakarta.persistence.cache.storeMode",value = "USE"),
            @QueryHint(name = "jakarta.persistence.query.timeOut",value = "500") //Set query time limit	in ms & Prevent slow queries
    })
    Slice<Employee> findBySalary(@Param("salary") double salary, Pageable pageable);
}

/*
Page vs Slice
Page: response includes content+metadata
Slice: response includes on content & doesn't contain information like totalPages,totalElements
 */
