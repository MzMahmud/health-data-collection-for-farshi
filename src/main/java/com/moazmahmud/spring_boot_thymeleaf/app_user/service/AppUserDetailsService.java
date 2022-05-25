package com.moazmahmud.spring_boot_thymeleaf.app_user.service;

import com.moazmahmud.spring_boot_thymeleaf.app_user.model.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = appUserService.findByUsername(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("User with username=" + username + " not found"));
        return new AppUserDetails(appUser);
    }
}
