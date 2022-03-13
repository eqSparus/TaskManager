package ru.manager.models.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Модель для парсинга данных пользователя из JSON.
 */
public class UserDto {

    private final String login;
    private final String password;

    @JsonCreator
    public UserDto(
            @JsonProperty("login") String login,
            @JsonProperty("password") String password
    ) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
