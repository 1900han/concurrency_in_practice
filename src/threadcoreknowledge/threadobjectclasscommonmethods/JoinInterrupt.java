package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 中断");
            }
        });

        thread.start();
        System.out.println("等待子线程执行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 主线程中断了");
//            e.printStackTrace();
//            thread.interrupt();
        }
        System.out.println("子线程已执行完毕");
    }
}
