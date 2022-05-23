package com.moazmahmud.spring_boot_thymeleaf.health_data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class BloodPressure {
    private Double systolicInMmHg;
    private Double diastolicInMmHg;
}
