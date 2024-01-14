package ru.miit.webapp.services;

import ru.miit.webapp.models.dto.AddBookDTO;
import ru.miit.webapp.models.views.ShowBookDetailInfoModelView;
import ru.miit.webapp.models.views.ShowBookInfoModelView;

import java.util.List;

public interface BookService {
    void addBook(AddBookDTO bookDTO);
    List<ShowBookInfoModelView> allBooks();
    ShowBookDetailInfoModelView bookDetailsById(String title);
    void removeBook(String id);
    void receiveBook(String bookId);
    void returnBook(String bookId);
}
