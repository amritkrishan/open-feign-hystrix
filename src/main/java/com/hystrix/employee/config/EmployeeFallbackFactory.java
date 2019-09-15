package com.hystrix.employee.config;

import com.hystrix.employee.client.EmployeeClient;
import com.hystrix.employee.hystrix.EmployeeFallback;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;

@Component
public class EmployeeFallbackFactory implements FallbackFactory<EmployeeClient>{

    @Override
    public EmployeeClient create(Throwable cause) {
        return new EmployeeFallback(cause);
    }

}