package tracker;

public class Student {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;

    private int javaPoints = 0;
    private int dsaPoints = 0;
    private int databasesPoints = 0;
    private int springPoints = 0;


    Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    int getId() {
        return id;
    }

    String getFullName() {
        return firstName + " " + lastName;
    }

    String getEmail() {
        return email;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    int getDsaPoints() {
        return dsaPoints;
    }

    int getDatabasesPoints() {
        return databasesPoints;
    }

    int getSpringPoints() {
        return springPoints;
    }

    void addJavaPoints(int javaPoints) {
        if (javaPoints > 0) {
            this.javaPoints += javaPoints;
        }
    }

    void addDsaPoints(int dsaPoints) {
        if (dsaPoints > 0) {
            this.dsaPoints += dsaPoints;
        }
    }

    void addDatabasesPoints(int databasesPoints) {
        if (databasesPoints > 0) {
            this.databasesPoints += databasesPoints;
        }

    }

    void addSpringPoints(int springPoints) {
        if (springPoints > 0) {
            this.springPoints += springPoints;
        }
    }
}
