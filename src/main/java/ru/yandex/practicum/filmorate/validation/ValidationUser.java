package ru.yandex.practicum.filmorate.validation;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Component
public class ValidationUser {

    public void validation (User user) throws ValidationException {
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
            throw new ValidationException("Неправильно введенная почта");
        } else if (validLogin) {
            throw new ValidationException("Неправильно введен логин");
        } else if (user.getLogin().length() == 0) {
            throw new ValidationException("Логин не может быть пустым");
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
        } else if (user.getName().length() == 0) {
            user.setName(user.getLogin());
        }
        LocalDate localDateNow = LocalDate.now();

        if (localDateNow.isBefore(user.getBirthday())) {
            throw new ValidationException("Дата рождения не может быть больше текущей даты");
        }
    }

    public void validationAddFriend(int id, int idFriend) {
        if (id == idFriend) {
            throw new ValidationException("Не удается добавить или удалить себя из списка друзей");
        } else if (id < 1 || idFriend  < 1) {
            throw new NullPointerException("Идентификатор " + id + " не может быть отрицательным");
        }
    }

    public void searchValidation(User user) {
        if (user == null) {
            throw new NullPointerException("Объект не найден");
        }
    }

    public void checkId(int id) {
        if (id < 1) {
            throw new NullPointerException("Идентификатор" + id + " не может быть отрицательным");
        }
    }
}
