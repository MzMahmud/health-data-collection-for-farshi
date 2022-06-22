package com.moazmahmud.health_data_collection.app_user.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppUserRolesResponse {
    private Long userId;
    private String username;
    private List<RoleResponse> roles;
}
