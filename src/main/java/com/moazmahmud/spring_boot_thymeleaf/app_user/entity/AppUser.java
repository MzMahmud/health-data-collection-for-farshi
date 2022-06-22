package com.moazmahmud.spring_boot_thymeleaf.app_user.entity;

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

    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "app_user_roles",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles = new LinkedHashSet<>();

    public void updateRoles(Set<Role> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
    }
}
