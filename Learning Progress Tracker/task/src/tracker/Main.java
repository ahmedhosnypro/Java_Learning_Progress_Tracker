package tracker;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final DataSet data = new DataSet();

    static String lastLog = "";

    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        CLI.run();
    }
}
