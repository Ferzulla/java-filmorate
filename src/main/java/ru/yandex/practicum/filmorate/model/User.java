package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)   //делает все поля приватными
public class User {

     int id ;
     String email;
     String login;
     String name;

     LocalDate birthday;
    private Set<Integer> listFriends = new HashSet<>();

    public User(int id, String email, String login, String name, LocalDate birthday, Set<Integer> listFriends) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
        this.listFriends = listFriends;
    }



    public Set<Integer> getFriends() {
        return listFriends;
    }

    public void addListFriend(int id) {
        listFriends.add(id);
    }
    public void deleteFriends(int id) {
        listFriends.remove(id);
    }
}
