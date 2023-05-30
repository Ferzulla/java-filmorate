package ru.yandex.practicum.filmorate.controller;

import jdk.jfr.Registered;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.ValidationFilm;

import java.util.*;

@Slf4j
@Data
@Registered
@RequestMapping("/films")
@RestController
public class FilmController {
    private  final Map<Integer, Film> films = new HashMap<>();
    private  int generatedId = 1;

    ValidationFilm validationFilm = new ValidationFilm();

    @PostMapping
    public Film postFilm (@RequestBody Film film) {
        log.info("Запрос на добавление фильма");
        validationFilm.validationFilm(film);
        film.setId(generatedId);
        films.put(generatedId, film);
        generatedId++;
        return film;
    }

    @PutMapping
    public Film putFilm (@RequestBody Film film) {
        log.info("Запрос на ,обновление фильма");
        validationFilm.validationFilm(film);
        validationFilm.validationFilmId(film, films);
        if (film.getId() != 0 && (films.containsKey(film.getId()))) {
           films.put(film.getId(), film);
        }
        return film;
    }

    @GetMapping
    public List<Film> getAllFilm() {
        log.info("Запрос на получение списка фильмов");
        return new ArrayList<>(films.values());
    }
}
