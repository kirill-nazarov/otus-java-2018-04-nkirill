package ru.otus.executor;

import java.sql.*;


public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execQuery(String query, long id, ResultHandler handler) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            handler.handle(result);
            result.close();
        }
    }

    public int execUpdate(String update) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(update)) {
            ps.executeUpdate();
            return ps.getUpdateCount();
        }
    }

    public int execUpdate(String update, ResultHandler handler) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            handler.handle(result);
            result.close();
            return ps.getUpdateCount();
        }
    }
}
