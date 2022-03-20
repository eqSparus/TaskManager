package ru.manager.dao;

import java.util.List;
import java.util.Optional;

/**
 * Базовый интерфейс для всех dao классов.
 */
public interface IDao<T> {

    /**
     * Метод создает экземпляр класс в БД.
     * @param t объект для добавления
     * @return добавленый объект
     */
    Optional<T> create(T t);

    /**
     * Метод удаляет сущность из БД по ID.
     * @param id идентификтор сущности
     */
    void delete(Long id);

    /**
     * Метод обновляет параметры сущности.
     * @param t Объект для обновления
     * @param id идентификтор сущности
     * @return обновленный объект
     */
    Optional<T> update(T t, Long id);

    /**
     * Метод для получения всех сущностей из БД.
     * @return все сущности из БД
     */
    List<T> findAll();
}
