package tracker;

import java.util.Arrays;

public class Points {

    private static final String ERR_MSG = "Incorrect points format.";
    private static final String NOT_FOUND = "No student is found for id=";

    private Points() {
    }

    static void find(String input) {
        try {
            int id = Integer.parseInt(input);
            if (Main.data.getIdList().contains(id)) {
                Main.lastLog = Main.data.find(id);
                System.out.println(Main.lastLog);
            } else {
                Main.lastLog = NOT_FOUND + input;
                System.out.println(Main.lastLog);
            }
        } catch (IllegalArgumentException e) {
            Main.lastLog = NOT_FOUND + input; // should not valid id
            System.out.println(Main.lastLog);
        }
    }

    static void addPoints(String[] strPoints) {
        if (!isInValidPoints(strPoints)) {
            int[] points = Arrays.stream(strPoints).mapToInt(Integer::parseInt).toArray();
            Main.data.addPoints(points);
            Main.lastLog = "Points updated.";
            System.out.println(Main.lastLog);
        }
    }

    private static boolean isInValidPoints(String[] strPoints) {
        if (strPoints.length != 5) {
            Main.lastLog = ERR_MSG;
            System.out.println(Main.lastLog);
            return true;
        } else {
            int[] points;
            try {
                Integer.parseInt(strPoints[0]);
            } catch (IllegalArgumentException e) {
                Main.lastLog = "No student is found for id=" + strPoints[0];
                System.out.println(Main.lastLog);
                return true;
            }
            try {
                points = Arrays.stream(strPoints).mapToInt(Integer::parseInt).toArray();
                if (isNotExistingStudent(points[0])) {
                    Main.lastLog = "No student is found for id=" + points[0];
                    System.out.println(Main.lastLog);
                    return true;
                }
                if (isInValidPoints(points)) {
                    Main.lastLog = ERR_MSG;
                    System.out.println(Main.lastLog);
                    return true;
                }
            } catch (IllegalArgumentException e) {
                Main.lastLog = ERR_MSG;
                System.out.println(Main.lastLog);
                return true;
            }
            return false;
        }
    }

    private static boolean isNotExistingStudent(int id) {
        return !Main.data.getIdList().contains(id);
    }

    private static boolean isInValidPoints(int[] points) {
        return Arrays.stream(points).anyMatch(p -> p < 0);
    }
}
