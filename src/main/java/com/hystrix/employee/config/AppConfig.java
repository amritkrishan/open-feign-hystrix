package com.hystrix.employee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${employee.url}")
    private String employeeUrl;

    public AppConfig() {
    }

    public AppConfig(String employeeUrl) {
        this.employeeUrl = employeeUrl;
    }

    public String getEmployeeUrl() {
        System.out.println("Get Employee Url : "+employeeUrl);
        return employeeUrl;
    }

    public void setEmployeeUrl(String employeeUrl) {
        this.employeeUrl = employeeUrl;
    }

}
