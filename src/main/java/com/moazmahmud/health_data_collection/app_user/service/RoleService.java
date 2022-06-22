package com.moazmahmud.health_data_collection.app_user.service;

import com.moazmahmud.health_data_collection.app_user.entity.Role;
import com.moazmahmud.health_data_collection.app_user.model.RoleResponse;
import com.moazmahmud.health_data_collection.app_user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Set<Role> findByIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Set.of();
        }
        return roleRepository.findAllByIdIn(ids);
    }

    public RoleResponse getResponseFromEntity(Role role) {
        var response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }

    public List<RoleResponse> getResponseListFromEntity(Collection<Role> roles) {
        if (roles == null) {
            return List.of();
        }
        return roles.stream()
                    .map(this::getResponseFromEntity)
                    .collect(Collectors.toUnmodifiableList());
    }
}
