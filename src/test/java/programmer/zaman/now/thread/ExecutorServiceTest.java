package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Test
    void crate() {
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    System.out.println("Execute");
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
