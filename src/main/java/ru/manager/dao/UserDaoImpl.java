package ru.manager.dao;


import ru.manager.models.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Класс для взаимодействия с таблицей users в БД.
 */
public class UserDaoImpl implements UserDao {

    private static final String SQL_CREATE_USER
            = "INSERT INTO users(username, password) VALUES (?, ?)";

    private static final String SQL_FIND_USER_BY_USERNAME =
            "SELECT * FROM users WHERE username = ?";

    private final ConnectionDatabase connection;

    public UserDaoImpl() {
        this.connection = new ConnectionDatabase();
    }

    /**
     * Метод создает нового пользователя в БД.
     * @param user пользователь который должен быть добавлен.
     */
    @Override
    public void createUser(User user) {

        try (var connect = connection.getConnection();
             var statement = connect.prepareStatement(SQL_CREATE_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод возвращает из БД пользователя по имени или пустой объект .
     * @see java.util.Optional
     * @param username имя пользователя.
     * @return пользователь обернутый в Optional.
     */
    @Override
    public Optional<User> findUserByUsername(String username) {

        try (var connect = connection.getConnection();
             var statement = connect.prepareStatement(SQL_FIND_USER_BY_USERNAME)) {

            statement.setString(1, username);

            var result = statement.executeQuery();

            if (result.next()) {
                var user = new User(
                        result.getLong("user_id"),
                        result.getString("username"),
                        result.getString("password")
                );
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
