package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0-100的即偶数，用synchronized
 */
public class WaitNotifyPrintOddEvenSyn {

    public static final Object obj = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (obj) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        }, "偶数").start();


        new Thread(() -> {
            while (count < 100) {
                synchronized (obj) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        }, "奇数").start();
    }

}
