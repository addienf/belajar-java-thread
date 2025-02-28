package programmer.zaman.now.thread;

public class Counter {
    private Long val = 0L;

    public void increment(){
        val++;
    }

    public Long getVal() {
        return val;
    }
}
