package ru.miit.webapp.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ShowBookDetailInfoModelView {
    private String nameAuthor;
    private String title;
    private int yearOfPublished;
    private String nameReader;
    private LocalDate receivedIn;
}
