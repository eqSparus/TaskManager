package ru.manager.dao;

import ru.manager.models.StatusTask;
import ru.manager.models.Task;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для реализации dao классов задач.
 */
public interface ITaskDao extends IDao<Task> {

    /**
     * Метод для получения всех задач пользователя.
     * @param userId идентификатор пользователя
     * @return все задачи пользователя
     */
    List<Task> findAllTasksByUserId(Long userId);

    /**
     * Метод для получения задачи по идентификатору.
     * @param id идентификатор задачи
     * @return найденую задачу
     */
    Optional<Task> findTaskById(Long id);

    /**
     * Метод для изменения статус задачи.
     * @param status статус задачи
     * @param id идентификатор задачи
     * @return обновленную задачу
     */
    Optional<Task> getUpdateTaskStatus(StatusTask status, Long id);

    /**
     * Метод для изменения замороженного времени.
     * @param frozenTime новое время для заморозки
     * @param id идентификатор задачи
     * @return обновленную задачу
     */
    Optional<Task> getUpdateFrozenTime(long frozenTime, Long id);

    /**
     * Метод для обновления времени завершения задачи.
     * @param completionAt новое время завершения задачи
     * @param id идентификатор задачи
     * @return обновленную задачу
     */
    Optional<Task> getUpdateActiveTime(long completionAt, Long id);
}
