package com.moazmahmud.spring_boot_thymeleaf.app_user.model;

import com.moazmahmud.spring_boot_thymeleaf.app_user.entity.AppUser;
import com.moazmahmud.spring_boot_thymeleaf.role.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AppUserDetails implements UserDetails {

    private final AppUser appUser;
    private final Set<GrantedAuthority> grantedAuthorities;

    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
        grantedAuthorities = appUser.getRoles()
                                    .stream()
                                    .map(Role::getAuthorities)
                                    .map(authorities -> (GrantedAuthority) authorities)
                                    .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(appUser.getIsEnabled());
    }
}
