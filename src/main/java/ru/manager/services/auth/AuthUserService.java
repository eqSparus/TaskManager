package ru.manager.services.auth;

import ru.manager.dao.IUserDao;
import ru.manager.dao.UserDaoImpl;
import ru.manager.models.User;
import ru.manager.models.dto.UserDtoRequest;
import ru.manager.services.crypt.SecurityService;
import ru.manager.services.validations.IValidation;
import ru.manager.services.validations.ValidationLoginService;
import ru.manager.services.validations.ValidationPasswordService;

/**
 * Сервис для регистрации и авторизации пользователя.
 */
public class AuthUserService implements IAuthService {

    private final IUserDao dao;
    private final SecurityService security;
    private final IValidation<String> validPass;
    private final IValidation<String> validLogin;

    public AuthUserService() {
        this.dao = new UserDaoImpl();
        this.security = new SecurityService();
        this.validLogin = new ValidationLoginService();
        this.validPass = new ValidationPasswordService();
    }

    /**
     * Метод для проверки данных пользователя и его регистрации.
     * @param dto данные пользователя.
     * @return true если пользователь добавлен, false если пользователь
     * не прошел проверку
     */
    @Override
    public boolean registrationUser(UserDtoRequest dto) {

        if (!validLogin.isValid(dto.getLogin()) && !validPass.isValid(dto.getPassword())) {
            return false;
        }

        var user = dao.findUserByLogin(dto.getLogin());

        if (user.isEmpty()) {
            var newUser = new User(dto.getLogin(), security.encrypt(dto.getPassword()));
            dao.createUser(newUser);
            return true;
        }
        return false;
    }

    /**
     * Метод для авторизации пользователя и ответа сервера.
     * @param dto данные пользователя.
     * @return true если пользователь авторизован, false если
     * пользователь не авторизован
     */
    @Override
    public boolean authorizationUser(UserDtoRequest dto) {
        var user = dao.findUserByLogin(dto.getLogin());
        return user.isPresent() && security.isMatchPassword(dto.getPassword(), user.get().getPassword());
    }

}
