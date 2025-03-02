package programmer.zaman.now.thread;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class SimpleForkJoinTask extends RecursiveAction {

    final private List<Integer> integers;

    public SimpleForkJoinTask(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    protected void compute() {
        if (integers.size() <= 10){
            doCompute();
        } else {
            forkCompute();
        }
    }

    private void doCompute(){
        integers.forEach(integer -> {
            System.out.println(Thread.currentThread().getName() + " : " + integer);
        });
    }

    private void forkCompute(){
        List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
        SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);

        List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
        SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

        ForkJoinTask.invokeAll(task1, task2);

    }
}
