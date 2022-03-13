package ru.manager.services.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Парсер для сериализации и десириализции данных из JSON.
 */
public class ParserJsonService {

    private final ObjectMapper mapper;

    public ParserJsonService() {
        mapper = new ObjectMapper();
    }

    /**
     * Метод для сериализации данных в JSON.
     * @param object класс для сериализции.
     * @return JSON строка.
     * @throws JsonProcessingException
     */
    public <T> String toJson(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * Метод для преобразования строки JSON в оъект.
     * @param json строка.
     * @param clazz класс парсинга.
     * @return возвращает объект JSON.
     * @throws JsonProcessingException
     */
    public <T> T toObject(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    /**
     * Метод для преобразования потока символов в оъект.
     * @param reader поток символов.
     * @param clazz класс парсинга.
     * @return возвращает объект JSON.
     * @throws JsonProcessingException
     */
    public <T> T toObject(Reader reader, Class<T> clazz) throws IOException {
        return mapper.readValue(reader, clazz);
    }

    /**
     * Метод для преобразования потока байтов в оъект.
     * @param stream поток байтов.
     * @param clazz класс парсинга.
     * @return возвращает объект JSON.
     * @throws JsonProcessingException
     */
    public <T> T toObject(InputStream stream, Class<T> clazz) throws IOException {
        return mapper.readValue(stream, clazz);
    }
}
