package com.hystrix.employee.config;

import com.hystrix.employee.exception.EmployeeCustomException;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Configuration
public class ClientConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            System.out.println("Inside Error Decoder");
            int status = response.status();
            String body = null;
            try {
                BufferedReader br = new BufferedReader( new InputStreamReader(response.body().asInputStream()));
                body = br.lines().collect(Collectors.joining("\n"));
            }
            catch(Exception ignored) {
                body ="Bad Request";
            }
            return new EmployeeCustomException(status, body, response.request().url());
        };
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Accept", "application/json");
        };
    }
}
