package jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class NoVolatile2 implements Runnable{

    volatile boolean done = false;
    AtomicInteger realA= new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        NoVolatile2 r = new NoVolatile2();
        Thread t = new Thread(r);
        Thread t2 = new Thread(r);
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(r.done);
        System.out.println(r.realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000 ;i++){
            flipDone();
            realA.incrementAndGet();
        }
    }

    private void flipDone() {
        done = !done;
    }
}
