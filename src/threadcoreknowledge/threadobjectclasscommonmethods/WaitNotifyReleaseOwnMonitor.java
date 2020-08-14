package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 证明wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMonitor {
    public static final Object resA = new Object();
    public static final Object resB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resA) {
                System.out.println("ThreadA got resA lock.");
                synchronized (resB) {
                    System.out.println("ThreadA got resB lock.");
                    try {
                        System.out.println("ThreadA releases resA lock.");
                        resA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resA) {
                System.out.println("ThreadB got resA lock.");
                System.out.println("ThreadB tries to resB lock.");
                synchronized (resB) {
                    System.out.println("ThreadB got resB lock.");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
