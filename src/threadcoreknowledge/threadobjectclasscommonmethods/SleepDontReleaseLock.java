package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程sleep的时候不释放lock
 */
public class SleepDontReleaseLock implements Runnable {
    public static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable r = new SleepDontReleaseLock();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了lock。");
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "要休眠5s。");
            Thread.sleep(5000);
            System.out.println("线程" + Thread.currentThread().getName() + "被唤醒了。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "释放了lock。");
    }
}
