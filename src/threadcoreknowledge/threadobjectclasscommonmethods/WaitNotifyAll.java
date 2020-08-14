package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 线程A和线程B首先被阻塞，线程C唤醒它们，notify，notifyAll。start先执行不代表线程先启动。
 */
public class WaitNotifyAll implements Runnable {
    public static final Object resA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resA) {
                    resA.notifyAll();
//                    resA.notify();
                    System.out.println("ThreadC notified");
                }
            }
        });
        threadA.start();
        threadB.start();
//        Thread.sleep(500);
        threadC.start();
    }

    @Override
    public void run() {
        synchronized (resA) {
            System.out.println(Thread.currentThread().getName() + " got resA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + " wait to start.");
                resA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
