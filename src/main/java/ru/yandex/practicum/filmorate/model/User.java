package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
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

    public User( String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User(int id, String email, String login, String name,  LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User( String email, String name, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public User(int id, String email, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
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
