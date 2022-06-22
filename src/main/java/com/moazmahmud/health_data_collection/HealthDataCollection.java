package com.moazmahmud.health_data_collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HealthDataCollection {
    public static void main(String[] args) {
        SpringApplication.run(HealthDataCollection.class, args);
    }
}
