package jmm;

/**
 * 演示可见性带来的问题
 * b=3,a=3
 * b=2,a=1
 * b=3,a=1
 * b=3,a=1（概率低）
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;

    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.println("b=" + b + ",a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility fieldVisibility = new FieldVisibility();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fieldVisibility.change();
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fieldVisibility.print();
            }).start();
        }
    }
}
