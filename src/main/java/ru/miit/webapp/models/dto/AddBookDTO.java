package ru.miit.webapp.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookDTO {
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 3, max = 255, message = "В имени должно быть от 3 до 255 символов.")
    private String nameAuthor;

    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 2, max = 255, message = "В названии книги должно быть от 2 до 255 символов.")
    private String title;

    @NotNull(message = "Поле не должно быть пустым.")
    @DateTimeFormat(pattern = "yyyy")
    private int yearOfPublished;
}
