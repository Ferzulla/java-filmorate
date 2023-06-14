package ru.yandex.practicum.filmorate.validation;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Map;
@Component
public class ValidationFilm {

    public void validation (Film film) {
        if (film.getName() == null || film.getName().isBlank() || film.getName().length() == 0) {
            throw new ValidationException("Имя пользователя не может быть пустым");
        }

        if (film.getDescription().length() > 200) {
            throw new ValidationException("Описание фильма не может быть больше 200 символов");
        }

        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата релиза фильма не может быть раньше чем" +
                    LocalDate.of(1895, 12, 28));
        }

        if (film.getDuration() <= 0 ) {
            throw new ValidationException("Продолжительность фильма не может быть " +
                    "отрицательным или нулевым значением.");
        }
    }

    public void validationIdFilm(int id) {
        if (id < 1) {
            throw new NullPointerException("Идентификатор не может быть отрицательным");
        }
    }

    public void searchValidation(Film film) {
        if (film == null) {
            throw new NullPointerException("Объект не найден по указанному идентификатору");
        }
    }
}
