package ru.manager.models;

import java.util.Locale;

/**
 * Статусы задач.
 */
public enum StatusTask {

    ACTIVE, FROZEN, FAILED, DONE;

    /**
     * Метод для конвертации строкового статуса задачи в перечисления StatusTask
     * @param status строка статуса задачи
     * @return перечисления StatusTask
     */
    public static StatusTask convert(String status){
        return StatusTask.valueOf(status.toUpperCase(Locale.ROOT));
    }
}
