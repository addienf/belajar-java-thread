package programmer.zaman.now.thread;

import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<String>{

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        try {
            Thread.sleep(1_000);
            System.out.println(Thread.currentThread().getName());
            this.subscription.request(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println(Thread.currentThread().getName() + " : DONE");
    }
}
