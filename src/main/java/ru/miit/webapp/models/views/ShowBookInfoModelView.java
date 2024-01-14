package ru.miit.webapp.models.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowBookInfoModelView {
    private String id;
    private String nameAuthor;
    private String title;
    private int yearOfPublished;
}
