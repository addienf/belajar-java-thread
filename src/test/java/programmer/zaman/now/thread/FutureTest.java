package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {
    @Test
    void createFuture() throws InterruptedException, ExecutionException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
          Thread.sleep(5_000);
          return "Hi";
        };
        Future<String> future = executor.submit(callable);

//        var future = executor.submit(() -> {
//           Thread.sleep(5_000);
//           return "Hi";
//        });
//
//        while (!future.isDone()){
//            System.out.println("Waiting Result");
//            Thread.sleep(1_000);
//        }

        System.out.println(future.get());
    }

    @Test
    void tesFutureCancel() throws InterruptedException, ExecutionException {
        var executor = Executors.newSingleThreadExecutor();

        var future = executor.submit(() -> {
           Thread.sleep(5_000);
           return "Hi";
        });

        while (!future.isDone()){
            System.out.println("Waiting Result");
            Thread.sleep(1_000);
        }

        Thread.sleep(2_000);
        future.cancel(true);

        System.out.println(future.get());
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException{
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(operand -> (Callable<String>) () -> {
           Thread.sleep(operand * 5_00L);
           return String.valueOf(operand);
        }).collect(Collectors.toList());

        List<Future<String>> futures = executor.invokeAll(callables);
        for (Future<String> stringFuture : futures){
            System.out.println(stringFuture.get());
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException{
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(operand -> (Callable<String>) () -> {
            Thread.sleep(operand * 5_00L);
            return String.valueOf(operand);
        }).collect(Collectors.toList());

        var futures = executor.invokeAny(callables);
        System.out.println(futures);
    }
}
