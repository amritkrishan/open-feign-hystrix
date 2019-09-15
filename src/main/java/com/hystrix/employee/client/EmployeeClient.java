package com.hystrix.employee.client;

import com.hystrix.employee.model.Employee;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(name = "employee")
@Headers("Content-Type: application/json")
public interface EmployeeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/employee/getemployees", consumes = "application/json", produces = "application/json")
    List<Employee> getEmployees();


    @RequestMapping(method = RequestMethod.GET, value = "/employee/getemployee/{employeeId}", consumes = "application/json", produces = "application/json")
    Employee getEmployeeById(@PathVariable("employeeId") Long employeeId);
}