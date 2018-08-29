package ru.otus.connection;

import ru.otus.base.DBService;
import ru.otus.base.UsersDataSet;

import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceConnection implements DBService {

    private final Connection connection;

    public DBServiceConnection() {
        connection = ConnectionHelper.getConnection();
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
    public void prepareTables() throws SQLException {
    }


    @Override
    public <T extends UsersDataSet> void save(T user) throws SQLException {
    }

    @Override
    public <T extends UsersDataSet> T load(long id, Class<T> clazz) throws SQLException {
        return null;
    }

    @Override
    public void deleteTables() throws SQLException {
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
