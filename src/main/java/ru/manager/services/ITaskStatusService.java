package ru.manager.services;

import ru.manager.models.dto.TaskDtoResponse;

public interface ITaskStatusService {

    TaskDtoResponse updateTaskFrozen(Long id);

    TaskDtoResponse updateTaskDone(Long id);

    TaskDtoResponse updateTaskActive(Long id);
}
