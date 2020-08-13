package threadcoreknowledge.stopthreads.wrongways.volatiledemo;

/**
 * 演示volatile的局限part1：看似可行
 */
public class UseVolatileStopThread implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatileStopThread useVolatileStopThread = new UseVolatileStopThread();

        Thread thread = new Thread(useVolatileStopThread);
        thread.start();
        Thread.sleep(5000);
        useVolatileStopThread.canceled = true;
    }
}
