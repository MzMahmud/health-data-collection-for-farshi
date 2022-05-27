package com.moazmahmud.spring_boot_thymeleaf.app_user.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @SequenceGenerator(name = "seq_authority", sequenceName = "seq_authority")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_authority")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Authority authority = (Authority) o;
        return id != null && Objects.equals(id, authority.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public GrantedAuthority getGrantedAuthority() {
        return new SimpleGrantedAuthority(name);
    }
}
