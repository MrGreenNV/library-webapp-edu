package ru.miit.webapp.services;

import org.springframework.stereotype.Service;
import ru.miit.webapp.models.entities.User;

import java.util.List;

@Service
public interface UserService {
    void saveUser(User user);
    void updateUser(String id, User updateUser);
    User getUser(String username);
    User getUserById(String id);
    User getUserByEmail(String email);
    List<User> getAllUser();
    void deleteUser(String id);
}
