package com.moazmahmud.health_data_collection.health_data.model;

import com.moazmahmud.health_data_collection.common.enums.Gender;
import com.moazmahmud.health_data_collection.common.enums.Religion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HealthDataAddRequest {
    private Long id;
    private String locationName;
    private LocalDate eventDate;
    private Double age;
    private Gender gender;
    private Religion religion;
    private Double heightInInch;
    private Double weightInKg;
    private Double bloodPressureSystolicInMmHg;
    private Double bloodPressureDiastolicInMmHg;
    private Double bloodSugarInMilliMolePerL;
    private PhysicalActivity physicalActivity;
    private CarbIntakeFrequency carbIntakeFrequency;
    private CerealQuality cerealQuality;

    public static HealthDataAddRequest getEmptyInstance() {
        var healthDataAddRequest = new HealthDataAddRequest();
        healthDataAddRequest.gender = Gender.MALE;
        healthDataAddRequest.religion = Religion.ISLAM;
        healthDataAddRequest.physicalActivity = PhysicalActivity.LIGHT_ACTIVITY;
        healthDataAddRequest.carbIntakeFrequency = CarbIntakeFrequency.ONE_TWO_TIMES;
        healthDataAddRequest.cerealQuality = CerealQuality.SIMPLE;
        return healthDataAddRequest;
    }
}
