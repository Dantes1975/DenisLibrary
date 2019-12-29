package repository.dao;

import java.util.List;

public interface CrudDao<T> {
    T getById(long id);


    T insert(T t);

    T update(T t);

    void delete(long id);

    List<T> getAll();

}
