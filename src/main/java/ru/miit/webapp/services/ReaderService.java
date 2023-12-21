package ru.miit.webapp.services;

import ru.miit.webapp.models.dto.ActionBookDTO;
import ru.miit.webapp.models.dto.AddReaderDTO;
import ru.miit.webapp.models.view.ShowReaderDetailInfoModelView;
import ru.miit.webapp.models.view.ShowReaderInfoModelView;

import java.util.List;

public interface ReaderService {
    void addReader(AddReaderDTO readerDTO);
    List<ShowReaderInfoModelView> allReader();
    ShowReaderDetailInfoModelView readerDetails(String name);
    void receiveBook(String name, ActionBookDTO actionBookDTO);
    void returnBook(String name, ActionBookDTO actionBookDTO);
    void deleteReader(String name);
}
