package ru.manager.dao;

import ru.manager.models.Task;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl implements ITaskDao {

    private static final String SQL_CREATE_TASK =
            "INSERT INTO tasks (title, description, created_at, completion_at, status, fk_user) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_TASK_BY_ID =
            "SELECT task_id, title, description, created_at, completion_at, status, fk_user " +
                    "FROM tasks WHERE task_id = ?";

    private final ConnectionDatabase connection;

    public TaskDaoImpl() {
        connection = new ConnectionDatabase();
    }


    @Override
    public Optional<Task> create(Task task) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_CREATE_TASK, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new Date(task.getCreatedAt().toEpochMilli()));
            statement.setDate(4, new Date(task.getCompletionAt().toEpochMilli()));
            statement.setString(5, task.getStatus().toString());
            statement.setLong(6, task.getUserId());

            Long key = statement.getGeneratedKeys().getLong(1);

            return findTaskById(key);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Task> update(Task task, Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findTasksByUserId(Long userId) {
        return null;
    }

    @Override
    public Optional<Task> findTaskById(Long id) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_FIND_TASK_BY_ID)) {

            var result = statement.executeQuery();

            if (result.next()){

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
