package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.LocalDate;



@Data
@NoArgsConstructor
public class User {

    private int id ;
    @NonNull
    private String email;
    @NonNull
    private String login;
    private String name;
    @NonNull
    private LocalDate birthday;

    public User(@NonNull String email, @NonNull String login, String name, @NonNull LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User(int id, @NonNull String email, @NonNull String login, String name, @NonNull LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User(@NonNull String email, String name, @NonNull LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public User(int id, @NonNull String email, String name, @NonNull LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }
}
