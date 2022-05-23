package com.moazmahmud.spring_boot_thymeleaf.health_data.entity;

import com.moazmahmud.spring_boot_thymeleaf.common.enums.Gender;
import com.moazmahmud.spring_boot_thymeleaf.common.enums.Religion;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.BloodPressure;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.PhysicalActivity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;


@Getter
@Setter
@Entity
@Table(name = "health_data")
public class HealthData {
    @Id
    @SequenceGenerator(name = "seq_health_data", sequenceName = "seq_health_data")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_health_data")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "age")
    private Double age;

    @Column(name = "gender")
    @Enumerated(STRING)
    private Gender gender;

    @Column(name = "religion")
    @Enumerated(STRING)
    private Religion religion;

    @Column(name = "height_in_inch")
    private Double heightInInch;

    @Column(name = "weight_in_kg")
    private Double weightInKg;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "systolicInMmHg", column = @Column(name = "blood_pressure_systolicInMmHg")),
            @AttributeOverride(name = "diastolicInMmHg", column = @Column(name = "blood_pressure_diastolicInMmHg"))
    })
    private BloodPressure bloodPressure;

    @Column(name = "blood_sugar_in_milli_mole_per_l")
    private Double bloodSugarInMilliMolePerL;

    @Column(name = "physical_activity")
    @Enumerated(STRING)
    private PhysicalActivity physicalActivity;
}
