package jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class NoVolatile implements Runnable{

    volatile int a;
    AtomicInteger realA= new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        NoVolatile r = new NoVolatile();
        Thread t = new Thread(r);
        Thread t2 = new Thread(r);
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(r.a);
        System.out.println(r.realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000 ;i++){
            a++;
            realA.incrementAndGet();
        }
    }
}
