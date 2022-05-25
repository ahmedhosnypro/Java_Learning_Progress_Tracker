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
            System.out.println("No input.");
        } else if (isKnownCommand(commandInput)) {
            runCommand(Command.getCommand(commandInput));
        } else {
            System.out.println("Error: unknown command!");
        }
    }

    private static void runCommand(Command command) {
        switch (command) {
            case ADD_STUDENTS -> addStudents();
            case BACK -> back();
            case EXIT -> exit();
        }
    }

    private static void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        while (true) {
            String input = Main.scanner.nextLine().trim();
            if (isKnownCommand(input.toUpperCase()) &&
                    Command.getCommand(input.toUpperCase()) == Command.BACK) {
                System.out.println("Total " + Main.data.studentsCnt() + " students have been added.");
                break;
            } else {
                StudentBuilder.createNewStudent(input);
            }
        }
    }

    private static void back() {
        System.out.println("Enter 'exit' to exit the program.");
    }

    private static void exit() {
        System.out.println("Bye!");
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
}
