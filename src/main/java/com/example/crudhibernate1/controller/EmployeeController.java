package com.example.crudhibernate1.controller;
import com.example.crudhibernate1.entity.EmployeeEntity;
import com.example.crudhibernate1.model.Employee;
import com.example.crudhibernate1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = employeeService.getAllEmployees();
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<EmployeeEntity>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employee) {
        EmployeeEntity isAddedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(isAddedEmployee, new HttpHeaders(), HttpStatus.OK);
    }

    // Get employees by multiple condition
    @GetMapping("/custom-condition")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByConditions() {
        List<EmployeeEntity> list = employeeService.getEmployeesByConditions();
        return  new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/custom-model")
    public ResponseEntity<List<Employee>> getEmployeesByModel() {
        List<Employee> list = employeeService.getEmployeesByModel();
        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<EmployeeEntity>(null, new HttpHeaders(), HttpStatus.OK);
    }
}