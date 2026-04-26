package com.ex.jpa_demo.jpa_demo.service;

import com.ex.jpa_demo.jpa_demo.entity.Employee;
import com.ex.jpa_demo.jpa_demo.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl {

    private final EmpRepository empRepo;

    public Employee addEmployee(Employee employee){
        Employee saved = empRepo.save(employee);
        System.out.println("Employee Added");
        return saved;
    }

    public Employee getById(Long id){
        System.out.println("Getting Employee by Id");
        return empRepo.findById(id)
                .orElse(new Employee(0L,"---",0.0));
    }

    public Page<Employee> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        System.out.println("Employee Getting");
        return empRepo.findAll(pageable);
    }

    public Slice<Employee> getEmpBySal(double salary, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("salary").ascending());
        return empRepo.findBySalary(salary, pageable);
    }
}
