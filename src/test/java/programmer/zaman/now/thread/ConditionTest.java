package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    String message = null;
    @Test
    void testCondition() throws InterruptedException {
        var lock = new ReentrantLock();
        var condition = lock.newCondition();

        var thread1 = new Thread(() -> {
           try {
               lock.lock();
               condition.await();
               System.out.println(message);
           } catch (InterruptedException e){
               e.printStackTrace();
           } finally {
               lock.unlock();
           }
        });

        var thread2 = new Thread(() -> {
           try {
               lock.lock();
               message = "Fauzan Addien";
               condition.signal();
           } finally {
               lock.unlock();
           }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
