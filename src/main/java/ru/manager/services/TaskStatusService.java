package ru.manager.services;

import ru.manager.dao.ITaskDao;
import ru.manager.dao.TaskDaoImpl;
import ru.manager.models.StatusTask;
import ru.manager.models.dto.TaskDtoResponse;

import java.time.Instant;

/**
 * Сервис для изменения статусов задачь
 */
public class TaskStatusService implements ITaskStatusService {

    private final ITaskDao taskDao;

    public TaskStatusService() {
        this.taskDao = new TaskDaoImpl();
    }

    /**
     * Метод для заморозки задачи. Оставшееся время до конца задачи записывается поле frozen_day.
     * @param id идентификатор задачи.
     * @return тело ответа сервера.
     */
    @Override
    public TaskDtoResponse updateTaskFrozen(Long id) {

        var task = taskDao.getUpdateTaskStatus(StatusTask.FROZEN, id)
                .orElseThrow(IllegalArgumentException::new);

        var timeUpdateAt = task.getCompletionAt().toEpochMilli() - Instant.now().toEpochMilli();

        var updateTask =
                taskDao.getUpdateFrozenTime(timeUpdateAt, id).orElseThrow(IllegalArgumentException::new);

        return TaskDtoResponse.builder()
                .id(updateTask.getId())
                .title(updateTask.getTitle())
                .description(updateTask.getDescription())
                .createAt(updateTask.getCreatedAt().toEpochMilli())
                .completionAt(updateTask.getCompletionAt().toEpochMilli())
                .status(updateTask.getStatus())
                .build();
    }

    /**
     * Метод для завершения задачи.
     * @param id идентификатор задачи.
     * @return тело ответа сервера.
     */
    @Override
    public TaskDtoResponse updateTaskDone(Long id) {
        var updateTask = taskDao.getUpdateTaskStatus(StatusTask.DONE, id)
                .orElseThrow(IllegalArgumentException::new);

        return TaskDtoResponse.builder()
                .id(updateTask.getId())
                .title(updateTask.getTitle())
                .description(updateTask.getDescription())
                .createAt(updateTask.getCreatedAt().toEpochMilli())
                .completionAt(updateTask.getCompletionAt().toEpochMilli())
                .status(updateTask.getStatus())
                .build();
    }

    /**
     * Метод для активации задачи. Время из поля frozen_day прибавляется к дате активации.
     * @param id идентификатор задачи.
     * @return тело ответа сервера.
     */
    @Override
    public TaskDtoResponse updateTaskActive(Long id) {

        var task = taskDao.getUpdateTaskStatus(StatusTask.ACTIVE, id)
                .orElseThrow(IllegalArgumentException::new);

        var time = Instant.now().toEpochMilli() + task.getFrozenDay().toEpochMilli();

        var updateTask = taskDao.getUpdateActiveTime(time, id).orElseThrow(IllegalArgumentException::new);

        return TaskDtoResponse.builder()
                .id(updateTask.getId())
                .title(updateTask.getTitle())
                .description(updateTask.getDescription())
                .createAt(updateTask.getCreatedAt().toEpochMilli())
                .completionAt(updateTask.getCompletionAt().toEpochMilli())
                .status(updateTask.getStatus())
                .build();
    }

}
