package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {
    @Test
    void random() {
        var ex = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            ex.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    int nextInt = ThreadLocalRandom.current().nextInt(100);
                    System.out.println(nextInt);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    void randomStream() throws InterruptedException {
        var ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            ThreadLocalRandom.current().ints(1000, 0, 1000)
                    .forEach(System.out::println);
        });

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.DAYS);
    }
}
