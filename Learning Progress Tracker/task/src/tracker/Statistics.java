package tracker;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private static final String NA = "n/a";
    private final List<String> mostPopularCourses = new ArrayList<>();
    private final List<String> leastPopularCourses = new ArrayList<>();
    private final List<String> highestActivityCourses = new ArrayList<>();
    private final List<String> lowestActivityCourses = new ArrayList<>();
    private final List<String> easiestCourses = new ArrayList<>();
    private final List<String> hardestCourses = new ArrayList<>();

    Statistics() {
    }

    void generalStatistics() {
        StringBuilder out = new StringBuilder();
        mostPopular(out);
        leastPopular(out);
        highestActivity(out);
        lowestActivity(out);
        easiestCourses(out);
        hardestCourses(out);

        Main.updateLog(out.toString());
        System.out.println(Main.lastLog);
    }

    void courseStatistics(String courseNotation) {
        Course course = Course.getCourses(courseNotation);
        if (course != null) {
            StringBuilder out = new StringBuilder(course.getNotation());
            out.append("\n").append("id    points    completed").append("\n");
            if (course.getLearners() != 0) {
                course.getTopStudents()
                        .forEach(ts -> out.append(ts.get(0)).append("\t").append(ts.get(1)).append("\t").append(ts.get(2)).append("\n"));
            }
            Main.updateLog(out.toString());
            System.out.println(Main.lastLog);
        } else {
            Main.updateLog("Unknown course");
            System.out.println(Main.lastLog);
        }
    }

    private void mostPopular(StringBuilder out) {
        int mostPopularity = 0;
        for (var course : Course.getCourses()) {
            if (course.getLearners() > mostPopularity) {
                mostPopularity = course.getLearners();
                mostPopularCourses.clear();
                mostPopularCourses.add(course.getNotation());
            } else if (course.getLearners() == mostPopularity && course.getLearners() != 0) {
                mostPopularCourses.add(course.getNotation());
            }
        }

        appendDetails(out, "Most popular: ", mostPopularCourses);
    }

    private void leastPopular(StringBuilder out) {
        int leastPopularity = Integer.MAX_VALUE;
        for (var course : Course.getCourses()) {
            if (course.getLearners() < leastPopularity && course.getLearners() > 0) {
                leastPopularity = course.getLearners();
                leastPopularCourses.clear();
                if (!mostPopularCourses.contains(course.getNotation())) {
                    leastPopularCourses.add(course.getNotation());
                }
            } else if (course.getLearners() == leastPopularity && !mostPopularCourses.contains(course.getNotation())) {
                leastPopularCourses.add(course.getNotation());
            }
        }

        appendDetails(out, "Least popular: ", leastPopularCourses);
    }

    private void highestActivity(StringBuilder out) {
        int highestActivity = 0;
        for (var course : Course.getCourses()) {
            if (course.getActivity() > highestActivity) {
                highestActivity = course.getActivity();
                highestActivityCourses.clear();
                highestActivityCourses.add(course.getNotation());
            } else if (course.getActivity() == highestActivity && course.getActivity() != 0) {
                highestActivityCourses.add(course.getNotation());
            }
        }

        appendDetails(out, "Highest activity: ", highestActivityCourses);
    }

    private void lowestActivity(StringBuilder out) {
        int lowestActivity = Integer.MAX_VALUE;
        for (var course : Course.getCourses()) {
            if (course.getActivity() < lowestActivity && course.getActivity() > 0) {
                lowestActivity = course.getActivity();
                lowestActivityCourses.clear();
                if (!highestActivityCourses.contains(course.getNotation())) {
                    lowestActivityCourses.add(course.getNotation());
                }
            } else if (course.getActivity() == lowestActivity && !highestActivityCourses.contains(course.getNotation())) {
                lowestActivityCourses.add(course.getNotation());
            }
        }

        appendDetails(out, "Lowest activity: ", lowestActivityCourses);
    }

    private void easiestCourses(StringBuilder out) {
        int highestAvgScore = 0;
        for (var course : Course.getCourses()) {
            if (course.getAvgScores() > highestAvgScore) {
                highestAvgScore = course.getAvgScores();
                easiestCourses.clear();
                easiestCourses.add(course.getNotation());
            } else if (course.getAvgScores() == highestAvgScore && course.getAvgScores() != 0) {
                easiestCourses.add(course.getNotation());
            }
        }

        appendDetails(out, "Easiest course: ", easiestCourses);
    }

    private void hardestCourses(StringBuilder out) {
        int leastAvgScore = Integer.MAX_VALUE;
        for (var course : Course.getCourses()) {
            if (course.getAvgScores() < leastAvgScore && course.getAvgScores() > 0) {
                leastAvgScore = course.getAvgScores();
                hardestCourses.clear();
                if (!easiestCourses.contains(course.getNotation())) {
                    hardestCourses.add(course.getNotation());
                }
            } else if (course.getAvgScores() == leastAvgScore && !easiestCourses.contains(course.getNotation())) {
                hardestCourses.add(course.getNotation());
            }
        }

        appendDetails(out, "Hardest course ", hardestCourses);
    }

    private void appendDetails(StringBuilder out, String header, List<String> details) {
        out.append(header);
        if (details.isEmpty()) {
            out.append(NA);
        } else {
            details.forEach(c -> out.append(c).append(", "));
            out.delete(out.length() - 2, out.length());
        }
        out.append("\n");
    }
}
