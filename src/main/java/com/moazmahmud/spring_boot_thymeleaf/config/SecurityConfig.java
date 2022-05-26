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

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
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
            .antMatchers(GET,"/health-data").hasAnyAuthority("HEALTH_DATA_ADD")
            .antMatchers(POST,"api/v1/health-data").hasAnyAuthority("HEALTH_DATA_ADD")
            .antMatchers(GET,"/health-data/search", "/api/v1/health-data").hasAnyAuthority("HEALTH_DATA_VIEW", "HEALTH_DATA_EDIT", "HEALTH_DATA_DELETE")
            .antMatchers(GET,"api/v1/health-data/raw-data").hasAnyAuthority("HEALTH_DATA_VIEW")
            .antMatchers(GET,"/health-data/*").hasAnyAuthority("HEALTH_DATA_EDIT")
            .antMatchers(POST,"api/v1/health-data/*").hasAnyAuthority("HEALTH_DATA_EDIT")
            .antMatchers(DELETE,"/api/v1/health-data/*").hasAnyAuthority("HEALTH_DATA_DELETE")
            .antMatchers(permitAllURL).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .headers()
            .xssProtection();
        return http.build();
    }
}
