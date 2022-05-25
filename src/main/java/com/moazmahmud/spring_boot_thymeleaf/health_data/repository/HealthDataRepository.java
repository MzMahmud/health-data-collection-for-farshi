package com.moazmahmud.spring_boot_thymeleaf.health_data.repository;

import com.moazmahmud.spring_boot_thymeleaf.health_data.entity.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {
    List<HealthData> findAllByEventDate(LocalDate eventDate);
}