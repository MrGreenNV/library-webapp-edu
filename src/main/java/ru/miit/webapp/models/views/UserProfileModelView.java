package ru.miit.webapp.models.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.miit.webapp.models.entities.Book;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileModelView {
    private String username;
    private String email;
    private List<Book> writenBooks;
    private List<Book> rentedBooks;
}
