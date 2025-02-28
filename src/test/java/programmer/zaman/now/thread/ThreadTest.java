package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {
    @Test
    public void mainTest() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createTest() {
        Runnable runnable = () -> {
            System.out.println("Hello from Thread : " + Thread.currentThread().getName());
        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesai");
    }

    @Test
    void threadSleepTest() throws InterruptedException{
        Runnable runnable = () -> {
          try {
              Thread.sleep(2_000);
              System.out.println("Hello From Thread : " +Thread.currentThread().getName());
          } catch (InterruptedException e){
              e.printStackTrace();
          }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3_000);
    }

    @Test
    void threadJoin() throws InterruptedException{
        Runnable runnable = () -> {
          try {
              Thread.sleep(2_000);
              System.out.println("Hello From Thread : " +Thread.currentThread().getName());
          } catch (InterruptedException e){
              e.printStackTrace();
          }
        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu Seleasi");
        thread.join();
        System.out.println("Program Seleasi");
    }

    @Test
    void interuptThreadSalah() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
            }
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu Seleasi");
        Thread.sleep(5_000);
        thread.interrupt();
        thread.join();
    }

    @Test
    void interuptThreadBenar() {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e){
                    return;
                }
            }
        };
    }

    @Test
    void threadManual() {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable : " +i);
            }
        };
    }

    @Test
    void threadName() {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e){
                    return;
                }
            }
        };

        var thread = new Thread(() -> {
            System.out.println("Hello From Thread : " +Thread.currentThread().getName());
        });

        thread.setName("Addien");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e){
                    return;
                }
            }
        };

        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Hello from thread : " +Thread.currentThread().getName());
        });

        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
