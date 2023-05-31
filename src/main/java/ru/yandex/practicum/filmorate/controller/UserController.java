package ru.yandex.practicum.filmorate.controller;

import jdk.jfr.Registered;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.ValidationUsers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
@Registered
@RequestMapping("/users")
@RestController
public class UserController {
    private  final Map<Integer, User> users = new HashMap<> ();
    private  int generatedId = 1;

    ValidationUsers validationUsers = new ValidationUsers();
    @PostMapping
    public User postUser (@RequestBody User user) {
        log.info("Запрос на добавление пользователя");
        validationUsers.validationUser(user);
        user.setId(generatedId);
        users.put(generatedId, user);
        generatedId++;
        return user;
    }

    @PutMapping
    public User putUser (@RequestBody User user) {
        log.info("Запрос на ,обновление данных пользователя");
        validationUsers.validationUser(user);
        validationUsers.validationUserId(user, users);
        int id = user.getId();
        if (user.getId() != 0 && (users.containsKey(user.getId()))) {
            users.put(id, user);

        }
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Запрос на получение списка пользователей");
        return new ArrayList<>(users.values());
    }

}
