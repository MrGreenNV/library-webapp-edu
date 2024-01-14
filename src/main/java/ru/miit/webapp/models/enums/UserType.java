package ru.miit.webapp.models.enums;

import lombok.Getter;

@Getter
public enum UserType {
    AUTHOR(0),
    READER(1);

    private final int id;

    UserType(int code) {
        this.id = code;
    }

    public static UserType fromId(int code) {
        return UserType.values()[code];
    }

}
