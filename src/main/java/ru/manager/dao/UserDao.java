package ru.manager.dao;

import ru.manager.models.User;

import java.util.Optional;

/**
 * Интерфейс для реализации регистрации и авторизации пользователя.
 */
public interface UserDao {

    void createUser(User user);

    Optional<User> findUserByUsername(String username);

}
