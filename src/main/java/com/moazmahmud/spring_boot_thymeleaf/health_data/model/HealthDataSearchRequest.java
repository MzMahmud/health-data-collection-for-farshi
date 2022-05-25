package com.moazmahmud.spring_boot_thymeleaf.health_data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class HealthDataSearchRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate eventDate;
}
