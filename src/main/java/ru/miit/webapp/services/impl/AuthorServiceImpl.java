package ru.miit.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.miit.webapp.models.Author;
import ru.miit.webapp.models.dto.AddAuthorDTO;
import ru.miit.webapp.models.view.ShowAuthorDetailInfoModelView;
import ru.miit.webapp.models.view.ShowAuthorInfoModelView;
import ru.miit.webapp.repositories.AuthorRepository;
import ru.miit.webapp.services.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;


    @Override
    public void addAuthor(AddAuthorDTO addAuthorDTO) {
        authorRepository.save(mapper.map(addAuthorDTO, Author.class));
    }

    @Override
    public List<ShowAuthorInfoModelView> allAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> mapper.map(author, ShowAuthorInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowAuthorDetailInfoModelView authorDetails(String name) {
        return mapper.map(authorRepository.findByName(name).orElse(null), ShowAuthorDetailInfoModelView.class);
    }

    @Override
    public void removeAuthor(String name) {
        authorRepository.deleteByName(name);
    }
}
