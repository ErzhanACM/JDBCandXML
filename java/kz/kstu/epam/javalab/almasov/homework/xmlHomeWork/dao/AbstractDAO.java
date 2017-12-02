package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {

    private Connection connection;

    public AbstractDAO() {
    }

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    //create
    public abstract void add(T entity) throws SQLException;

    //read
    public abstract List<T> getAll() throws SQLException;

    public abstract T getEntityById(int id) throws SQLException;

    //update
    public abstract void update(T entity) throws SQLException;

    //delete
    public abstract void remove(int id) throws SQLException;

    public abstract void removeAll() throws SQLException;

    public Connection getConnection() {
        return connection;
    }

}
