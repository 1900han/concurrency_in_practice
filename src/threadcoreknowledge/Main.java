package threadcoreknowledge;

import threadcoreknowledge.createthreads.RunnableStyle;
import threadcoreknowledge.createthreads.ThreadStyle;

public class Main {

    public static void main(String[] args) {
        ThreadStyle thread = new ThreadStyle();
        thread.start();

        RunnableStyle runnableStyle = new RunnableStyle();
        Thread thread1 = new Thread(runnableStyle);
        thread1.start();
    }
}
