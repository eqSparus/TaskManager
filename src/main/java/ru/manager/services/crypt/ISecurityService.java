package ru.manager.services;

/**
 * Интерфейс для шифрования пароля пользователя.
 */
public interface ISecurityService {

    /**
     * Метод шифрует пароль пользователя.
     * @param password не шифрованый пароль.
     * @return шифрованый пароль.
     */
    String encrypt(String password);

    /**
     * Метод для проверки пароля при авторизации.
     * @param checkPassword присланый пароль.
     * @param originalPassword оригинальный пароль.
     * @return true если пароли совпадают, false если пароли не совпадают.
     */
    boolean isMatchPassword(String checkPassword, String originalPassword);

}
