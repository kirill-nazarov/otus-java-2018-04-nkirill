package ru.otus.dbservice;

import ru.otus.connection.ConnectionHelper;
import ru.otus.datasets.UsersDataSet;
import ru.otus.executor.Executor;
import ru.otus.reflection.ReflectionHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

public class DBServicePreparedTransactional implements DBService {
    private static final String DELETE_USER = "drop table %s";
    private static final String SELECT_USER = "select * from %s where id=?";
    private final Connection connection;


    public DBServicePreparedTransactional() {
        connection = ConnectionHelper.getConnection();
    }

    public DBServicePreparedTransactional(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to: " + getConnection().getMetaData().getURL() + "\n" +
                    "DB name: " + getConnection().getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + getConnection().getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + getConnection().getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void prepareTables(Class clazz) throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(generateCreateSQL(clazz));
    }

    @Override
    public void deleteTables(Class clazz) throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(String.format(DELETE_USER, clazz.getSimpleName()));
    }

    @Override
    public <T extends UsersDataSet> void save(T user) throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(generateInsertSQL(user),
                result -> {
                    result.next();
                    user.setId(result.getLong(1));
                });
    }

    @Override
    public <T extends UsersDataSet> T load(long id, Class<T> clazz) throws SQLException {
        Executor exec = new Executor(getConnection());
        T user = ReflectionHelper.instantiate(clazz);
        exec.execQuery(String.format(SELECT_USER, clazz.getSimpleName()), id,
                result -> {
                    result.next();
                    if (clazz.getSuperclass() != null) {
                        Class clzz = clazz.getSuperclass();
                        for (Field field : clzz.getDeclaredFields()) {
                            ReflectionHelper.setFieldValue(user, field, result.getObject(field.getName()));
                        }
                    }
                    for (Field field : clazz.getDeclaredFields()) {
                        ReflectionHelper.setFieldValue(user, field, result.getObject(field.getName()));
                    }
                }
        );

        return user;
    }

    private String getFieldAttribute(Field field) {
        if (field.getType().isAssignableFrom(Integer.class)) {
            return " int(3)";
        } else if (field.getType().isAssignableFrom(String.class)) {
            return " varchar(255)";
        } else {
            throw new IllegalArgumentException("Unknown type");
        }
    }

    private String generateCreateSQL(Class clazz) {
        StringBuilder insert = new StringBuilder("CREATE TABLE if not exists ");
        insert.append(clazz.getSimpleName()).append(" (id bigint(20) NOT NULL AUTO_INCREMENT,");
        for (Field field : clazz.getDeclaredFields()) {
            if ("id".equals(field.getName())) {
                continue;
            }
            insert.append(field.getName()).append(getFieldAttribute(field)).append(",");
        }
        insert.append("PRIMARY KEY (id));");
        return insert.toString();
    }

    private String generateInsertSQL(Object o) {
        StringBuilder insert = new StringBuilder("insert into ");
        insert.append(o.getClass().getSimpleName()).append(" (");
        StringBuilder values = new StringBuilder();
        for (Field field : o.getClass().getDeclaredFields()) {
            if ("id".equals(field.getName())) {
                continue;
            }
            insert.append(field.getName()).append(",");
            values.append("\'").append(ReflectionHelper.getFieldValue(o, field)).append("\'").append(", ");
        }
        insert.deleteCharAt(insert.lastIndexOf(","));
        insert.append(") values (");
        insert.append(values);
        insert.deleteCharAt(insert.lastIndexOf(","));
        insert.append(")");
        return insert.toString();
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed. Bye!");
    }

    protected Connection getConnection() {
        return connection;
    }

}
