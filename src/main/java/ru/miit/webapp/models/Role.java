package ru.miit.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private UserRoles name;

    public Role(UserRoles name) {
        this.name = name;
    }


    public Role() {

    }

    public UserRoles getName() {
        return this.name;
    }

    public void setName(UserRoles name) {
        this.name = name;
    }
}
