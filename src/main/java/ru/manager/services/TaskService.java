package ru.manager.services;

import ru.manager.dao.ITaskDao;
import ru.manager.dao.IUserDao;
import ru.manager.dao.TaskDaoImpl;
import ru.manager.dao.UserDaoImpl;
import ru.manager.models.StatusTask;
import ru.manager.models.Task;
import ru.manager.models.dto.TaskDtoRequest;
import ru.manager.models.dto.TaskDtoResponse;
import ru.manager.services.utilities.TaskStatusService;

import java.time.Instant;
import java.util.List;

public class TaskService implements ITaskService {

    private final IUserDao userDao;
    private final ITaskDao taskDao;
    private final TaskStatusService statusService;

    public TaskService() {
        this.userDao = new UserDaoImpl();
        this.taskDao = new TaskDaoImpl();
        this.statusService = new TaskStatusService();
    }

    @Override
    public TaskDtoResponse createTask(TaskDtoRequest taskDto, String login) {

        var user = userDao.findUserByLogin(login).orElseThrow(IllegalArgumentException::new);

        var task = Task.builder()
                .userId(user.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .completionAt(Instant.ofEpochMilli(taskDto.getCompletionAt()))
                .createdAt(Instant.ofEpochMilli(taskDto.getCreateAt()))
                .status(StatusTask.ACTIVE)
                .build();

        var newTask = taskDao.create(task).orElseThrow(IllegalArgumentException::new);

        return new TaskDtoResponse(newTask.getId(), newTask.getTitle(), newTask.getDescription(),
                newTask.getCreatedAt().toEpochMilli(), newTask.getCompletionAt().toEpochMilli(),
                newTask.getStatus());
    }

    @Override
    public List<TaskDtoResponse> getAllTasksByUserId(String login) {

        var user = userDao.findUserByLogin(login).orElseThrow(IllegalArgumentException::new);

        var task = taskDao.findAllTasksByUserId(user.getId());

        return task.stream()
                .map(statusService::checkStatus)
                .map(t -> new TaskDtoResponse(
                        t.getId(), t.getTitle(), t.getDescription(), t.getCreatedAt().toEpochMilli(),
                        t.getCompletionAt().toEpochMilli(), t.getStatus()
                ))
                .toList();
    }

    @Override
    public void deleteTaskById(Long id) {
        taskDao.delete(id);
    }

    @Override
    public TaskDtoResponse updateTitleAndDescriptionTaskById(TaskDtoRequest dtoRequest, Long id) {

        var task = taskDao.update(Task.builder()
                .title(dtoRequest.getTitle())
                .description(dtoRequest.getDescription())
                .build(), id).orElseThrow(IllegalArgumentException::new);

        return new TaskDtoResponse(
                task.getId(), task.getTitle(), task.getDescription(), task.getCreatedAt().toEpochMilli(),
                task.getCompletionAt().toEpochMilli(), task.getStatus()
        );
    }


}
