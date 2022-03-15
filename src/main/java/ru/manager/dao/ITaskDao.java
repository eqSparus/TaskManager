package ru.manager.dao;

import ru.manager.models.StatusTask;
import ru.manager.models.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskDao extends IDao<Task> {

    List<Task> findAllTasksByUserId(Long userId);

    Optional<Task> findTaskById(Long id);

    void updateTaskStatus(StatusTask status, Long id);
}
