package background;

/**
 * 观察者模式
 */
public class MultiThreadsError5 {
    int count;

    public MultiThreadsError5(MySource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("\n我得到的数字是" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
//        MultiThreadsError5 multiThreadsError5 = new MultiThreadsError5(mySource);
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();

        MultiThreadsError5 multiThreadsError5 = new MultiThreadsError5(mySource);
    }


    static class MySource {
        private EventListener listener;

        void registerListener(EventListener listener) {
            this.listener = listener;
        }

        void eventCome(Event event) {
            if (listener != null) {
                listener.onEvent(event);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {

    }
}
