package ru.manager.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * Класс для подсоединения к БД.
 */
public final class ConnectionDatabase implements AutoCloseable {

    private Connection connection;
    private String url;
    private String username;
    private String password;

    public ConnectionDatabase() {
        var classLoader = getClass().getClassLoader();
        var config = new Properties();
        try (var inputConfig = classLoader.getResourceAsStream("config_db.properties")) {
            config.load(inputConfig);
            url = config.getProperty("jdbc.url");
            username = config.getProperty("jdbc.username");
            password = config.getProperty("jdbc.password");
            var driver = config.getProperty("jdbc.driver");
            Class.forName(driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для подсоединения к БД .
     * @return подключения к БД.
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * Метод для закрытия подключения к БД.
     */
    @Override
    public void close()  {
        if (Objects.nonNull(connection)){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
