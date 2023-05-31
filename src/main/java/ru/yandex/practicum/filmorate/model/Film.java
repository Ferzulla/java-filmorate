package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)   //делает все поля приватными
public class Film {
    int id;
   @NonNull
    String name;
   @NonNull
    String description;
   @NonNull
    LocalDate releaseDate;
   @NonNull
    int duration;

   public Film(@NonNull String name, @NonNull String description,
               @NonNull LocalDate releaseDate, @NonNull int duration) {
      this.name = name;
      this.description = description;
      this.releaseDate = releaseDate;
      this.duration = duration;
   }
}
