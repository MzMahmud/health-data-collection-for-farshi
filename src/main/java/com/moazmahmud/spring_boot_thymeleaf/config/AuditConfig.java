package com.moazmahmud.spring_boot_thymeleaf.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@RequiredArgsConstructor
public class AuditConfig {

    private final Clock clock;

    @Bean(name = "dateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now(clock));
    }
}
