package com.moazmahmud.spring_boot_thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HealthDataCollectionForFarshiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthDataCollectionForFarshiApplication.class, args);
    }
}
