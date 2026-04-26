package com.ex.jpa_demo.jpa_demo.controller;

import com.ex.jpa_demo.jpa_demo.dto.SliceResponse;
import com.ex.jpa_demo.jpa_demo.entity.Employee;
import com.ex.jpa_demo.jpa_demo.service.EmpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {

    private final EmpServiceImpl service;

    @PostMapping("/")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee saved = service.addEmployee(employee);
        System.out.println("Employee Added");
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable("id") Long id){
        Employee emp = service.getById(id);
        return ResponseEntity.ok(emp);
    }
    @GetMapping("/salary/{salaryAmount}")
    public ResponseEntity<SliceResponse<Employee>> getEmpBySal(
            @PathVariable("salaryAmount") double salaryAmount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
            ){
        Slice<Employee> empList = service.getEmpBySal(salaryAmount,page,size);
        return ResponseEntity.ok(new SliceResponse<>(empList,"/emp",salaryAmount));
    }
    @GetMapping("/")
    public ResponseEntity<Page<Employee>> getAllEmp(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Page<Employee> empList = service.getAll(page,size);
        return ResponseEntity.ok(empList);
    }
}
