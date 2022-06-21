package com.moazmahmud.spring_boot_thymeleaf.app_user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserAddRequest {
    private Long id;
    private String username;
    private String password;
}
