package com.moazmahmud.spring_boot_thymeleaf.app_user.repository;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findAllByIdIn(Collection<Long> ids);
}