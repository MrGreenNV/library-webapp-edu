package ru.miit.webapp.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShowReaderInfoModelView {
    private String name;
    private String email;
    private LocalDate dateOfBirth;
}
