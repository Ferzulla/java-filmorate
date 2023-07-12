package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Genre {
     int id;
    @NotBlank
     String name;
}
