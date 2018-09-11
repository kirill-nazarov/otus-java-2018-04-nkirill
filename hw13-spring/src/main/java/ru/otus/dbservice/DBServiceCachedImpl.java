package ru.otus.dbservice;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.datasets.UserDataSet;

@Service
public class DBServiceCachedImpl extends DBServiceHibernateImpl {

    CacheEngine<Long, UserDataSet> cache = new CacheEngineImpl<>();
    int numberOfDBReads = 0;
    private final static Logger logger = Logger.getLogger(DBServiceCachedImpl.class);

    public DBServiceCachedImpl() {
        super();
    }

    public DBServiceCachedImpl(CacheEngine<Long, UserDataSet> cache) {
        super();
        this.cache = cache;
    }


    public DBServiceCachedImpl(Configuration configuration, CacheEngine<Long, UserDataSet> cache) {
        super(configuration);
        this.cache = cache;
    }

    @Override
    public void save(UserDataSet dataSet) {
        super.save(dataSet);
        if (dataSet.getId() >= 0) {
            cache.put(dataSet.getId(), dataSet);
            logger.info("user inserted into cache ID:" + dataSet.getId());
        }

    }

    @Override
    public UserDataSet read(long id) {
        UserDataSet dataSet = cache.get(id);
        if (dataSet != null) {
            logger.info("user read from cache ID: " + id);
            logger.info("Cache hit count = " + cache.getHitCount());
            logger.info("Cache miss count = " + cache.getMissCount());
            logger.info("Number of DB reads = " + numberOfDBReads);
            return dataSet;
        }
        numberOfDBReads++;
        return super.read(id);
    }

    @Override
    public void shutdown() {
        super.shutdown();
        cache.dispose();
    }
}
