package background;

/**
 * 第二种线程安全问题，死锁
 */
public class MultiThreadsError2 implements Runnable {
    public static final Object o1 = new Object();
    public static final Object o2 = new Object();

    int flag = 1;

    public static void main(String[] args) {
        MultiThreadsError2 r1 = new MultiThreadsError2();
        MultiThreadsError2 r2 = new MultiThreadsError2();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }
}
