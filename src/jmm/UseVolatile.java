package jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class UseVolatile implements Runnable{

    volatile boolean done = false;
    AtomicInteger realA= new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        UseVolatile r = new UseVolatile();
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
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }
}
