import java.util.*;


class MapFunctions {
    private static final int NAME_LENGTH = 7;

    public static void removeLongNames(Map<String, String> map) {
        map.entrySet().removeIf(entry -> entry.getKey().length() > NAME_LENGTH ||
                entry.getValue().length() > NAME_LENGTH);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.removeLongNames(map);

        System.out.println(map.size());
    }
}