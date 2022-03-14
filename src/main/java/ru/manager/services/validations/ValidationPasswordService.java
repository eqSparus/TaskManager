package ru.manager.services.validations;

import java.util.regex.Pattern;

/**
 * Проверка пароля пользователя.
 */
public class ValidationPasswordService implements IValidation<String> {

    private static final Pattern regPassword = Pattern.compile("[a-zA-Z0-9]{8,50}");

    /**
     * Проверяет пароля пользователя на присутсвие букв или цифр и размер пароля.
     * @param str пароль пользователя.
     * @return true если проверка пройдена, false если проверка не пройдена.
     */
    @Override
    public boolean isValid(String str) {
        var mather = regPassword.matcher(str);
        return mather.matches();
    }

}
