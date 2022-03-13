package ru.manager.services.validations;

/**
 * Интерфейс для проверки данных.
 * @param <T> Класс который необходимо проверить.
 */
public interface Validation<T> {

    boolean isValid(T str);

}
