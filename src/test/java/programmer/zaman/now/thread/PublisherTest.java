package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;

public class PublisherTest {
    @Test
    void createPublisher() {
        var ex = Executors.newSingleThreadExecutor();
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        PrintSubscriber subscriber = new PrintSubscriber();
        publisher.subscribe(subscriber);

        ex.execute(() -> {
            for (int i = 0; i < 100; i++) {
                publisher.submit("Addien" + i);
                System.out.println("Send Addien : " + i);
            }
        });
    }

    @Test
    void buffer() {
        var ex = Executors.newSingleThreadExecutor();
        var forkJoinPool = Executors.newWorkStealingPool();
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>(forkJoinPool, 5);

        PrintSubscriber subscriber = new PrintSubscriber();
        publisher.subscribe(subscriber);

        ex.execute(() -> {
            for (int i = 0; i < 100; i++) {
                publisher.submit("Addien" + i);
                System.out.println("Send Addien : " + i);
            }
        });
    }

    @Test
    void processor() {
        var ex = Executors.newSingleThreadExecutor();
        var forkJoinPool = Executors.newWorkStealingPool();

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>(forkJoinPool, 5);
        HelloProcessor helloProcessor = new HelloProcessor();
        publisher.subscribe(helloProcessor);

        var sub = new PrintSubscriber();
        helloProcessor.subscribe(sub);
    }
}
