package ru.yandex.practicum.filmorate;


import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

public class TestFilm {


    public FilmController filmController = new FilmController();


    @Test
    void createAnObjectTest() {
        Film film = new Film("Восстание мертвецов (2010)",
                "Братья-японцы пытаются выжить в мире, который заполонили зомби.",
                LocalDate.of(2010, 10, 02), 83);
        filmController.postFilm(film);
        assertEquals(1, filmController.getAllFilm().size(),
                "Тест на создание  добавление фильма в список не пройден");
    }

    @Test
    void UpdateTest() {

        Film filmUpdate = new Film(2,"Мегалодон (2023)",
                "Пол и его семья собираются провести отпуск в райском местечке, но что то пошло не так",
                LocalDate.of(2023, 6, 15), 83);
        Film film1 = new Film("Восстание мертвецов (2010)",
                "Братья-японцы пытаются выжить в мире, который заполонили зомби.",
                LocalDate.of(2010, 10, 2), 142);
        Film film2 = new Film("Вызов (2023)",
                "Фильм про хирурга Женю",
                LocalDate.of(2023, 4, 20), 164);

        filmController.postFilm(film1);
        filmController.postFilm(film2);
        filmController.putFilm(filmUpdate);

        assertEquals("Мегалодон (2023)", filmController.getFilms().get(2).getName(),
                "Проверка на обновление фильма не пройдена");
    }

    void getAllFilmTest() {
        Film film = new Film("Мегалодон (2023)",
                "Пол и его семья собираются провести отпуск в райском местечке, но что то пошло не так",
                LocalDate.of(2023, 6, 15), 83);
        Film film1 = new Film("Восстание мертвецов (2010)",
                "Братья-японцы пытаются выжить в мире, который заполонили зомби.",
                LocalDate.of(2010, 10, 2), 142);
        Film film2 = new Film("Вызов (2023)",
                "Фильм про хирурга Женю",
                LocalDate.of(2023, 4, 20), 164);
        assertEquals(3, filmController.getAllFilm().size(),
                "Проверка на получение списка фильмов не пройдена");
    }
}
