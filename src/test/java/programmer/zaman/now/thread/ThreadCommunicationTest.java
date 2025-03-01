package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void testCommunication() {

        var thread1 = new Thread(() -> {
            while (message == null){
           }
            System.out.println(message);
        });

        var thread2 = new Thread(() -> {
           message = "Fauzan Addiend";
        });
    }
}
