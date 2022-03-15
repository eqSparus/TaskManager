package ru.manager.dao;

import ru.manager.models.User;

import java.util.Optional;

/**
 * Интерфейс для реализации регистрации и авторизации пользователя.
 */
public interface IUserDao {

    /**
     * Метод создает нового пользователя в БД.
     * @param user пользователь который должен быть добавлен.
     */
    void createUser(User user);

    /**
     * Метод возвращает из БД пользователя по имени или пустой объект .
     * @see java.util.Optional
     * @param username имя пользователя.
     * @return пользователь обернутый в Optional.
     */
    Optional<User> findUserByLogin(String username);

}
