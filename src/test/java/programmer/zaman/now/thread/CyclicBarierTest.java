package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class CyclicBarierTest {
    @Test
    void create() {
        final var cyclicBarrier = new CyclicBarrier(5);
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    cyclicBarrier.await();
                    System.out.println("Done Waiting");
                } catch (InterruptedException | BrokenBarrierException e){
                    e.printStackTrace();
                }
            });
        }

    }
}
