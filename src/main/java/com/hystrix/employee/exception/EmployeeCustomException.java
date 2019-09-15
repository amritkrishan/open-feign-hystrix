package com.hystrix.employee.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class EmployeeCustomException extends RuntimeException{

    @JsonProperty
    private final Integer status;
    @JsonProperty
    private final String message;
    @JsonProperty
    private final String path;

    public EmployeeCustomException(Integer status, String message, String path) {
        super(message, null, true, false);
        this.status = status;
        this.message = message;
        this.path = path;
    }

    @JsonProperty
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @JsonProperty
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Exception: "+getMessage();
    }
}