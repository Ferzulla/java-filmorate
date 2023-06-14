package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.validation.ValidationFilm;


import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FilmServiceInt implements FilmService {
    private InMemoryFilmStorage filmStorage;
    ValidationFilm validationFilm = new ValidationFilm();

    @Override
    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    @Override
    public Film postFilm(Film film) {
        return filmStorage.postFilm(film);
    }

    @Override
    public Film putFilm(Film film) {
        return filmStorage.putFilm(film);
    }

    @Override
    public void addLikeFilm(int id, int userId) {
        Film film = filmStorage.getFilmsMap().get(id);
        film.addLike(userId);
        filmStorage.putFilm(film);
    }

    @Override
    public void deleteLikeFilm(int id, int userId) {
        Film film = filmStorage.getFilmsMap().get(id);
        film.deleteLike(userId);
        filmStorage.putFilm(film);
    }

    @Override
    public Film getFilm(Integer id) {
        validationFilm.searchValidation(filmStorage.films.get(id));
        return filmStorage.getFilmsMap().get(id);
    }

    @Override
    public List<Film> getListBestMovies(Integer count) {
        if (count == null) {
            return getListBestTenMovies();
        }
        log.info("count " + count);
        return filmStorage.films.
                values().
                stream().
                sorted().
                limit(count).
                collect(Collectors.toList());
    }

    @Override
    public List<Film> getListBestTenMovies() {
        return  filmStorage.films.
                values().
                stream().
                sorted().
                limit(10).
                collect(Collectors.toList());
    }

}

