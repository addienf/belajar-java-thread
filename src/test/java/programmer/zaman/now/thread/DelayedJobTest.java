package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedJobTest {
    @Test
    void create() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(() -> {
            System.out.println("Hello Delayed Job");
        }, 2, TimeUnit.SECONDS);
    }

    @Test
    void periodicJob() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Hello Delayed Job");
        }, 2, 2, TimeUnit.SECONDS);
    }
}
