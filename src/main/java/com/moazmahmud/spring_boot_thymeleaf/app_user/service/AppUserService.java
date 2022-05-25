package com.moazmahmud.spring_boot_thymeleaf.app_user.service;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.AppUser;
import com.moazmahmud.spring_boot_thymeleaf.app_user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public Optional<AppUser> findByUsername(String username) {
        if (username == null) {
            return Optional.empty();
        }
        return appUserRepository.findByUsername(username);
    }
}
