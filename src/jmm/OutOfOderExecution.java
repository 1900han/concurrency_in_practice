package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序的现象 "直到达到某个条件才停止"，测试小概率事件
 */
public class OutOfOderExecution {
    public static int x, y = 0;
    public static int a, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(3);

            Thread oneThread = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread twoThread = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            twoThread.start();
            oneThread.start();
            latch.countDown();
            oneThread.join();
            twoThread.join();
            String result = "第" + i + "次（" + x + "，" + y + "）";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
