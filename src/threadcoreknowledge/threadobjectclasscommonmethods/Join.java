package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * 演示join，注意语句输出顺序，会变化
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
        });
        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
        });

        thread.start();
        thread2.start();
        System.out.println("开始等待子线程运行完毕");
        // 注释👇两句，输出有变化
        thread.join();
        thread2.join();
        System.out.println("所有子线程执行完毕");
    }
}
