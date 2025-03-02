package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLacthTest {
    @Test
    void create() throws InterruptedException {
        final var countDownLatch = new CountDownLatch(5);
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Star Task");
                    Thread.sleep(2_000);
                    System.out.println("End Task");
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        executor.execute(() -> {
            try {
                countDownLatch.await();
                System.out.println("Finish All Task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
