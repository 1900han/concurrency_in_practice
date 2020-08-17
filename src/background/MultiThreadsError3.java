package background;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布逸出
 * 用副本解决
 */
public class MultiThreadsError3 {
    private Map<String, String> states;

    public MultiThreadsError3() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
        states.put("5", "周五");
    }

    public Map<String, String> getStates() {
        return states;
    }

    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadsError3 multiThreadsError3 = new MultiThreadsError3();
        Map<String, String> states = multiThreadsError3.getStates();

//        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));

        System.out.println("***************************");

        Map<String, String> statesImproved = multiThreadsError3.getStatesImproved();
        System.out.println(statesImproved.get("1"));
        statesImproved.remove("1");
        System.out.println(states.get("1"));
    }
}
