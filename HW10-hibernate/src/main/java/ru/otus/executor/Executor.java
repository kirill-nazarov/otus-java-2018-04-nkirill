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
            try (ResultSet result = ps.getResultSet()) {
                handler.handle(result);
            }
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
            try (ResultSet result = ps.getGeneratedKeys()) {
                handler.handle(result);
            }
            return ps.getUpdateCount();
        }
    }
}
