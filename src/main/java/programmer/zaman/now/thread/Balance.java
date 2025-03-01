package programmer.zaman.now.thread;

public class Balance {
    private  Long val;

    public Balance(Long val) {
        this.val = val;
    }

    public Long getVal() {
        return val;
    }

    public void setVal(Long val) {
        this.val = val;
    }

    public static void transfer(Balance from, Balance to, Long val) throws InterruptedException{
        synchronized (from){
            Thread.sleep(1_000L);
            from.setVal(from.getVal() - val);
        }
        synchronized (to){
            Thread.sleep(1_000L);
            to.setVal(to.getVal() + val);
        }
    }
}
