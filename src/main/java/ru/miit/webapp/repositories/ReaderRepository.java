package ru.miit.webapp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.miit.webapp.models.Reader;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {

    Optional<Reader> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
