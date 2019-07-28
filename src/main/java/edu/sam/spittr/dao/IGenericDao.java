package edu.sam.spittr.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
    void create(final T entity);

    T findOne(final long id);
    List<T> findAll();

    T update(final T entity);

    void delete(final T entity);
    void deleteById(final long entityId);
}
