package ru.miit.webapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.miit.webapp.models.Author;
import ru.miit.webapp.models.Book;
import ru.miit.webapp.models.Reader;
import ru.miit.webapp.models.Role;
import ru.miit.webapp.models.UserRoles;
import ru.miit.webapp.repositories.AuthorRepository;
import ru.miit.webapp.repositories.BookRepository;
import ru.miit.webapp.repositories.ReaderRepository;
import ru.miit.webapp.repositories.UserRoleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class Init implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPass;

    public Init(AuthorRepository authorRepository, ReaderRepository readerRepository, BookRepository bookRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("@{app.default.pass}") String defaultPass) {
        this.authorRepository = authorRepository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPass = defaultPass;
    }

    @Override
    public void run(String... args) {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var authorRole = new Role(UserRoles.AUTHOR);
            var readerRole = new Role(UserRoles.READER);
            var adminRole = new Role(UserRoles.ADMIN);

            userRoleRepository.save(authorRole);
            userRoleRepository.save(readerRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initUsers() {
        if (authorRepository.count() == 0 && readerRepository.count() == 0) {
            initAdmin();
            initAutor();
            initReader();
            initBooks();
        }
    }

    private void initAdmin() {
        var adminRole = userRoleRepository.findRoleByName(UserRoles.ADMIN).orElseThrow(() -> new RuntimeException("Роль не найдена!"));

        var adminUser = new Author();
        adminUser.setName("admin");
        adminUser.setPassword(passwordEncoder.encode(defaultPass));
        adminUser.setEmail("admin@example.com");
        adminUser.setRoles(List.of(adminRole));

        authorRepository.save(adminUser);
    }

    private void initAutor() {
        var authorRole = userRoleRepository.findRoleByName(UserRoles.AUTHOR).orElseThrow(() -> new RuntimeException("Роль не найдена!"));

        var authorUser = new Author();
        authorUser.setName("Pushkin");
        authorUser.setPassword(passwordEncoder.encode(defaultPass));
        authorUser.setEmail("pushka@example.com");
        authorUser.setRoles(List.of(authorRole));

        authorRepository.save(authorUser);
    }

    private void initReader() {
        var readerRole = userRoleRepository.findRoleByName(UserRoles.READER).orElseThrow(() -> new RuntimeException("Роль не найдена!"));

        var readerUser = new Reader();
        readerUser.setName("Ivanov");
        readerUser.setPassword(passwordEncoder.encode(defaultPass));
        readerUser.setEmail("vano@example.com");
        readerUser.setRoles(List.of(readerRole));

        readerRepository.save(readerUser);
    }

    private void initBooks() {
        var author = authorRepository.findByName("Pushkin").orElseThrow(() -> new RuntimeException("Автор не найден!"));
        var reader = readerRepository.findByName("Ivanov").orElseThrow(() -> new RuntimeException("Читатель не найден!"));
        var book_1 = new Book(author, "Капитанская дочка", 1836, reader, LocalDate.now());
        var book_2 = new Book(author, "Евгений Онегин", 1833, reader, LocalDate.now());
        author.setWrittenBooks(Set.of(book_1, book_2));

        bookRepository.save(book_1);
        bookRepository.save(book_2);

        authorRepository.save(author);
    }


}
