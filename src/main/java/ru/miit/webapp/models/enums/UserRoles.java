package ru.miit.webapp.models.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    UNKNOWN(0),
    ADMIN(1),
    MODERATOR(2),
    AUTHOR(3),
    READER(4);

    private final int id;

    UserRoles(int code) {
        this.id = code;
    }

    public static UserRoles fromId(int code) {

        UserRoles[] roles = UserRoles.values();
        if (code <= 0 || code > roles.length) {
            return UserRoles.UNKNOWN;
        }
        return roles[code];
    }
}
