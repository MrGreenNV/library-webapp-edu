package ru.miit.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.miit.webapp.models.dto.ActionBookDTO;
import ru.miit.webapp.models.dto.AddReaderDTO;
import ru.miit.webapp.models.view.ShowReaderDetailInfoModelView;
import ru.miit.webapp.models.view.ShowReaderInfoModelView;
import ru.miit.webapp.services.BookService;
import ru.miit.webapp.services.ReaderService;

import java.util.List;

@RestController
@RequestMapping("readers")
@RequiredArgsConstructor
public class ReadersController {

    private final ReaderService readerService;

    @PostMapping()
    public void addReader(@RequestBody AddReaderDTO readerDTO) {
        readerService.addReader(readerDTO);
    }

    @GetMapping()
    public List<ShowReaderInfoModelView> showAllReaders() {
        return readerService.allReader();
    }

    @GetMapping("/{reader-name}")
    public ShowReaderDetailInfoModelView showReaderDetails(@PathVariable("reader-name") String name) {
        return readerService.readerDetails(name);
    }

    @DeleteMapping("/{reader-name}")
    public void deleteReader(@PathVariable("reader-name") String name) {
        readerService.deleteReader(name);
    }

    @PatchMapping("/{reader-name}/receive")
    public void receiveBook(@PathVariable("reader-name") String name, @RequestBody ActionBookDTO actionBookDTO) {
        readerService.receiveBook(name, actionBookDTO);
    }

    @PatchMapping("/{reader-name}/return")
    public void returnBook(@PathVariable("reader-name") String name, @RequestBody ActionBookDTO actionBookDTO) {
        readerService.returnBook(name, actionBookDTO);
    }
}
