package ru.manager.dao;

import ru.manager.models.StatusTask;
import ru.manager.models.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskDao extends IDao<Task> {

    List<Task> findAllTasksByUserId(Long userId);

    Optional<Task> findTaskById(Long id);

    Optional<Task> getUpdateTaskStatus(StatusTask status, Long id);

    Optional<Task> getUpdateFrozenTime(long frozenTime, Long id);

    Optional<Task> getUpdateActiveTime(long completionAt, Long id);
}
