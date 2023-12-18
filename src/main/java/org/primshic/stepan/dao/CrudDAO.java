package org.primshic.stepan.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    List<T> index();

    Optional<T> getById(int id);

    void save(T t);

    void update(int id, T t);

    void delete(int id);
}
