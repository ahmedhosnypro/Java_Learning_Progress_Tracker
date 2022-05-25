// You can experiment here, it won’t be checked

import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(scanner.nextLine().matches("[._A-Za-z\\d]+@[._A-Za-z\\d]+[.][._A-Za-z\\d]+"));
        }
    }
}
