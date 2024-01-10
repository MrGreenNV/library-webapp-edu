package ru.miit.webapp.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorRegistrationDTO {
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 3, max = 255, message = "В имени должно быть от 3 до 255 символов.")
    private String name;
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(max = 255, message = "Email не должен содержать больше 255 символов.")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+.+.[A-Za-z]{2,4}$", message = "Email должен быть валиден.")
    private String email;
    private String password;
}