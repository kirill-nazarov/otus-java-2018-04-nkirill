package ru.otus.cache;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CacheEngine<K, V> {

    void put(K key, V value);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();

    Map<String, Object> getCacheInfo();
}
