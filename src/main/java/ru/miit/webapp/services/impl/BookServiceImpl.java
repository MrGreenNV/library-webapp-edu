package ru.miit.webapp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.Author;
import ru.miit.webapp.models.Book;
import ru.miit.webapp.models.dto.AddBookDTO;
import ru.miit.webapp.models.view.ShowBookDetailInfoModelView;
import ru.miit.webapp.models.view.ShowBookInfoModelView;
import ru.miit.webapp.repositories.AuthorRepository;
import ru.miit.webapp.repositories.BookRepository;
import ru.miit.webapp.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;
    @Transactional
    @Override
    public void addBook(AddBookDTO bookDTO) {
        Author author = authorRepository.findByName(bookDTO.getNameAuthor()).orElse(null);
        int date = bookDTO.getYearOfPublished();
        if (author != null) {
            Book book = mapper.map(bookDTO, Book.class);
            book.setAuthor(author);
            book.setYearOfPublished(date);
            author.getWrittenBooks().add(book);
            bookRepository.save(book);
            authorRepository.save(author);
        }
    }

    @Override
    public List<ShowBookInfoModelView> allBooks() {
        return bookRepository.findAll().stream()
                .map(book -> mapper.map(book, ShowBookInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowBookDetailInfoModelView bookDetails(String title) {
        Book book = bookRepository.findByTitle(title).orElse(null);
        ShowBookDetailInfoModelView bookDetailInfoModelView = null;
        if (book != null) {
            bookDetailInfoModelView = mapper.map(book, ShowBookDetailInfoModelView.class);
            bookDetailInfoModelView.setNameReader(book.getAbonent() == null ? null : book.getAbonent().getName());
        }
        return bookDetailInfoModelView;
    }

    @Override
    public void removeBook(String title) {
        bookRepository.deleteByTitle(title);
    }
}
