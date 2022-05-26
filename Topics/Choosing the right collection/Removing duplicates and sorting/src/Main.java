import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int sequenceSize = Integer.parseInt(scanner.nextLine().trim());

        Set<String> strings = new TreeSet<>();

        for (int i = 0; i < sequenceSize; i++) {
            strings.add(scanner.nextLine().trim());
        }

        strings.forEach(System.out::println);
    }
}
