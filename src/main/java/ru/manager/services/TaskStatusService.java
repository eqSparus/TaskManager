package ru.manager.services;

import ru.manager.dao.ITaskDao;
import ru.manager.dao.TaskDaoImpl;
import ru.manager.models.StatusTask;
import ru.manager.models.dto.TaskDtoResponse;

import java.time.Instant;

public class TaskStatusService implements ITaskStatusService {

    private final ITaskDao taskDao;

    public TaskStatusService() {
        this.taskDao = new TaskDaoImpl();
    }

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
