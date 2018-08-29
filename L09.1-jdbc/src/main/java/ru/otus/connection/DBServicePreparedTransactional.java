package ru.otus.connection;

import ru.otus.base.UsersDataSet;
import ru.otus.executor.Executor;
import ru.otus.reflection.ReflectionHelper;

import java.sql.SQLException;

public class DBServicePreparedTransactional extends DBServiceConnection {
    private static final String CREATE_TABLE_USER = "create table if not exists user (id bigint(20) not null auto_increment, name varchar(255), age int(3), primary key (id))";
    private static final String INSERT_USER = "insert into user (name, age) values ('%s','%s')";
    private static final String DELETE_USER = "drop table user";
    private static final String SELECT_USER = "select id, name, age from user where id=%s";

    @Override
    public void prepareTables() throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(CREATE_TABLE_USER);
        System.out.println("Table created");
    }

    @Override
    public void deleteTables() throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(DELETE_USER);
        System.out.println("Table dropped");
    }

    @Override
    public <T extends UsersDataSet> void save(T user) throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(String.format(INSERT_USER, user.getName(), user.getAge()),
                result -> {
                    result.next();
                    user.setId(result.getLong(1));
                });
        System.out.println("User added to DB");
    }

    @Override
    public <T extends UsersDataSet> T load(long id, Class<T> clazz) throws SQLException {
        Executor exec = new Executor(getConnection());
        T user = ReflectionHelper.instantiate(clazz);
        exec.execQuery(String.format(SELECT_USER, id),
                result -> {
                    result.next();
                    user.setId(result.getInt("id"));
                    user.setAge(result.getInt("age"));
                    user.setName(result.getString("name"));
                    System.out.println("Read user: " + result.getString("name"));
                }
        );

        return user;
    }


    private <T extends UsersDataSet> String getInsertUserString(T user) {
        return null;
    }

}
