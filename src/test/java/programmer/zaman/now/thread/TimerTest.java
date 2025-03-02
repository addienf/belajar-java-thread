package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    @Test
    void timerDelayedTest() {
        var task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 5_000L);
    }

    @Test
    void timerPeriodTest() {
        var task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Periodic Job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 5_000L, 2_000L);
    }
}
