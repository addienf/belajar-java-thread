package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    @Test
    void crateSemaphore() {
        final Semaphore semaphore = new Semaphore(1);
        final ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1_000);
                    System.out.println("Finish");
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
    }
}
