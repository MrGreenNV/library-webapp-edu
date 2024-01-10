package ru.miit.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author extends Person {
    @OneToMany(mappedBy = "author")
    private Set<Book> writtenBooks;
}
