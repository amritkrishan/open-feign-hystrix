package com.hystrix.employee.controller;

import com.hystrix.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping(value = "/getemployees", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Employee>> getEmployees() {
        System.out.println("Employee Controller Get Employees");
        List<Employee> employeeList = Arrays.asList(new Employee(1L, "Amrit", 10000L), new Employee(2L, "Som", 20000L));
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @GetMapping(value = "/getemployee/{employeeId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("employeeId") Long employeeId){
        System.out.println("Employee Controller Get Employee By Id");
        List<Employee> employeeList = Arrays.asList(new Employee(1L, "Amrit", 10000L), new Employee(2L, "Som", 20000L));
        List<Employee> employeesById = employeeList.stream().filter(el -> el.getId().equals(employeeId)).collect(Collectors.toList());
        return new ResponseEntity<Employee>(employeesById.get(0), HttpStatus.OK);
    }
}
