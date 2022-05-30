package com.moazmahmud.spring_boot_thymeleaf.config;

import com.moazmahmud.spring_boot_thymeleaf.utils.LoggedInUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
@RequiredArgsConstructor
public class AuditConfig {

    private final Clock clock;

    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorProvider() {
        return LoggedInUserUtil::getUsername;
    }

    @Bean(name = "dateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now(clock));
    }
}
