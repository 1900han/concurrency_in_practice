package jmm;

public class FixedFieldVisibility {
    int a = 1;
    // 给b加volatile，b=3时，a一定已经是3了
    volatile int b = 2;

    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.println("b=" + b + ",a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            FixedFieldVisibility fieldVisibility = new FixedFieldVisibility();
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
