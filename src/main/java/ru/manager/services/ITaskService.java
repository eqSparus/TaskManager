package ru.manager.services;

import ru.manager.models.dto.TaskDtoRequest;
import ru.manager.models.dto.TaskDtoResponse;

public interface ITaskService {

    TaskDtoResponse createTask(TaskDtoRequest taskDto, String login);



}
