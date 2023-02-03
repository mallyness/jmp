package com.example.jmp.cache;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LRUCacheTest {

    private static final Data APPLE = new Data("apple");
    private static final Data BANANA = new Data("banana");
    private static final Data LEMON = new Data("lemon");
    private static final Data MANGO = new Data("mango");
    private static final Data KIWI = new Data("kiwi");
    private LRUCache cache;

    @Test
    void putShouldAddDataToCache() {
        cache = new LRUCache(4);

        cache.put(APPLE);
        cache.put(BANANA);
        cache.put(LEMON);

        assertEquals(3, cache.getSize());
    }

    @Test
    void putShouldAddDataToCacheAndRemoveFirstAddedElementWhenCacheFull() {
        cache = new LRUCache(4);

        cache.put(APPLE);
        cache.put(BANANA);
        cache.put(LEMON);
        cache.put(MANGO);
        cache.put(KIWI);

        assertEquals(4, cache.getSize());
        assertFalse(cache.contains(APPLE));
    }

    @Test
    void putSameDataToCacheShouldRemoveItFromPreviousPositionAndAddItLast() {
        cache = new LRUCache(4);

        cache.put(APPLE);
        cache.put(BANANA);
        cache.put(LEMON);
        cache.put(APPLE);

        assertEquals(3, cache.getSize());
        assertEquals(BANANA, cache.getFirst().get());
        assertEquals(APPLE, cache.getLast().get());
    }

    @Test
    void getShouldAddDataToCacheIfItAbsent() {
        cache = new LRUCache(4);
        assertEquals(0, cache.getSize());

        cache.get(APPLE);

        assertEquals(1, cache.getSize());
        assertEquals(APPLE, cache.getLast().get());
    }

    @Test
    void getShouldAddDataToCacheAtLastRecentPositionIfPresent() {
        cache = new LRUCache(4);

        cache.get(APPLE);
        cache.put(BANANA);
        cache.put(LEMON);

        assertEquals(LEMON, cache.getLast().get());

        Data actual = cache.get(APPLE);

        assertEquals(APPLE, cache.getLast().get());
        assertEquals(APPLE, actual);
    }

    @Test
    void putDataInConcurrentShouldNotLostData() throws Exception {
        int cacheSize = 50;
        cache = new LRUCache(cacheSize);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(cacheSize);
        try {
            IntStream.range(0, cacheSize)
                    .mapToObj(i -> (Runnable) () -> {
                        cache.put(new Data(String.valueOf(i)));
                        countDownLatch.countDown();
                    }).forEach(executorService::submit);
            countDownLatch.await();
        } finally {
            executorService.shutdown();
        }

        assertEquals(cache.getSize(), cacheSize);
        for (int i = 0; i < cacheSize; i++) {
            Data data = new Data(String.valueOf(i));
            assertEquals(String.valueOf(i), cache.get(data).value());
        }
    }
}
