package ru.manager.services.crypt;

/**
 * Интерфейс для шифрования пароля пользователя.
 */
public interface ISecurityService {

    String encrypt(String password);

    boolean isMatchPassword(String checkPassword, String originalPassword);

}
