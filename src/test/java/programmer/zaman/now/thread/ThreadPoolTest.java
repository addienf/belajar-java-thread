package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    @Test
    void create() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);
    }

    @Test
    void executeRunnable() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

        executor.execute(() -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello From ThreadPool : " + Thread.currentThread().getName());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread.sleep(3_000);
    }

    @Test
    void shutDown() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(1000);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

        for (int i = 0; i < 1000; i++) {
            final var task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2_000);
                    System.out.println("Task " + task + "From ThreadPool : " + Thread.currentThread().getName());
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(3_000);
    }

    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task : " + r + " Is Rejected!");
        }
    }

    @Test
    void rejected() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;
        var rejected = new LogRejectedExecutionHandler();

        var queue = new ArrayBlockingQueue<Runnable>(1000);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue, rejected);

        for (int i = 0; i < 1000; i++) {
            final var task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2_000);
                    System.out.println("Task " + task + "From ThreadPool : " + Thread.currentThread().getName());
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(3_000);
    }
}
