/*package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {

    public List<Film> getFilms();
    public Film postFilm(Film film);
    public Film putFilm(Film film);
    public void addLikeFilm (int id , int userId);
    public void deleteLikeFilm (int id , int userId);
    public Film getFilm(Integer id);
    List<Film> getListBestMovies(Integer count);
    List<Film> getListBestTenMovies();

}

 */
package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;

public interface FilmService {
    Film createFilm(Film film);

    Film updateFilm(Film film);

    List<Film> filmList();

    Film getOneFilm(long id);

    Collection<Long> addLike(long filmId, long userId);

    Collection<Long> deleteLike(long filmId, long userId);

    Collection<Film> listPopularFilms(int count);
}
