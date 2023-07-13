package ru.yandex.practicum.filmorate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private static final String USERS_PATH = "/users";

    @Autowired
    public UserController(@Qualifier("UserDbService") UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody  User user) throws JsonProcessingException { //добавление пользователя
        log.info(String.format("POST request received: {} %s, %s ", USERS_PATH, user));
        return userService.addUser(user);
    }

    @PutMapping
    public User update(@RequestBody  User user) {  //обновление пользователя
        log.info(String.format("Получен PUT запрос {} %s, %s ", USERS_PATH, user));
        return userService.updateUser(user);
    }

    @GetMapping
    public Collection<User> usersList() { //список всех пользователей
        log.info(String.format("Получен GET запрос %s ", USERS_PATH));
        log.info("Получен GET запрос /users");
        return userService.usersList();
    }

    @PutMapping("/{id}/friends/{friendId}") //добавление в друзья
    public Collection<Long> addFriend(@PathVariable("id") long userId, @PathVariable long friendId) {
        log.info(String.format("Получен PUT запрос {} : %s/{id}/friends/{friendId}. %s ", USERS_PATH, friendId));
        return userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}") //удаление из друзей
    public void deleteFriend(@PathVariable("id") long userId, @PathVariable long friendId) {
        log.info(String.format("Получен DELETE запрос {}: %s/{id}/friends/{friendId}. %s ", USERS_PATH, friendId));
        userService.deleteFriend(userId, friendId);
    }

    @GetMapping("/{id}")  //получение пользователя по идентификатору
    public User getOneUser(@PathVariable long id) {
        log.info(String.format("Получен GET запрос {} : %s/{id}. %s", USERS_PATH, id));
        return userService.getOneUser(id);
    }

    @GetMapping("/{id}/friends") //вывести список всех друзей одного пользователя
    public Collection<User> getAllUserFriends(@PathVariable long id) {
        log.info(String.format("Получен GET запрос {} : %s/{id}/friends. %s", USERS_PATH, id));
        return userService.getAllUserFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> listOfMutualFriends(@PathVariable long id, @PathVariable long otherId) {
        log.info(String.format("Получен GET запрос {} : %s/{id}/friends/common/{otherId}. %s", USERS_PATH, otherId));
        return userService.listOfMutualFriends(id, otherId);

    }
}
