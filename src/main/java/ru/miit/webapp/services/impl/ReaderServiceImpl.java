package ru.miit.webapp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.Book;
import ru.miit.webapp.models.Reader;
import ru.miit.webapp.models.dto.ActionBookDTO;
import ru.miit.webapp.models.dto.AddReaderDTO;
import ru.miit.webapp.models.view.ShowReaderDetailInfoModelView;
import ru.miit.webapp.models.view.ShowReaderInfoModelView;
import ru.miit.webapp.repositories.BookRepository;
import ru.miit.webapp.repositories.ReaderRepository;
import ru.miit.webapp.services.ReaderService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    @Override
    public void addReader(AddReaderDTO readerDTO) {
        readerRepository.save(mapper.map(readerDTO, Reader.class));
    }

    @Override
    public List<ShowReaderInfoModelView> allReader() {
        return readerRepository.findAll().stream()
                .map(reader -> mapper.map(reader, ShowReaderInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowReaderDetailInfoModelView readerDetails(String name) {
        return mapper.map(readerRepository.findByName(name), ShowReaderDetailInfoModelView.class);
    }

    @Transactional
    @Override
    public void receiveBook(String name, ActionBookDTO actionBookDTO) {
        LocalDate date = LocalDate.now();
        Book book = bookRepository.findByTitle(actionBookDTO.getTitle()).orElse(null);
        Reader reader = readerRepository.findByName(name).orElse(null);
        if (book != null && reader != null && book.getAbonent() == null) {
            reader.getRentedBooks().add(book);
            book.setAbonent(reader);
            book.setReceivedIn(date);
            readerRepository.save(reader);
            bookRepository.save(book);
        }
    }

    @Transactional
    @Override
    public void returnBook(String name, ActionBookDTO actionBookDTO) {
        Book book = bookRepository.findByTitle(actionBookDTO.getTitle()).orElse(null);
        Reader reader = readerRepository.findByName(name).orElse(null);
        if (book != null && reader != null && book.getAbonent().getName().equals(name)) {
            reader.getRentedBooks().remove(book);
            book.setAbonent(null);
            book.setReceivedIn(null);
            readerRepository.save(reader);
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteReader(String name) {
        readerRepository.deleteByName(name);
    }
}
