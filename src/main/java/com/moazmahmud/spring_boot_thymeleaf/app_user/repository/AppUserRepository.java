package com.moazmahmud.spring_boot_thymeleaf.app_user.repository;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    @Query("from AppUser appUser left join fetch appUser.roles where appUser.id = :id")
    Optional<AppUser> findWithRolesById(@Param("id") Long id);
}