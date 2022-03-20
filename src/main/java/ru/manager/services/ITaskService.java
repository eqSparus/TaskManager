package ru.manager.services;

import ru.manager.models.dto.TaskDtoRequest;
import ru.manager.models.dto.TaskDtoResponse;

import java.util.List;

/**
 * Интерфейс для реализации crud сервиса задач.
 */
public interface ITaskService {

    /**
     * Метод для создания задачи пользователя.
     * @param taskDto тело запроса на сервер.
     * @param login имя пользователя.
     * @return возвращает тела ответа сервера.
     */
    TaskDtoResponse createTask(TaskDtoRequest taskDto, String login);

    /**
     * Метод для получения всех задача пользователя.
     * @param login имя пользователя.
     * @return все задачи.
     */
    List<TaskDtoResponse> getAllTasksByUserId(String login);

    /**
     * Метод для удаления задачи.
     * @param id идентификатор задачи.
     */
    void deleteTaskById(Long id);

    /**
     * Метод для обновления названия и описания задачи.
     * @param dtoRequest тело запроса на сервер.
     * @param id идентификатор задачи.
     * @return возвращает тела ответа сервера.
     */
    TaskDtoResponse updateTitleAndDescriptionTaskById(TaskDtoRequest dtoRequest, Long id);
}
