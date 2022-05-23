package com.moazmahmud.spring_boot_thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfig {
    @Bean
    public Clock getClock() {
        return Clock.systemUTC();
    }
}
