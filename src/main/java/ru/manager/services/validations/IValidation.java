package ru.manager.services.validations;

/**
 * Интерфейс для проверки данных.
 * @param <T> Класс который необходимо проверить.
 */
public interface IValidation<T> {

    /**
     * Проверяет правильность данных
     * @param t объект для проверка.
     * @return true если проверка пройдена, false если проверка не пройдена.
     */
    boolean isValid(T t);

}
