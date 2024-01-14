package ru.miit.webapp;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.miit.webapp.models.entities.Book;
import ru.miit.webapp.models.entities.Role;
import ru.miit.webapp.models.entities.User;
import ru.miit.webapp.models.enums.UserRoles;
import ru.miit.webapp.repositories.BookRepository;
import ru.miit.webapp.repositories.RoleRepository;
import ru.miit.webapp.services.UserService;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Init implements CommandLineRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final String defaultPass;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
        initBooks();
    }


    private void initRoles() {
        if (roleRepository.count() == 0) {
            var unknownRole =   new Role(UserRoles.UNKNOWN);
            var adminRole =     new Role(UserRoles.ADMIN);
            var moderatorRole = new Role(UserRoles.MODERATOR);
            var authorRole =    new Role(UserRoles.AUTHOR);
            var readerRole =    new Role(UserRoles.READER);

            roleRepository.save(unknownRole);
            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
            roleRepository.save(authorRole);
            roleRepository.save(readerRole);
        }
    }

    private void initUsers() {
        if (userService.getAllUser().isEmpty()) {
            initAdmin();
            initAuthor();
            initReader();
        }
    }

    private void initAdmin() {
        var adminRole = roleRepository.findRoleByName(UserRoles.ADMIN).orElseThrow();

        var adminUser = new User(
                "Admin",
                passwordEncoder.encode(defaultPass),
                "admin@example.com"
        );

        adminUser.setRoles(List.of(adminRole));
        userService.saveUser(adminUser);
    }

    private void initAuthor() {
        var authorRole = roleRepository.findRoleByName(UserRoles.AUTHOR).orElseThrow();
        var readerRole = roleRepository.findRoleByName(UserRoles.READER).orElseThrow();

        var authorUser = new User(
                "Pushkin",
                passwordEncoder.encode(defaultPass),
                "pushka@yandex.ru"
        );

        authorUser.setRoles(List.of(authorRole, readerRole));
        userService.saveUser(authorUser);
    }

    private void initReader() {
        var readerRole = roleRepository.findRoleByName(UserRoles.READER).orElseThrow();

        var readerUser = new User(
                "Elis",
                passwordEncoder.encode(defaultPass),
                "elis@mail.ru"
        );

        readerUser.setRoles(List.of(readerRole));
        userService.saveUser(readerUser);
    }

    private void initBooks() {
        if (bookRepository.count() == 0) {
            var author = userService.getUser("Pushkin");
            var abonent = userService.getUser("Elis");

            var book_1 = new Book(author, "Капитанская дочка", 1836, abonent, LocalDate.now());
            var book_2 = new Book(author, "Евгений онегин", 1833, null, null);

            author.getWritenBooks().add(book_1);
            author.getWritenBooks().add(book_2);

            abonent.getRentedBooks().add(book_1);

            bookRepository.save(book_1);
            bookRepository.save(book_2);
            userService.saveUser(author);
            userService.saveUser(abonent);
        }
    }
}
