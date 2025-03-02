package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

public class WaitNotifyTest {
    private String message = null;

    @Test
    void create() throws InterruptedException{
        final Object lock = new Object();

        var thread1 = new Thread(() -> {
           synchronized (lock){
               try {
                   lock.wait();
                   System.out.println(message);
               } catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        });

        var thread2 = new Thread(() -> {
           synchronized (lock){
               message = "Addien";
               lock.notify();
           }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    void waitNotifyAllTest() throws InterruptedException{
        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        var thread3 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Addien";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread3.start();

        thread2.start();

        thread1.join();
        thread2.join();
    }
}
