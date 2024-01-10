package ru.miit.webapp.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionBookDTO {
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 2, max = 255, message = "В названии книги должно быть от 2 до 255 символов.")
    private String title;
}
