package ru.miit.webapp.services;

import ru.miit.webapp.models.dto.AddBookDTO;
import ru.miit.webapp.models.view.ShowBookDetailInfoModelView;
import ru.miit.webapp.models.view.ShowBookInfoModelView;

import java.util.List;

public interface BookService {
    void addBook(AddBookDTO bookDTO);
    List<ShowBookInfoModelView> allBooks();
    ShowBookDetailInfoModelView bookDetails(String title);
    void removeBook(String title);
}
