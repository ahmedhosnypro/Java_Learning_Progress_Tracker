package tracker;

public enum Command {
    ADD_STUDENTS,
    BACK,
    EXIT;

    static Command getCommand(String command) {
        if (command.equalsIgnoreCase("add students")) {
            return Command.valueOf(command.replace(" ", "_"));
        }
        return Command.valueOf(command);
    }
}
