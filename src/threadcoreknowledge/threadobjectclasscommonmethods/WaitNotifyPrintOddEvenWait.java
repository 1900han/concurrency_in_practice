package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0-100的即偶数，用wait和notify
 */
public class WaitNotifyPrintOddEvenWait {
    public static final Object obj = new Object();
    private static int count = 0;

    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    obj.notify();
                    if (count <= 100) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable r = new TurningRunner();
        new Thread(r, "偶数").start();
        new Thread(r, "奇数").start();
    }
}
