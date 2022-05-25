package tracker;

import java.util.ArrayList;

public class DataSet {
    private final ArrayList<Student> students = new ArrayList<>();


    void addStudent(Student student) {
        students.add(student);
    }

    int studentsCnt() {
        return students.size();
    }

    Student getLastStudent() {
        return students.get(students.size() - 1);
    }
}
