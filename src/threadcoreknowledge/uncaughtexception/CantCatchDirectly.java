package threadcoreknowledge.uncaughtexception;

public class CantCatchDirectly implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCatchDirectly(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-4").start();
        } catch (RuntimeException e) {
            System.out.println("Caught Exception.");
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();

//        解决方法

//        try {
//            throw new RuntimeException();
//        } catch (RuntimeException e) {
//            System.out.println("Caught Exception.");
//        }
    }
}
