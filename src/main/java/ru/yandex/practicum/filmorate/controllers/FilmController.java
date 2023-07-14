package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    private static final String FILMS_PATH = "/films";

    @Autowired
    public FilmController(@Qualifier(value = "FilmDbService") FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film addFilm( @RequestBody Film film) {
        log.info(String.format("Получен POST {} запрос %s. %s ", FILMS_PATH, film));
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film putFilm(@RequestBody Film film) {
        log.info(String.format("Получен PUT {} запрос %s. %s ", FILMS_PATH, film));
        return filmService.updateFilm(film);
    }

    @PutMapping("/{id}/like/{userId}")
    public Collection<Long> addLike(@PathVariable("id") long filmId, @PathVariable long userId) {
        log.info(String.format("Получен PUT {} запрос: %s/{id}/like/{userId}. %s", FILMS_PATH, userId));
        return filmService.addLike(filmId, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Collection<Long> removeLike(@PathVariable("id") long filmId, @PathVariable long userId) {
        log.info(String.format("Получен DELETE {} запрос: %s/{id}/like/{userId}. %s", FILMS_PATH, userId));
        return filmService.deleteLike(filmId, userId);
    }

    @GetMapping
    public Collection<Film> filmsList() {
        log.info(String.format("Получен GET запрос %s/films ", FILMS_PATH));
        return filmService.filmList();
    }

    @GetMapping("/popular")
    public Collection<Film> listPopularFilms(@RequestParam(defaultValue = "10") int count) {
        log.info(String.format("Получен GET {} запрос: %s/popular.  %s", FILMS_PATH, count));
        return filmService.listPopularFilms(count);
    }

    @GetMapping("/{id}")
    public Film getOneFilm(@PathVariable long id) {
        log.info(String.format("Получен GET {} запрос: %s/{id}. %s", FILMS_PATH, id));
        return filmService.getOneFilm(id);
    }
}
