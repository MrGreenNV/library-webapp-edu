package ru.miit.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private Author author;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "year_of_published", nullable = false, unique = true)
    private int yearOfPublished;
    @ManyToOne
    @JsonIgnore
    private Reader abonent;
    @Column(name = "received_in")
    @Temporal(TemporalType.DATE)
    private LocalDate receivedIn;
}