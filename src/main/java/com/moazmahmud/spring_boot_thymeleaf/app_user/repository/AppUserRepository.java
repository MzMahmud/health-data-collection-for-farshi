package com.moazmahmud.spring_boot_thymeleaf.app_user.repository;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}