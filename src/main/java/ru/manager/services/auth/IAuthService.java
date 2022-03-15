package ru.manager.services.auth;

import ru.manager.models.dto.UserDtoRequest;

/**
 * Интерфейс сервиса для реализции регистрации и авторизации пользователя.
 */
public interface IAuthService {

    /**
     * Метод для проверки данных пользователя и его регистрации.
     * @param dto данные пользователя.
     * @return true если пользователь добавлен, false если пользователь
     * не прошел проверку
     */
    boolean registrationUser(UserDtoRequest dto);

    /**
     * Метод для авторизации пользователя и ответа сервера.
     * @param dto данные пользователя.
     * @return true если пользователь авторизован, false если
     * пользователь не авторизован
     */
    boolean authorizationUser(UserDtoRequest dto);
}
