package ru.manager.services.auth;

import ru.manager.dao.UserDao;
import ru.manager.dao.UserDaoImpl;
import ru.manager.models.User;
import ru.manager.models.dto.UserDto;
import ru.manager.services.crypt.SecurityService;
import ru.manager.services.validations.Validation;
import ru.manager.services.validations.ValidationLoginService;
import ru.manager.services.validations.ValidationPasswordService;

import java.util.Map;

/**
 * Сервис для регистрации и авторизации пользователя.
 */
public class AuthUserService implements AuthService {

    private final UserDao dao;
    private final SecurityService security;
    private final Validation<String> validPass;
    private final Validation<String> validLogin;

    public AuthUserService() {
        this.dao = new UserDaoImpl();
        this.security = new SecurityService();
        this.validLogin = new ValidationLoginService();
        this.validPass = new ValidationPasswordService();
    }

    /**
     * Метод для проверки данных пользователя и его регистрации.
     * @param dto данные пользователя.
     * @return ответа сервера.
     */
    @Override
    public Map<String, String> registrationUser(UserDto dto) {

        if (!validLogin.isValid(dto.getLogin()) && !validPass.isValid(dto.getPassword())) {
            return Map.of("message", "Wrong data entered!");
        }

        var user = dao.findUserByUsername(dto.getLogin());

        if (user.isEmpty()) {
            var newUser = new User(dto.getLogin(), security.encrypt(dto.getPassword()));
            dao.createUser(newUser);
            return Map.of("html", "http://localhost:8080/login");
        }
        return Map.of("message", "This user already exists!");
    }

    /**
     * Метод для авторизации пользователя и ответа сервера.
     * @param dto данные пользователя.
     * @return ответа сервера.
     */
    @Override
    public Map<String, String> authorizationUser(UserDto dto) {

        var user = dao.findUserByUsername(dto.getLogin());

        if (user.isPresent() && security.isMatchPassword(dto.getPassword(), user.get().getPassword())) {
            return Map.of("html", "http://localhost:8080/main");
        }
        return Map.of("message", "Incorrect login or password!");
    }

}
