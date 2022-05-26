package tracker;

import java.util.HashSet;
import java.util.Set;

public class Notification {
    private Notification() {
    }

    static void notifySuccessStudents() {
        StringBuilder out = new StringBuilder();
        Set<String> notifiedStudents = new HashSet<>();
        for (var course : Course.getCourses()) {
            if (!course.getNonNotifiedStudents().isEmpty()) {
                course.getNonNotifiedStudents()
                        .forEach(s -> {
                            out.append("To: ").append(s.get(0)).append("\n")
                                    .append("Re: Your Learning Progress").append("\n")
                                    .append("Hello, ").append(s.get(1))
                                    .append("! You have accomplished our ").append(course.getNotation())
                                    .append(" course!").append("\n");
                            notifiedStudents.add(s.get(1));
                        });

                course.clearNotificationList();
            }
        }

        out.append("Total ").append(notifiedStudents.size()).append(" students have been notified.");

        Main.updateLog(out.toString());
        System.out.println(Main.lastLog);
    }
}
