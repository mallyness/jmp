package com.example.jmp.cache;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

public class LRUGuavaCache implements Cache {

    private static final int DEFAULT_EVICTION_TIME = 5;
    private final LoadingCache<String, Data> cache;

    public LRUGuavaCache(int maxSize) {
        this(maxSize, DEFAULT_EVICTION_TIME);
    }

    public LRUGuavaCache(int maxSize, int evictionTime) {
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(evictionTime, TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .concurrencyLevel(1)
                .removalListener(getRemovalListener())
                .recordStats()
                .build(getCacheLoader());
    }

    private CacheLoader<String, Data> getCacheLoader() {
        return new CacheLoader<>() {
            @Override
            public Data load(String key) {
                return new Data(key);
            }
        };
    }

    private RemovalListener<String, Data> getRemovalListener() {
        return notification -> {
            if (notification.wasEvicted()) {
                System.out.println(notification.getKey() + " element was evicted for the following reason: " + notification.getCause().name());
            }
        };
    }

    @Override
    public void put(Data data) {
        cache.put(data.value(), data);
    }

    @Override
    public Data get(Data data) {
        return cache.getUnchecked(data.value());
    }

    @Override
    public long getSize() {
        return cache.size();
    }

    public Data getIfPresent(Data data) {
        return cache.getIfPresent(data.value());
    }

    public CacheStats getStats() {
        return cache.stats();
    }
}
