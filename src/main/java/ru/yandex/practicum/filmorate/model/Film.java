package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Film {

     long id;

    @NotBlank
     String name;
    @Size(max = 200)
     String description;
    @NotNull
     LocalDate releaseDate;
    @Positive
     int duration;
    @NotNull
     Mpa mpa;
     final Set<Genre> genres = new HashSet<>();//список жанров
     final Set<Long> likes = new HashSet<>(); //айди юзеров, поставивших лайк фильму

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void createGenre(Genre genre) {
        genres.add(genre);
    }

    public List<Genre> getGenres() {
        return genres.stream()
                .sorted(Comparator.comparing(Genre::getId))
                .collect(Collectors.toList());
    }

    public Map<String, Object> filmValue() {
        Map<String, Object> values = new HashMap<>();
        values.put("film_name", name);
        values.put("film_description", description);
        values.put("release_date", releaseDate);
        values.put("duration", duration);
        values.put("mpa_id", mpa.getId());
        return values;
    }

}
