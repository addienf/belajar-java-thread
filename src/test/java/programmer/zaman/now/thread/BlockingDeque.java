package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class BlockingDeque {
    @Test
    void blockingDeque() throws InterruptedException {
        var queue = new LinkedBlockingDeque<String>();
        var executor = Executors.newFixedThreadPool(20);

        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                final var index = i;
                try {
                    queue.putLast("Data : " + index);
                    System.out.println("Success Put Data : " + index);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        executor.execute(() -> {
            while (true){
                try {
                    Thread.sleep(2_000);
                    String data = queue.takeLast();
                    System.out.println("Recieve : " + data);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void transferQueue() throws InterruptedException {
        var queue = new LinkedTransferQueue<String>();
        var executor = Executors.newFixedThreadPool(20);

        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                final var index = i;
                try {
                    queue.transfer("Data : " + index);
                    System.out.println("Success Put Data : " + index);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        executor.execute(() -> {
            while (true){
                try {
                    Thread.sleep(2_000);
                    String data = queue.take();
                    System.out.println("Recieve : " + data);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
