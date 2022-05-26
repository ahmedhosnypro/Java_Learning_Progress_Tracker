import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> words = new LinkedHashMap<>();
        for (String s : scanner.nextLine().toLowerCase().split(" ")) {
            if (words.containsKey(s)) {
                words.put(s, words.get(s) + 1);
            } else {
                words.put(s, 1);
            }
        }

        words.forEach((w, c) -> System.out.println(w + " " + c));
    }
}