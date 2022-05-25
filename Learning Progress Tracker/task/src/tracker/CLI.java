package tracker;

public class CLI {
    private CLI() {
    }

    static void run() {
        while (true) {
            listenToUserCommand();
        }
    }

    private static void listenToUserCommand() {
        String commandInput = Main.scanner.nextLine().trim().toUpperCase();
        if (commandInput.isEmpty() || commandInput.isBlank()) {
            System.out.println("No input.");
        } else if (isKnownCommand(commandInput)) {
            runCommand(Command.valueOf(commandInput));
        } else {
            System.out.println("Error: unknown command!");
        }
    }

    private static void runCommand(Command command) {
        switch (command) {
            case EXIT -> exit();
        }
    }

    private static void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private static boolean isKnownCommand(String commandInput) {
        try {
            Command.valueOf(commandInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
