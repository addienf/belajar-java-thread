package programmer.zaman.now.thread;

public class SynchronizedCounter {
    private Long val = 0L;

    public synchronized void increment(){
        val++;
    }

    public Long getVal() {
        return val;
    }
}
