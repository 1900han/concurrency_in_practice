package singleton;

/**
 * 懒汉式（线程安全）（不推荐）存在性能问题
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
