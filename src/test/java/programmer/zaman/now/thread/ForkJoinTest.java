package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinTest {
    @Test
    void create() {
        ForkJoinPool pool1 = ForkJoinPool.commonPool();
        ForkJoinPool pool2 = new ForkJoinPool(5);

        ExecutorService service1 = Executors.newWorkStealingPool();
        ExecutorService service2 = Executors.newWorkStealingPool(5);
    }

    @Test
    void recursiveAction() throws InterruptedException {
        var pool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0, 1000).boxed()
                .collect(Collectors.toList());

        pool.execute(new SimpleForkJoinTask(integers));

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class TotalForkJoinTask extends RecursiveTask<Long>{

        private List<Integer> integers;

        public TotalForkJoinTask(List<Integer> integers){
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if (integers.size() <= 10){
                return doCompute();
            } else {
                return forkCompute();
            }
        }

        private Long forkCompute() {
            List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
            TotalForkJoinTask task1 = new TotalForkJoinTask(integers1);

            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
            TotalForkJoinTask task2 = new TotalForkJoinTask(integers2);

            ForkJoinTask.invokeAll(task1, task2);
            return task1.join() + task2.join();
        }

        private Long doCompute() {
            return integers.stream().mapToLong(value -> value).peek(value -> {
                System.out.println(Thread.currentThread().getName() + " : " + value);
            }).sum();
        }
    }
}
