package background;

/**
 * 用工厂模式修复刚才的初始化问题
 */
public class MultiThreadsError7 {
    int count;
    private MultiThreadsError5.EventListener listener;

    private MultiThreadsError7(MultiThreadsError5.MySource source) {
        listener = new MultiThreadsError5.EventListener() {
            @Override
            public void onEvent(MultiThreadsError5.Event event) {
                System.out.println("\n我得到的数字是 " + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        this.count = 100;
    }

    public static MultiThreadsError7 getInstance(MultiThreadsError5.MySource source) {
        MultiThreadsError7 multiThreadsError7 = new MultiThreadsError7(source);
        source.registerListener(multiThreadsError7.listener);
        return multiThreadsError7;
    }

    public static void main(String[] args) {
        MultiThreadsError5.MySource mySource = new MultiThreadsError5.MySource();

/*        mySource.eventCome(new MultiThreadsError5.Event() {
        });
        MultiThreadsError7 multiThreadsError7 = getInstance(mySource);*/
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new MultiThreadsError5.Event() {
            });
        }).start();
        MultiThreadsError7 multiThreadsError7 = getInstance(mySource);

    }


//    static class MySource {
//        private MultiThreadsError5.EventListener listener;
//
//        void registerListener(MultiThreadsError5.EventListener listener) {
//            this.listener = listener;
//        }
//
//        void eventCome(MultiThreadsError5.Event event) {
//            if (listener != null) {
//                listener.onEvent(event);
//            } else {
//                System.out.println("还未初始化完毕");
//            }
//        }
//    }
}
