package programmer.zaman.now.thread;

public class SynchronizedCounter {
    private Long val = 0L;

    public void increment(){
        synchronized (this){
            val++;
        }
    }

    public Long getVal() {
        return val;
    }
}
