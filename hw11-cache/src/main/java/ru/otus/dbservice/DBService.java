package ru.otus.dbservice;

import ru.otus.datasets.UsersDataSet;

import java.sql.SQLException;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void prepareTables(Class clazz) throws SQLException;

    <T extends UsersDataSet> void save(T user) throws SQLException;

    <T extends UsersDataSet> T load(long id, Class<T> clazz) throws SQLException;

    void deleteTables(Class clazz) throws SQLException;
}
