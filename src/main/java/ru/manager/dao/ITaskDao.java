package ru.manager.dao;

import ru.manager.models.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskDao extends IDao<Task> {

    List<Task> findTasksByUserId(Long userId);

    Optional<Task> findTaskById(Long id);
}
