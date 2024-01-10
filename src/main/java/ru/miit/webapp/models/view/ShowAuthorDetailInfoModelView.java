package ru.miit.webapp.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.miit.webapp.models.Book;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ShowAuthorDetailInfoModelView {
    private String name;
    private String email;
    private Set<Book> writtenBooks;
}
