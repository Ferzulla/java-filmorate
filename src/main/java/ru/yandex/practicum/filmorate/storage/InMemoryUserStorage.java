package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.ValidationUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    public    Map<Integer, User> users = new HashMap<>();
    private  int generatedId = 1;
    ValidationUser validationUser = new ValidationUser();

    @Override
    public List<User> getUsers() {
        List list = new ArrayList(users.values());
        log.info("Возвращает пользователей" + list);
        return list;
    }
    @Override
    public User postUser (User user) {
        validationUser.validation(user);
        user.setId(generatedId);
        users.put(generatedId, user);
        generatedId++;
        log.info("Запрос на добавление пользователя с Id = " + user.getId());
        return user;
    }

    @Override
    public User putUser (User user) {
        validationUser.validation(user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("Запрос на обновление данных пользователя с Id = " + user.getId());
        } else {
            throw new NullPointerException("Id = " + user.getId() + " не найдено");
        }
        return user;
    }

    @Override
    public Map<Integer, User> getUsersMap() {
        return users;
    }

}
