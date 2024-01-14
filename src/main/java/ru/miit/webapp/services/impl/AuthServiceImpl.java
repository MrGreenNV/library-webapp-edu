package ru.miit.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.dto.UserRegistrationDTO;
import ru.miit.webapp.models.entities.Role;
import ru.miit.webapp.models.entities.User;
import ru.miit.webapp.models.enums.UserRoles;
import ru.miit.webapp.repositories.RoleRepository;
import ru.miit.webapp.services.AuthService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("Пароли не совпадают");
        }

        User user = modelMapper.map(registrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        switch (registrationDTO.getUserType()) {
            case AUTHOR -> {
                Role authorRole = roleRepository.findRoleByName(UserRoles.AUTHOR).orElseThrow(() -> new RuntimeException("Список ролей не содержит допустимых значений"));
                Role readerRole = roleRepository.findRoleByName(UserRoles.READER).orElseThrow(() -> new RuntimeException("Список ролей не содержит допустимых значений"));
                user.setRoles(List.of(authorRole, readerRole));
            }
            case READER -> {
                Role readerRole = roleRepository.findRoleByName(UserRoles.READER).orElseThrow(() -> new RuntimeException("Список ролей не содержит допустимых значений"));
                user.setRoles(List.of(readerRole));
            }
            default     -> throw new RuntimeException("Неизвестный тип пользователя");
        }

        userService.saveUser(user);
    }
}
