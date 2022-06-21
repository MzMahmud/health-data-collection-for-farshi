package com.moazmahmud.spring_boot_thymeleaf.app_user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponse {
    private Long id;
    private String username;
    private Boolean isEnabled;
}
