package org.care.dao;

import java.io.Serializable;
import java.sql.SQLException;

public interface DAO<T> {

    void create(T obj) throws SQLException;

    void update(T obj);

    boolean delete(Serializable id);

    T get(Serializable id);
}
