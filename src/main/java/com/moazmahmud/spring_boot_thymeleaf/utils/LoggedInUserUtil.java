package com.moazmahmud.spring_boot_thymeleaf.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LoggedInUserUtil {

    private LoggedInUserUtil() {

    }

    public static final String ROLE_PREFIX = "ROLE_";

    public static boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    public static boolean hasAnyRole(String... roles) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return hasAnyRole(authentication, ROLE_PREFIX, roles);
    }

    public static boolean hasAuthority(String authority) {
        return hasAnyAuthority(authority);
    }

    public static boolean hasAnyAuthority(String... authorities) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return hasAnyRole(authentication, null, authorities);
    }

    private static boolean hasAnyRole(Set<String> rolesWithPrefix,
                                      Stream<? extends GrantedAuthority> grantedAuthorityStream) {
        return grantedAuthorityStream
                .map(GrantedAuthority::getAuthority)
                .anyMatch(rolesWithPrefix::contains);
    }

    private static boolean hasAnyRole(Authentication authentication, String rolePrefix, String... roles) {
        Set<String> rolesWithPrefix =
                Arrays.stream(roles)
                      .map(role -> (rolePrefix != null) ? (rolePrefix + role) : role)
                      .collect(Collectors.toUnmodifiableSet());
        
        return Optional.ofNullable(authentication)
                       .map(Authentication::getAuthorities)
                       .map(Collection::stream)
                       .map(grantedAuthorityStream -> hasAnyRole(rolesWithPrefix, grantedAuthorityStream))
                       .orElse(false);
    }
}
