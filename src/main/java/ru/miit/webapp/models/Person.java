package ru.miit.webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Person extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "pass", nullable = false)
    private String password;
    @ManyToMany
    private List<Role> roles;

}
