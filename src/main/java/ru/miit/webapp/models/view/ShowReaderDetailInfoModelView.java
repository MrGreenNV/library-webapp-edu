package ru.miit.webapp.models.view;

import lombok.Getter;
import lombok.Setter;
import ru.miit.webapp.models.Book;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ShowReaderDetailInfoModelView {
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Set<Book> rentedBooks;
}