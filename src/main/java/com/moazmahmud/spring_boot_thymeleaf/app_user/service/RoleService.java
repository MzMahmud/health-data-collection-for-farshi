package com.moazmahmud.spring_boot_thymeleaf.app_user.service;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.Role;
import com.moazmahmud.spring_boot_thymeleaf.app_user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

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
}
