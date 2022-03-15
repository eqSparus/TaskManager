package ru.manager.dao;

import ru.manager.models.StatusTask;
import ru.manager.models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl implements ITaskDao {

    private static final String SQL_CREATE_TASK =
            "INSERT INTO tasks (title, description, created_at, completion_at, status, fk_user) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_TASK_BY_ID =
            "SELECT task_id, title, description, created_at, completion_at, status " +
                    "FROM tasks WHERE task_id = ?";

    private static final String SQL_DELETE_TASK_BY_ID =
            "DELETE FROM tasks WHERE task_id = ?";

    private static final String SQL_FIND_ALL_BY_USER_ID =
            "SELECT task_id, title, description, created_at, completion_at, status " +
                    "FROM tasks WHERE fk_user = ?";

    private static final String SQL_FIND_ALL =
            "SELECT task_id, title, description, created_at, completion_at, status FROM tasks";

    private static final String SQL_UPDATE_TASK_BY_ID =
            "UPDATE tasks SET title = ?, description = ? WHERE task_id = ?";

    private static final String SQL_UPDATE_TASK_STATUS_BY_ID =
            "UPDATE tasks SET status = ? WHERE task_id = ?";

    private final ConnectionDatabase connection;

    public TaskDaoImpl() {
        connection = new ConnectionDatabase();
    }


    @Override
    public Optional<Task> create(Task task) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_CREATE_TASK,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setLong(3, task.getCreatedAt().toEpochMilli());
            statement.setObject(4, task.getCompletionAt().toEpochMilli());
            statement.setString(5, task.getStatus().toString());
            statement.setLong(6, task.getUserId());

            statement.executeUpdate();

            var key = statement.getGeneratedKeys();

            if (key.next()) {
                return findTaskById(key.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_DELETE_TASK_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Optional<Task> update(Task task, Long id) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_UPDATE_TASK_BY_ID,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setLong(3, id);

            statement.executeUpdate();

            var key = statement.getGeneratedKeys();

            if (key.next()) {
                return findTaskById(key.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {

        var tasks = new ArrayList<Task>();

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_FIND_ALL)) {

            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tasks.add(getTask(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public List<Task> findAllTasksByUserId(Long userId) {

        var tasks = new ArrayList<Task>();

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_FIND_ALL_BY_USER_ID)) {

            statement.setLong(1, userId);

            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tasks.add(getTask(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public Optional<Task> findTaskById(Long id) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_FIND_TASK_BY_ID)) {

            statement.setLong(1, id);

            var result = statement.executeQuery();

            if (result.next()) {
                return Optional.of(getTask(result));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateTaskStatus(StatusTask status, Long id) {

        try (var con = connection.getConnection();
             var statement = con.prepareStatement(SQL_UPDATE_TASK_STATUS_BY_ID)) {

            statement.setString(1, status.toString());
            statement.setLong(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Task getTask(ResultSet result) throws SQLException {
        return Task.builder()
                .id(result.getLong("task_id"))
                .title(result.getString("title"))
                .description(result.getString("description"))
                .createdAt(Instant.ofEpochMilli(result.getLong("created_at")))
                .completionAt(Instant.ofEpochMilli(result.getLong("completion_at")))
                .status(StatusTask.convert(result.getString("status")))
                .build();
    }
}
