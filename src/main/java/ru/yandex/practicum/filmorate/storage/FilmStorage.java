package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface FilmStorage {

     public List<Film> getFilms();
     public Film postFilm(Film film);
     public Film putFilm(Film film);
     public Map<Integer, Film> getFilmsMap();

}
