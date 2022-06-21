package com.moazmahmud.spring_boot_thymeleaf.app_user.controller;

import com.moazmahmud.spring_boot_thymeleaf.app_user.model.AppUserAddRequest;
import com.moazmahmud.spring_boot_thymeleaf.app_user.service.AppUserService;
import com.moazmahmud.spring_boot_thymeleaf.common.classes.BaseRestController;
import com.moazmahmud.spring_boot_thymeleaf.common.classes.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app-user")
@RequiredArgsConstructor
public class AppUserRestController extends BaseRestController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<RestResponse> saveAddRequest(@RequestBody AppUserAddRequest appUserAddRequest) {
        var response = appUserService.saveAddRequest(appUserAddRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestResponse
                        .builder()
                        .payload(response)
                        .build());
    }
}
