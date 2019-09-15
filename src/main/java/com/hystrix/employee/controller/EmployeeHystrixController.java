package com.hystrix.employee.controller;

import com.hystrix.employee.client.EmployeeClient;
import com.hystrix.employee.config.AppConfig;
import com.hystrix.employee.config.ClientConfiguration;
import com.hystrix.employee.config.EmployeeFallbackFactory;
import com.hystrix.employee.model.Employee;
import feign.Client;
import feign.codec.ErrorDecoder;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/hystrix")
public class EmployeeHystrixController {

    EmployeeClient employeeClient;

    @Autowired
    Client client;

    @Autowired
    AppConfig config;

    @Autowired
    ErrorDecoder errorDecoder;

    @Autowired
    ClientConfiguration clientConfiguration;


    @PostConstruct
    public void init() {
        System.out.println("Hystrix Init");
        employeeClient = HystrixFeign.builder()
                .contract(new SpringMvcContract())
                .client(client)
                .decoder(new JacksonDecoder())
                .errorDecoder(errorDecoder)
                .requestInterceptor(clientConfiguration.requestInterceptor())
                .target(EmployeeClient.class, config.getEmployeeUrl(), new EmployeeFallbackFactory());
    }


    @GetMapping(value = "/getemployees", consumes = "application/json", produces = "application/json")
    public List<Employee> getEmployees() {
        System.out.println("Hystrix Get Employees");
        return employeeClient.getEmployees();
    }

    @GetMapping(value = "/getemployee/{employeeId}", consumes = "application/json", produces = "application/json")
    public Employee getEmployeeById (@PathVariable("employeeId") Long employeeId){
        System.out.println("Hystrix Get Employee By Id");
        return employeeClient.getEmployeeById(employeeId);
    }

}
