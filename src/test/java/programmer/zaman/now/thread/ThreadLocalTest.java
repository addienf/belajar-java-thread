package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    @Test
    void userServiceTest() throws InterruptedException {
        var random = new Random();
        var userService = new UserService();
        var ex = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            final var index = i;
            ex.execute(() -> {
                try {
                    userService.setUser("User - " + index);
                    Thread.sleep(5_00 + random.nextInt(3_000));
                    userService.doAction();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        ex.awaitTermination(1, TimeUnit.DAYS);
    }
}
