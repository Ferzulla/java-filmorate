package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Friendship {
    @NotNull
     long userId;
    @NotNull
     long friendId;
     boolean status;
}
