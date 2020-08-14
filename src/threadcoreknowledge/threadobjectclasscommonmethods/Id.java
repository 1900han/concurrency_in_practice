package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * tid从1开始，JVM运行起来之后，我们自己创建的线程的tid早已经不是2
 */
public class Id {
    public static void main(String[] args) {

        Thread thread = new Thread();
        System.out.println("主线程的ID " + Thread.currentThread().getId());
        System.out.println("子线程的ID " + thread.getId());
    }
}
