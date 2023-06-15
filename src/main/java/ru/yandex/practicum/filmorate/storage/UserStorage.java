package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {
    public List<User> getUsers();
    public User postUser(User user);
    public User putUser(User user);
    public Map<Integer, User> getUsersMap();

}
