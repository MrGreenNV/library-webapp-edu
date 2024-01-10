package ru.miit.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role extends BaseEntity {
    private UserRoles name;
    public Role(UserRoles name) {
        this.name = name;
    }
}
