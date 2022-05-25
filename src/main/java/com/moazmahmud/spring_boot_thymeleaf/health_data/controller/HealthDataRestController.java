package com.moazmahmud.spring_boot_thymeleaf.health_data.controller;

import com.moazmahmud.spring_boot_thymeleaf.common.classes.BaseRestController;
import com.moazmahmud.spring_boot_thymeleaf.common.classes.RestResponse;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.HealthDataAddRequest;
import com.moazmahmud.spring_boot_thymeleaf.health_data.model.HealthDataSearchRequest;
import com.moazmahmud.spring_boot_thymeleaf.health_data.service.HealthDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/health-data")
@RequiredArgsConstructor
public class HealthDataRestController extends BaseRestController {

    private final HealthDataService healthDataService;

    @PostMapping
    public RestResponse saveAddRequest(@RequestBody HealthDataAddRequest healthDataAddRequest) {
        healthDataService.saveAddRequest(healthDataAddRequest);
        return RestResponse.builder().build();
    }

    @GetMapping
    public RestResponse getSearchResponse(HealthDataSearchRequest searchRequest) {
        return RestResponse
                .builder()
                .payload(healthDataService.getSearchResponseList(searchRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable("id") Long id) {
        healthDataService.delete(id);
        return RestResponse.builder().build();
    }

    @GetMapping("/raw-data")
    public RestResponse getHealthData(HealthDataSearchRequest searchRequest) {
        return RestResponse
                .builder()
                .payload(healthDataService.getEntityList(searchRequest))
                .build();
    }
}
