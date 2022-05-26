package tracker;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataSet {
    private final ArrayList<Student> students = new ArrayList<>();

    private final DecimalFormat decimalFormat = new DecimalFormat();

    void addStudent(Student student) {
        students.add(student);
    }

    void addPoints(int[] points) {
        var optStudent = students.stream().filter(s -> s.getId() == points[0]).findFirst();
        if (optStudent.isPresent()) {
            var s = optStudent.get();
            s.addJavaPoints(points[1]);
            s.addDsaPoints(points[2]);
            s.addDatabasesPoints(points[3]);
            s.addSpringPoints(points[4]);

            updateStatistics(points);
        }
    }

    List<Integer> getIdList() {
        return students.stream().map(Student::getId).toList();
    }

    List<String> getEmailList() {
        return students.stream().map(Student::getEmail).toList();
    }

    int getSize() {
        return students.size();
    }

    String find(int id) {
        var optStudent = students.stream().filter(s -> s.getId() == id).findFirst();
        if (optStudent.isPresent()) {
            var s = optStudent.get();
            return String.format("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d",
                    id, s.getJavaPoints(), s.getDsaPoints(), s.getDatabasesPoints(), s.getSpringPoints());
        }
        return "No student is found for id=" + id;
    }

    private void updateStatistics(int[] points) {
        updateLearners();
        updateActivity(points);
        updateAvgScores();
        updateTopStudents();
    }

    private void updateLearners() {
        Course.JAVA.setLearners(javaLearnersCount());
        Course.DSA.setLearners(dsaLearnersCount());
        Course.DATABASES.setLearners(databasesLearnersCount());
        Course.SPRING.setLearners(springLearnersCount());
    }

    private void updateActivity(int[] points) {
        Course.JAVA.setActivity(points[0] > 0 ? Course.JAVA.getActivity() + 1 : Course.JAVA.getActivity());
        Course.DSA.setActivity(points[0] > 0 ? Course.DSA.getActivity() + 1 : Course.DSA.getActivity());
        Course.DATABASES.setActivity(points[0] > 0 ? Course.DATABASES.getActivity() + 1 : Course.DATABASES.getActivity());
        Course.SPRING.setActivity(points[0] > 0 ? Course.SPRING.getActivity() + 1 : Course.SPRING.getActivity());
    }

    private void updateAvgScores() {
        Course.JAVA.setAvgScores(javaLearnersCount() == 0 ? 0 : javaTotalScores() / javaLearnersCount());
        Course.DSA.setAvgScores(dsaLearnersCount() == 0 ? 0 : dsaTotalScores() / dsaLearnersCount());
        Course.DATABASES.setAvgScores(databasesLearnersCount() == 0 ? 0 : databasesTotalScores() / databasesLearnersCount());
        Course.SPRING.setAvgScores(springLearnersCount() == 0 ? 0 : springTotalSores() / springLearnersCount());
    }

    private void updateTopStudents() {
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat.setMaximumFractionDigits(1);
        decimalFormat.setMinimumFractionDigits(1);
        if (javaLearnersCount() != 0) {
            Course.JAVA.setTopStudents(students.stream()
                    .filter(s -> s.getJavaPoints() > 0)
                    .sorted(Comparator.comparingInt(Student::getId))
                    .sorted((Comparator.comparingInt(Student::getJavaPoints)).reversed())
                    .map(s -> List.of(String.valueOf(s.getId()), String.valueOf(s.getJavaPoints()),
                            decimalFormat.format((s.getJavaPoints() / Course.JAVA.getGrade()) * 100L) + "%"))
                    .toList());
        }
        if (dsaLearnersCount() != 0) {
            Course.DSA.setTopStudents(students.stream()
                    .filter(s -> s.getDsaPoints() > 0)
                    .sorted(Comparator.comparingInt(Student::getId))
                    .sorted((Comparator.comparingInt(Student::getDsaPoints)).reversed())
                    .map(s -> List.of(String.valueOf(s.getId()), String.valueOf(s.getDsaPoints()),
                            decimalFormat.format((s.getDsaPoints() / Course.DSA.getGrade()) * 100L) + "%"))
                    .toList());
        }
        if (databasesLearnersCount() != 0) {
            Course.DATABASES.setTopStudents(students.stream()
                    .filter(s -> s.getDatabasesPoints() > 0)
                    .sorted(Comparator.comparingInt(Student::getId))
                    .sorted((Comparator.comparingInt(Student::getDatabasesPoints)).reversed())
                    .map(s -> List.of(String.valueOf(s.getId()), String.valueOf(s.getDatabasesPoints()),
                            decimalFormat.format((s.getDatabasesPoints() / Course.DATABASES.getGrade()) * 100L) + "%"))
                    .toList());
        }
        if (springLearnersCount() != 0) {
            Course.SPRING.setTopStudents(students.stream()
                    .filter(s -> s.getSpringPoints() > 0)
                    .sorted(Comparator.comparingInt(Student::getId))
                    .sorted((Comparator.comparingInt(Student::getSpringPoints)).reversed())
                    .map(s -> List.of(String.valueOf(s.getId()), String.valueOf(s.getSpringPoints()),
                            decimalFormat.format((s.getSpringPoints() / Course.SPRING.getGrade()) * 100L) + "%"))
                    .toList());
        }
    }

    int javaLearnersCount() {
        return (int) students.stream().filter(s -> s.getJavaPoints() > 0).count();
    }

    int dsaLearnersCount() {
        return (int) students.stream().filter(s -> s.getDsaPoints() > 0).count();
    }

    int databasesLearnersCount() {
        return (int) students.stream().filter(s -> s.getDatabasesPoints() > 0).count();
    }

    int springLearnersCount() {
        return (int) students.stream().filter(s -> s.getSpringPoints() > 0).count();
    }

    int javaTotalScores() {
        return students.stream().mapToInt(Student::getJavaPoints).sum();
    }

    int dsaTotalScores() {
        return students.stream().mapToInt(Student::getDsaPoints).sum();
    }

    int databasesTotalScores() {
        return students.stream().mapToInt(Student::getDatabasesPoints).sum();
    }

    int springTotalSores() {
        return students.stream().mapToInt(Student::getSpringPoints).sum();
    }
}
