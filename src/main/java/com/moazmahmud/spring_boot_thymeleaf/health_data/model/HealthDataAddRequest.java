package com.moazmahmud.spring_boot_thymeleaf.health_data.model;

import com.moazmahmud.spring_boot_thymeleaf.common.enums.Gender;
import com.moazmahmud.spring_boot_thymeleaf.common.enums.Religion;
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

    public static HealthDataAddRequest getEmptyInstance() {
        var healthDataAddRequest = new HealthDataAddRequest();
        healthDataAddRequest.locationName = "Rakin City, Mirpur-13";
        healthDataAddRequest.eventDate = LocalDate.now();
        return healthDataAddRequest;
    }
}
