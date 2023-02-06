package com.example.jmp.cache;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LRUGuavaCacheTest {

    private static final Data APPLE = new Data("apple");
    private static final Data BANANA = new Data("banana");
    private static final Data LEMON = new Data("lemon");
    private static final Data MANGO = new Data("mango");
    private static final Data KIWI = new Data("kiwi");
    private LRUGuavaCache cache;

    @Test
    void putShouldAddDataToCache() {
        cache = new LRUGuavaCache(4);

        cache.put(APPLE);
        cache.put(BANANA);
        cache.put(LEMON);

        assertEquals(3, cache.getSize());
        assertEquals(APPLE, cache.getIfPresent(new Data("apple")));
        assertEquals(BANANA, cache.getIfPresent(new Data("banana")));
        assertEquals(LEMON, cache.getIfPresent(new Data("lemon")));
    }

    @Test
    void putShouldAddDataToCacheAndRemoveFirstAddedElementWhenCacheFull() {
        cache = new LRUGuavaCache(4);

        cache.put(APPLE);
        cache.put(BANANA);
        cache.put(LEMON);
        cache.put(MANGO);
        cache.put(KIWI);

        assertEquals(4, cache.getSize());
        assertNull(cache.getIfPresent(APPLE));
    }

    @Test
    void getShouldAddDataToCacheIfItAbsent() {
        cache = new LRUGuavaCache(4);
        assertEquals(0, cache.getSize());

        cache.get(APPLE);

        assertEquals(1, cache.getSize());
        assertEquals(APPLE, cache.getIfPresent(new Data("apple")));
    }

    @Test
    void cacheShouldEvictOldRecordsAfterConfiguredIdleTimeAfterAccess() throws InterruptedException {
        cache = new LRUGuavaCache(4, 1);

        cache.get(APPLE);
        assertEquals(1, cache.getSize());

        Thread.sleep(1_500);

        assertNull(cache.getIfPresent(APPLE));
        assertEquals(0, cache.getSize());
        assertEquals(1, cache.getStats().evictionCount());
    }

    @Test
    void putDataInConcurrentShouldNotLostData() throws Exception {
        int cacheSize = 50;
        cache = new LRUGuavaCache(cacheSize);

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

        System.out.println(cache.getStats().toString());
        assertEquals(cacheSize, cache.getSize());
        for (int i = 0; i < cacheSize; i++) {
            Data data = new Data(String.valueOf(i));
            assertEquals(String.valueOf(i), cache.get(data).value());
        }
    }
}
