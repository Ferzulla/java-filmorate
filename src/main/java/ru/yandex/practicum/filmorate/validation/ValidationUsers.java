package ru.yandex.practicum.filmorate.validation;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Map;

public class ValidationUsers {

    public void validationUser (User user) throws ValidationException {
        char[] mail = user.getEmail().toCharArray();
        char[] login = user.getLogin().toCharArray();
        boolean validMail = false;
        boolean validLogin = false;
        for (char c : mail) {
            if (c == '@') {
                validMail = true;
                break;
            }
        }
        for (char c : login) {
            if (c == ' ') {
                validLogin = true;
                break;
            }
        }
        if (!validMail | user.getEmail().length() == 0) {
            throw new ValidationException("Mail entered incorrectly");
        } else if (validLogin) {
            throw new ValidationException("Login entered incorrectly");
        } else if (user.getLogin().length() == 0) {
            throw new ValidationException("Login cannot be empty");
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
        } else if (user.getName().length() == 0) {
            user.setName(user.getLogin());
        }
        LocalDate localDateNow = LocalDate.now();

        if (localDateNow.isBefore(user.getBirthday())) {
            throw new ValidationException("Date of birth cannot be greater than the current date");
        }
    }

    public void validationUserId(User user, Map<Integer, User> users) {
        if (!users.containsKey(user.getId())) {
            throw new ValidationException(String.format("Пользователь с Id - %s - не найден в базе", user.getId()));
        }
    }
}
