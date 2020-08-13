package threadcoreknowledge.createthreads;

/**
 * 更好
 * 1. 从代码架构角度（解耦）
 * 2. 新建线程的损耗
 * 3. Java单继承
 */
public class RunnableStyle implements Runnable{
    @Override
    public void run() {
        System.out.println("implements runnable");
    }
}
