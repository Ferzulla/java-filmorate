package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceInt implements UserService {

    public InMemoryUserStorage inMemoryUserStorage;

    @Override
    public List<User> getUsers() {
        return inMemoryUserStorage.getUsers();
    }

    @Override
    public User getUser(int id) {
        return inMemoryUserStorage.getUsersMap().get(id);
    }
    @Override
    public User postUser (User user) {
        return inMemoryUserStorage.postUser(user);
    }

    @Override
    public User putUser (User user) {
        return inMemoryUserStorage.putUser(user);
    }

    @Override
    public void addFriends(int userId, int friendId) {
        log.info("Добавляем в список друзей userId - " + userId + "друга с ID " + friendId);
        User user = inMemoryUserStorage.users.get(userId);
        User friendsUser = inMemoryUserStorage.users.get(friendId);
        user.addListFriend(friendId);
        friendsUser.addListFriend(userId);
        inMemoryUserStorage.putUser(user);
        inMemoryUserStorage.putUser(friendsUser);
}

    @Override
    public void deleteFriends(int userId, int friendId) {
       log.info("Удаляем из списка друзей userId - " + userId + "друга с ID " + friendId);
        User user = inMemoryUserStorage.users.get(userId);
        User friendsUser = inMemoryUserStorage.users.get(friendId);
        user.deleteFriends(friendId);
        friendsUser.deleteFriends(userId);
        inMemoryUserStorage.putUser(user);
        inMemoryUserStorage.putUser(friendsUser);
}

    @Override
    public List<User> getUserFriend(int userId) {
        log.info("Получаем список друзей userId - " + userId);
        User user = inMemoryUserStorage.users.get(userId);
        List<User> friend = new ArrayList<>();
        for (Integer id : user.getListFriends()) {
            friend.add(inMemoryUserStorage.users.get(id));
        }
        return friend;
    }

    @Override
    public List<User> getListMutualFriend(int userId, int otherId) {
        User user = inMemoryUserStorage.users.get(userId);
        User user1 = inMemoryUserStorage.users.get(otherId);
        List<Integer> friends = new ArrayList<>(user.getListFriends());
        List<Integer> friends1 = new ArrayList<>(user1.getListFriends());
        List<User> mutualListFriends = new ArrayList<>();
        for (int i = 0; i < friends1.size(); i++) {
            for (int j = 0; j < friends.size(); j++) {
                if (friends1.get(i) == friends.get(j)) {
                    mutualListFriends.add(inMemoryUserStorage.users.get(friends1.get(i)));
                    break;
                }
            }
        }
        return mutualListFriends;
    }
}
