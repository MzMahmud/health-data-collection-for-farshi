package com.moazmahmud.spring_boot_thymeleaf.config;

import com.moazmahmud.spring_boot_thymeleaf.app_user.service.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserDetailsService appUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        return daoAuthenticationProvider;
    }

    private final String[] permitAllURL = {
            "/login",
            "/css/**",
            "/images/**",
            "/js/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf()
            .disable()
            .sessionManagement()
            .and()
            .authorizeHttpRequests()
            .antMatchers(GET, "/health-data").hasAnyAuthority("HEALTH_DATA_ADD")
            .antMatchers(POST, "/api/v1/health-data").hasAnyAuthority("HEALTH_DATA_ADD")
            .antMatchers(GET, "/health-data/search", "/api/v1/health-data").hasAnyAuthority("HEALTH_DATA_VIEW", "HEALTH_DATA_EDIT", "HEALTH_DATA_DELETE")
            .antMatchers(GET, "/api/v1/health-data/raw-data").hasAnyAuthority("HEALTH_DATA_VIEW")
            .antMatchers(GET, "/health-data/*").hasAnyAuthority("HEALTH_DATA_EDIT")
            .antMatchers(PUT, "/api/v1/health-data/update-dependent-values").hasAnyAuthority("HEALTH_DATA_EDIT")
            .antMatchers(POST, "/api/v1/health-data/*").hasAnyAuthority("HEALTH_DATA_EDIT")
            .antMatchers(DELETE, "/api/v1/health-data/*").hasAnyAuthority("HEALTH_DATA_DELETE")
            .antMatchers(POST, "/api/v1/app-user").hasAnyAuthority("APP_USER_ADD")
            .antMatchers(GET, "/api/v1/app-user/*/roles").hasAnyAuthority("APP_USER_ROLES_VIEW")
            .antMatchers(PUT, "/api/v1/app-user/*/roles").hasAnyAuthority("APP_USER_ROLES_UPDATE")
            .antMatchers(permitAllURL).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/error-page/401")
            .and()
            .formLogin()
            .and()
            .headers()
            .xssProtection();
        return http.build();
    }
}
