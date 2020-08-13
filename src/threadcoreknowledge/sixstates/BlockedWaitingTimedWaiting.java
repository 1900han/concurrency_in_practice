package threadcoreknowledge.sixstates;

/**
 * 展示Blocked，Waiting，TimedWaiting
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        Thread.sleep(1300);
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
