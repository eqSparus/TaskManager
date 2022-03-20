package ru.manager.services;

import ru.manager.models.dto.TaskDtoResponse;

/**
 * Интерфейс для изменения статусов задач.
 */
public interface ITaskStatusService {

    /**
     * Метод для заморозки задачи.
     * @param id идентификатор задачи.
     * @return тело ответа сервера.
     */
    TaskDtoResponse updateTaskFrozen(Long id);

    /**
     * Метод для завершения задачи.
     * @param id идентификатор задачи.
     * @return тело ответа сервера.
     */
    TaskDtoResponse updateTaskDone(Long id);

    /**
     * Метод для активации задачи.
     * @param id идентификатор задачи.
     * @return тело ответа сервера.
     */
    TaskDtoResponse updateTaskActive(Long id);
}
