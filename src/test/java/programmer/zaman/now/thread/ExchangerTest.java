package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {
    @Test
    void createTest() throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            try {
                Thread.sleep(1_000);
                var value = exchanger.exchange("First");
                System.out.println("1. " + value);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                Thread.sleep(2_000);
                var value = exchanger.exchange("Second");
                System.out.println("2. " + value);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
