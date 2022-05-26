package tracker;

public class CLI {
    private CLI() {
    }

    private static boolean toContinue = true;

    static void run() {
        while (toContinue) {
            listenToUserCommand();
        }
    }

    private static void listenToUserCommand() {
        String commandInput = Main.scanner.nextLine().trim().toUpperCase();
        if (commandInput.isEmpty() || commandInput.isBlank()) {
            Main.lastLog = "No input.";
            System.out.println(Main.lastLog);
        } else if (isKnownCommand(commandInput)) {
            runCommand(Command.getCommand(commandInput));
        } else {
            Main.lastLog = "Error: unknown command!";
            System.out.println(Main.lastLog);
        }
    }

    private static void runCommand(Command command) {
        switch (command) {
            case ADD_STUDENTS -> addStudents();
            case LIST -> list();
            case ADD_POINTS -> addPoints();
            case FIND -> find();
            case STATISTICS -> statistics();
            case NOTIFY -> notifySuccessStudents();
            case BACK -> back();
            case EXIT -> exit();
        }
    }

    private static void addStudents() {
        Main.lastLog = "Enter student credentials or 'back' to return:";
        System.out.println(Main.lastLog);
        while (true) {
            String input = Main.scanner.nextLine().trim();
            if (isKnownCommand(input.toUpperCase()) && Command.getCommand(input.toUpperCase()) == Command.BACK) {
                Main.lastLog = "Total " + Main.data.getSize() + " students have been added.";
                System.out.println(Main.lastLog);
                break;
            } else {
                StudentBuilder.createNewStudent(partCredentials(input));
            }
        }
    }

    private static void list() {
        if (Main.data.getSize() == 0) {
            Main.lastLog = "No students found";
            System.out.println(Main.lastLog);
            return;
        }

        StringBuilder out = new StringBuilder("Students:\n");
        Main.data.getIdList().forEach(id -> out.append(id).append("\n"));

        Main.lastLog = out.toString();
        System.out.println(Main.lastLog);
    }

    private static void addPoints() {
        Main.lastLog = "Enter an id and points or 'back' to return";
        System.out.println(Main.lastLog);
        while (true) {
            String input = Main.scanner.nextLine().trim();
            if (isKnownCommand(input.toUpperCase()) && Command.getCommand(input.toUpperCase()) == Command.BACK) {
                break;
            } else {
                Points.addPoints(partPoints(input));
            }
        }
    }

    private static void find() {
        Main.lastLog = "Enter an id or 'back' to return";
        System.out.println(Main.lastLog);

        while (true) {
            String input = Main.scanner.nextLine().trim();
            if (isKnownCommand(input.toUpperCase()) && Command.getCommand(input.toUpperCase()) == Command.BACK) {
                break;
            } else {
                Points.find(input);
            }
        }
    }

    private static void statistics() {
        Main.lastLog = "Type the name of a course to see details or 'back' to quit:";
        System.out.println(Main.lastLog);

        Statistics statistics = new Statistics();
        statistics.generalStatistics();

        while (true) {
            String input = Main.scanner.nextLine().trim();
            if (isKnownCommand(input.toUpperCase()) && Command.getCommand(input.toUpperCase()) == Command.BACK) {
                break;
            } else {
                statistics.courseStatistics(input);
            }
        }
    }

    private static void notifySuccessStudents() {
        Notification.notifySuccessStudents();
    }

    private static void back() {
        Main.lastLog = "Enter 'exit' to exit the program.";
        System.out.println(Main.lastLog);
    }

    private static void exit() {
        Main.lastLog = "Bye!";
        System.out.println(Main.lastLog);
        toContinue = false;
        System.exit(0);
    }

    private static boolean isKnownCommand(String commandInput) {
        try {
            Command.getCommand(commandInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String[] partCredentials(String input) {
        String[] args = input.split(" ");

        if (args.length > 3) {
            String[] ret = new String[3];
            ret[0] = args[0];
            ret[1] = "";
            ret[2] = args[args.length - 1];
            for (int i = 1; i < args.length - 2; i++) {
                ret[1] += args[i] + " ";
            }
            ret[1] = ret[1].trim();
            return ret;
        }
        return args;
    }

    private static String[] partPoints(String input) {
        return input.split(" ");
    }
}
