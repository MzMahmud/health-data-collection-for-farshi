package com.moazmahmud.spring_boot_thymeleaf.health_data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthDataSearchResponse {
    private Long id;
    private String locationName;
    private String eventDate;
    private Double age;
    private String gender;
    private Double heightInInch;
    private Double weightInKg;
}
