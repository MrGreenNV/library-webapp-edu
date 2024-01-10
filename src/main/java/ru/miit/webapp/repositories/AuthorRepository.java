package ru.miit.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.miit.webapp.models.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    Optional<Author> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
