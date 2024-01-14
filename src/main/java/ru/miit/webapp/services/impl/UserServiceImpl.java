package ru.miit.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.entities.User;
import ru.miit.webapp.repositories.UserRepository;
import ru.miit.webapp.services.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(String id, User updateUser) {
        User saveUser = getUserById(id);
        saveUser.setUsername(updateUser.getUsername());
        saveUser.setEmail(updateUser.getEmail());

        userRepository.save(saveUser);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь с именем " + username + " не найден"));
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден по id"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь с email " + email + " не найден"));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(getUserById(id));
    }

}
