package deadlock;

/**
 * 演示哲学家就餐问题导致的死锁
 */
public class DiningPhilosophers {

    public static class Philosopher implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Picked up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstick");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long) (Math.random() * 10));
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsitcks = new Object[philosophers.length];

        for (int i = 0; i < chopsitcks.length; i++) {
            chopsitcks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsitcks[i];
            Object rightChopstick = chopsitcks[(i + 1) % chopsitcks.length];
//            if (i == philosophers.length - 1) {
//                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
//            } else {
//                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
//            }
            philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }
}

