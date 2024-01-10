package ru.miit.webapp.services;

import ru.miit.webapp.models.dto.AuthorRegistrationDTO;
import ru.miit.webapp.models.dto.ReaderRegistrationDTO;

public interface AuthService {
    void registerAuthor(AuthorRegistrationDTO authorRegistrationDTO);
    void registerReader(ReaderRegistrationDTO readerRegistrationDTO);
}
