package ru.otus.executor;

import java.sql.*;


public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execQuery(String query, ResultHandler handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            handler.handle(result);
        }
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

    public int execUpdateGetId(String update, ResultHandler handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(update, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.getGeneratedKeys();
            handler.handle(result);
            return stmt.getUpdateCount();
        }
    }

    Connection getConnection() {
        return connection;
    }
}
