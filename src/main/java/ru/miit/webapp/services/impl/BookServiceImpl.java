package ru.miit.webapp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.dto.AddBookDTO;
import ru.miit.webapp.models.entities.Book;
import ru.miit.webapp.models.entities.User;
import ru.miit.webapp.models.views.ShowBookDetailInfoModelView;
import ru.miit.webapp.models.views.ShowBookInfoModelView;
import ru.miit.webapp.repositories.BookRepository;
import ru.miit.webapp.services.BookService;
import ru.miit.webapp.services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final UserService userService;
    private final BookRepository bookRepository;
    @Override
    @Transactional
    public void addBook(AddBookDTO bookDTO) {
        String nameAuthor = bookDTO.getNameAuthor();

        if (nameAuthor == null || nameAuthor.isEmpty()) {
            nameAuthor = SecurityContextHolder.getContext().getAuthentication().getName();
        }

        User author = userService.getUser(nameAuthor);

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(bookDTO.getTitle());
        book.setYearOfPublished(bookDTO.getYearOfPublished());

        author.getWritenBooks().add(book);

        userService.saveUser(author);
        bookRepository.save(book);
    }

    @Override
    public List<ShowBookInfoModelView> allBooks() {
        List<ShowBookInfoModelView> booksViews = new ArrayList<>();
        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            ShowBookInfoModelView showBookInfoModelView = new ShowBookInfoModelView();
            showBookInfoModelView.setId(book.getId());
            showBookInfoModelView.setNameAuthor(book.getAuthor().getUsername());
            showBookInfoModelView.setTitle(book.getTitle());
            showBookInfoModelView.setYearOfPublished(book.getYearOfPublished());

            booksViews.add(showBookInfoModelView);
        }

        return booksViews;
    }

    @Override
    public ShowBookDetailInfoModelView bookDetailsById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найдена"));

        ShowBookDetailInfoModelView booksViews = new ShowBookDetailInfoModelView();
        booksViews.setId(book.getId());
        booksViews.setTitle(book.getTitle());
        booksViews.setReceivedIn(book.getReceivedIn());
        booksViews.setNameAuthor(book.getAuthor().getUsername());
        booksViews.setYearOfPublished(book.getYearOfPublished());

        if (book.getAbonent() != null) {
            booksViews.setNameReader(book.getAbonent().getUsername());
        }

        return booksViews;
    }

    @Override
    public void removeBook(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void receiveBook(String bookId) {
        User abonent = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));

        abonent.getRentedBooks().add(book);

        book.setAbonent(abonent);
        book.setReceivedIn(LocalDate.now());

        userService.saveUser(abonent);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void returnBook(String bookId) {
        User abonent = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));

        abonent.getRentedBooks().remove(book);

        book.setAbonent(null);
        book.setReceivedIn(null);

        userService.saveUser(abonent);
        bookRepository.save(book);
    }
}
