package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    Random random = new Random();

    public CompletableFuture<String> getValue(){
        CompletableFuture<String> future = new CompletableFuture<>();

        executorService.execute(() -> {
            try {
                Thread.sleep(2_000);
                future.complete("Addien");
            } catch (InterruptedException e){
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    private void execute(CompletableFuture<String> future, String val){
        executorService.execute(() -> {
            try {
                Thread.sleep(1_000 + random.nextInt(5000));
                future.complete(val);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }

    private Future<String> getFastest(){
        CompletableFuture<String> future = new CompletableFuture<>();

        execute(future, "Thread 1");
        execute(future, "Thread 2");

        return future;
    }

    @Test
    void create() throws InterruptedException, InterruptedException {
        Future<String> future = getValue();
        System.out.println(future);
    }

    @Test
    void testFastest() throws ExecutionException, InterruptedException {
        System.out.println(getFastest().get());
    }

    @Test
    void completionStage() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getValue();

        CompletableFuture<String[]> future2 = future.thenApply(String::toUpperCase)
                .thenApply(s -> s.split(" "));

        String[] strings = future2.get();

        for (var val : strings){
            System.out.println(val);
        }

    }
}
