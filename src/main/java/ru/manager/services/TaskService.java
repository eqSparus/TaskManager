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

/**
 * Crud сервис для манипуляции задачами
 */
public class TaskService implements ITaskService {

    private final IUserDao userDao;
    private final ITaskDao taskDao;
    private final TaskStatusService statusService;

    public TaskService() {
        this.userDao = new UserDaoImpl();
        this.taskDao = new TaskDaoImpl();
        this.statusService = new TaskStatusService();
    }

    /**
     * Метод для создания задачи пользователя.
     * @param taskDto тело запроса на сервер.
     * @param login имя пользователя.
     * @return возвращает тела ответа сервера.
     */
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

        return TaskDtoResponse.builder()
                .id(newTask.getId())
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .createAt(newTask.getCreatedAt().toEpochMilli())
                .completionAt(newTask.getCompletionAt().toEpochMilli())
                .status(newTask.getStatus())
                .build();
    }

    /**
     * Метод для получения всех задача пользователя.
     * @param login имя пользователя.
     * @return все задачи.
     */
    @Override
    public List<TaskDtoResponse> getAllTasksByUserId(String login) {

        var user = userDao.findUserByLogin(login).orElseThrow(IllegalArgumentException::new);

        var task = taskDao.findAllTasksByUserId(user.getId());

        return task.stream()
                .map(statusService::checkStatus)
                .map(t -> TaskDtoResponse.builder()
                        .id(t.getId())
                        .title(t.getTitle())
                        .description(t.getDescription())
                        .createAt(t.getCreatedAt().toEpochMilli())
                        .completionAt(t.getCompletionAt().toEpochMilli())
                        .status(t.getStatus())
                        .build()
                ).toList();
    }

    /**
     * Метод для удаления задачи.
     * @param id идентификатор задачи.
     */
    @Override
    public void deleteTaskById(Long id) {
        taskDao.delete(id);
    }

    /**
     * Метод для обновления названия и описания задачи.
     * @param dtoRequest тело запроса на сервер.
     * @param id идентификатор задачи.
     * @return возвращает тела ответа сервера.
     */
    @Override
    public TaskDtoResponse updateTitleAndDescriptionTaskById(TaskDtoRequest dtoRequest, Long id) {

        var task = taskDao.update(Task.builder()
                .title(dtoRequest.getTitle())
                .description(dtoRequest.getDescription())
                .build(), id).orElseThrow(IllegalArgumentException::new);

        return TaskDtoResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createAt(task.getCreatedAt().toEpochMilli())
                .completionAt(task.getCompletionAt().toEpochMilli())
                .status(task.getStatus())
                .build();
    }


}
