package com.example.jmp.cache;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache implements Cache {

    private final int maxSize;
    private final Set<Data> cache;
    private final ReentrantLock lock;


    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        cache = new LinkedHashSet<>(maxSize);
        lock = new ReentrantLock();
    }

    @Override
    public void put(Data data) {
        lock.lock();
        try {
            if (cache.size() == maxSize) {  // cache full
                cache.remove(getFirst().get());
            }

            cache.remove(data); // if data present due to move to the least recent position
            cache.add(data);
        } finally {
            lock.unlock();
        }

    }

    /**
     * Add data to the cache if data is absent. Move to the least recent position otherwise.
     *
     * @param data is data to getting or computing if absent.
     * @return data at the least recent position.
     */
    @Override
    public Data get(Data data) {
        lock.lock();
        try {
            put(data);
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getSize() {
        return cache.size();
    }

    public boolean contains(Data data) {
        return cache.contains(data);
    }

    public void print() {
        System.out.println(cache);
    }

    public Optional<Data> getFirst() {
        return Optional.ofNullable(cache.iterator().next());
    }

    public Optional<Data> getLast() {
        var iterator = cache.iterator();
        Optional<Data> result = Optional.empty();
        while (iterator.hasNext()) {
            result = Optional.of(iterator.next());
        }
        return result;
    }
}
