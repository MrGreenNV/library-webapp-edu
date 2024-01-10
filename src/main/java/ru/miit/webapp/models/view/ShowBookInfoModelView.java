package ru.miit.webapp.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShowBookInfoModelView {
    private String nameAuthor;
    private String title;
    private int yearOfPublished;
}
