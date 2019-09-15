package com.hystrix.employee.hystrix;

import com.hystrix.employee.client.EmployeeClient;
import com.hystrix.employee.exception.EmployeeCustomException;
import com.hystrix.employee.model.Employee;
import feign.FeignException;
import java.util.List;

public class EmployeeFallback implements EmployeeClient {

    private final Throwable cause;

    public EmployeeFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<Employee> getEmployees() {
        System.out.println("Get Employees Fallback");
        if(cause instanceof FeignException) {
            FeignException feignException = (FeignException) cause;
            parseEmployeeExceptionResponse(feignException, "/getemployees");
        }
        throw new EmployeeCustomException(500, "Internal Server Exception" , "/getemployees");
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        System.out.println("Get Employee By Id Fallback");
        if(cause instanceof FeignException) {
            FeignException feignException = (FeignException) cause;
            parseEmployeeExceptionResponse(feignException, "/getemployees");
        }
        throw new EmployeeCustomException(500, "Internal Server Exception" , "/getemployees");
    }

    private void  parseEmployeeExceptionResponse(FeignException exp, String path){
        if(exp.status() == 0) {
            throw new EmployeeCustomException(500, exp.getMessage(), path);
        }
        else{
            throw new EmployeeCustomException(exp.status(), exp.contentUTF8(), path);
        }
    }
}