package com.moazmahmud.spring_boot_thymeleaf.app_user.service;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.AppUser;
import com.moazmahmud.spring_boot_thymeleaf.app_user.repository.AppUserRepository;
import com.moazmahmud.spring_boot_thymeleaf.role.entity.Authority;
import com.moazmahmud.spring_boot_thymeleaf.role.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Transactional
    public Optional<AppUser> findByUsername(String username) {
        if (username == null) {
            return Optional.empty();
        }
        return appUserRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Set<GrantedAuthority> getGrantedAuthoritiesByUser(AppUser appUser) {
        return appUser.getRoles()
                      .stream()
                      .map(role -> {
                          var grantedAuthorities = role.getAuthorities()
                                                       .stream()
                                                       .map(Authority::getGrantedAuthority)
                                                       .collect(Collectors.toSet());
                          grantedAuthorities.add(role.getGrantedAuthority());
                          return grantedAuthorities;
                      })
                      .flatMap(Collection::stream)
                      .collect(Collectors.toUnmodifiableSet());
    }
}
