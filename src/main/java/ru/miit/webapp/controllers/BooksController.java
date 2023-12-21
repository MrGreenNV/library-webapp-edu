package ru.miit.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.miit.webapp.models.dto.AddBookDTO;
import ru.miit.webapp.models.view.ShowBookDetailInfoModelView;
import ru.miit.webapp.models.view.ShowBookInfoModelView;
import ru.miit.webapp.services.BookService;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @PostMapping()
    public void addBook(@RequestBody AddBookDTO bookDTO) {
        bookService.addBook(bookDTO);
    }

    @GetMapping()
    public List<ShowBookInfoModelView> showAllBooks() {
        return bookService.allBooks();
    }

    @GetMapping("/{title}")
    public ShowBookDetailInfoModelView showBookDetails(@PathVariable("title") String title) {
        return bookService.bookDetails(title);
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable("title") String  title) {
        bookService.removeBook(title);
    }
}
