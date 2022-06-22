package com.moazmahmud.health_data_collection.health_data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class BloodPressure {
    private Double systolicInMmHg;
    private Double diastolicInMmHg;
    @Enumerated(STRING)
    private HypertensionStatus hypertensionStatus;
}
