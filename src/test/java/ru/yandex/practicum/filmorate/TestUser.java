package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {

    public UserControllerOld userController = new UserControllerOld();


    @Test
    void createAnObjectTest() {
        User user = new User("andrey@gmail.com", "Login", "Name",
                LocalDate.of(2010, 10, 02));
        userController.postUser(user);
        assertEquals(1, userController.getAllUsers().size(),
                "Тест на создание  добавление фильма в список не пройден");
    }

    @Test
    void UpdateTest() {

        User userUpdate = new User(1,"ivan@gmail.com", "ivan", "NameIvan",
                LocalDate.of(1975, 11, 28));
        User user1 = new User("vany@gmail.com", "LoginVany", "NameVany",
                LocalDate.of(1995, 5, 19));
        User user2 = new User("andrey@gmail.com", "Login", "Name",
                LocalDate.of(2010, 10, 02));

        userController.postUser(user1);
        userController.postUser(user2);
        userController.putUser(userUpdate);

        assertEquals("NameIvan", userController.getUsers().get(1).getName(),
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
        assertEquals(3, userController.getAllUsers().size(),
                "Проверка на получение списка фильмов не пройдена");
    }
}
