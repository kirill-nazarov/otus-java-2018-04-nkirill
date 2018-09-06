package ru.otus.dbservice;

import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.datasets.UsersDataSet;

import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceCached extends DBServicePreparedTransactional {

    CacheEngine<Long, UsersDataSet> cache = new CacheEngineImpl<>();
    int numberOfDBReads = 0;

    public DBServiceCached() {
        super();
    }

    public DBServiceCached(Connection connect) {
        super(connect);
    }

    public DBServiceCached(int numOfCachedElements) {
        super();
        cache = new CacheEngineImpl<>(numOfCachedElements);
    }

    public DBServiceCached(int numOfCachedElements, Connection connection) {
        super(connection);
        cache = new CacheEngineImpl<>(numOfCachedElements);
    }

    @Override
    public <T extends UsersDataSet> void save(T user) throws SQLException {
        super.save(user);
        cache.put(user.getId(), user);
        System.out.println("user inserted into cache ID:" + user.getId());
    }

    @Override
    public <T extends UsersDataSet> T load(long id, Class<T> clazz) throws SQLException {
        UsersDataSet user = cache.get(id);
        if (user != null) {
            System.out.println("user read from cache ID: " + id);
            System.out.println("Cache hit count = " + cache.getHitCount());
            System.out.println("Cache miss count = " + cache.getMissCount());
            System.out.println("Number of DB reads = " + numberOfDBReads);
            return (T) user;
        }
        numberOfDBReads++;
        return super.load(id, clazz);
    }

    @Override
    public void close() throws Exception {
        super.close();
        cache.dispose();
    }
}
