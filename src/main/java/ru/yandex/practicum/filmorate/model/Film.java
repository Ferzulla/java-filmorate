package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)   //делает все поля приватными
public class Film implements Comparable<Film> {
    int id;
    String name;
    String description;
    LocalDate releaseDate;
    int duration;
    private Set<Integer> usersLikeMovie = new HashSet<>();
    private int likes = 0;

    public int getLikes() {
        return likes;
    }

    public Film( String name,  String description,
                 LocalDate releaseDate, int duration) {
      this.name = name;
      this.description = description;
      this.releaseDate = releaseDate;
      this.duration = duration;
   }

    public void setUsersLikeMovie(Set<Integer> usersLikeMovie) {
        this.setLikes(usersLikeMovie.size());
        this.usersLikeMovie = usersLikeMovie;
    }

    public void addLike(int id) {
        usersLikeMovie.add(id);
        setLikes(getLikes() + 1);
    }
    public void deleteLike(int id) {
        usersLikeMovie.remove(id);
        setLikes(getLikes() - 1);
    }

    @Override
    public int compareTo(Film o) {
        return Integer.compare(o.getLikes(), this.likes);
    }
}
