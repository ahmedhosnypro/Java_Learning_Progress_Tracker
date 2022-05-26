package tracker;

import java.util.List;

public enum Command {
    ADD_STUDENTS,
    LIST,
    ADD_POINTS,
    FIND,
    STATISTICS,
    BACK,
    EXIT;

    private static final List<String> spaceCommands = List.of("add students", "add points");

    static Command getCommand(String command) {
        if (spaceCommands.contains(command.toLowerCase())) {
            return Command.valueOf(command.replace(" ", "_"));
        }
        return Command.valueOf(command);
    }
}
