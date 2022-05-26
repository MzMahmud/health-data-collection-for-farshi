package com.moazmahmud.spring_boot_thymeleaf.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public final class LoggedInUserUtil {

    private LoggedInUserUtil() {

    }

    public static final String ROLE_PREFIX = "ROLE_";

    public static boolean hasRole(String role) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return hasRole(authentication, role, ROLE_PREFIX);
    }

    public static boolean hasAnyRole(String... roles) {
        return Arrays.stream(roles)
                     .anyMatch(LoggedInUserUtil::hasRole);
    }

    public static boolean hasAuthority(String authority) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return hasRole(authentication, authority, null);
    }

    public static boolean hasAnyAuthority(String... authorities) {
        return Arrays.stream(authorities)
                     .anyMatch(LoggedInUserUtil::hasAuthority);
    }

    private static boolean hasRole(String roleWithPrefix, Stream<? extends GrantedAuthority> grantedAuthorityStream) {
        return grantedAuthorityStream
                .map(GrantedAuthority::getAuthority)
                .anyMatch(roleWithPrefix::equals);
    }

    private static boolean hasRole(Authentication authentication, String role, String rolePrefix) {
        String roleWithPrefix = (rolePrefix != null) ? (rolePrefix + role) : role;
        return Optional.ofNullable(authentication)
                       .map(Authentication::getAuthorities)
                       .map(Collection::stream)
                       .map(grantedAuthorityStream -> hasRole(roleWithPrefix, grantedAuthorityStream))
                       .orElse(false);
    }
}
