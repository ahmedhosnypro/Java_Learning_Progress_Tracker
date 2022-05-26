package tracker;

public class Student {

    private final int id;
    private String firstName;
    private String lastName;
    private final String email;

    private int javaPoints = 0;
    private int dsaPoints = 0;
    private int databasesPoints = 0;
    private int springPoints = 0;


    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public int getDatabasesPoints() {
        return databasesPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void addJavaPoints(int javaPoints) {
        if (javaPoints > 0) {
            this.javaPoints += javaPoints;
        }
    }

    public void addDsaPoints(int dsaPoints) {
        if (dsaPoints > 0) {
            this.dsaPoints += dsaPoints;
        }
    }

    public void addDatabasesPoints(int databasesPoints) {
        if (databasesPoints > 0) {
            this.databasesPoints += databasesPoints;
        }

    }

    public void addSpringPoints(int springPoints) {
        if (springPoints > 0) {
            this.springPoints += springPoints;
        }
    }

}
