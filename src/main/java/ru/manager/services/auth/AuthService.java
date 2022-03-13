package ru.manager.services.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.manager.models.dto.UserDto;

import java.util.Map;

/**
 * Интерфейс сервиса для реализции регистрации и авторизации пользователя.
 */
public interface AuthService {

    Map<String, String> registrationUser(UserDto dto) throws JsonProcessingException;

    Map<String, String> authorizationUser(UserDto dto);
}
