package ru.manager.services.utilities;

import ru.manager.dao.ITaskDao;
import ru.manager.dao.TaskDaoImpl;
import ru.manager.models.StatusTask;
import ru.manager.models.Task;

import java.time.Instant;

public class TaskStatusService {

    private final ITaskDao taskDao;

    public TaskStatusService() {
        this.taskDao = new TaskDaoImpl();
    }

    public Task checkStatus(Task task) {

        if (task.getStatus().equals(StatusTask.ACTIVE) &&
                task.getCompletionAt().toEpochMilli() <= Instant.now().toEpochMilli()) {
            return taskDao.getUpdateTaskStatus(StatusTask.FAILED, task.getId())
                    .orElseThrow(IllegalArgumentException::new);
        }

        return task;
    }

}
