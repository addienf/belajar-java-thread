package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {
    @Test
    void createConcurrentMap() throws InterruptedException {
        final var countDown = new CountDownLatch(100);
        var map = new ConcurrentHashMap<Integer, String>();
        var ex = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            final var key = i;
            ex.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    map.putIfAbsent(key, "Data : " + key);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDown.countDown();
                }
            });
        }

        ex.execute(() -> {
            try {
                countDown.await();
                map.forEach((integer, s) -> System.out.println(integer + " : " + s));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ex.awaitTermination(1, TimeUnit.DAYS);
    }
}
