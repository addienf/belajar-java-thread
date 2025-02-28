package programmer.zaman.now.thread;

public class MainApplication {
    public static void main(String[] args) {
        var thread = new Thread(() -> {
           try {
               Thread.sleep(3_000);
               System.out.println("Hello Daemon");
           } catch (InterruptedException e){
               e.printStackTrace();
           }
        });

        thread.setDaemon(false);
        thread.start();
    }
}
