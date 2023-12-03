package org.primshic.stepan.dao;

import org.primshic.stepan.entity.Players;

import java.util.List;
import java.util.Optional;

public interface CRUD<T> {
    List<T> index();

    Optional<T> getById(int id);

    Optional<Players> save(T t);

    void update(int id, T t);

    void delete(int id);
}
