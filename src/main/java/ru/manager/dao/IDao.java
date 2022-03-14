package ru.manager.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    Optional<T> create(T t);

    void delete(Long id);

    Optional<T> update(T t, Long id);

    List<T> findAll();
}
