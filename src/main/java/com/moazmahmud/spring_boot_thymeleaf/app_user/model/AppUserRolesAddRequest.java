package com.moazmahmud.spring_boot_thymeleaf.app_user.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AppUserRolesAddRequest {
    private Long userId;
    private Set<Long> roleIds;
}
