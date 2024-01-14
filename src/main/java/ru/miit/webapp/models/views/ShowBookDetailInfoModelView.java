package ru.miit.webapp.models.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowBookDetailInfoModelView {
    private String id;
    private String nameAuthor;
    private String title;
    private int yearOfPublished;
    private String nameReader;
    private LocalDate receivedIn;
}
