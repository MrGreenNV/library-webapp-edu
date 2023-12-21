package ru.miit.webapp.services;

import ru.miit.webapp.models.dto.AddAuthorDTO;
import ru.miit.webapp.models.view.ShowAuthorDetailInfoModelView;
import ru.miit.webapp.models.view.ShowAuthorInfoModelView;

import java.util.List;

public interface AuthorService {
    void addAuthor(AddAuthorDTO addAuthorDTO);
    List<ShowAuthorInfoModelView> allAuthors();
    ShowAuthorDetailInfoModelView authorDetails(String name);
    void removeAuthor(String name);
}
