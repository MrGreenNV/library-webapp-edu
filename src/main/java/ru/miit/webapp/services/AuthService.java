package ru.miit.webapp.services;

import ru.miit.webapp.models.dto.UserRegistrationDTO;

public interface AuthService {
    void register(UserRegistrationDTO registrationDTO);
}
