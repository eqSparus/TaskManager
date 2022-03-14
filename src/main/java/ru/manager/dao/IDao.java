package ru.manager.dao;

public interface IDao<T> {

    T create(T t);

    void delete(Long id);

    T update(T t, Long id);

    T findAll();
}
