package ru.miit.webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "readers")
public class Reader extends Person {
    @OneToMany(mappedBy = "abonent")
    private Set<Book> rentedBooks;
    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
}
