package ru.otus.dbservice;

import org.apache.log4j.Logger;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.datasets.UsersDataSet;

import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceCached extends DBServicePreparedTransactional {

    CacheEngine<Long, UsersDataSet> cache = new CacheEngineImpl<>();
    int numberOfDBReads = 0;
    private final static Logger logger = Logger.getLogger(DBServiceCached.class);

    public DBServiceCached() {
        super();
    }

    public DBServiceCached(CacheEngine<Long, UsersDataSet> cache) {
        super();
        this.cache = cache;
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
        logger.info("user inserted into cache ID:" + user.getId());
    }

    @Override
    public <T extends UsersDataSet> T load(long id, Class<T> clazz) throws SQLException {
        UsersDataSet user = cache.get(id);
        if (user != null) {
            logger.info("user read from cache ID: " + id);
            logger.info("Cache hit count = " + cache.getHitCount());
            logger.info("Cache miss count = " + cache.getMissCount());
            logger.info("Number of DB reads = " + numberOfDBReads);
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
