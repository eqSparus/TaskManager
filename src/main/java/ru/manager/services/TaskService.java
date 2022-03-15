package ru.manager.services;

import ru.manager.dao.ITaskDao;
import ru.manager.dao.IUserDao;
import ru.manager.dao.TaskDaoImpl;
import ru.manager.dao.UserDaoImpl;
import ru.manager.models.StatusTask;
import ru.manager.models.Task;
import ru.manager.models.dto.TaskDtoRequest;
import ru.manager.models.dto.TaskDtoResponse;

public class TaskService implements ITaskService {

    private final IUserDao userDao;
    private final ITaskDao taskDao;

    public TaskService() {
        this.userDao = new UserDaoImpl();
        this.taskDao = new TaskDaoImpl();
    }

    @Override
    public TaskDtoResponse createTask(TaskDtoRequest taskDto, String login) {

        var user = userDao.findUserByLogin(login).orElseThrow(IllegalArgumentException::new);

        var task = new Task.Builder()
                .userId(user.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .completionAt(taskDto.getCompletionAt())
                .createdAt(taskDto.getCreateAt())
                .status(StatusTask.ACTIVE)
                .build();

        var newTask = taskDao.create(task).orElseThrow(IllegalArgumentException::new);

        return new TaskDtoResponse(newTask.getId(), newTask.getTitle(), newTask.getDescription(),
                newTask.getCreatedAt(), newTask.getCompletionAt(), newTask.getStatus());
    }

}
