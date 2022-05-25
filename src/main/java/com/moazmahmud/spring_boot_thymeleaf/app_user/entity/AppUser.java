package com.moazmahmud.spring_boot_thymeleaf.app_user.entity;

import com.moazmahmud.spring_boot_thymeleaf.role.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @SequenceGenerator(name = "seq_app_user", sequenceName = "seq_app_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_app_user")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ManyToMany
    @JoinTable(
            name = "app_user_roles",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles = new LinkedHashSet<>();
}
