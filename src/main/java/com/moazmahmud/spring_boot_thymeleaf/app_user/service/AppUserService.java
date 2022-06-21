package com.moazmahmud.spring_boot_thymeleaf.app_user.service;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.AppUser;
import com.moazmahmud.spring_boot_thymeleaf.app_user.model.AppUserAddRequest;
import com.moazmahmud.spring_boot_thymeleaf.app_user.model.AppUserResponse;
import com.moazmahmud.spring_boot_thymeleaf.app_user.repository.AppUserRepository;
import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.Authority;
import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Transactional
    public Optional<AppUser> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return appUserRepository.findById(id);
    }

    private void setEntityValueFromAddRequest(AppUserAddRequest addRequest, AppUser appUser) {
        appUser.setUsername(addRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(addRequest.getPassword()));
        appUser.setIsEnabled(true);
    }

    private AppUserResponse getAddRequestFromEntity(AppUser appUser) {
        var response = new AppUserResponse();
        response.setId(appUser.getId());
        response.setUsername(appUser.getUsername());
        response.setIsEnabled(appUser.getIsEnabled());
        return response;
    }

    @Transactional
    public AppUserResponse addUser(AppUserAddRequest appUserAddRequest) {
        var optionalAppUser = findByUsername(appUserAddRequest.getUsername());
        if(optionalAppUser.isPresent()) {
            throw new BadRequestException("username=" + appUserAddRequest.getUsername() + " already exists");
        }
        AppUser appUser = new AppUser();
        setEntityValueFromAddRequest(appUserAddRequest, appUser);
        return getAddRequestFromEntity(appUserRepository.save(appUser));
    }
}
