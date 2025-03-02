package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {
    @Test
    void create() throws InterruptedException {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    phaser.register();
                    System.out.println("Start Task");
                    Thread.sleep(2_000);
                    System.out.println("End Task");
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    phaser.arrive();
                }
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(0);
            System.out.println("All Task Finished");
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void cyclicBarrierPhaser() {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);
        phaser.bulkRegister(5);
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                phaser.arriveAndAwaitAdvance();
                System.out.println("Done Waiting");
            });
        }
    }
}
