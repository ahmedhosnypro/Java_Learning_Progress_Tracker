package tracker;

import java.util.ArrayList;
import java.util.List;

public enum Course {
    JAVA("Java", 600, 0, 0, 0),
    DSA("DSA", 400, 0, 0, 0),
    DATABASES("Databases", 480, 0, 0, 0),
    SPRING("Spring", 550, 0, 0, 0);
    private final String notation;
    final double grade;
    private int learners;
    private int activity;
    private int avgScores;

    private static final List<Course> courses = List.of(Course.JAVA, Course.DSA, Course.DATABASES, Course.SPRING);

    private List<List<String>> topStudents; // List.of(List.of(id, thisCoursePoints, completionPercentage))
    private final List<List<String>> notifiedStudents = new ArrayList<>(); // List.of(List.of(email, fullName))
    private final List<List<String>> nonNotifiedStudents = new ArrayList<>(); // List.of(List.of(email, fullName))

    Course(String notation, double grade, int learners, int activity, int avgScores) {
        this.notation = notation;
        this.grade = grade;
        this.learners = learners;
        this.activity = activity;
        this.avgScores = avgScores;
    }


    static List<Course> getCourses() {
        return courses;
    }

    static Course getCourses(String notation) {
        var optCourse = courses.stream().filter(c -> notation.equalsIgnoreCase(c.notation)).findFirst();
        return optCourse.orElse(null);
    }

    public List<List<String>> getTopStudents() {
        return new ArrayList<>(topStudents);
    }

    String getNotation() {
        return notation;
    }

    double getGrade() {
        return grade;
    }

    int getLearners() {
        return learners;
    }

    int getActivity() {
        return activity;
    }

    int getAvgScores() {
        return avgScores;
    }

    void setLearners(int learners) {
        this.learners = learners;
    }

    void setTopStudents(List<List<String>> topStudents) {
        this.topStudents = topStudents;
    }

    void updateNotifyList(List<List<String>> successStudents) {
        for (var student : successStudents) {
            if (!notifiedStudents.contains(student)) {
                nonNotifiedStudents.add(student);
            }
        }
    }

    void clearNotificationList() {
        notifiedStudents.addAll(nonNotifiedStudents);
        nonNotifiedStudents.clear();
    }

    List<List<String>> getNonNotifiedStudents() {
        return nonNotifiedStudents;
    }

    void setActivity(int activity) {
        this.activity = activity;
    }

    void setAvgScores(int avgScores) {
        this.avgScores = avgScores;
    }
}
