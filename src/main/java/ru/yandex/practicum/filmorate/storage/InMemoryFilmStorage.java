package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.ValidationFilm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    public   Map<Integer, Film> films = new HashMap<>();
    private  int generatedId = 1;
    ValidationFilm validationFilm = new ValidationFilm();

    @Override
    public List<Film> getFilms() {
        log.info("Получение списка фильмов");
        List<Film> list = new ArrayList<>(films.values());
        return list;
    }

    @Override
    public Film postFilm (Film film) {
        validationFilm.validation(film);
        film.setId(generatedId);
        films.put(generatedId, film);
        generatedId++;
        log.info(String.format("Запрос на добавление фильма с Id = %s", film.getId()));
        return film;
    }


    @Override
    public Film putFilm (@RequestBody Film film) {
        validationFilm.validation(film);
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
        } else {
            throw new NullPointerException(String.format("Id = %s не найдено", film.getId()));
        }
        return film;
    }

    @Override
    public Map<Integer, Film> getFilmsMap() {
        return films;
    }

}
