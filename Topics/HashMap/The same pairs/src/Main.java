import java.util.*;

class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        int cnt = 0;
        for (var entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey()) && map2.get(entry.getKey()).equals(entry.getValue())) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}