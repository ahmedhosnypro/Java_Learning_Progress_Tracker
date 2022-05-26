package tracker;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private final ArrayList<Student> students = new ArrayList<>();


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
}
