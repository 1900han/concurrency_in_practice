package singleton;

/**
 * 双重检查（推荐面试使用）
 * 为什么需要volatile？
 * new 操作不是原子性的，有3个步骤
 * 重排序会带来NPE
 * 防止重排序
 */
public class Singleton6 {
    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
