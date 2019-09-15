package com.hystrix.employee.model;

public class Employee {

    private Long id;
    private String title;
    private Long salary;

    public Employee() {
    }

    public Employee(Long id, String title, Long salary) {
        this.id = id;
        this.title = title;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}