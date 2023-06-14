package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.validation.ValidationFilm;


import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/films")
public class FilmController  {

    private final ValidationFilm validationFilm = new ValidationFilm();
    private final FilmService filmService;
   //tring partOfTheWay = "/films";


    @GetMapping
    public List<Film> getFilms() {
        log.info("Получение запроса");
        return filmService.getFilms();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Film getFilms(@PathVariable Integer id) {
        validationFilm.validationIdFilm(id);
        validationFilm.searchValidation(filmService.getFilm(id));
        return filmService.getFilm(id);
    }

    @PostMapping
    public Film postFilm(@RequestBody Film film) {
        validationFilm.validation(film);
        log.info("Создание фильма" + film);
        return filmService.postFilm(film);
    }

    @PutMapping
    public Film putFilm(@RequestBody Film film) {
        validationFilm.validation(film);
        return filmService.putFilm(film);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLikeFilm(@PathVariable int id, @PathVariable int userId) {
        filmService.addLikeFilm(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLikeFilm(@PathVariable int id, @PathVariable int userId) {
        validationFilm.validationIdFilm(id);
        validationFilm.validationIdFilm(userId);
        filmService.deleteLikeFilm(id, userId);
    }

    @GetMapping("/popular")
    @ResponseBody
    public List<Film> getListBestMovies(
            @RequestParam(required = false) Integer count) {
        return filmService.getListBestMovies(count);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> errorValidation(final ValidationException e) {
        return Map.of("Ошибка", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> noRequiredObject(final NullPointerException e) {
        return Map.of("Ошибка", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> internalServerError(final IndexOutOfBoundsException e) {
        return Map.of("Ошибка" , "внутренняя ошибка сервера");
    }
}
