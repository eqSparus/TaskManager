package ru.manager.services;

import ru.manager.models.dto.TaskDtoRequest;
import ru.manager.models.dto.TaskDtoResponse;

import java.util.List;

public interface ITaskService {

    TaskDtoResponse createTask(TaskDtoRequest taskDto, String login);

    List<TaskDtoResponse> getAllTasksByUserId(String login);

    void deleteTaskById(Long id);

    TaskDtoResponse updateTitleAndDescriptionTaskById(TaskDtoRequest dtoRequest, Long id);
}
