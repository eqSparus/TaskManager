package ru.manager.dao;

public interface Dao<T> {

    T create(T t);

    void delete(Long id);

    T update(T t, Long id);

    T findAll();
}
