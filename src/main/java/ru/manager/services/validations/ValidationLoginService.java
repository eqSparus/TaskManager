package ru.manager.services.validations;

import java.util.regex.Pattern;

/**
 * Проверка логина пользователя.
 */
public class ValidationLoginService implements Validation<String> {

    private static final Pattern regLogin = Pattern.compile("\\w{1,50}");

    /**
     * Проверяет логин пользователя на размер строки.
     * @param str логин пользователя.
     * @return true если проверка пройдена, false если проверка не пройдена.
     */
    @Override
    public boolean isValid(String str) {
        var mather = regLogin.matcher(str);
        return mather.matches();
    }
}
