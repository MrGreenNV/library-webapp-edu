package ru.miit.webapp.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User author;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "year_of_published", nullable = false)
    private int yearOfPublished;
    @ManyToOne
    @JsonIgnore
    private User abonent;
    @Column(name = "received_in")
    @Temporal(TemporalType.DATE)
    private LocalDate receivedIn;
}
