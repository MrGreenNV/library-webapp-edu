package ru.miit.webapp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.Author;
import ru.miit.webapp.models.Reader;
import ru.miit.webapp.models.UserRoles;
import ru.miit.webapp.models.dto.AuthorRegistrationDTO;
import ru.miit.webapp.models.dto.ReaderRegistrationDTO;
import ru.miit.webapp.repositories.AuthorRepository;
import ru.miit.webapp.repositories.ReaderRepository;
import ru.miit.webapp.repositories.UserRoleRepository;
import ru.miit.webapp.services.AuthService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void registerAuthor(AuthorRegistrationDTO authorRegistrationDTO) {
        if (!authorRegistrationDTO.getPassword().equals(authorRegistrationDTO.getConfirmPassword())) {
            throw new RuntimeException("Пароли не совпадают!");
        }

        var authorRole = userRoleRepository.findRoleByName(UserRoles.AUTHOR).orElseThrow();

        Author author = new Author();

        author.setName(authorRegistrationDTO.getName());
        author.setPassword(passwordEncoder.encode(authorRegistrationDTO.getPassword()));
        author.setEmail(authorRegistrationDTO.getEmail());
        author.setRoles(List.of(authorRole));

        authorRepository.save(author);
    }

    @Override
    public void registerReader(ReaderRegistrationDTO readerRegistrationDTO) {
        if (!readerRegistrationDTO.getPassword().equals(readerRegistrationDTO.getConfirmPassword())) {
            throw new RuntimeException("Пароли не совпадают!");
        }

        var readerRole = userRoleRepository.findRoleByName(UserRoles.READER).orElseThrow();

        Reader reader = new Reader();

        reader.setName(readerRegistrationDTO.getName());
        reader.setPassword(passwordEncoder.encode(readerRegistrationDTO.getPassword()));
        reader.setEmail(readerRegistrationDTO.getEmail());
        reader.setDateOfBirth(readerRegistrationDTO.getDateOfBirth());
        reader.setRoles(List.of(readerRole));

        readerRepository.save(reader);
    }

    public Author getAuthor(String username) {
        return authorRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Автор с именем " + username + " не найден!"));
    }

    public Reader getReader(String username) {
        return readerRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Читатель с именем " + username + " не найден!"));
    }
}
