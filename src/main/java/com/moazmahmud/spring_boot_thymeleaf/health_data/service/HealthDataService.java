package com.moazmahmud.spring_boot_thymeleaf.health_data.service;

import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.NotFoundException;
import com.moazmahmud.spring_boot_thymeleaf.health_data.entity.HealthData;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.BloodPressure;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.HealthDataAddRequest;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.HealthDataSearchRequest;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.HealthDataSearchResponse;
import com.moazmahmud.spring_boot_thymeleaf.health_data.repository.HealthDataRepository;
import com.moazmahmud.spring_boot_thymeleaf.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SuppressWarnings({"Duplicates"})
public class HealthDataService {

    private final HealthDataRepository healthDataRepository;

    @Transactional
    public Optional<HealthData> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return healthDataRepository.findById(id);
    }

    private void setEntityValueFromAddRequest(HealthDataAddRequest healthDataAddRequest,
                                              HealthData healthData) {
        healthData.setId(healthDataAddRequest.getId());
        healthData.setLocationName(healthDataAddRequest.getLocationName());
        healthData.setEventDate(healthDataAddRequest.getEventDate());
        healthData.setAge(healthDataAddRequest.getAge());
        healthData.setGender(healthDataAddRequest.getGender());
        healthData.setReligion(healthDataAddRequest.getReligion());
        healthData.setHeightInInch(healthDataAddRequest.getHeightInInch());
        healthData.setWeightInKg(healthDataAddRequest.getWeightInKg());
        healthData.setWeightInKg(healthDataAddRequest.getWeightInKg());

        var bloodPressure = new BloodPressure();
        bloodPressure.setSystolicInMmHg(healthDataAddRequest.getBloodPressureSystolicInMmHg());
        bloodPressure.setDiastolicInMmHg(healthDataAddRequest.getBloodPressureDiastolicInMmHg());
        healthData.setBloodPressure(bloodPressure);

        healthData.setBloodSugarInMilliMolePerL(healthDataAddRequest.getBloodSugarInMilliMolePerL());
        healthData.setPhysicalActivity(healthDataAddRequest.getPhysicalActivity());
        healthData.setCarbIntakeFrequency(healthDataAddRequest.getCarbIntakeFrequency());
        healthData.setCerealQuality(healthDataAddRequest.getCerealQuality());
    }

    private HealthDataAddRequest getAddRequestFromEntity(HealthData healthData) {
        var healthDataAddRequest = new HealthDataAddRequest();
        healthDataAddRequest.setId(healthData.getId());
        healthDataAddRequest.setLocationName(healthData.getLocationName());
        healthDataAddRequest.setEventDate(healthData.getEventDate());
        healthDataAddRequest.setAge(healthData.getAge());
        healthDataAddRequest.setGender(healthData.getGender());
        healthDataAddRequest.setReligion(healthData.getReligion());
        healthDataAddRequest.setHeightInInch(healthData.getHeightInInch());
        healthDataAddRequest.setWeightInKg(healthData.getWeightInKg());
        healthDataAddRequest.setWeightInKg(healthData.getWeightInKg());
        var bloodPressure = healthData.getBloodPressure();
        if (bloodPressure != null) {
            healthDataAddRequest.setBloodPressureSystolicInMmHg(bloodPressure.getSystolicInMmHg());
            healthDataAddRequest.setBloodPressureDiastolicInMmHg(bloodPressure.getDiastolicInMmHg());
        }
        healthDataAddRequest.setBloodSugarInMilliMolePerL(healthData.getBloodSugarInMilliMolePerL());
        return healthDataAddRequest;
    }

    @Transactional
    public void saveAddRequest(HealthDataAddRequest healthDataAddRequest) {
        var healthData = findById(healthDataAddRequest.getId()).orElse(new HealthData());
        setEntityValueFromAddRequest(healthDataAddRequest, healthData);
        healthDataRepository.save(healthData);
    }

    public HealthDataAddRequest getAddRequestById(Long id) {
        var healthData = findById(id).orElseThrow(() -> new NotFoundException("No HealthData found with id=" + id));
        return getAddRequestFromEntity(healthData);
    }

    private HealthDataSearchResponse mapEntityToSearchResponse(HealthData healthData) {
        var response = new HealthDataSearchResponse();
        response.setId(healthData.getId());
        response.setLocationName(healthData.getLocationName());
        response.setEventDate(StringUtil.getFormattedDate(healthData.getEventDate()));
        response.setAge(healthData.getAge());
        response.setGender(StringUtil.getText(healthData.getGender()));
        response.setHeightInInch(healthData.getHeightInInch());
        response.setWeightInKg(healthData.getWeightInKg());
        return response;
    }

    @Transactional(readOnly = true)
    public List<HealthData> getEntityList(HealthDataSearchRequest searchRequest) {
        if (searchRequest.getEventDate() == null) {
            return healthDataRepository.findAll();
        }
        return healthDataRepository.findAllByEventDate(searchRequest.getEventDate());
    }

    @Transactional(readOnly = true)
    public List<HealthDataSearchResponse> getSearchResponseList(HealthDataSearchRequest searchRequest) {
        return getEntityList(searchRequest)
                .stream()
                .map(this::mapEntityToSearchResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    public void delete(Long id) {
        var healthData = findById(id).orElseThrow(() -> new NotFoundException("No HealthData found with id=" + id));
        healthDataRepository.delete(healthData);
    }
}
