package com.moazmahmud.health_data_collection.app_user.model;

import com.moazmahmud.health_data_collection.app_user.entity.AppUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
public class AppUserDetails implements UserDetails {

    private final AppUser appUser;
    private final Set<GrantedAuthority> grantedAuthorities;

    public AppUserDetails(AppUser appUser, Set<GrantedAuthority> grantedAuthorities) {
        this.appUser = appUser;
        this.grantedAuthorities = grantedAuthorities;
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
