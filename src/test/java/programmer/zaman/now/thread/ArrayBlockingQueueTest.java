package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class ArrayBlockingQueueTest {
    @Test
    void createArrayBlockingQueue() {
        var queue = new ArrayBlockingQueue<String>(5);
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    queue.put("Data");
                    System.out.println("Success Put Data");
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try{
                    Thread.sleep(2_000);
                    String data = queue.take();
                    System.out.println("Recieve : " + data);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    void createLinkedBlockingQueue() {
        var queue = new LinkedBlockingQueue<String>();
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    queue.put("Data");
                    System.out.println("Success Put Data");
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try{
                    Thread.sleep(2_000);
                    String data = queue.take();
                    System.out.println("Recieve : " + data);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    void priorityBlockingQueue() throws InterruptedException {
        var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            var index = i;
            executor.execute(() -> {
                queue.put(index);
                System.out.println("Success Put Data");
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    var value = queue.take();
                    System.out.println("Recieve Data : " + value);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void delayedQueue() throws InterruptedException {
        var queue = new DelayQueue<ScheduledFuture<String>>();
        var executor = Executors.newFixedThreadPool(20);
        final var executorSchedule = Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            var index = i;
            queue.put(executorSchedule.schedule(() -> "Data" + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
            while (true) {
                try {
                    var value = queue.take();
                    System.out.println("Recieve Data : " + value.get());
                } catch (InterruptedException e){
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void synchronusQueue() throws InterruptedException {
        var queue = new SynchronousQueue<String>();
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.put("Data" + index);
                    System.out.println("Success Put Data " + index);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    var value = queue.take();
                    System.out.println("Recieve Data : " + value);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
